package store.model;

public class ProductStatus {
    private final String productName;
    private final int stock;
    private final String promotionName;

    private ProductStatus(String productName, int stock, String promotionName) {
        this.productName = productName;
        this.stock = stock;
        this.promotionName = promotionName;
    }

    public static ProductStatus of(String productName, int stock, String promotionName) {
        return new ProductStatus(productName, stock, promotionName);
    }

    public String getProductName() {
        return productName;
    }

    public int getStock() {
        return stock;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
