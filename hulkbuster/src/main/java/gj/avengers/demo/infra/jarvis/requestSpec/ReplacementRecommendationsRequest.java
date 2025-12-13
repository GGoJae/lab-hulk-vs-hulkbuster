package gj.avengers.demo.infra.jarvis.requestSpec;

import gj.avengers.demo.domain.hulkbuster.HulkBuster;

import java.util.List;

public record ReplacementRecommendationsRequest(
        List<HulkBuster.TotalState.State> states
) {}
