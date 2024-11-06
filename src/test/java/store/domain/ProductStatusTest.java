package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.Product;

class ProductStatusTest {
    @Test
    @DisplayName("product 객체, 재고, 프로모션을 갖는 객체가 잘 생성되는지 확인")
    void productStatus_객체_생성_테스트() {
        DateRange twoPlusOneRange = DateRange.of("2024-01-01", "2024-12-31");
        Promotion twoPlusOne = Promotion.of("twoPlusOne", 2, 1, twoPlusOneRange);
        Product coke = Product.of("coke", 1000);
        ProductStatus cokeStatus = ProductStatus.of(coke, 10, twoPlusOne);

        LocalDate currentDate = LocalDate.of(2024,11,1);

        assertThat(cokeStatus.getProduct().getName()).isEqualTo("coke");
        assertThat(cokeStatus.getPromotion().getDateRange().isBetween()).isTrue();
    }
}