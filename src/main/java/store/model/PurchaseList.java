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
                processPromotion(promotionName, productName, quantity);
            }

            if (!isPromotion(promotionName)) {
                addRegularProduct(productName, quantity);
            }
        });
    }

    private void processPromotion(String promotionName, String productName, int quantity) {
        setCurPromotion(promotionName);

        int unavailableQuantity = calculateUnavailableQuantity(quantity);
        int promotionQuantity = quantity - unavailableQuantity;

        addRegularProduct(productName, unavailableQuantity);
        addPromotionProduct(productName, promotionQuantity);
    }

    private void setCurPromotion(String promotionName) {
        Map<String, Promotion> PROMOTION_BY_NAME = PromotionManager.getInstance().getPromotionByName();
        curPromotion = PROMOTION_BY_NAME.get(promotionName);
    }

    private boolean isPromotion(String promotionName) {
        return !Objects.equals(promotionName, "null");
    }

    private void addRegularProduct(String productName, int quantity) {
        PurchaseProduct product = PurchaseProduct.of(productName, quantity, false);
        purchaseList.add(product);
    }

    private void addPromotionProduct(String productName, int quantity) {
        PurchaseProduct product = PurchaseProduct.of(productName, quantity, true);
        purchaseList.add(product);
    }

    private int calculateUnavailableQuantity(int quantity) {
        int threshold = curPromotion.getPurchaseThreshold();
        return quantity % threshold;
    }

    public List<PurchaseProduct> getPurchaseList() {
        return new ArrayList<>(purchaseList);
    }
}
