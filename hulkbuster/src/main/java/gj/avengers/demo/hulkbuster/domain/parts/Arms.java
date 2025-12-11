package gj.avengers.demo.hulkbuster.domain.parts;

import gj.avengers.demo.hulkbuster.domain.PartValue;
import lombok.Getter;

@Getter
public class Arms implements Part {
    private int durable;
    private boolean isBroken;

    private static final int MAX_DURABLE = 100;

    public Arms(int durable, boolean isBroken) {
        this.durable = durable;
        this.isBroken = isBroken;
    }

    public static Arms newArms() {
        return new Arms(MAX_DURABLE, false);
    }

    @Override
    public PartValue type() {
        return PartValue.ARMS;
    }

    @Override
    public void getDamage(int damage) {
        if (this.durable <= damage) {
            this.durable = 0;
            isBroken = true;
        }

        this.durable -= damage;
        this.durable = Math.max(0, durable);
        // TODO 파츠가 부셔지면 어떻게 할건지 생각해보기
    }

    @Override
    public int currentDurable() {
        return this.durable;
    }
}
