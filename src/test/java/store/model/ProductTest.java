package store.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    @DisplayName("상품명과 금액으로 Product 객체가 잘 생성되는지 확인")
    void product_객체_생성_테스트() {
        Product coke = Product.of("coke", 1000);

        assertThat(coke.getName()).isEqualTo("coke");
        assertThat(coke.getPrice()).isEqualTo(1000);
    }
}