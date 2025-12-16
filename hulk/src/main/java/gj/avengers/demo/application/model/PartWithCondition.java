package gj.avengers.demo.application.model;

import gj.avengers.demo.domain.model.TargetPart;

public record PartWithCondition(
        TargetPart part,
        PartCondition condition
) {
}
