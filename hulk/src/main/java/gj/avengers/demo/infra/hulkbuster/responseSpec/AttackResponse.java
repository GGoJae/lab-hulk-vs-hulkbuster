package gj.avengers.demo.infra.hulkbuster.responseSpec;

import gj.avengers.demo.application.model.PartWithCondition;
import gj.avengers.demo.infra.hulkbuster.dto.PartServiceStatus;
import gj.avengers.demo.infra.hulkbuster.dto.PartType;

public record AttackResponse(
        PartType part,
        PartServiceStatus condition
) {

    public PartWithCondition toDomain() {
        return new PartWithCondition(part.toDomain(), condition.toDomain());
    }
}
