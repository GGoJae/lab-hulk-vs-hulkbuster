package gj.avengers.demo.hulkbuster.service;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.hulkbuster.domain.PartValue;
import gj.avengers.demo.shared.event.AttackReceivedEvent;
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

    public void getDamage(PartValue part, int damage) {

        hulkBuster.beAttacked(part, damage);
        log.info("{} 에 {} 의 데미지를 입었다!", part, damage);

        HulkBuster.TotalState state = hulkBuster.getState();
        publisher.publishEvent(new AttackReceivedEvent(state));
    }

}
