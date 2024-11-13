package store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import store.manager.ProductManager;
import store.model.CalculatedProduct;
import store.model.Product;
import store.model.PurchaseList;
import store.model.PurchaseProduct;
import store.model.SaleList;

public class CounterService {
    private final Map<String, Product> PRODUCT_BY_NAME = ProductManager.getInstance().getProductByName();
    private final SaleList saleList;
    private final Map<String, Integer> totalPurchaseList;
    private final PurchaseList purchaseList = new PurchaseList();

    private final List<CalculatedProduct> calculatedProducts = new ArrayList<>();
    private final List<CalculatedProduct> giftsProducts = new ArrayList<>();
    private int regularPrice = 0;


    public CounterService(SaleList saleList, Map<String, Integer> totalPurchaseList) {
        this.saleList = saleList;
        this.totalPurchaseList = totalPurchaseList;
    }

    public void counter() {
        totalPurchaseList.forEach((name, quantity) -> {
            Map<String, Integer> quantityInfo = saleList.purchase(name, quantity);

            purchaseList.addProducts(name, quantityInfo);

            List<PurchaseProduct> products = purchaseList.getPurchaseList();
            processProductCalculations(name, products);
        });
    }

    private void processProductCalculations(String name, List<PurchaseProduct> products) {
        int price = PRODUCT_BY_NAME.get(name).getPrice();

        int regularQuantity = calculateQuantity(products, false);
        int promotionQuantity = calculateQuantity(products, true);

        addCalculatedProduct(name, price, regularQuantity, promotionQuantity);
        addGiftsProduct(name, price);
    }

    private void addCalculatedProduct(String name, int price, int regularQuantity, int promotionQuantity) {
        int totalQuantity = regularQuantity + promotionQuantity;
        regularPrice += regularQuantity * price;

        CalculatedProduct product = CalculatedProduct.of(name, totalQuantity, totalQuantity * price);
        calculatedProducts.add(product);
    }

    private void addGiftsProduct(String name, int price) {
        int giftsQuantity = purchaseList.getGiftsQuantity();

        if (giftsQuantity > 0) {
            CalculatedProduct product = CalculatedProduct.of(name, giftsQuantity, giftsQuantity * price);
            giftsProducts.add(product);
        }
    }

    private int calculateQuantity(List<PurchaseProduct> products, boolean isPromotion) {
        return products.stream()
                .filter(product -> product.getIsPromotion() == isPromotion)
                .mapToInt(PurchaseProduct::getQuantity)
                .sum();
    }

    public List<CalculatedProduct> getCalculatedProducts() {
        return new ArrayList<>(calculatedProducts);
    }

    public List<CalculatedProduct> getGiftsProducts() {
        return new ArrayList<>(giftsProducts);
    }

    public int getMembershipPrice() {
        return MembershipService.applyMemberShip(regularPrice);
    }
}
