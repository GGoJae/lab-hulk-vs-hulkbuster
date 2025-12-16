package gj.avengers.demo.application.out;

import gj.avengers.demo.application.model.PartWithCondition;
import gj.avengers.demo.domain.model.TargetPart;

public interface CombatResultRecorder {
    void record(int turn, PartWithCondition result);
    void recordFailure(int turn, TargetPart part, Throwable ex);
}
