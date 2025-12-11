package gj.avengers.demo.infra.jarvis.web_client.v2;

import gj.avengers.demo.common.util.WebClientUtil;
import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class JarvisApiWebClientV2 implements JarvisApiGateway {

    private final WebClient jarvisWebClient;
    private final Scheduler apiScheduler;

    @Override
    public CompletableFuture<ReplacementRecommendationsResponse> requestReplacementRecommendations(HulkBuster.TotalState state) {
        return toFutureWithPolicy(recommendationsCall(state));
    }

    private Mono<ReplacementRecommendationsResponse> recommendationsCall(HulkBuster.TotalState state) {
        return jarvisWebClient.post()
                .uri("/recommendations")
                .bodyValue(new ReplacementRecommendationsRequest(state.states()))
                .retrieve()
                .bodyToMono(ReplacementRecommendationsResponse.class);
    }

    private <T> CompletableFuture<T> toFutureWithPolicy(Mono<T> mono) {
        return WebClientUtil.defaultPolicy(mono, apiScheduler).toFuture();
    }
}
