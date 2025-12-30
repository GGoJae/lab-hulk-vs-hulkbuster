package gj.avengers.demo.application.out;

import gj.avengers.demo.application.model.BodyPart;
import gj.avengers.demo.application.model.Reaction;

import java.util.concurrent.CompletionStage;

public interface HulkPort {

    CompletionStage<Reaction> attack(BodyPart targetPart);
}
