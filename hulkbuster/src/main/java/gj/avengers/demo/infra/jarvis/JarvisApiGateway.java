package gj.avengers.demo.infra.jarvis;

import gj.avengers.demo.domain.hulkbuster.HulkBuster;
import gj.avengers.demo.shared.model.PartType;
import gj.avengers.demo.shared.model.LocationInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface JarvisApiGateway {

    CompletableFuture<List<PartType>> requestReplacementRecommendations(HulkBuster.TotalState state);

    CompletableFuture<LocationInfo> requestHulkbusterLocation();
}
