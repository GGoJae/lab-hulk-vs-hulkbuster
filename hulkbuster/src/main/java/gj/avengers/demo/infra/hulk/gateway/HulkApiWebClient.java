package gj.avengers.demo.infra.hulk.gateway;

import gj.avengers.demo.common.util.WebClientUtil;
import gj.avengers.demo.shared.model.BodyParts;
import gj.avengers.demo.infra.hulk.HulkGateway;
import gj.avengers.demo.infra.hulk.responseSpec.AttackResponse;
import gj.avengers.demo.shared.model.Reaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class HulkApiWebClient implements HulkGateway {

    private final HulkApiCall apiCall;


    @Override
    public CompletableFuture<Reaction> attack(BodyParts targetPart) {
        return toFutureWithDefaultPolicy(
                apiCall.attackCall(targetPart)
                        .map(AttackResponse::reaction)
        );
    }

    private <T> CompletableFuture<T> toFutureWithDefaultPolicy(Mono<T> mono) {
        return WebClientUtil.defaultPolicy(mono).toFuture();
    }
}
