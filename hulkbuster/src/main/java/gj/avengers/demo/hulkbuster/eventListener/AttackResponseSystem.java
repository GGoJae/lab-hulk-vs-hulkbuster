package gj.avengers.demo.hulkbuster.eventListener;

import gj.avengers.demo.hulkbuster.event.AttackReceivedEvent;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttackResponseSystem {

    private final JarvisApiGateway jarvisApiGateway;

    @Async("asyncExecutor")
    @EventListener
    public void attackResponse(AttackReceivedEvent event) {

        // TODO 우선 상태에서 망가진 파츠가 있다면 바로 베로니카에게 파츠 요청 api 날리기

        CompletableFuture<ReplacementRecommendationsResponse> future = jarvisApiGateway.requestReplacementRecommendations(event.state());

        ReplacementRecommendationsResponse join = future.join();
        log.info("응답은 어떤게 왔나? {}", join);

    }
}
