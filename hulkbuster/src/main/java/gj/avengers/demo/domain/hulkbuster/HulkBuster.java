package gj.avengers.demo.domain.hulkbuster;

import gj.avengers.demo.domain.hulkbuster.parts.*;
import gj.avengers.demo.shared.model.PartType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Component
public class HulkBuster {

    private Helmets helmets = Helmets.newHelmets();
    private Armor armor = Armor.newArmor();
    private Arms arms = Arms.newArms();
    private Legs legs = Legs.newLegs();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public List<Part> getBrokenParts() {
        readLock.lock();
        try {
            ArrayList<Part> out = new ArrayList<>();
            if (this.helmets == null || this.helmets.isBroken()) out.add(helmets);
            if (this.armor == null || this.armor.isBroken()) out.add(armor);
            if (this.arms == null || this.arms.isBroken()) out.add(arms);
            if (this.legs == null || this.legs.isBroken()) out.add(legs);

            return out;
        } finally {
            readLock.unlock();
        }
    }

    public void beAttacked(PartType partType, int damage) {
        writeLock.lock();
        try {
            Part part = switch (partType) {
                case HELMETS -> this.helmets;
                case ARMOR -> this.armor;
                case ARMS -> this.arms;
                case LEGS -> this.legs;
            };
            part.getDamage(damage);
        } finally {
            writeLock.unlock();
        }
    }

    public TotalState getState() {
        return TotalState.builder()
                .partState(this.helmets)
                .partState(this.armor)
                .partState(this.arms)
                .partState(this.legs)
                .build();
    }

    public record TotalState(
            List<State> states
    ) {

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private final List<State> states = new ArrayList<>();

            public Builder partState(Part part) {
                states.add(new State(part.type(), part.currentDurable()));
                return this;
            }

            public TotalState build() {
                return new TotalState(this.states);
            }
        }

        public record State(PartType part, int durable) {

        }
    }



}
