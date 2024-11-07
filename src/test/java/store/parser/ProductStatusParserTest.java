package store.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.ProductStatus;
import store.utility.CSVReader;

class ProductStatusParserTest {
    private ProductStatusParser productStatusParser;

    @BeforeEach
    void setUp() {
        CSVReader reader = new CSVReader("src/main/resources/products.md");
        productStatusParser = new ProductStatusParser(reader.getLists());
        productStatusParser.parseProductStatus();
    }

    @Test
    @DisplayName("올바른 productStatus 객체가 생성되는지 확인")
    void productStatus_객체_생성_테스트() {
        List<ProductStatus> productStatus = productStatusParser.getProductStatus();
        List<String> productNames = productStatus.stream().map(ProductStatus::getProductName).toList();

        assertThat(productNames).contains("콜라", "오렌지주스", "물", "초코바");
    }
}