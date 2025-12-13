package gj.avengers.demo.infra.jarvis.rest_template.v1;

import gj.avengers.demo.infra.jarvis.requestSpec.ReplacementRecommendationsRequest;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import gj.avengers.demo.domain.hulkbuster.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class JarvisApiRestTemplateSync {

    private final RestTemplate restTemplate;

    @Value("${jarvis.url}")
    private String jarvisBaseUrl;

    @Retryable(
            retryFor = { RestClientException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 500)
    )
    public ReplacementRecommendationsResponse requestReplacementRecommendationsSync(Status state) {

        return restTemplate.postForObject(
                jarvisBaseUrl + "/recommendations",
                new ReplacementRecommendationsRequest(state.partStatuses()),
                ReplacementRecommendationsResponse.class);
    }
}
