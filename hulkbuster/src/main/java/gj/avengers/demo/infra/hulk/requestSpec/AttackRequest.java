package gj.avengers.demo.infra.hulk.requestSpec;

import gj.avengers.demo.shared.model.BodyParts;

public record AttackRequest(
        BodyParts targetPart
) {
}
