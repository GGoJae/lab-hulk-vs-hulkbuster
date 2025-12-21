package gj.avengers.demo.infra.veronica.requestSpec;

import gj.avengers.demo.application.model.LocationInfo;
import gj.avengers.demo.application.model.PartType;

import java.util.List;

public record RequestPartsRequest(
        LocationInfo location,
        List<PartType> parts
) {

    public static RequestPartsRequest of(LocationInfo location, List<PartType> parts) {
        return new RequestPartsRequest(location, parts);
    }
}
