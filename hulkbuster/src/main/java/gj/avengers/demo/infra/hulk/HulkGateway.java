package gj.avengers.demo.infra.hulk;

import gj.avengers.demo.shared.model.BodyParts;
import gj.avengers.demo.shared.model.Reaction;

import java.util.concurrent.CompletableFuture;

public interface HulkGateway {

    CompletableFuture<Reaction> attack(BodyParts targetPart);
}
