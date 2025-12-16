package gj.avengers.demo.infra.combat_recorder;

import gj.avengers.demo.application.model.PartWithCondition;
import gj.avengers.demo.application.out.CombatResultRecorder;
import gj.avengers.demo.domain.model.TargetPart;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MemoryCombatRecorder implements CombatResultRecorder {

    @Override
    public void record(int turn, PartWithCondition result) {
        // TODO 전투 기록하기
    }

    @Override
    public void recordFailure(int turn, TargetPart part, Throwable ex) {
        // TODO 전투 실패 기록하기
    }

    private final Map<>


}
