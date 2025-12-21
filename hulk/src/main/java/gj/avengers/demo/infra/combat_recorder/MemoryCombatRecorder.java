package gj.avengers.demo.infra.combat_recorder;

import gj.avengers.demo.application.model.PartWithCondition;
import gj.avengers.demo.application.out.CombatResultRecorder;
import gj.avengers.demo.domain.model.TargetPart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class MemoryCombatRecorder implements CombatResultRecorder {

    private final Map<Integer, CombatTurnResult> turns = new ConcurrentHashMap<>();

    @Override
    public void record(int turn, PartWithCondition result) {
        CombatTurnResult.Success success = new CombatTurnResult.Success(turn, result.part(), result.condition());
        turns.putIfAbsent(turn, success);
        log.info("공격 성공 기록 저장 :{}, {}", turn, success);
    }

    @Override
    public void recordFailure(int turn, TargetPart part, Throwable ex) {
        CombatTurnResult.Failure failure = new CombatTurnResult.Failure(turn, part, ex.getMessage());
        turns.putIfAbsent(turn, failure);
        log.info("공격 실패 기록 저장 : {}, {}", turn, failure);
    }


}
