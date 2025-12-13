package gj.avengers.demo.infra.jarvis.responseSpec;

import gj.avengers.demo.hulkbuster.domain.PartValue;

import java.util.List;

public record ReplacementRecommendationsResponse(
        List<PartValue> needReplacementParts
) {

    public boolean hasReplacementParts() {
        return !needReplacementParts.isEmpty();
    }

    public boolean isNothingToReplace() {
        return needReplacementParts.isEmpty();
    }
}
