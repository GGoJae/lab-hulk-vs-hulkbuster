package gj.avengers.demo.infra.veronica.web_client;

import gj.avengers.demo.common.util.WebClientUtil;
import gj.avengers.demo.hulkbuster.domain.PartValue;
import gj.avengers.demo.infra.dto.LocationResponse;
import gj.avengers.demo.infra.veronica.VeronicaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class VeronicaApiWebClient implements VeronicaGateway {

    private final VeronicaApiCall apiCall;


    @Override
    public CompletableFuture<Void> requestParts(LocationResponse location, List<PartValue> parts) {
        return toFutureWithDefaultPolicy(apiCall.requestPartsCall(location, parts));
    }

    private <T> CompletableFuture<T> toFutureWithDefaultPolicy(Mono<T> mono) {
        return WebClientUtil.defaultPolicy(mono).toFuture();
    }
}
