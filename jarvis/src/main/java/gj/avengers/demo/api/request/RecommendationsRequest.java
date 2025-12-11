package gj.avengers.demo.api.request;

import gj.avengers.demo.dto.State;

import java.util.List;

public record RecommendationsRequest(
    List<State> states
) {
}
