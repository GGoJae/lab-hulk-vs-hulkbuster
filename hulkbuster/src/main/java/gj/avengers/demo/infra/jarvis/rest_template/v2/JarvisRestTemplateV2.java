package gj.avengers.demo.infra.jarvis.rest_template.v2;

import gj.avengers.demo.domain.hulkbuster.HulkBuster;
import gj.avengers.demo.shared.model.PartType;
import gj.avengers.demo.common.util.externalCall.ExternalCallExecutor;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.infra.jarvis.requestSpec.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import gj.avengers.demo.shared.model.LocationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class JarvisRestTemplateV2 implements JarvisApiGateway {

    private final ExternalCallExecutor externalCall;
    private final RestTemplate restTemplate;

    @Value("${jarvis.url}")
    private String jarvisBaseUrl;

    @Override
    public CompletableFuture<List<PartType>> requestReplacementRecommendations(HulkBuster.TotalState state) {
        return externalCall.executeAsync(() -> restTemplate.postForObject(
                jarvisBaseUrl + "/recommendations",
                new ReplacementRecommendationsRequest(state.states()),
                ReplacementRecommendationsResponse.class
        )).thenApply(ReplacementRecommendationsResponse::needReplacementParts);
    }

    @Override
    public CompletableFuture<LocationInfo> requestHulkbusterLocation() {
        // TODO
        return null;
    }
}
