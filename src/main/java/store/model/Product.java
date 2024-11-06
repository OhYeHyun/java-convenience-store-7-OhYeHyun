package store.model;

public class Product {
    private final String name;
    private final int price;

    private Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Product of(String name, int price) {
        return new Product(name, price);
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
