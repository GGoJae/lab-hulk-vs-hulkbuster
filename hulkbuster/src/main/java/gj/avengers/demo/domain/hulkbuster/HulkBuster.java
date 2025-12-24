package gj.avengers.demo.domain.hulkbuster;

import gj.avengers.demo.domain.hulkbuster.model.DamageResult;
import gj.avengers.demo.domain.hulkbuster.parts.*;
import gj.avengers.demo.domain.hulkbuster.model.Status;
import gj.avengers.demo.domain.hulkbuster.model.PartStatus;
import gj.avengers.demo.domain.hulkbuster.parts.impl.Armor;
import gj.avengers.demo.domain.hulkbuster.parts.impl.Arms;
import gj.avengers.demo.domain.hulkbuster.parts.impl.Helmets;
import gj.avengers.demo.domain.hulkbuster.parts.impl.Legs;
import gj.avengers.demo.application.model.PartType;
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

    public DamageResult beAttacked(PartType partType, int damage) {
        if (damage < 0) throw new IllegalStateException();
        writeLock.lock();
        try {
            Part part = switch (partType) {
                case HELMETS -> this.helmets;
                case ARMOR -> this.armor;
                case ARMS -> this.arms;
                case LEGS -> this.legs;
            };
            return part.applyDamage(damage);
        } finally {
            writeLock.unlock();
        }
    }

    public Status getState() {
        return Status.builder()
                .addPartStatus(PartStatus.of(this.helmets.type(), this.helmets.currentDurable()))
                .addPartStatus(PartStatus.of(this.armor.type(), this.armor.currentDurable()))
                .addPartStatus(PartStatus.of(this.arms.type(), this.arms.currentDurable()))
                .addPartStatus(PartStatus.of(this.legs.type(), this.legs.currentDurable()))
                .build();
    }



}
