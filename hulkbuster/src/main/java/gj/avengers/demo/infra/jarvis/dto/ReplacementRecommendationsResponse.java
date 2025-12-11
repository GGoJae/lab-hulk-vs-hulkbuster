package gj.avengers.demo.infra.jarvis.dto;

import gj.avengers.demo.hulkbuster.domain.PartValue;

import java.util.List;

public record ReplacementRecommendationsResponse(
        List<PartValue> needReplacementParts
) {
}
