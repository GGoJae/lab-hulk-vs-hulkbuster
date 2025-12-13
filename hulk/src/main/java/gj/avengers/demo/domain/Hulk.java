package gj.avengers.demo.domain;

import gj.avengers.demo.api.Response.OnAttackResponse;
import gj.avengers.demo.event.HulkInabilityToFight;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class Hulk {

    private final ApplicationEventPublisher eventPublisher;
    private AtomicLong hp = new AtomicLong(100_000);
    private final static long DEFAULT_GET_DAMAGE = 50;
    private final static long ATTACKED_WITH_WEAKNESS_PART_DAMAGE = 1000;

    @Getter
    private BodyParts weaknessPart;

    public Hulk(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        changeWeaknessPart();
    }

    public void changeWeaknessPart() {
        int nextInt = ThreadLocalRandom.current().nextInt(BodyParts.values().length);
        this.weaknessPart = BodyParts.values()[nextInt];
    }

    public OnAttackResponse getDamage(BodyParts part) {
        log.info("헐크 getDamage 진입 : {}", part);
        log.info("헐크의 현재 약점 : {}", this.weaknessPart);
        long damage = damageCalculator(part);

        // 데미지가 hp 보다 크면 헐크 전투 불능, 전투 종료
        if (this.hp.get() <= damage) eventPublisher.publishEvent(new HulkInabilityToFight());

        this.hp.updateAndGet(currentHp -> currentHp - damage);

        Reaction reaction = this.weaknessPart == part ? Reaction.PAINFUL : Reaction.ITCHY;
        log.info("reaction : {}", reaction);
        return new OnAttackResponse(reaction);
    }

    private long damageCalculator(BodyParts part) {
        return this.weaknessPart == part ? ATTACKED_WITH_WEAKNESS_PART_DAMAGE : DEFAULT_GET_DAMAGE;
    }
}
