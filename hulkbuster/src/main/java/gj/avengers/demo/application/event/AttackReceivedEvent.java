package gj.avengers.demo.application.event;

import gj.avengers.demo.application.model.PartType;
import gj.avengers.demo.domain.hulkbuster.model.PartCondition;

public record AttackReceivedEvent(
        PartType partType,
        PartCondition condition,
        int durable
        ) {
}
