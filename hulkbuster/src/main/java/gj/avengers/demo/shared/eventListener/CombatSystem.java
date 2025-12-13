package gj.avengers.demo.shared.eventListener;

import gj.avengers.demo.shared.event.AttackReceivedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CombatSystem {

    @EventListener
    public void counterAttack(AttackReceivedEvent event) {
        /*
         TODO 헐크 공격하는 로직 , 어떤 부위를 공격하면 헐크가 아파하는지 상태를 받아서 자비스한테 데이터 축적.
         다음 반격 시작시엔 데이터를 바탕으로 아파하는곳 때리기 같은 로직  비동기 코드로 작성하기
         */
    }
}
