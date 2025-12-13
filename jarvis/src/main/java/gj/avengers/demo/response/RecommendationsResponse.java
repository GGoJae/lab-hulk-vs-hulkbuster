package gj.avengers.demo.response;

import gj.avengers.demo.domain.PartValue;

import java.util.List;

public record RecommendationsResponse(
        List<PartValue> needReplacementParts
) {
    // TODO 경고 수준? 같은 상태 코드 같은거 만들어서 넣으면 좋을듯 + message 정도 필드
    public static RecommendationsResponse of(List<PartValue> needReplacementParts) {
        return new RecommendationsResponse(needReplacementParts);
    }
}
