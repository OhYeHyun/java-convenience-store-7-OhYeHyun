package store.utility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CSVReaderTest {

    @Test
    @DisplayName("products 파일에서 잘 파싱하는지 확인")
    void products_파싱_테스트() {
        CSVReader reader = new CSVReader("src/main/resources/products.md");
        List<String[]> productsList = reader.getLists();

        assertThat(productsList.get(0)).contains("콜라", "1000", "10", "탄산2+1");
    }

    @Test
    @DisplayName("promotions 파일에서 잘 파싱하는지 확인")
    void promotions_파싱_테스트() {
        CSVReader reader = new CSVReader("src/main/resources/promotions.md");
        List<String[]> promotionsList = reader.getLists();

        assertThat(promotionsList.get(0)).contains("탄산2+1", "2", "1", "2024-01-01", "2024-12-31");
    }
}