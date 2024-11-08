package store.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaleList {
    private final List<ProductStatus> saleList;
    private final Map<String, Integer> quantityInfo = new LinkedHashMap<>();

    public SaleList(List<ProductStatus> saleList) {
        this.saleList = saleList;
    }

    public Map<String, Integer> purchase(String name, int quantity) {
        List<ProductStatus> productList = findProductList(name);
        validateQuantity(productList, quantity);

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

    private static void validateQuantity(List<ProductStatus> productList, int purchaseQuantity) {
        int totalQuantity = productList.stream()
                .mapToInt(ProductStatus::getQuantity)
                .sum();

        if (totalQuantity < purchaseQuantity) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
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

    public List<ProductStatus> getSaleList() {
        return new ArrayList<>(saleList);
    }
}
