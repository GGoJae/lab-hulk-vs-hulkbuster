package gj.avengers.demo.domain.hulkbuster.model;

import gj.avengers.demo.application.model.PartType;

import java.util.ArrayList;
import java.util.List;

public record Status(
        List<PartStatus> partStatuses
) {

    public PartStatus findByPart(PartType type) {
        return partStatuses.stream().filter(ps -> ps.part() == type).findFirst().orElseThrow();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        List<PartStatus> parts = new ArrayList<>();

        public Builder addPartStatus(PartStatus partStatus) {
            this.parts.add(partStatus);
            return this;
        }

        public Status build() {
            return new Status(this.parts);
        }
    }
}
