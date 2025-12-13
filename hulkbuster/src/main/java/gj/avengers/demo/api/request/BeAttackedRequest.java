package gj.avengers.demo.api.request;

import gj.avengers.demo.shared.model.PartType;

public record BeAttackedRequest(
        PartType part,
        int damage
) {
}
