package gj.avengers.demo.hulkbuster.api.dto;

import gj.avengers.demo.hulkbuster.domain.PartValue;

public record BeAttackedRequest(
        PartValue part,
        int damage
) {
}
