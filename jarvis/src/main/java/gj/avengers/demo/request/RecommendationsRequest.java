package gj.avengers.demo.request;

import gj.avengers.demo.dto.State;

import java.util.List;

public record RecommendationsRequest(
    List<State> states
) {
}
