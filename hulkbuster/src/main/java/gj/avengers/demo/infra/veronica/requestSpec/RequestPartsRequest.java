package gj.avengers.demo.infra.veronica.requestSpec;

import gj.avengers.demo.shared.model.LocationInfo;
import gj.avengers.demo.shared.model.PartType;

import java.util.List;

public record RequestPartsRequest(
        LocationInfo location,
        List<PartType> parts
) {

    public static RequestPartsRequest of(LocationInfo location, List<PartType> parts) {
        return new RequestPartsRequest(location, parts);
    }
}
