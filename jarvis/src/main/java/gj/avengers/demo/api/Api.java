package gj.avengers.demo.api;

import gj.avengers.demo.api.request.RecommendationsRequest;
import gj.avengers.demo.api.response.RecommendationsResponse;
import gj.avengers.demo.service.RecommendationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Api {

    private final RecommendationsService recommendationsService;

    @PostMapping("/recommendations")
    public CompletableFuture<ResponseEntity<RecommendationsResponse>> replacementRecommendations(
            @RequestBody RecommendationsRequest request
    ) {
        log.info("request 한번 볼까!!! {}", request);

        return recommendationsService.detectionReplacement(request.states())
                .thenApply(RecommendationsResponse::new)
                .thenApply(res -> {
                    log.info("res : {}", res);
                    return res;
                })
                .thenApply(ResponseEntity::ok);
    }

}
