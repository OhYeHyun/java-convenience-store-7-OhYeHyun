package store.model;

import java.util.Objects;

public class ProductStatus {
    private final String productName;
    private final int quantity;
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

    public String getPromotionName() {
        return promotionName;
    }
}
