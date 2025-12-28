package gj.avengers.demo.domain.hulkbuster.parts.impl;

import gj.avengers.demo.domain.hulkbuster.parts.DurablePart;
import gj.avengers.demo.application.model.PartType;
import lombok.Getter;

@Getter
public class Legs extends DurablePart {

    public static final int MAX_DURABLE = 100;

    private Legs() {
        super(PartType.LEGS, MAX_DURABLE);
    }

    public static Legs newLegs() {
        return new Legs();
    }
}
