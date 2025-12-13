package gj.avengers.demo.infra.jarvis.web_client.v2;

import gj.avengers.demo.domain.hulkbuster.model.Status;
import gj.avengers.demo.shared.model.PartType;
import gj.avengers.demo.common.util.WebClientUtil;
import gj.avengers.demo.application.out.JarvisPort;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import gj.avengers.demo.shared.model.LocationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Primary
@Component
@RequiredArgsConstructor
public class JarvisApiWebClientV2 implements JarvisPort {

    private final JarvisApiCall apiCall;

    @Override
    public CompletableFuture<List<PartType>> requestReplacementRecommendations(Status state) {

        return toFutureWithDefaultPolicy(
                apiCall.recommendationsCall(state)
                        .map(ReplacementRecommendationsResponse::needReplacementParts)
        );
    }

    @Override
    public CompletableFuture<LocationInfo> requestHulkbusterLocation() {
        return toFutureWithDefaultPolicy(
                apiCall.locationCall()
                        .map(loc -> new LocationInfo(loc.latitude(), loc.longitude())));
    }

    private <T> CompletableFuture<T> toFutureWithDefaultPolicy(Mono<T> mono) {
        return WebClientUtil.defaultPolicy(mono).toFuture();
    }
}
