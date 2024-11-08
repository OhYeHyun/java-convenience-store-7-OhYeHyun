package store.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.model.Promotion;

public class PromotionManager {
    private final List<Promotion> promotions;
    private final Map<String, Promotion> promotionByName = new HashMap<>();

    public PromotionManager(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void makePromotionByName() {
        for (Promotion promotion : promotions) {
            promotionByName.put(promotion.getName(), promotion);
        }
    }

    public Map<String, Promotion> getPromotionByName() {
        return Collections.unmodifiableMap(promotionByName);
    }
}
