package store.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.Promotion;
import store.utility.CSVReader;

class PromotionParserTest {
    private PromotionParser promotionParser;

    @BeforeEach
    void setUp() {
        CSVReader reader = new CSVReader("src/main/resources/promotions.md");
        promotionParser = new PromotionParser(reader.getLists());
        promotionParser.parsePromotions();
    }

    @Test
    @DisplayName("올바른 promotion 객체가 생성되는지 확인")
    void promotion_객체_생성_테스트() {
        List<Promotion> promotions = promotionParser.getPromotions();
        List<String> promotionNames = promotions.stream().map(Promotion::getName).toList();

        assertThat(promotionNames).contains("탄산2+1", "MD추천상품", "반짝할인");
    }
}