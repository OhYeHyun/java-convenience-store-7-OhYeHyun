package store.controller;

import java.util.List;
import java.util.Map;
import store.manager.ProductManager;
import store.manager.PromotionManager;
import store.model.CalculatedProduct;
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
            purchasing = requireRepurchase();
        }
    }

    public void salesStart() {
        StoreOutputView.printVisitText(loadSaleList());
        Map<String, Integer> purchaseInfos = storeInputView.getPurchaseInfos(saleList);

        CounterService counter = new CounterService(saleList, purchaseInfos);
        counter.counter();

        ReceiptService receipt = applyMembership(counter.getCalculatedProducts(), counter.getGiftsProducts(), counter.getMembershipPrice());
        receipt.issueReceipt();
    }

    private ReceiptService applyMembership(List<CalculatedProduct> calculatedProducts, List<CalculatedProduct> giftsProducts, int membershipPrice) {
        if (requireMembership()) {
            return new ReceiptService(calculatedProducts, giftsProducts, membershipPrice);
        }
        return new ReceiptService(calculatedProducts, giftsProducts, 0);
    }

    private boolean requireRepurchase() {
        StoreOutputView.printRepurchaseInstructions();
        return storeInputView.getYesOrNo();
    }

    private boolean requireMembership() {
        StoreOutputView.printMembershipInstructions();
        return storeInputView.getYesOrNo();
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
