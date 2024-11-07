package store.model;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;

public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static DateRange of(LocalDate startDate, LocalDate endDate) {
        return new DateRange(startDate, endDate);
    }

    public boolean isBetween() {
        LocalDate currentDate = LocalDate.from(DateTimes.now());
        return startDate.isBefore(currentDate) && endDate.isAfter(currentDate);
    }
}
