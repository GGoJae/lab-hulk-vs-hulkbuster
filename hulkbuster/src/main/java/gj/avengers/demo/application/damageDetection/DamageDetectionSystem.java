package gj.avengers.demo.application.damageDetection;

import gj.avengers.demo.domain.hulkbuster.HulkBuster;
import gj.avengers.demo.shared.event.AttackReceivedEvent;
import gj.avengers.demo.domain.hulkbuster.model.Status;
import gj.avengers.demo.shared.model.PartType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DamageDetectionSystem {

    private final HulkBuster hulkBuster;
    private final ApplicationEventPublisher publisher;

    public void getDamage(PartType part, int damage) {

        hulkBuster.beAttacked(part, damage);
        log.info("{} 에 {} 의 데미지를 입었다!", part, damage);

        Status state = hulkBuster.getState();
        publisher.publishEvent(new AttackReceivedEvent(state));
    }

}
