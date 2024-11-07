package store.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.model.Promotion;

public class PromotionManager {
    private final List<Promotion> promotions;

    public PromotionManager(List<Promotion> products) {
        this.promotions = products;
    }

    public Map<String, Promotion> makePromotionByName() {
        Map<String, Promotion> promotionByName = new HashMap<>();
        for (Promotion promotion : promotions) {
            promotionByName.put(promotion.getName(), promotion);
        }
        return Collections.unmodifiableMap(promotionByName);
    }
}
