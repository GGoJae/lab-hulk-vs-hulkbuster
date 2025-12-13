package gj.avengers.demo.domain.hulkbuster.parts;

import gj.avengers.demo.shared.model.PartType;

public interface Part {
    PartType type();
    int currentDurable();
    void getDamage(int damage);
    boolean isBroken();
}
