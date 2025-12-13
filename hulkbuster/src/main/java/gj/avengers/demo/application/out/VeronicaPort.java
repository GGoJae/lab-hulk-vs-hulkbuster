package gj.avengers.demo.application.out;

import gj.avengers.demo.shared.model.LocationInfo;
import gj.avengers.demo.shared.model.PartType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface VeronicaPort {

    CompletableFuture<Void> requestParts(LocationInfo location, List<PartType> parts);
}
