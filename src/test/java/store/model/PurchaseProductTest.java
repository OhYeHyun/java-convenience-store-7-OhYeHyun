package store.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseProductTest {

    @Test
    @DisplayName("물건명, 수량, 프로모션 유무를 갖는 PurchaseProduct 객체가 올바르게 생성되는지 확인")
    void purchaseProduct_객체_생성_테스트() {
        PurchaseProduct purchaseProduct = PurchaseProduct.of("콜라", 10, true);
        assertThat(purchaseProduct.getName()).isEqualTo("콜라");
    }
}