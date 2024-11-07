package store.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseProductTest {

    @Test
    @DisplayName("물건명, 수량, 프로모션 유무를 갖는 PurchaseProduct 객체가 올바르게 생성되는지 확인")
    void purchaseProduct_객체_생성_테스트() {
        PurchaseProduct purchaseProduct = new PurchaseProduct("콜라", 10);
        assertThat(purchaseProduct.getName()).isEqualTo("콜라");
    }

    @Test
    @DisplayName("해당 물건을 프로모션 상품으로 제대로 변경하는지 확인")
    void 프로모션_변경_테스트() {
        PurchaseProduct purchaseProduct = new PurchaseProduct("콜라", 10);
        purchaseProduct.changePromotionMark();
        boolean hasPromotionMark = purchaseProduct.getHasPromotionMark();

        assertThat(hasPromotionMark).isTrue();
    }
}