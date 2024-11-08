package store.model;

public class CalculatedProduct {
    private final String name;
    private final int quantity;
    private final int price;

    private CalculatedProduct(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static CalculatedProduct of(String name, int quantity, int price) {
        return new CalculatedProduct(name, quantity, price);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
