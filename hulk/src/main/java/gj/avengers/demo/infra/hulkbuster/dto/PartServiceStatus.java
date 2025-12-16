package gj.avengers.demo.infra.hulkbuster.dto;

import gj.avengers.demo.application.model.PartCondition;

public enum PartServiceStatus {
    OK(PartCondition.OPERATIONAL),            // 정상
    REPLACE(PartCondition.DEGRADED),       // 교체 필요
    BEYOND_REPAIR(PartCondition.DESTROYED);  // 수리/교체 불가(회생 불가)

    private PartCondition domain;

    PartServiceStatus(PartCondition domain) {
        this.domain = domain;
    }

    public PartCondition toDomain() {
        return this.domain;
    }
}
