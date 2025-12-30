package gj.avengers.demo.application.out;

import gj.avengers.demo.application.model.LocationInfo;
import gj.avengers.demo.application.model.PartType;
import gj.avengers.demo.domain.hulkbuster.model.Status;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface JarvisPort {

    CompletionStage<List<PartType>> requestReplacementRecommendations(Status state);

    CompletionStage<LocationInfo> requestHulkbusterLocation();
}
