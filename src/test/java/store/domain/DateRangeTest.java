package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateRangeTest {
    private String startDate = "2024-01-01";
    private String endDate = "2024-12-31";

    @Test
    @DisplayName("날짜가 프로모션 기간 안인지 확인")
    void 프로모션_기간_안_테스트() {
        LocalDate currentDate = LocalDate.of(2024,11,1);

        DateRange dateRange = DateRange.of(startDate, endDate);
        boolean result = dateRange.isBetween(currentDate);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("날짜가 프로모션 기간 밖인지 확인")
    void 프로모션_기간_밖_테스트() {
        LocalDate currentDate = LocalDate.of(2023,12,31);
        DateRange dateRange = DateRange.of(startDate,endDate);

        boolean result = dateRange.isBetween(currentDate);

        assertThat(result).isFalse();
    }
}