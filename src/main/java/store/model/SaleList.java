package store.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import store.manager.ProductManager;

public class SaleList {
    private static final Map<String, Product> productByName = ProductManager.getInstance().getProductByName();
    private final List<ProductStatus> saleList;
    private final Map<String, Integer> quantityInfo = new LinkedHashMap<>();

    public SaleList(List<ProductStatus> saleList) {
        this.saleList = saleList;
    }

    public Map<String, Integer> purchase(String name, int quantity) {
        List<ProductStatus> productList = findProductList(name);

        quantityInfo.clear();
        updateProductList(productList, quantity);

        return new LinkedHashMap<>(quantityInfo);
    }

    private void updateProductList(List<ProductStatus> productList, int quantity) {
        for (ProductStatus product : productList) {
            int purchaseQuantity = calculatePurchaseQuantity(product, quantity);
            if (purchaseQuantity == 0) {
                break;
            }
            quantity = purchaseQuantity;
        }
    }

    private int calculatePurchaseQuantity(ProductStatus product, int purchaseQuantity) {
        int quantityToDeduct = Math.min(product.getQuantity(), purchaseQuantity);
        product.updateQuantity(quantityToDeduct);

        quantityInfo.put(product.getPromotionName(), quantityToDeduct);

        return purchaseQuantity - quantityToDeduct;
    }

    private List<ProductStatus> findProductList(String name) {
        return saleList.stream()
                .filter((list) -> Objects.equals(list.getProductName(), name))
                .sorted(sortProductList())
                .toList();
    }

    private static Comparator<ProductStatus> sortProductList() {
        return Comparator.comparing((ProductStatus product) -> {
            if (Objects.equals(product.isPromotion(), true)) {
                return 0;
            }
            return 1;
        });
    }

    public List<String[]> getDisplyedSaleList() {
        List<String[]> displayedSaleList = new ArrayList<>();
        for (ProductStatus product : saleList) {
            String productName = product.getProductName();
            String price = Integer.toString(productByName.get(productName).getPrice());
            String quantity = Integer.toString(product.getQuantity());

            String[] list = {productName, price, quantity, product.getPromotionName()};
            displayedSaleList.add(list);
        }
        return new ArrayList<>(displayedSaleList);
    }

    public List<ProductStatus> getSaleList() {
        return new ArrayList<>(saleList);
    }

    public boolean isValidateName(String name) {
        return productByName.containsKey(name);
    }

    public boolean isValidateQuantity(String name, int purchaseQuantity) {
        List<ProductStatus> productList = findProductList(name);

        int totalQuantity = productList.stream()
                .mapToInt(ProductStatus::getQuantity)
                .sum();

        return totalQuantity >= purchaseQuantity;
    }
}
