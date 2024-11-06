package store.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import store.domain.DateRange;
import store.domain.Promotion;

class PromotionParser{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final List<String[]> rawPromotions;
    private final List<Promotion> promotions = new ArrayList<>();

    public PromotionParser(List<String[]> rawPromotions) {
        this.rawPromotions = rawPromotions;
    }

    public List<Promotion> parsePromotions() {
        for (String[] attribute : rawPromotions) {
            parsePromotion(attribute);
        }
        return new ArrayList<>(promotions);
    }

    private void parsePromotion(String[] attribute) {
        DateRange dateRange = parseDateRange(attribute[3], attribute[4]);
        Promotion promotion = Promotion.of(attribute[0],
                Integer.parseInt(attribute[1]),
                Integer.parseInt(attribute[2]),
                dateRange);

        promotions.add(promotion);
    }

    private DateRange parseDateRange(String rawStartDate, String rawEndDate) {
        LocalDate startDate = LocalDate.parse(rawStartDate, FORMATTER);
        LocalDate endDate = LocalDate.parse(rawEndDate, FORMATTER);

        return DateRange.of(startDate, endDate);
    }
}