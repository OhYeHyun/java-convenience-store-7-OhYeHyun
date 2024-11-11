package store.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import store.manager.ProductManager;
import store.manager.PromotionManager;
import store.model.CalculatedProduct;
import store.model.ProductStatus;
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
        notifyPromotionQuantity(purchaseInfos);

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

    private void notifyPromotionQuantity(Map<String, Integer> purchaseInfos) {
        purchaseInfos.forEach((name, quantity) -> {
            ProductStatus promotionProduct = saleList.getPromotionProduct(name);
            if (promotionProduct != null) {
                processRegularPriceNotification(purchaseInfos, name, promotionProduct, quantity);
                processGiftsProductsNotification(purchaseInfos, name, promotionProduct, quantity);
            }
        });
    }

    private void processRegularPriceNotification(Map<String, Integer> purchaseInfos, String name, ProductStatus promotionProduct, int quantity) {
        if (saleList.checkRegularPrice(promotionProduct, quantity)) {
            int newQuantity = saleList.getQuantityNoPurchaseRegular(quantity);
            if (!requireRegularPrice(name, newQuantity)) {
                purchaseInfos.put(name, quantity - newQuantity);
            }
        }
    }

    private void processGiftsProductsNotification(Map<String, Integer> purchaseInfos, String name, ProductStatus promotionProduct, int quantity) {
        if (saleList.checkGiftsProduct(promotionProduct, quantity)) {
            int newQuantity = saleList.getQuantityAddedGiftsProducts(quantity);
            if (requireGiftsProducts(name, newQuantity)) {
                purchaseInfos.put(name, quantity + newQuantity);
            }
        }
    }

    private boolean requireRegularPrice(String name, int quantity) {
        StoreOutputView.printRegularPriceInstructions(name, quantity);
        return StoreInputView.getYesOrNo();
    }

    private boolean requireGiftsProducts(String name, int quantity) {
        StoreOutputView.printReceiveGiftsInstructions(name, quantity);
        return StoreInputView.getYesOrNo();
    }

    private boolean requireRepurchase() {
        StoreOutputView.printRepurchaseInstructions();
        return StoreInputView.getYesOrNo();
    }

    private boolean requireMembership() {
        StoreOutputView.printMembershipInstructions();
        return StoreInputView.getYesOrNo();
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
