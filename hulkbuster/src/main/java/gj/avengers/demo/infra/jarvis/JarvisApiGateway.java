package gj.avengers.demo.infra.jarvis;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.responseSpec.LocationResponse;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;

import java.util.concurrent.CompletableFuture;

public interface JarvisApiGateway {

    CompletableFuture<ReplacementRecommendationsResponse> requestReplacementRecommendations(HulkBuster.TotalState state);

    CompletableFuture<LocationResponse> requestHulkbusterLocation();
}
