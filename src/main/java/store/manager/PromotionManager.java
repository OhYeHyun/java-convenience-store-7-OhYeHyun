package store.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.model.Promotion;

public class PromotionManager {
    private static PromotionManager instance;
    private final Map<String, Promotion> promotionByName = new HashMap<>();

    private PromotionManager() {}

    public static PromotionManager getInstance() {
        if (instance == null) {
            instance = new PromotionManager();
        }
        return instance;
    }

    public void makePromotionByName(List<Promotion> promotions) {
        for (Promotion promotion : promotions) {
            promotionByName.put(promotion.getName(), promotion);
        }
    }

    public Map<String, Promotion> getPromotionByName() {
        return Collections.unmodifiableMap(promotionByName);
    }
}
