package gj.avengers.demo.infra.dto;

import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;

public record LocationInfo(
        @Digits(integer = 3, fraction = 6)
        BigDecimal latitude,
        @Digits(integer = 3, fraction = 6)
        BigDecimal longitude
) {
}
