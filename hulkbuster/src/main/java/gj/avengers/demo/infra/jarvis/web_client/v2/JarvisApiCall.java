package gj.avengers.demo.infra.jarvis.web_client.v2;

import gj.avengers.demo.infra.jarvis.requestSpec.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.responseSpec.LocationResponse;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import gj.avengers.demo.domain.hulkbuster.model.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JarvisApiCall {


    private final WebClient jarvisWebClient;

    public JarvisApiCall(@Qualifier("jarvisWebClient")WebClient jarvisWebClient) {
        this.jarvisWebClient = jarvisWebClient;
    }


    public Mono<ReplacementRecommendationsResponse> recommendationsCall(Status state) {
        return jarvisWebClient.post()
                .uri("/recommendations")
                .bodyValue(new ReplacementRecommendationsRequest(state.partStatuses()))
                .retrieve()
                .bodyToMono(ReplacementRecommendationsResponse.class);
    }


    public Mono<LocationResponse> locationCall() {
        return jarvisWebClient.get()
                .uri("/locations")
                .retrieve()
                .bodyToMono(LocationResponse.class);
    }



}
