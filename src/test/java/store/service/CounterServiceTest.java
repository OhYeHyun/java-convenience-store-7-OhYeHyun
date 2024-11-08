package store.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.manager.ProductManager;
import store.manager.PromotionManager;
import store.model.CalculatedProduct;
import store.model.SaleList;
import store.parser.ProductStatusParser;
import store.parser.PromotionParser;
import store.utility.CSVReader;

class CounterServiceTest {
    private SaleList saleList;

    @BeforeEach
    void setUp() {
        CSVReader reader1 = new CSVReader("src/main/resources/promotions.md");
        PromotionParser promotionParser = new PromotionParser(reader1.getLists());
        promotionParser.parsePromotions();
        PromotionManager.getInstance().makePromotionByName(promotionParser.getPromotions());

        CSVReader reader2 = new CSVReader("src/main/resources/products.md");
        ProductStatusParser productStatusParser = new ProductStatusParser(reader2.getLists());
        productStatusParser.parseProductStatus();
        saleList = new SaleList(productStatusParser.getSaleList());
        ProductManager.getInstance().makeProductByName(productStatusParser.getProducts());
    }

    @Test
    @DisplayName("구매한 상품과 수량으로 올바른 전체 금액이 나왔는지 확인")
    void 구매한_상품의_전체_금액_테스트() {
        Map<String, Integer> totalPurchaseList = new LinkedHashMap<>();
        totalPurchaseList.put("콜라", 12);
        totalPurchaseList.put("에너지바", 4);

        CounterService counter = new CounterService(saleList, totalPurchaseList);
        counter.counter();

        List<CalculatedProduct> result = counter.getCalculatedProducts();

        assertThat(result.get(0).getName()).isEqualTo("콜라");
        assertThat(result.get(0).getPrice()).isEqualTo(12000);
        assertThat(result.get(1).getName()).isEqualTo("에너지바");
        assertThat(result.get(1).getPrice()).isEqualTo(8000);
    }


    @Test
    @DisplayName("구매한 상품과 수량으로 올바른 증정 금액이 나왔는지 확인")
    void 구매한_상품의_증정_금액_테스트() {
        Map<String, Integer> totalPurchaseList = new LinkedHashMap<>();
        totalPurchaseList.put("콜라", 12);
        totalPurchaseList.put("에너지바", 4);

        CounterService counter = new CounterService(saleList, totalPurchaseList);
        counter.counter();

        List<CalculatedProduct> result = counter.getGiftsProducts();

        assertThat(result.get(0).getName()).isEqualTo("콜라");
        assertThat(result.get(0).getPrice()).isEqualTo(3000);
    }
}