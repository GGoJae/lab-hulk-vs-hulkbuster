package gj.avengers.demo.infra.jarvis.web_client.v1;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.infra.jarvis.responseSpec.LocationResponse;
import gj.avengers.demo.infra.jarvis.requestSpec.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.scheduler.Scheduler;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class JarvisApiWebClient implements JarvisApiGateway {

    private final WebClient jarvisWebClient;
    private final Scheduler apiScheduler;

    @Override
    public CompletableFuture<ReplacementRecommendationsResponse> requestReplacementRecommendations(
            HulkBuster.TotalState state
    ) {
        log.info("여기의 state? {} ", state);
        return jarvisWebClient.post()
                .uri("/recommendations")
                .bodyValue(new ReplacementRecommendationsRequest(state.states()))
                .retrieve()
                .bodyToMono(ReplacementRecommendationsResponse.class)
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500))
                        .filter(t -> t instanceof WebClientRequestException ||
                                (t instanceof WebClientResponseException r && r.getStatusCode().is5xxServerError())
                        )
                )
                .subscribeOn(apiScheduler)
                .toFuture();


    }

    @Override
    public CompletableFuture<LocationResponse> requestHulkbusterLocation() {
        // TODO 공통
        return null;
    }

}
