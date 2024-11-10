package store.view;

import java.util.List;
import java.util.Objects;
import store.model.OutputInfo.Instructions;
import store.model.OutputInfo.SaleList;
import store.model.OutputInfo.Text;

public class StoreOutputView {

    public static void print(String text) {
        System.out.println(text);
    }

    public static void printLineSpace() {
        System.out.print(System.lineSeparator());
    }

    public static void printVisitText(List<String[]> saleList) {
        promptWelcomeText();
        promptListText();
        promptSaleList(saleList);
        promptInputText();
    }

    private static void promptWelcomeText() {
        print(Text.WELCOME_TEXT.getText());
    }

    private static void promptListText() {
        print(Text.lIST_TEXT.getText());
    }

    private static void promptSaleList(List<String[]> saleList) {
        printLineSpace();

        for (String[] product : saleList) {
            parseSaleList(product);
        }
    }

    private static void parseSaleList(String[] product) {
        String productName = product[0];
        int price = Integer.parseInt(product[1]);
        int quantity = Integer.parseInt(product[2]);
        String promotionName = product[3];

        printProduct(productName, price, quantity, promotionName);
    }

    private static void printProduct(String productName, int price, int quantity, String promotionName) {
        if (Objects.equals(promotionName, "null")) {
            print(SaleList.SALE_LIST_NO_PROMOTION.getSaleList(productName, price, quantity));
        }
        if (!Objects.equals(promotionName, "null")) {
            print(SaleList.SALE_LIST_HAS_PROMOTION.getSaleList(productName, price, quantity, promotionName));
        }
    }

    private static void promptInputText() {
        printLineSpace();
        print(Text.INPUT_TEXT.getText());
    }

    public static void printReceiveGiftsInstructions(String[] product) {
        String name = product[0];
        int quantity = Integer.parseInt(product[1]);

        printLineSpace();
        print(Instructions.RECEIVE_GIFTS_INSTRUCTIONS.getInstructions(name, quantity));
    }

    public static void printRegularPriceInstructions(String[] product) {
        String name = product[0];
        int quantity = Integer.parseInt(product[1]);

        printLineSpace();
        print(Instructions.REPURCHASE_INSTRUCTIONS.getInstructions(name, quantity));
    }

    public static void printMembershipInstructions() {
        printLineSpace();
        print(Instructions.MEMBERSHIP_INSTRUCTIONS.getInstructions());
    }

    public static void printRepurchaseInstructions() {
        printLineSpace();
        print(Instructions.REPURCHASE_INSTRUCTIONS.getInstructions());
    }
}