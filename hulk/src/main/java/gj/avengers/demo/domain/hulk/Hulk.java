package gj.avengers.demo.domain.hulk;

import gj.avengers.demo.domain.event.DomainEvent;
import gj.avengers.demo.domain.event.HulkInabilityToFightEvent;
import gj.avengers.demo.shared.model.BodyPart;
import gj.avengers.demo.shared.model.Reaction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Component
public class Hulk {

    private final static int DEFAULT_GET_DAMAGE = 50;
    private final static int ATTACKED_WITH_WEAKNESS_PART_DAMAGE = 1000;
    private final static int MAX_ANGER = 90;

    private final List<DomainEvent> domainEvents = new ArrayList<>();
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    private final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    @Getter
    private BodyPart weaknessPart;
    private int anger = 0;
    private int hp = 100_000;
    private int power = 10;
    private int hittingCount = 0;

    public Hulk() {
        changeWeaknessPart();
    }

    public int totalPower() {
        readLock.lock();
        try {
            return this.power + this.anger;
        } finally {
            readLock.unlock();
        }
    }

    public long currentHp() {
        readLock.lock();
        try {
            return this.hp;
        } finally {
            readLock.unlock();
        }
    }

    public boolean isHulkFainted() {
        readLock.lock();
        try {
            return this.hp <= 0;
        } finally {
            readLock.unlock();
        }
    }

    public Reaction takeHit(BodyPart part) {
        log.info("헐크 getDamage 진입 공격 부위 : {}", part);
        writeLock.lock();
        try {
            log.info("헐크의 현재 약점 : {}", this.weaknessPart);
            int damage = damageCalculator(part);

            hittingCountInc();
            runOutHp(damage);

            Reaction reaction = this.weaknessPart == part ? Reaction.PAINFUL : Reaction.ITCHY;
            log.info("reaction : {}", reaction);
            return reaction;
        } finally {
            writeLock.unlock();
        }

    }

    public List<DomainEvent> pullEvents() {
        writeLock.lock();
        try {
            var copy = List.copyOf(domainEvents);
            domainEvents.clear();
            return copy;
        } finally {
            writeLock.unlock();
        }
    }

    private void increaseAnger() {
        if (this.anger == MAX_ANGER) {
            log.info("이미 최대 분노라 더 이상 분노게이지가 오르지 않습니다.");
            return;
        }

        this.anger++;
        log.info("현재 분노 게이지 : {}", this.anger);
    }

    private void runOutHp(int damage) {
        int before = this.hp;
        this.hp = Math.max(0, this.hp - damage);
        if (before > 0 && this.hp == 0) domainEvents.add(new HulkInabilityToFightEvent());
    }

    private void changeWeaknessPart() {
        int nextInt = ThreadLocalRandom.current().nextInt(BodyPart.values().length);
        this.weaknessPart = BodyPart.values()[nextInt];
        log.info("이번 약점은 ! : {}", this.weaknessPart);
    }

    private void hittingCountInc() {
        this.hittingCount++;

        if (this.hittingCount % 5 == 0) {
            this.increaseAnger();
        }

        if (this.hittingCount % 10 == 0) {
            this.changeWeaknessPart();
        }
        log.info("현재 힛팅 카운트 : {}", this.hittingCount);
    }

    private int damageCalculator(BodyPart part) {
        return this.weaknessPart == part ? ATTACKED_WITH_WEAKNESS_PART_DAMAGE : DEFAULT_GET_DAMAGE;
    }
}
