package gj.avengers.demo.infra.veronica.gateway;

import gj.avengers.demo.common.util.WebClientUtil;
import gj.avengers.demo.application.model.LocationInfo;
import gj.avengers.demo.application.model.PartType;
import gj.avengers.demo.application.out.VeronicaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class VeronicaApiWebClient implements VeronicaPort {

    private final VeronicaApiCall apiCall;


    @Override
    public CompletableFuture<Void> requestParts(LocationInfo location, List<PartType> parts) {
        return toFutureWithDefaultPolicy(apiCall.requestPartsCall(location, parts));
    }

    private <T> CompletableFuture<T> toFutureWithDefaultPolicy(Mono<T> mono) {
        return WebClientUtil.defaultPolicy(mono).toFuture();
    }
}
