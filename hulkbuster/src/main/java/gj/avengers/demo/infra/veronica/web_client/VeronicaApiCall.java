package gj.avengers.demo.infra.veronica.web_client;

import gj.avengers.demo.hulkbuster.domain.PartValue;
import gj.avengers.demo.infra.dto.LocationResponse;
import gj.avengers.demo.infra.veronica.dto.RequestPartsRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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

    public Mono<Void> requestPartsCall(LocationResponse location, List<PartValue> parts) {
        return veronicaWebClient.post()
                .uri("/request-parts")
                .bodyValue(RequestPartsRequest.of(location, parts))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
