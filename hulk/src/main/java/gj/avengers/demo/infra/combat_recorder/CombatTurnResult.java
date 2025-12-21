package gj.avengers.demo.infra.combat_recorder;

import gj.avengers.demo.application.model.PartCondition;
import gj.avengers.demo.domain.model.TargetPart;

public sealed interface CombatTurnResult permits CombatTurnResult.Success, CombatTurnResult.Failure {
    int turn();
    TargetPart targetPart();

    record Success(int turn, TargetPart targetPart, PartCondition condition) implements CombatTurnResult {}
    record Failure(int turn, TargetPart targetPart, String reason) implements CombatTurnResult {}
}
