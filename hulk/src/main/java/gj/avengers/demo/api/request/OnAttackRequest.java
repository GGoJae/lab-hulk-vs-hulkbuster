package gj.avengers.demo.api.request;

import gj.avengers.demo.domain.BodyParts;

public record OnAttackRequest(
        BodyParts targetPart
) {
}
