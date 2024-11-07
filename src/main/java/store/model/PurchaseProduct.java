package store.model;

public class PurchaseProduct {
    private final String name;
    private final int quantity;
    private boolean hasPromotionMark;

    private PurchaseProduct(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.hasPromotionMark = false;
    }

    public static PurchaseProduct of(String name, int quantity) {
        return new PurchaseProduct(name, quantity);
    }

    public void changePromotionMark() {
        hasPromotionMark = true;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getHasPromotionMark() {
        return hasPromotionMark;
    }
}
