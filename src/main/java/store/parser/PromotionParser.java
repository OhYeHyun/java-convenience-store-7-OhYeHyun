package store.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import store.model.DateRange;
import store.model.Promotion;

class PromotionParser{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final List<String[]> rawPromotions;
    private final List<Promotion> promotions = new ArrayList<>();

    public PromotionParser(List<String[]> rawPromotions) {
        this.rawPromotions = rawPromotions;
    }

    public void parsePromotions() {
        for (String[] attributes : rawPromotions) {
            DateRange dateRange = parseDateRange(attributes[3], attributes[4]);
            Promotion newPromotion = Promotion.of(attributes[0],
                    toInt(attributes[1]),
                    toInt(attributes[2]),
                    dateRange);

            promotions.add(newPromotion);
        }
    }

    private DateRange parseDateRange(String rawStartDate, String rawEndDate) {
        LocalDate startDate = LocalDate.parse(rawStartDate, FORMATTER);
        LocalDate endDate = LocalDate.parse(rawEndDate, FORMATTER);

        return DateRange.of(startDate, endDate);
    }

    private static int toInt(String attribute) {
        return Integer.parseInt(attribute);
    }

    public List<Promotion> getPromotions() {
        return new ArrayList<>(promotions);
    }
}