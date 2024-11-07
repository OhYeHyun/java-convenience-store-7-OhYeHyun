package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.parser.ProductStatusParser;
import store.utility.CSVReader;

class ProductStatusTest {

    @Test
    @DisplayName("상품명, 재고, 프로모션명을 갖는 객체가 잘 생성되는지 확인")
    void productStatus_객체_생성_테스트() {
        CSVReader reader = new CSVReader("src/main/resources/products.md");
        ProductStatusParser productStatusParser = new ProductStatusParser(reader.getLists());
        productStatusParser.parseProductStatus();

        List<ProductStatus> productStatus = productStatusParser.getProductStatus();
        List<String> productNames = productStatus.stream().map(ProductStatus::getProductName).toList();

        assertThat(productNames).contains("콜라", "오렌지주스", "물", "초코바");
    }
}