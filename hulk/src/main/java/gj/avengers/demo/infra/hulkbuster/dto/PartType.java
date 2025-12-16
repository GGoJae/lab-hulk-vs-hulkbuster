package gj.avengers.demo.infra.hulkbuster.dto;

import gj.avengers.demo.domain.model.TargetPart;

public enum PartType {

    HELMETS(TargetPart.HELMETS),
    ARMOR(TargetPart.ARMOR),
    ARMS(TargetPart.ARMS),
    LEGS(TargetPart.LEGS);

    PartType(TargetPart targetPart) {
        this.targetPart = targetPart;
    }

    private TargetPart targetPart;

    public static PartType from(TargetPart targetPart) {
        return switch (targetPart) {
            case ARMS -> PartType.ARMS;
            case HELMETS -> PartType.HELMETS;
            case ARMOR -> PartType.ARMOR;
            case LEGS -> PartType.LEGS;
        };
    }

    public TargetPart toDomain() {
        return this.targetPart;
    }

}
