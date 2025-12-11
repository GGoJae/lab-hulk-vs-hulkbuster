package gj.avengers.demo.dto;

import gj.avengers.demo.domain.PartValue;

public record State(
        PartValue part, int durable
) {
}
