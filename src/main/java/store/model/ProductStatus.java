package store.model;

import java.util.Objects;

public class ProductStatus {
    private final String productName;
    private int quantity;
    private final String promotionName;

    private ProductStatus(String productName, int quantity, String promotionName) {
        this.productName = productName;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public static ProductStatus of(String productName, int quantity, String promotionName) {
        return new ProductStatus(productName, quantity, promotionName);
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public boolean isPromotion() {
        return !Objects.equals(promotionName, "null");
    }

    public void updateQuantity(int quantityToDeduct) {
        quantity -= quantityToDeduct;
    }
}
