package gj.avengers.demo.infra.jarvis.rest_template.v2;

import gj.avengers.demo.common.util.externalCall.ExternalCallExecutor;
import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class JarvisRestTemplateV2 implements JarvisApiGateway {

    private final ExternalCallExecutor externalCall;
    private final RestTemplate restTemplate;

    @Value("${jarvis.url}")
    private String jarvisBaseUrl;

    @Override
    public CompletableFuture<ReplacementRecommendationsResponse> requestReplacementRecommendations(HulkBuster.TotalState state) {
        return externalCall.executeAsync(() -> restTemplate.postForObject(
                jarvisBaseUrl + "/recommendations",
                new ReplacementRecommendationsRequest(state.states()),
                ReplacementRecommendationsResponse.class
        ));
    }
}
