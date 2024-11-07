package store.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.model.Product;

public class ProductManager {
    private final List<Product> products;

    public ProductManager(List<Product> products) {
        this.products = products;
    }

    public Map<String, Product> makeProductByName() {
        Map<String, Product> productByName = new HashMap<>();
        for (Product product : products) {
            productByName.put(product.getName(), product);
        }
        return Collections.unmodifiableMap(productByName);
    }
}
