package store.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.model.Product;

public class ProductManager {
    private final List<Product> products;
    private final Map<String, Product> productByName = new HashMap<>();

    public ProductManager(List<Product> products) {
        this.products = products;
    }

    public void makeProductByName() {
        for (Product product : products) {
            productByName.put(product.getName(), product);
        }
    }

    public Map<String, Product> getProductByName() {
        return Collections.unmodifiableMap(productByName);
    }
}
