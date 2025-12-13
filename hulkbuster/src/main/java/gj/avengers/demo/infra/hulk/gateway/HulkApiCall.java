package gj.avengers.demo.infra.hulk.gateway;

import gj.avengers.demo.shared.model.BodyParts;
import gj.avengers.demo.infra.hulk.requestSpec.AttackRequest;
import gj.avengers.demo.infra.hulk.responseSpec.AttackResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class HulkApiCall {

    private final WebClient hulkWebClient;

    public HulkApiCall(@Qualifier("hulkWebClient") WebClient hulkWebClient) {
        this.hulkWebClient = hulkWebClient;
    }

    public Mono<AttackResponse> attackCall(BodyParts targetPart) {
        return hulkWebClient.post()
                .uri("/attack")
                .bodyValue(new AttackRequest(targetPart))
                .retrieve()
                .bodyToMono(AttackResponse.class);
    }
}
