package gj.avengers.demo.infra.jarvis.rest_template.v1;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;
import gj.avengers.demo.infra.jarvis.JarvisApiGateway;
import gj.avengers.demo.infra.dto.LocationResponse;
import gj.avengers.demo.infra.jarvis.dto.ReplacementRecommendationsResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
public class JarvisApiRestTemplate implements JarvisApiGateway {


    private final Executor executor;
    private final JarvisApiRestTemplateSync jarvisApiRestTemplateSync;

    public JarvisApiRestTemplate(
            @Qualifier("externalApiExecutor") Executor executor,
            JarvisApiRestTemplateSync jarvisApiRestTemplateSync
    ) {
        this.executor = executor;
        this.jarvisApiRestTemplateSync = jarvisApiRestTemplateSync;
    }

    @Override
    public CompletableFuture<ReplacementRecommendationsResponse> requestReplacementRecommendations(HulkBuster.TotalState state) {
        return CompletableFuture.supplyAsync(
                () -> jarvisApiRestTemplateSync.requestReplacementRecommendationsSync(state),
                executor
        );
    }

    @Override
    public CompletableFuture<LocationResponse> requestHulkbusterLocation() {
        // TODO
        return null;
    }

}
