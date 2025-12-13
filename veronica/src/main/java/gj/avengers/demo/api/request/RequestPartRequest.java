package gj.avengers.demo.api.request;

import gj.avengers.demo.api.domain.PartValue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record RequestPartRequest(
        @NotNull
        LocationInfo location,
        @NotEmpty
        List<PartValue> parts

) {

    public record LocationInfo(
            @NotNull
            @Digits(integer = 3, fraction = 6)
            BigDecimal latitude,

            @NotNull
            @Digits(integer = 3, fraction = 6)
            BigDecimal longitude
    ) {
    }
}
