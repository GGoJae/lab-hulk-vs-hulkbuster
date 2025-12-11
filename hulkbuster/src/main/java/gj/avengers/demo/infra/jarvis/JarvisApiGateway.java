package gj.avengers.demo.infra.jarvis;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsResponse;

import java.util.concurrent.CompletableFuture;

public interface JarvisApiGateway {

    CompletableFuture<ReplacementRecommendationsResponse> requestReplacementRecommendations(HulkBuster.TotalState state);
}
