package gj.avengers.demo.application.maintenance;

import gj.avengers.demo.application.out.JarvisPort;
import gj.avengers.demo.application.out.VeronicaPort;
import gj.avengers.demo.shared.event.AttackReceivedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class MaintenanceSystem {

    private final JarvisPort jarvisPort;
    private final VeronicaPort veronicaPort;

    /*
    중요: 만약 AttackReceivedEvent 를 구독하고있는 이벤트 리스너가 여러개고
    그 이벤트 리스너에서 비동기 코드들을 호출만 하고 끝나는게 아니라
    블럭킹 코드로 기다리고 있다면 사용자 응답 속도도 그만큼 느려지니
    만약 지금 코드에서 이벤트 리스너가 늘어나고, 블럭킹 코드를 사용하고 있다면
    이벤트리스너 자체를 비동기 호출로 바꾸는게 나아보인다.
    하지만 위 같은 경우에도 에벤트 리스너가 비동기 코드들을 호출만하고 끝난다면 동기코드가 낫다.
    괜히 각각의 이벤트 리스너를 위해 비동기 등록한 스레드들이 여러개 나와서 호출만 하고 사라지기 떄문.
    상황에 따라 다르겠지만 대체로 이벤트 리스너는 동기 호출 + 비동기 논블록킹 코드들을 호출만 하고 끝내는 방법이면 충분해보임
    지금은 AttackReceivedEvent 를 구독하는 이벤트 리스너가 1개고
    비동기 코드들을 호출만 하고 끝내고 비동기 코드들은
     */
//    @Async("asyncExecutor")
    @EventListener
    public void determiningDamageAndRequestParts(AttackReceivedEvent event) {

        // TODO 우선 상태에서 망가진 파츠가 있다면 바로 베로니카에게 파츠 요청 api 날리기

        jarvisPort.requestReplacementRecommendations(event.state())
                .thenCompose(needReplacementParts -> {
                    if (needReplacementParts.isEmpty()) {
                        log.info("교체 불필요, 위치 정보 필요없음");
                        return CompletableFuture.<Void>completedFuture(null);
                    }

                    return jarvisPort.requestHulkbusterLocation()
                            .thenCompose(loc -> veronicaPort.requestParts(loc, needReplacementParts))
                            .thenAccept(v -> log.info("베로니카에 파츠 요청 성공"));

                })
                .exceptionally(ex -> {
                    log.error("공격 당한 후 파츠 요청 메서드 중 에러 발생", ex);
                    return null;
                });

    }
}
