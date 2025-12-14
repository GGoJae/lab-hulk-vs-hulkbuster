package gj.avengers.demo.infra.hulk.gateway;

import gj.avengers.demo.common.util.WebClientUtil;
import gj.avengers.demo.shared.model.BodyPart;
import gj.avengers.demo.application.out.HulkPort;
import gj.avengers.demo.infra.hulk.responseSpec.AttackResponse;
import gj.avengers.demo.shared.model.Reaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class HulkApiWebClient implements HulkPort {

    private final HulkApiCall apiCall;


    @Override
    public CompletableFuture<Reaction> attack(BodyPart targetPart) {
        return toFutureWithDefaultPolicy(
                apiCall.attackCall(targetPart)
                        .map(AttackResponse::reaction)
        );
    }

    private <T> CompletableFuture<T> toFutureWithDefaultPolicy(Mono<T> mono) {
        return WebClientUtil.defaultPolicy(mono).toFuture();
    }
}
