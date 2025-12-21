package gj.avengers.demo.domain.hulkbuster.parts;

import gj.avengers.demo.domain.hulkbuster.model.DamageResult;
import gj.avengers.demo.domain.hulkbuster.model.PartCondition;
import gj.avengers.demo.application.model.PartType;

public abstract class DurablePart implements Part{

    private final PartType type;
    private int durable;
    private PartCondition condition;

    protected DurablePart(PartType type, int maxDurable) {
        this.type = type;
        this.durable = maxDurable;
        this.condition = PartCondition.OK;
    }

    @Override
    public final PartType type() {
        return type;
    }

    @Override
    public final PartCondition currentCondition() {
        return condition;
    }

    @Override
    public final int currentDurable() {
        return durable;
    }

    @Override
    public DamageResult applyDamage(int damage) {
        if (damage < 0) throw new IllegalArgumentException("damage는 0 이상");

        int beforeDurable = durable;
        PartCondition beforeCond = condition;

        if (condition != PartCondition.BEYOND_REPAIR) {
            int effectiveDamage = computeEffectiveDamage(damage); // 훅
            durable = Math.max(0, durable - effectiveDamage);
            condition = condition.onHitAfter(durable);            // 상태 머신
        }

        return new DamageResult(type, beforeDurable, durable, beforeCond, condition);
    }

    protected int computeEffectiveDamage(int damage) {
        return damage;
    }
}
