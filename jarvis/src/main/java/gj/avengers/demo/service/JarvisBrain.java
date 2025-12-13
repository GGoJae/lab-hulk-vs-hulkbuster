package gj.avengers.demo.service;

import gj.avengers.demo.domain.PartValue;
import gj.avengers.demo.dto.State;
import gj.avengers.demo.response.CurrentLocation;
import gj.avengers.demo.response.RecommendationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class JarvisBrain {

    private final ThreadPoolTaskExecutor taskTreadPool;

    public CompletableFuture<RecommendationsResponse> detectionReplacement(List<State> states) {
        return CompletableFuture.supplyAsync(() -> {
            List<PartValue> partValues = states.stream()
                    .filter(s -> s.durable() < 30)
                    .map(State::part)
                    .toList();
            return RecommendationsResponse.of(partValues);
        }, taskTreadPool);
    }


    public CompletableFuture<CurrentLocation> currentLocation() {
        return CompletableFuture.supplyAsync(() -> {
            /*
            TODO 위치정보 받아오는 Api 같은거 연동? 원래는 hulkbuster 가 이것도 호출해야겠지만
            외부 api 호출 -> 외부 api 호출 같은 시나리오 연습
            */
            double latitude = ThreadLocalRandom.current().nextDouble(200);
            double longitude = ThreadLocalRandom.current().nextDouble(200);
            return CurrentLocation.of(latitude, longitude);
        }, taskTreadPool);
    }
}
