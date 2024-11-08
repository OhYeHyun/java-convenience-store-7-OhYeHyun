package store.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.model.Product;

public class ProductManager {
    private static ProductManager instance;
    private final Map<String, Product> productByName = new HashMap<>();

    private ProductManager() {}

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public void makeProductByName(List<Product> products) {
        for (Product product : products) {
            productByName.put(product.getName(), product);
        }
    }

    public Map<String, Product> getProductByName() {
        return Collections.unmodifiableMap(productByName);
    }
}
