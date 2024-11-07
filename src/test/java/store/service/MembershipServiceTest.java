package store.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MembershipServiceTest {

    @Test
    @DisplayName("멤버십 할인을 적용한 금액이 올바른지 확인")
    void 멤버십_할인_테스트() {
        int appliedAmount = MembershipService.applyMemberShip(10000);
        assertThat(appliedAmount).isEqualTo(Math.round(10000 * 0.7));
    }

    @Test
    @DisplayName("멤버십 적용 금액이 최대 8000원인지 확인")
    void 맴버십_최대_금액_테스트() {
        int appliedAmount = MembershipService.applyMemberShip(30000);
        assertThat(appliedAmount).isEqualTo(8000);
    }
}