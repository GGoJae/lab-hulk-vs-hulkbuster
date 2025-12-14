package gj.avengers.demo.infra.hulk.requestSpec;

import gj.avengers.demo.shared.model.BodyPart;

public record AttackRequest(
        BodyPart targetPart
) {
}
