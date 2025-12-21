package gj.avengers.demo.domain.hulkbuster.parts.impl;

import gj.avengers.demo.domain.hulkbuster.parts.DurablePart;
import gj.avengers.demo.application.model.PartType;
import lombok.Getter;

@Getter
public class Helmets extends DurablePart {

    private static final int MAX_DURABLE = 100;

    private Helmets() {
        super(PartType.HELMETS, MAX_DURABLE);
    }

    public static Helmets newHelmets() {
        return new Helmets();
    }
}
