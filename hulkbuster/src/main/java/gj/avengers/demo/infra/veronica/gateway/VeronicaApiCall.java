package gj.avengers.demo.infra.veronica.gateway;

import gj.avengers.demo.application.model.LocationInfo;
import gj.avengers.demo.application.model.PartType;
import gj.avengers.demo.infra.veronica.requestSpec.RequestPartsRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class VeronicaApiCall {

    private final WebClient veronicaWebClient;

    public VeronicaApiCall(@Qualifier("veronicaWebClient")WebClient veronicaWebClient) {
        this.veronicaWebClient = veronicaWebClient;
    }

    public Mono<Void> requestPartsCall(LocationInfo location, List<PartType> parts) {
        return veronicaWebClient.post()
                .uri("/request-parts")
                .bodyValue(RequestPartsRequest.of(location, parts))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
