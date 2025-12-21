package gj.avengers.demo.infra.jarvis.rest_template.v1;

import gj.avengers.demo.application.out.JarvisPort;
import gj.avengers.demo.infra.jarvis.responseSpec.ReplacementRecommendationsResponse;
import gj.avengers.demo.domain.hulkbuster.model.Status;
import gj.avengers.demo.application.model.LocationInfo;
import gj.avengers.demo.application.model.PartType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
public class JarvisApiRestTemplate implements JarvisPort {


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
    public CompletableFuture<List<PartType>> requestReplacementRecommendations(Status state) {
        return CompletableFuture.supplyAsync(
                () -> jarvisApiRestTemplateSync.requestReplacementRecommendationsSync(state),
                executor
        ).thenApply(ReplacementRecommendationsResponse::needReplacementParts);
    }

    @Override
    public CompletableFuture<LocationInfo> requestHulkbusterLocation() {
        // TODO
        return null;
    }

}
