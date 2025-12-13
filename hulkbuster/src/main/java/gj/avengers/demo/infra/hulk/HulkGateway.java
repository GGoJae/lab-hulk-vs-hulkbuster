package gj.avengers.demo.infra.hulk;

import gj.avengers.demo.infra.dto.BodyParts;
import gj.avengers.demo.infra.hulk.responseSpec.AttackResponse;

import java.util.concurrent.CompletableFuture;

public interface HulkGateway {

    CompletableFuture<AttackResponse> attack(BodyParts targetPart);
}
