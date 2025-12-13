package gj.avengers.demo.infra.veronica.dto;

import gj.avengers.demo.hulkbuster.domain.PartValue;
import gj.avengers.demo.infra.dto.LocationResponse;

import java.util.List;

public record RequestPartsRequest(
        LocationResponse location,
        List<PartValue> parts
) {

    public static RequestPartsRequest of(LocationResponse location, List<PartValue> parts) {
        return new RequestPartsRequest(location, parts);
    }
}
