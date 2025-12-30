package gj.avengers.demo.application.out;

import gj.avengers.demo.application.model.LocationInfo;
import gj.avengers.demo.application.model.PartType;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface VeronicaPort {

    CompletionStage<Void> requestParts(LocationInfo location, List<PartType> parts);
}
