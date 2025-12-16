package gj.avengers.demo.api;

import gj.avengers.demo.request.RecommendationsRequest;
import gj.avengers.demo.response.CurrentLocation;
import gj.avengers.demo.response.RecommendationsResponse;
import gj.avengers.demo.service.JarvisBrain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Api {

    private final JarvisBrain jarvisBrain;

    @PostMapping("/recommendations")
    public CompletableFuture<ResponseEntity<RecommendationsResponse>> replacementRecommendations(
            @RequestBody RecommendationsRequest request
    ) {
        log.info("request 한번 볼까!!! {}", request);

        return jarvisBrain.detectionReplacement(request.states())
                .thenApply(res -> {
                    log.info("recommendations 의 res : {}", res);
                    return res;
                })
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/locations")
    public CompletableFuture<ResponseEntity<CurrentLocation>> getLocationInfo() {
        log.info("locations 호출!");

        return jarvisBrain.currentLocation()
                .thenApply(res -> {
                    log.info("location res : {}", res);
                    return res;
                })
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/battles/history")
    public ResponseEntity<Void> battleHistory() {
        /*
         TODO 버스터가 공격한 후 헐크의 리액션을 기록
         자비스가 다음에 어디 파츠를 공격할지 추천할때 쓰임
         우선 memory Repository 로 구현 -> 추후 갈아끼우기
         */
        return null;
    }

}
