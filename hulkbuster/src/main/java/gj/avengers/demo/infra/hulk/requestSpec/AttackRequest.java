package gj.avengers.demo.infra.hulk.requestSpec;

import gj.avengers.demo.application.model.BodyPart;

public record AttackRequest(
        BodyPart targetPart
) {
}
