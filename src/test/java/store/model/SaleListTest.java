package store.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SaleListTest {

    @Test
    @DisplayName("구매를 상품명과 수량을 알려주면 재고에서 감소되는지 확인")
    void 재고_감소_테스트() {
        SaleList saleList = new SaleList(Arrays.asList(
                ProductStatus.of("콜라", 10, "탄산2+1"),
                ProductStatus.of("콜라", 10, "null")
        ));

        saleList.purchase("콜라", 12);
        int result = getTotalQuantity(saleList);

        assertThat(result).isEqualTo(8);
    }

    private int getTotalQuantity(SaleList saleList) {
        int result = 0;
        for (ProductStatus product : saleList.getSaleList()) {
            if (Objects.equals(product.getProductName(), "콜라")) {
                result += product.getQuantity();
            }
        }
        return result;
    }
}