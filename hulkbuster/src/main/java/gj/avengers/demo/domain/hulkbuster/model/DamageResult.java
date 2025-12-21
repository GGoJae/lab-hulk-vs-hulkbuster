package gj.avengers.demo.domain.hulkbuster.model;

import gj.avengers.demo.application.model.PartType;

public record DamageResult(
        PartType type,
        int beforeDurable,
        int afterDurable,
        PartCondition beforeCond,
        PartCondition afterCond
) {

}
