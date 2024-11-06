package store.domain;

import store.model.Product;

public class ProductStatus {
    private final Product product;
    private final int stock;
    private final Promotion promotion;

    private ProductStatus(Product product, int stock, Promotion promotion) {
        this.product = product;
        this.stock = stock;
        this.promotion = promotion;
    }

    public static ProductStatus of(Product product, int stock, Promotion promotion) {
        return new ProductStatus(product, stock, promotion);
    }

    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
