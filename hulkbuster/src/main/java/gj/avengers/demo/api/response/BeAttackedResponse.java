package gj.avengers.demo.api.response;

import gj.avengers.demo.domain.hulkbuster.model.DamageResult;
import gj.avengers.demo.domain.hulkbuster.model.PartCondition;
import gj.avengers.demo.application.model.PartType;

import java.util.Arrays;

public record BeAttackedResponse(
        PartTypeSpec part,
        PartCondSpec condition
) {

    public static BeAttackedResponse from(DamageResult damageResult) {
        return new BeAttackedResponse(
                PartTypeSpec.of(damageResult.type()),
                PartCondSpec.of(damageResult.afterCond())
        );
    }

    public enum PartTypeSpec {
        HELMETS( PartType.HELMETS),
        ARMOR( PartType.ARMOR),
        ARMS( PartType.ARMS),
        LEGS( PartType.LEGS);

        private PartType domain;

        PartTypeSpec(PartType domain) {
            this.domain = domain;
        }

        public static PartTypeSpec of(PartType domainType) {
            return Arrays.stream(values())
                    .filter(r -> r.domain == domainType)
                    .findFirst()
                    .orElseThrow();
        }
    }

    public enum PartCondSpec {
        OK(PartCondition.OK),
        REPLACE(PartCondition.REPLACE),
        BEYOND_REPAIR(PartCondition.BEYOND_REPAIR);

        private PartCondition domain;

        PartCondSpec(PartCondition domain) {
            this.domain = domain;
        }

        public static PartCondSpec of(PartCondition domainType) {
            return Arrays.stream(values())
                    .filter(r -> r.domain == domainType)
                    .findFirst()
                    .orElseThrow();
        }
    }
}
