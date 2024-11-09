package store.service;

import java.util.List;
import store.model.CalculatedProduct;

public class ReceiptService {
    private final List<CalculatedProduct> calculatedProducts;
    private final List<CalculatedProduct> giftsProducts;
    private final int membershipPrice;
    private int totalPrice = 0;
    private int totalQuantity = 0;
    private int totalGiftsPrice = 0;

    public ReceiptService(List<CalculatedProduct> calculatedProducts, List<CalculatedProduct> giftsProducts, int membershipPrice) {
        this.calculatedProducts = calculatedProducts;
        this.giftsProducts = giftsProducts;
        this.membershipPrice = membershipPrice;
    }

    public void issueReceipt() {
        printCalculatedProductsHistory();
        printGiftsProductsHistory();
        printPriceInfo();
    }

    private void printCalculatedProductsHistory() {
        topPrompt();
        calculatedProducts.forEach((product) -> {
            totalPrice += product.getPrice();
            totalQuantity += product.getQuantity();

            String formattedOutput = String.format("%s\t\t%d \t%,d"
                    , product.getName(), product.getQuantity(), product.getPrice());
            System.out.println(formattedOutput);
        });
    }

    private void printGiftsProductsHistory() {
        giftsPrompt();
        giftsProducts.forEach((product) -> {
            totalGiftsPrice += product.getPrice();

            String formattedOutput = String.format("%s\t\t%d"
                    , product.getName(), product.getQuantity());
            System.out.println(formattedOutput);
        });
    }

    private void printPriceInfo() {
        bottomPrompt();
        giftsProducts.forEach((product) -> {
            printTotalPrice();
            printPromotionPrice();
            printMembershipPrice();
            printPriceToPay();
        });
    }

    private void printTotalPrice() {
        String formattedOutput = String.format("%s\t\t%d\t%,d"
                , "총구매액", totalQuantity, totalPrice);
        System.out.println(formattedOutput);
    }

    private void printPromotionPrice() {
        String formattedOutput = String.format("%s\t\t\t%,d"
                , "행사할인", totalGiftsPrice * -1);
        System.out.println(formattedOutput);
    }

    private void printMembershipPrice() {
        String formattedOutput = String.format("%s\t\t\t%,d"
                , "멤버십할인", membershipPrice * -1);
        System.out.println(formattedOutput);
    }

    private void printPriceToPay() {
        String formattedOutput = String.format("%s\t\t\t %,d"
                , "내실돈", calculatePriceToPay());
        System.out.println(formattedOutput);
    }

    private int calculatePriceToPay() {
        return totalPrice + (totalGiftsPrice * -1) + (membershipPrice * -1);
    }

    private void topPrompt() {
        System.out.println("==============W 편의점================");
    }

    private void giftsPrompt() {
        System.out.println("=============증\t정===============");
    }

    private void bottomPrompt() {
        System.out.println("====================================");
    }
}
