package gj.avengers.demo.application.combat;

import gj.avengers.demo.shared.model.BodyParts;
import gj.avengers.demo.infra.hulk.HulkGateway;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.shared.event.AttackReceivedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CombatSystem {

    private final JarvisApiGateway jarvisGateway;
    private final HulkGateway hulkGateway;

    @EventListener
    public void counterAttack(AttackReceivedEvent event) {
        /*
         TODO 헐크 공격하는 로직 , 어떤 부위를 공격하면 헐크가 아파하는지 상태를 받아서 자비스한테 데이터 축적.
         다음 반격 시작시엔 데이터를 바탕으로 아파하는곳 때리기 같은 로직  비동기 코드로 작성하기
         */

        hulkGateway.attack(BodyParts.ARMS)
                .thenAccept(res -> {
                    log.info("헐크 공격에 대한 reaction: {}", res);
                });

    }
}
