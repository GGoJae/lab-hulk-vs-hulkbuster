package gj.avengers.demo.infra.hulkbuster;

import gj.avengers.demo.application.out.HulkbusterPort;
import gj.avengers.demo.domain.model.TargetPart;
import gj.avengers.demo.infra.hulkbuster.requestSpec.AttackRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class HulkbusterRestTemplateAdapter implements HulkbusterPort {

    private final RestTemplate restTemplate;
    private final ThreadPoolTaskExecutor te;

    public HulkbusterRestTemplateAdapter(
            @Qualifier("hulkbusterRestTemplate") RestTemplate hulkbusterRestTemplate,
            @Qualifier("externalApiExecutor") ThreadPoolTaskExecutor externalApiExecutor
    ) {
        this.restTemplate = hulkbusterRestTemplate;
        this.te = externalApiExecutor;
    }

    @Override
    public CompletableFuture<Void> attackOnHulkbuster(TargetPart targetPart, int damage) {
        return CompletableFuture.runAsync(() -> {
            restTemplate.postForObject(
                    "/attack",
                    AttackRequest.from(targetPart, damage),
                    Void.class
            );
        }, te);
    }
}
