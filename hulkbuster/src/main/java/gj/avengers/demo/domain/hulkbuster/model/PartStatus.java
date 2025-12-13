package gj.avengers.demo.domain.hulkbuster.model;

import gj.avengers.demo.shared.model.PartType;

public record PartStatus(
        PartType part, int durable
) {

    public static PartStatus of (PartType partType, int durable) {
        return new PartStatus(partType, durable);
    }
}
