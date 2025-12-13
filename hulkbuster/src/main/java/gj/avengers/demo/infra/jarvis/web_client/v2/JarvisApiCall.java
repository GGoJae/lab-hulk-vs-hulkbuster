package gj.avengers.demo.infra.jarvis.web_client.v2;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.dto.LocationResponse;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JarvisApiCall {

    private final WebClient jarvisWebClient;


    public Mono<ReplacementRecommendationsResponse> recommendationsCall(HulkBuster.TotalState state) {
        return jarvisWebClient.post()
                .uri("/recommendations")
                .bodyValue(new ReplacementRecommendationsRequest(state.states()))
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
