package gj.avengers.demo.infra.hulkbuster.requestSpec;

import gj.avengers.demo.domain.model.TargetPart;

public record AttackRequest(
        PartType part,
        int damage
) {

    public static AttackRequest from(TargetPart targetPart, int damage) {
        return new AttackRequest(PartType.mapping(targetPart), damage);
    }

    public enum PartType {
        HELMETS, ARMOR, ARMS, LEGS;

        public static PartType mapping(TargetPart targetPart) {
            return switch (targetPart) {
                case ARMS -> PartType.ARMS;
                case HELMETS -> PartType.HELMETS;
                case ARMOR -> PartType.ARMOR;
                case LEGS -> PartType.LEGS;
            };
        }
    }
}
