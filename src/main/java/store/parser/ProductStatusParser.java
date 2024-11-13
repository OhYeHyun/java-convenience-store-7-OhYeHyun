package store.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import store.manager.PromotionManager;
import store.model.ProductStatus;
import store.model.Product;
import store.model.Promotion;

public class ProductStatusParser {
    public static final Map<String, Promotion> promotionsByName = PromotionManager.getInstance().getPromotionByName();
    private final List<String[]> rawProductStatus;
    private List<ProductStatus> saleList = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

    public ProductStatusParser(List<String[]> rawProductStatus) {
        this.rawProductStatus = rawProductStatus;
    }

    public void parseProductStatus() {
        for (String[] attributes : rawProductStatus) {
            parseProduct(attributes[0], toInt(attributes[1]));
            ProductStatus productStatus = createProductStatus(attributes);

            handleProductStatus(productStatus);

            addRegularProductIfExist(productStatus);
        }
    }

    private void handleProductStatus(ProductStatus productStatus) {
        boolean isExist = isExistProduct(productStatus);
        if (isExist) {
            updateQuantity(productStatus);
        }
        if (!isExist) {
            saleList.add(productStatus);
        }
    }

    private void addRegularProductIfExist(ProductStatus productStatus) {
        ProductStatus normalProduct = ProductStatus.of(productStatus.getProductName(), 0, "null");
        if (!Objects.equals(productStatus.getPromotionName(), "null") && !isExistProduct(normalProduct)) {
            saleList.add(normalProduct);
        }
    }

    private void updateQuantity(ProductStatus productStatus) {
        saleList = saleList.stream()
                .map(product -> {
                    if (isSameProduct(product, productStatus)) {
                        product.setQuantity(product.getQuantity() + productStatus.getQuantity());
                        return product;
                    }
                    return product;
                })
                .collect(Collectors.toList());
    }

    private boolean isSameProduct(ProductStatus product, ProductStatus productStatus) {
        return Objects.equals(product.getProductName(), productStatus.getProductName()) &&
                Objects.equals(product.getPromotionName(), productStatus.getPromotionName());
    }

    private boolean isExistProduct(ProductStatus productStatus) {
        return saleList.stream()
                .anyMatch(product -> isSameProduct(product, productStatus));
    }

    private boolean checkPromotionPeriod(String promotionName) {
        if (!Objects.equals(promotionName, "null")) {
            Promotion promotion = promotionsByName.get(promotionName);

            return promotion.getDateRange().isBetween();
        }
        return true;
    }

    private ProductStatus createProductStatus(String[] attributes) {
        if (checkPromotionPeriod(attributes[3])) {
            return ProductStatus.of(attributes[0], toInt(attributes[2]), attributes[3]);
        }
        return ProductStatus.of(attributes[0], toInt(attributes[2]), "null");
    }

    private void parseProduct(String name, int price) {
        products.add(Product.of(name, price));
    }

    private static int toInt(String attribute) {
        return Integer.parseInt(attribute);
    }

    public List<ProductStatus> getSaleList() {
        return new ArrayList<>(saleList);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
