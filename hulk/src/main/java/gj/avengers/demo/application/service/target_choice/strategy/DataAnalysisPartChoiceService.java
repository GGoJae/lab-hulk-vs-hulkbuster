package gj.avengers.demo.application.service.target_choice.strategy;

import gj.avengers.demo.application.model.PartCondition;
import gj.avengers.demo.application.service.HulkbusterStatus;
import gj.avengers.demo.application.service.target_choice.TargetPartChoiceService;
import gj.avengers.demo.domain.model.TargetPart;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Component
@RequiredArgsConstructor
public class DataAnalysisPartChoiceService implements TargetPartChoiceService {

    private final HulkbusterStatus status;

    @Override
    public TargetPart choiceTargetPart() {
        Map<TargetPart, PartCondition> targetPartConditionMap = status.currentState();
        if (targetPartConditionMap.size() != TargetPart.values().length) {
            throw new IllegalStateException();
        }
        Set<Map.Entry<TargetPart, PartCondition>> attackCandidate = targetPartConditionMap.entrySet().stream()
                .filter(e -> e.getValue() != PartCondition.DESTROYED)
                .collect(Collectors.toUnmodifiableSet());

        return attackCandidate.stream()
                .filter(e -> e.getValue() == PartCondition.DEGRADED)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseGet(() -> {
                    return attackCandidate.stream()
                            .map(Map.Entry::getKey)
                            .findAny()
                            .orElseThrow();
                });
    }
}
