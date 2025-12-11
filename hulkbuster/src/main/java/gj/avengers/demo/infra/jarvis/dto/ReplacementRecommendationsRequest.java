package gj.avengers.demo.infra.jarvis.dto;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;

import java.util.List;

public record ReplacementRecommendationsRequest(
        List<HulkBuster.TotalState.State> states
) {}
