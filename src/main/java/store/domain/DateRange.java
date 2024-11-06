package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRange {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final LocalDate startDate;
    private final LocalDate endDate;

    private DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static DateRange of(String rawStartDate, String rawEndDate) {
        LocalDate startDate = LocalDate.parse(rawStartDate, FORMATTER);
        LocalDate endDate = LocalDate.parse(rawEndDate, FORMATTER);

        return new DateRange(startDate, endDate);
    }

    public boolean isBetween() {
        LocalDate currentDate = LocalDate.from(DateTimes.now());
        return startDate.isBefore(currentDate) && endDate.isAfter(currentDate);
    }
}
