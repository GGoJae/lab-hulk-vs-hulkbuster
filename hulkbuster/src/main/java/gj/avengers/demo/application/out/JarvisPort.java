package gj.avengers.demo.application.out;

import gj.avengers.demo.domain.hulkbuster.model.Status;
import gj.avengers.demo.shared.model.LocationInfo;
import gj.avengers.demo.shared.model.PartType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface JarvisPort {

    CompletableFuture<List<PartType>> requestReplacementRecommendations(Status state);

    CompletableFuture<LocationInfo> requestHulkbusterLocation();
}
