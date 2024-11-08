package store.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.manager.PromotionManager;
import store.parser.ProductStatusParser;
import store.parser.PromotionParser;
import store.utility.CSVReader;

class PurchaseListTest {
    private ProductStatusParser productStatusParser;

    @BeforeEach
    void setUp() {
        CSVReader reader1 = new CSVReader("src/main/resources/promotions.md");
        PromotionParser promotionParser = new PromotionParser(reader1.getLists());
        promotionParser.parsePromotions();
        PromotionManager.getInstance().makePromotionByName(promotionParser.getPromotions());

        CSVReader reader2 = new CSVReader("src/main/resources/products.md");
        productStatusParser = new ProductStatusParser(reader2.getLists());
        productStatusParser.parseProductStatus();
    }

    @Test
    @DisplayName("구매한 상품이 purchaseProduct 객체로 잘 생성되는지 확인")
    void purchaseProduct_객체_생성_테스트() {
        SaleList saleList = new SaleList(productStatusParser.getSaleList());

        saleList.purchase("콜라", 12);

        PurchaseList purchaseList = new PurchaseList();
        purchaseList.addProducts("콜라", saleList.getQuantityInfo());

        List<PurchaseProduct> result = purchaseList.getPurchaseList();

        assertThat(getQuantitySumByPromotion(result, true)).isEqualTo(9);
        assertThat(getQuantitySumByPromotion(result, false)).isEqualTo(3);
    }

    private int getQuantitySumByPromotion(List<PurchaseProduct> products, boolean isPromotion) {
        return products.stream()
                .filter(product -> product.getIsPromotion() == isPromotion)
                .mapToInt(PurchaseProduct::getQuantity)
                .sum();
    }
}