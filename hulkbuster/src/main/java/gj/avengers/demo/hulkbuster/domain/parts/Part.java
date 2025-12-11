package gj.avengers.demo.hulkbuster.domain.parts;

import gj.avengers.demo.hulkbuster.domain.PartValue;

public interface Part {
    PartValue type();
    int currentDurable();
    void getDamage(int damage);
    boolean isBroken();
}
