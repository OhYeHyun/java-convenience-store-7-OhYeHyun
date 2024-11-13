package store.model;

public class PurchaseProduct {
    private final String name;
    private final int quantity;
    private final boolean isPromotion;

    private PurchaseProduct(String name, int quantity, boolean isPromotion) {
        this.name = name;
        this.quantity = quantity;
        this.isPromotion = isPromotion;
    }

    public static PurchaseProduct of(String name, int quantity, boolean isPromotion) {
        return new PurchaseProduct(name, quantity, isPromotion);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getIsPromotion() {
        return isPromotion;
    }
}
