package gj.avengers.demo.infra.jarvis.web_client.v2;

import gj.avengers.demo.common.util.WebClientUtil;
import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.infra.jarvis.responseSpec.LocationResponse;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Primary
@Component
@RequiredArgsConstructor
public class JarvisApiWebClientV2 implements JarvisApiGateway {

    private final JarvisApiCall apiCall;

    @Override
    public CompletableFuture<ReplacementRecommendationsResponse> requestReplacementRecommendations(HulkBuster.TotalState state) {
        return toFutureWithDefaultPolicy(apiCall.recommendationsCall(state));
    }

    @Override
    public CompletableFuture<LocationResponse> requestHulkbusterLocation() {
        return toFutureWithDefaultPolicy(apiCall.locationCall());
    }

    private <T> CompletableFuture<T> toFutureWithDefaultPolicy(Mono<T> mono) {
        return WebClientUtil.defaultPolicy(mono).toFuture();
    }
}
