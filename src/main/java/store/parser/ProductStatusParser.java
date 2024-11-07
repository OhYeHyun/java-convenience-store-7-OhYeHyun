package store.parser;

import java.util.ArrayList;
import java.util.List;
import store.domain.ProductStatus;
import store.model.Product;

public class ProductStatusParser {
    private final List<String[]> rawProductStatus;
    private final List<ProductStatus> productStatus = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

    public ProductStatusParser(List<String[]> rawProductStatus) {
        this.rawProductStatus = rawProductStatus;
    }

    public void parseProductStatus() {
        for (String[] attributes : rawProductStatus) {
            parseProduct(attributes[0], toInt(attributes[1]));
            ProductStatus newProductStatus = ProductStatus.of(attributes[0], toInt(attributes[2]), attributes[3]);

            productStatus.add(newProductStatus);
        }
    }

    private void parseProduct(String name, int price) {
        products.add(Product.of(name, price));
    }

    private static int toInt(String attribute) {
        return Integer.parseInt(attribute);
    }

    public List<ProductStatus> getProductStatus() {
        return new ArrayList<>(productStatus);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
