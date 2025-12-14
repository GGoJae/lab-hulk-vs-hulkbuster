package gj.avengers.demo.domain.hulk;

import gj.avengers.demo.domain.event.HulkInabilityToFight;
import gj.avengers.demo.shared.model.BodyPart;
import gj.avengers.demo.shared.model.Reaction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class Hulk {

    private final static long DEFAULT_GET_DAMAGE = 50;
    private final static long ATTACKED_WITH_WEAKNESS_PART_DAMAGE = 1000;
    private final static int MAX_ANGER = 90;

    private final ApplicationEventPublisher eventPublisher;

    @Getter
    private BodyPart weaknessPart;
    private final AtomicInteger anger = new AtomicInteger(0);
    private final AtomicLong hp = new AtomicLong(100_000);
    private final AtomicInteger power = new AtomicInteger(10);

    public Hulk(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        changeWeaknessPart();
    }

    public void changeWeaknessPart() {
        int nextInt = ThreadLocalRandom.current().nextInt(BodyPart.values().length);
        this.weaknessPart = BodyPart.values()[nextInt];
    }

    public int totalPower() {
        return this.power.get() + this.anger.get();
    }

    public long currentHp() {
        return this.hp.get();
    }

    public int increaseAnger() {
        if (anger.get() == MAX_ANGER) return anger.get();
            /*
             TODO 동시성 생각하면 이 로직은 위험하고 헐크 설계 다시 해보자.
             상태에 따라 헐크의 공격력이나 행동이 달라지니까 ReentrantLock 고려 해보자
             */
        return anger.incrementAndGet();
    }

    public Reaction getDamage(BodyPart part) {
        log.info("헐크 getDamage 진입 공격 부위 : {}", part);
        log.info("헐크의 현재 약점 : {}", this.weaknessPart);
        long damage = damageCalculator(part);

        // 데미지가 hp 보다 크면 헐크 전투 불능, 전투 종료
        if (this.hp.get() <= damage) eventPublisher.publishEvent(new HulkInabilityToFight());

        this.hp.updateAndGet(currentHp -> currentHp - damage);

        Reaction reaction = this.weaknessPart == part ? Reaction.PAINFUL : Reaction.ITCHY;
        log.info("reaction : {}", reaction);
        return reaction;
    }

    private long damageCalculator(BodyPart part) {
        return this.weaknessPart == part ? ATTACKED_WITH_WEAKNESS_PART_DAMAGE : DEFAULT_GET_DAMAGE;
    }
}
