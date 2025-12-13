package gj.avengers.demo.infra.hulk.requestSpec;

import gj.avengers.demo.infra.dto.BodyParts;

public record AttackRequest(
        BodyParts targetPart
) {
}
