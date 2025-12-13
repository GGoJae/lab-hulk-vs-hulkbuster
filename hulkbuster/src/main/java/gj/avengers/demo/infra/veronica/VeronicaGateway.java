package gj.avengers.demo.infra.veronica;

import gj.avengers.demo.shared.model.LocationInfo;
import gj.avengers.demo.shared.model.PartType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface VeronicaGateway {

    CompletableFuture<Void> requestParts(LocationInfo location, List<PartType> parts);
}
