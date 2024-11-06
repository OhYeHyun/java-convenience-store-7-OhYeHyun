package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateRangeTest {

    @Test
    @DisplayName("날짜가 프로모션 기간 안인지 확인")
    void 프로모션_기간_안_테스트() {
        LocalDate startDate = LocalDate.parse("2024-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse("2024-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        DateRange dateRange = DateRange.of(startDate, endDate);
        boolean result = dateRange.isBetween();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("날짜가 프로모션 기간 밖인지 확인")
    void 프로모션_기간_밖_테스트() {
        LocalDate startDate = LocalDate.parse("2020-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse("2021-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        DateRange dateRange = DateRange.of(startDate,endDate);
        boolean result = dateRange.isBetween();

        assertThat(result).isFalse();
    }
}