package gj.avengers.demo.infra.veronica;

import gj.avengers.demo.hulkbuster.domain.PartValue;
import gj.avengers.demo.infra.jarvis.responseSpec.LocationResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface VeronicaGateway {

    CompletableFuture<Void> requestParts(LocationResponse location, List<PartValue> parts);
}
