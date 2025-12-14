package gj.avengers.demo.api.request;

import gj.avengers.demo.shared.model.BodyPart;

public record OnAttackRequest(
        BodyPart targetPart
) {
}
