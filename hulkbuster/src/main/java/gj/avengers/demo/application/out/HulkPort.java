package gj.avengers.demo.application.out;

import gj.avengers.demo.application.model.BodyPart;
import gj.avengers.demo.application.model.Reaction;

import java.util.concurrent.CompletableFuture;

public interface HulkPort {

    CompletableFuture<Reaction> attack(BodyPart targetPart);
}
