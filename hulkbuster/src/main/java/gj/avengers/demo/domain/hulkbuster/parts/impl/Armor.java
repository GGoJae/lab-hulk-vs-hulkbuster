package gj.avengers.demo.domain.hulkbuster.parts.impl;

import gj.avengers.demo.domain.hulkbuster.parts.DurablePart;
import gj.avengers.demo.application.model.PartType;


public class Armor extends DurablePart {

    public static final int MAX_DURABLE = 100;

    private Armor() {
        super(PartType.ARMOR, MAX_DURABLE);
    }

    public static Armor newArmor() {
        return new Armor();
    }
}
