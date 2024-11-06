package store.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Promotion;

class PromotionParserTest {
    @Test
    @DisplayName("promotions 파일에서 잘 파싱하는지 확인")
    void promotions_파싱_테스트() {
        List<String[]> rawPromotions = Arrays.asList(
                new String[] {"탄산2+1", "2", "1", "2024-01-01", "2024-12-31"},
                new String[] {"MD추천상품", "1", "1", "2024-01-01", "2024-12-31"}
        );
        PromotionParser promotionParser = new PromotionParser(rawPromotions);
        List<Promotion> promotions = promotionParser.parsePromotions();

        assertThat(promotions).hasSize(2).extracting(Promotion::getName).contains("탄산2+1", "MD추천상품");
    }

    @Test
    @DisplayName("올바른 Promotion 객체를 생성하는지 확인")
    void 파일_변화_후_promotion_객체_생성_테스트() {

    }

    @Test
    @DisplayName("올바른 DateRange 객체를 생성하는지 확인")
    void 파일_변화_후_dateRange_객체_생성_테스트() {

    }
}