package gj.avengers.demo.service;

import gj.avengers.demo.api.request.RecommendationsRequest;
import gj.avengers.demo.api.response.RecommendationsResponse;
import gj.avengers.demo.domain.PartValue;
import gj.avengers.demo.dto.State;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RecommendationsService {

    public CompletableFuture<List<PartValue>> detectionReplacement(List<State> states) {
        return CompletableFuture.supplyAsync(() -> {
            return states.stream()
                    .filter(s -> s.durable() < 30)
                    .map(State::part)
                    .toList();
        });
    }
}
