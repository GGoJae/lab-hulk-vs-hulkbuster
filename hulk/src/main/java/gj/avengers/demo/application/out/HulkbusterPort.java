package gj.avengers.demo.application.out;

import gj.avengers.demo.application.model.PartWithCondition;
import gj.avengers.demo.domain.model.TargetPart;

import java.util.concurrent.CompletableFuture;

public interface HulkbusterPort {

    CompletableFuture<PartWithCondition> attackOnHulkbuster(TargetPart targetPart, int damage);
}
