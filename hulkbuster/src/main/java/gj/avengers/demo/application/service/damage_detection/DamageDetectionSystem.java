package gj.avengers.demo.application.service.damage_detection;

import gj.avengers.demo.application.event.AttackReceivedEvent;
import gj.avengers.demo.application.model.PartType;
import gj.avengers.demo.domain.hulkbuster.HulkBuster;
import gj.avengers.demo.domain.hulkbuster.model.DamageResult;
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

    public DamageResult getDamage(PartType part, int damage) {

        DamageResult damageResult = hulkBuster.beAttacked(part, damage);
        log.info("{} 에 데미지를 입었다! durable : {} -> {}, partCond : {} -> {}",
                damageResult.type(), damageResult.beforeDurable(), damageResult.afterDurable(),
                damageResult.beforeCond(), damageResult.afterCond());

//        Status state = hulkBuster.getState();

        publisher.publishEvent(
                new AttackReceivedEvent(
                        damageResult.type(),
                        damageResult.afterCond(),
                        damageResult.afterDurable()));
        return damageResult;
    }

}
