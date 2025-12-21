package gj.avengers.demo.domain.hulkbuster.parts;

import gj.avengers.demo.domain.hulkbuster.model.DamageResult;
import gj.avengers.demo.domain.hulkbuster.model.PartCondition;
import gj.avengers.demo.application.model.PartType;

public interface Part {
    PartType type();
    PartCondition currentCondition();
    int currentDurable();
    DamageResult applyDamage(int damage);

    default boolean isBroken() {
        return currentDurable() == 0;
    }

    default boolean isBeyondRepair(){
        return currentCondition() == PartCondition.BEYOND_REPAIR;
    }
}
