package store.parser;

import java.util.ArrayList;
import java.util.List;
import store.domain.DateRange;
import store.domain.Promotion;

class PromotionParser{
    private final List<String[]> rawPromotions;
    private final List<Promotion> promotions = new ArrayList<>(); // 파싱된 Promotion 객체를 저장할 리스트

    public PromotionParser(List<String[]> promotions) {
        this.rawPromotions = promotions;
    }

    public List<Promotion> parsePromotions() {
        for (String[] attribute : rawPromotions) {
            parsePromotion(attribute);
        }
        return new ArrayList<>(promotions);
    }

    private void parsePromotion(String[] attribute) {
        DateRange dateRange = DateRange.of(attribute[3], attribute[4]);
        Promotion promotion = Promotion.of(attribute[0],
                Integer.parseInt(attribute[1]),
                Integer.parseInt(attribute[2]),
                dateRange);

        promotions.add(promotion);
    }
}