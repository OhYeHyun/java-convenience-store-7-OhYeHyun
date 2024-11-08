package store.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import store.manager.PromotionManager;

public class PurchaseList {
    private static final Map<String, Promotion> PROMOTION_BY_NAME = PromotionManager.getInstance().getPromotionByName();
    private final List<PurchaseProduct> purchaseList = new ArrayList<>();

    public void addProducts(String productName, Map<String, Integer> quantityInfos) {
        quantityInfos.forEach((promotionName, quantity) -> {
            int currentQuantity = quantity;
            if (isPromotion(promotionName)) {
                int unavailableCount = calculateUnavailablePromotionCount(promotionName, currentQuantity);
                productAddToList(productName, unavailableCount, false);
                currentQuantity -= unavailableCount;
            }
            productAddToList(productName, currentQuantity, isPromotion(promotionName));
        });
    }

    private boolean isPromotion(String promotionName) {
        return !Objects.equals(promotionName, "null");
    }

    private int calculateUnavailablePromotionCount(String promotionName, int quantity) {
        Promotion promotion = PROMOTION_BY_NAME.get(promotionName);
        int threshold = promotion.getPurchaseThreshold();

        return quantity % threshold;
    }

    private void productAddToList(String productName, int quantity, boolean isPromotion) {
        PurchaseProduct product = PurchaseProduct.of(productName, quantity, isPromotion);
        purchaseList.add(product);
    }

    public List<PurchaseProduct> getPurchaseList() {
        return new ArrayList<>(purchaseList);
    }
}
