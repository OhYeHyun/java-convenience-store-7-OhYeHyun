package store.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductStatusTest {

    @Test
    @DisplayName("상품명, 재고, 프로모션명을 갖는 객체가 잘 생성되는지 확인")
    void productStatus_객체_생성_테스트() {
        ProductStatus productStatus = ProductStatus.of("콜라", 10, "탄산2+1");
        assertThat(productStatus.getProductName()).isEqualTo("콜라");
    }
}