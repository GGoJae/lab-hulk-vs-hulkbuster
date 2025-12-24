package gj.avengers.demo.domain.hulkbuster;

import gj.avengers.demo.application.model.PartType;
import gj.avengers.demo.domain.hulkbuster.model.DamageResult;
import gj.avengers.demo.domain.hulkbuster.model.PartCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


class HulkBusterTest {

    private static final Logger log = LoggerFactory.getLogger(HulkBusterTest.class);

    @Test
    @DisplayName("Durable 이 0이 되면 교체가 필요하다.")
    void shouldMarkAsReplaceTarget() {
        // given
        int maxDurable = 100;
        int damage = 100;

        PartType arms = PartType.ARMS;
        HulkBuster hulkBuster = new HulkBuster();

        // when
        DamageResult damageResult = hulkBuster.beAttacked(arms, damage);
        log.debug("damageResult : {}", damageResult);

        // then
        assertThat(damageResult.afterDurable())
                .isEqualTo(maxDurable - damage);

        assertThat(damageResult.afterCond()).isEqualTo(PartCondition.REPLACE);
    }

    @ParameterizedTest
    @CsvSource({
            "0", "1", "99"
    })
    @DisplayName("Durable 이 0이 아니라면 Cond 는 Ok 가 나온다.")
    void shouldMarkAsOkCaseTakeDamage0(int damage) {
        // given
        int maxDurable = 100;
        PartType armor = PartType.ARMOR;
        HulkBuster hulkBuster = new HulkBuster();

        // when
        DamageResult damageResult = hulkBuster.beAttacked(armor, damage);

        // then
        assertThat(damageResult.afterDurable()).isEqualTo(maxDurable - damage);
        assertThat(damageResult.afterCond()).isEqualTo(PartCondition.OK);
    }

    @Test
    @DisplayName("Durable 이 0 인 상태에서 한번 더 데미지를 받으면 Cond 은 BEYOND_REPAIR 가 된다.")
    void shouldMarkAsBeyondRepair() {
        // given
        int maxDurable = 100;
        int damage = 100;
        PartType armor = PartType.ARMOR;
        HulkBuster hulkBuster = new HulkBuster();

        // when
        hulkBuster.beAttacked(armor, damage);
        DamageResult damageResult = hulkBuster.beAttacked(armor, damage);

        // then
        assertThat(damageResult.afterDurable()).isEqualTo(0);
        assertThat(damageResult.afterCond()).isEqualTo(PartCondition.BEYOND_REPAIR);
    }

    @Test
    @DisplayName("100 초과의 데미지를 받아도 Durable 은 0 미만으로 떨어지지 않는다.(0이다) damage = 101")
    void durableIsZeroWhenTake101Damage() {
        // given
        int maxDurable = 100;
        int damage = 101;
        PartType armor = PartType.ARMOR;
        HulkBuster hulkBuster = new HulkBuster();

        // when
        DamageResult damageResult = hulkBuster.beAttacked(armor, damage);

        // then
        assertThat(damageResult.afterDurable()).isEqualTo(0);
        assertThat(damageResult.afterCond()).isEqualTo(PartCondition.REPLACE);
    }

    @Test
    @DisplayName("음수의 데미지를 받으면 IllegalStateException 이 터진다.")
    void throwIllegalStateExceptionWhenTakeMinorDamage() {
        // given
        int damage = -1;
        PartType armor = PartType.ARMOR;
        HulkBuster hulkBuster = new HulkBuster();

        // when, then
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalStateException.class,
                () -> hulkBuster.beAttacked(armor, damage)
        );

    }

    @Test
    @DisplayName("예외 발생 시 장비 상태는 변하지 않는다.")
    void negativeDamageShouldNotChangeDurable() {
        // given
        int damage = -1;
        PartType armor = PartType.ARMOR;
        HulkBuster hulkBuster = new HulkBuster();
        int beforeDurable = hulkBuster.getState().findByPart(armor).durable();

        // when
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalStateException.class,
                () -> hulkBuster.beAttacked(armor, damage)
        );

        // then
        int afterDurable = hulkBuster.getState()
                .findByPart(armor)
                .durable();

        assertThat(afterDurable).isEqualTo(beforeDurable);

    }



}