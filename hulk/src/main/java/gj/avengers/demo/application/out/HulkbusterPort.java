package gj.avengers.demo.application.out;

import gj.avengers.demo.domain.model.TargetPart;

import java.util.concurrent.CompletableFuture;

public interface HulkbusterPort {

    CompletableFuture<Void> attackOnHulkbuster(TargetPart targetPart, int damage);
}
