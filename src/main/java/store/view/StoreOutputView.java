package store.view;

import java.util.List;
import java.util.Objects;
import store.model.OutputInfo.Instructions;
import store.model.OutputInfo.SaleList;
import store.model.OutputInfo.Text;

public class StoreOutputView {
    private final List<String[]> saleList;

    public StoreOutputView(List<String[]> saleList) {
        this.saleList = saleList;
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public void printVisitText() {
        promptWelcomeText();
        promptListText();
        promptSaleList();
        promptInputText();
    }

    private void promptWelcomeText() {
        print(Text.WELCOME_TEXT.getText());
    }

    private void promptListText() {
        print(Text.lIST_TEXT.getText());
    }

    private void promptSaleList() {
        print(System.lineSeparator());
        for (String[] product : saleList) {
            printProduct(product);
        }
    }

    private void printProduct(String[] product) {
        String productName = product[0];
        int price = Integer.parseInt(product[1]);
        int quantity = Integer.parseInt(product[2]);
        String promotionName = product[3];

        if (Objects.equals(promotionName, "null")) {
            print(SaleList.SALE_LIST_NO_PROMOTION.getSaleList(productName, price, quantity));
        }
        print(SaleList.SALE_LIST_HAS_PROMOTION.getSaleList(productName, price, quantity, promotionName));
    }

    private void promptInputText() {
        print(System.lineSeparator());
        print(Text.INPUT_TEXT.getText());
    }

    public void printReceiveGiftsInstructions(String[] product) {
        String name = product[0];
        int quantity = Integer.parseInt(product[1]);

        print(System.lineSeparator());
        print(Instructions.RECEIVE_GIFTS_INSTRUCTIONS.getInstructions(name, quantity));
    }

    public void printRegularPriceInstructions(String[] product) {
        String name = product[0];
        int quantity = Integer.parseInt(product[1]);

        print(System.lineSeparator());
        print(Instructions.REPURCHASE_INSTRUCTIONS.getInstructions(name, quantity));
    }

    public void printMembershipInstructions() {
        print(System.lineSeparator());
        print(Instructions.MEMBERSHIP_INSTRUCTIONS.getInstructions());
    }

    public void printRepurchaseInstructions() {
        print(System.lineSeparator());
        print(Instructions.REPURCHASE_INSTRUCTIONS.getInstructions());
    }
}