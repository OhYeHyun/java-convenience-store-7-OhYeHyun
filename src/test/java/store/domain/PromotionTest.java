package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @Test
    @DisplayName("구매해야 하는 단위를 제대로 알려주는지 확인")
    void 구매_단위_확인_테스트() {
        LocalDate startDate = LocalDate.parse("2024-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse("2024-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        DateRange twoPlusOneRange = DateRange.of(startDate, endDate);
        Promotion twoPlusOne = Promotion.of("탄산2+1", 2, 1, twoPlusOneRange);

        int result = twoPlusOne.getPurchaseThreshold();
        assertThat(result).isEqualTo(3);
    }
}