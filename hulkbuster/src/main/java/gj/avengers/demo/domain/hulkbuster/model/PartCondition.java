package gj.avengers.demo.domain.hulkbuster.model;

public enum PartCondition {
    OK,
    REPLACE,
    BEYOND_REPAIR;

    public PartCondition onHitAfter(int durableAfterHit) {
        return switch (this) {
            case BEYOND_REPAIR -> BEYOND_REPAIR;
            case REPLACE -> BEYOND_REPAIR;
            case OK -> (durableAfterHit <= 0 ? REPLACE : OK);
        };
    }
}
