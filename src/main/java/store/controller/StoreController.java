package store.controller;

import java.util.List;
import java.util.Map;
import store.manager.ProductManager;
import store.manager.PromotionManager;
import store.model.SaleList;
import store.parser.ProductStatusParser;
import store.parser.PromotionParser;
import store.service.CounterService;
import store.service.ReceiptService;
import store.utility.CSVReader;
import store.view.StoreInputView;
import store.view.StoreOutputView;

public class StoreController {
    private static final String PRODUCTS_FILE = "src/main/resources/products.md";
    private static final String PROMOTION_FILE = "src/main/resources/promotions.md";

    private final StoreInputView storeInputView;

    private SaleList saleList;

    public StoreController(StoreInputView storeInputView) {
        this.storeInputView = storeInputView;
    }

    public void openStore() {
        readPromotionFile();
        readProductFile();

        boolean purchasing = true;
        while (purchasing) {
            salesStart();
            StoreOutputView.printRepurchaseInstructions();
            purchasing = storeInputView.getYesOrNo();
        }
    }

    public void salesStart() {
        StoreOutputView.printVisitText(loadSaleList());
        Map<String, Integer> purchaseInfos = storeInputView.getPurchaseInfos(saleList);

        CounterService counterService = new CounterService(saleList, purchaseInfos);
        counterService.counter();

        ReceiptService receiptService = new ReceiptService(counterService.getCalculatedProducts(), counterService.getGiftsProducts(), counterService.getMembershipPrice());
        receiptService.issueReceipt();
    }

    private void readPromotionFile() {
        CSVReader reader = new CSVReader(PROMOTION_FILE);
        PromotionParser promotionParser = new PromotionParser(reader.getLists());
        promotionParser.parsePromotions();

        PromotionManager.getInstance().makePromotionByName(promotionParser.getPromotions());
    }

    private void readProductFile() {
        CSVReader reader = new CSVReader(PRODUCTS_FILE);
        ProductStatusParser productStatusParser = new ProductStatusParser(reader.getLists());
        productStatusParser.parseProductStatus();

        saleList = new SaleList(productStatusParser.getSaleList());

        ProductManager.getInstance().makeProductByName(productStatusParser.getProducts());
    }

    private List<String[]> loadSaleList() {
        return saleList.getDisplyedSaleList();
    }
}
