package gj.avengers.demo.infra.jarvis.requestSpec;

import gj.avengers.demo.domain.hulkbuster.model.PartStatus;

import java.util.List;

public record ReplacementRecommendationsRequest(
        List<PartStatus> states
) {}
