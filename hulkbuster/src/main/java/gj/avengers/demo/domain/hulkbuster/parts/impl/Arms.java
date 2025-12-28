package gj.avengers.demo.domain.hulkbuster.parts.impl;

import gj.avengers.demo.domain.hulkbuster.parts.DurablePart;
import gj.avengers.demo.application.model.PartType;
import lombok.Getter;

@Getter
public class Arms extends DurablePart {

    public static int MAX_DURABLE = 100;

    private Arms() {
        super(PartType.ARMS, MAX_DURABLE);
    }

    public static Arms newArms() {
        return new Arms();
    }

}
