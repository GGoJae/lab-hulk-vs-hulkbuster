package gj.avengers.demo.api.request;

import gj.avengers.demo.application.model.PartType;

public record BeAttackedRequest(
        PartType part,
        int damage
) {
}
