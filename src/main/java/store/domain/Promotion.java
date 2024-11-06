package store.domain;

public class Promotion {
    private final String name;
    private final int buyProduct;
    private final int getProduct;
    private final DateRange dateRange;

    private Promotion(String name, int buyProduct, int getProduct, DateRange dateRange) {
        this.name = name;
        this.buyProduct = buyProduct;
        this.getProduct = getProduct;
        this.dateRange = dateRange;
    }

    public static Promotion of(String name, int buyProduct, int getProduct, DateRange dateRange) {
        return new Promotion(name, buyProduct, getProduct, dateRange);
    }

    public int getPurchaseThreshold() {
        return buyProduct + getProduct;
    }

    public DateRange getDateRange() {
        return dateRange;
    }
}
