package gj.avengers.demo.infra.jarvis.responseSpec;

import gj.avengers.demo.shared.model.PartType;

import java.util.List;

public record ReplacementRecommendationsResponse(
        List<PartType> needReplacementParts
) {

    public boolean hasReplacementParts() {
        return !needReplacementParts.isEmpty();
    }

    public boolean isNothingToReplace() {
        return needReplacementParts.isEmpty();
    }
}
