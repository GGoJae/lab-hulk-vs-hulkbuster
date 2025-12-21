package gj.avengers.demo.infra.jarvis.rest_template.v2;

import gj.avengers.demo.application.out.JarvisPort;
import gj.avengers.demo.common.util.externalCall.ExternalCallExecutor;
import gj.avengers.demo.infra.jarvis.requestSpec.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import gj.avengers.demo.domain.hulkbuster.model.Status;
import gj.avengers.demo.application.model.LocationInfo;
import gj.avengers.demo.application.model.PartType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class JarvisRestTemplateV2 implements JarvisPort {

    private final ExternalCallExecutor externalCall;
    private final RestTemplate restTemplate;

    @Value("${jarvis.url}")
    private String jarvisBaseUrl;

    @Override
    public CompletableFuture<List<PartType>> requestReplacementRecommendations(Status state) {
        return externalCall.executeAsync(() -> restTemplate.postForObject(
                jarvisBaseUrl + "/recommendations",
                new ReplacementRecommendationsRequest(state.partStatuses()),
                ReplacementRecommendationsResponse.class
        )).thenApply(ReplacementRecommendationsResponse::needReplacementParts);
    }

    @Override
    public CompletableFuture<LocationInfo> requestHulkbusterLocation() {
        // TODO
        return null;
    }
}
