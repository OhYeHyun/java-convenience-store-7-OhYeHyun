package store.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import store.manager.PromotionManager;

public class PurchaseList {
    private final List<PurchaseProduct> purchaseList = new ArrayList<>();
    private Promotion curPromotion;

    public void addProducts(String productName, Map<String, Integer> quantityInfos) {
        quantityInfos.forEach((promotionName, quantity) -> {
            if (isPromotion(promotionName)) {
                setCurPromotion(promotionName);

                int unavailableqQuantity = calculateUnavailableQuantity(quantity);
                productAddToList(productName, unavailableqQuantity, false);
                quantity -= unavailableqQuantity;
            }
            productAddToList(productName, quantity, isPromotion(promotionName));
        });
    }

    private void setCurPromotion(String promotionName) {
        Map<String, Promotion> PROMOTION_BY_NAME = PromotionManager.getInstance().getPromotionByName();
        curPromotion = PROMOTION_BY_NAME.get(promotionName);
    }

    private boolean isPromotion(String promotionName) {
        return !Objects.equals(promotionName, "null");
    }

    private int calculateUnavailableQuantity(int quantity) {
        int threshold = curPromotion.getPurchaseThreshold();
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
