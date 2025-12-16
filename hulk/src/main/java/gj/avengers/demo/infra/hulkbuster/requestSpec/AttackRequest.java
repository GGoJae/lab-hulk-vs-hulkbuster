package gj.avengers.demo.infra.hulkbuster.requestSpec;

import gj.avengers.demo.domain.model.TargetPart;
import gj.avengers.demo.infra.hulkbuster.dto.PartType;

public record AttackRequest(
        PartType part,
        int damage
) {

    public static AttackRequest from(TargetPart targetPart, int damage) {
        return new AttackRequest(PartType.from(targetPart), damage);
    }


}
