package store.service;

import java.util.List;
import store.model.CalculatedProduct;
import store.model.ReceiptInfo.Format;
import store.model.ReceiptInfo.Label;
import store.model.ReceiptInfo.Line;

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

    private void print(String format, Object... args) {
        String formatted = String.format(format, args);
        System.out.println(formatted);
    }

    private void printCalculatedProductsHistory() {
        headPrompt();
        categoryPrompt();

        calculatedProducts.forEach((product) -> {
            totalPrice += product.getPrice();
            totalQuantity += product.getQuantity();

            print(Format.CALCULATED_PRODUCTS_FORMAT.getFormat(), product.getName(), product.getQuantity(), product.getPrice());
        });
    }

    private void printGiftsProductsHistory() {
        giftsPrompt();

        giftsProducts.forEach((product) -> {
            totalGiftsPrice += product.getPrice();

            print(Format.GIFTS_PRODUCTS_FORMAT.getFormat(), product.getName(), product.getQuantity());
        });
    }

    private void printPriceInfo() {
        footerPrompt();

        giftsProducts.forEach((product) -> {
            printTotalPrice();
            printPromotionPrice();
            printMembershipPrice();
            printFinalPrice();
        });
    }

    private void printTotalPrice() {
        print(Format.TOTAL_PRICE_FORMAT.getFormat(), Label.TOTAL_PRICE_LABEL.getLabel(), totalQuantity, totalPrice);
    }

    private void printPromotionPrice() {
        print(Format.DISCOUNT_FORMAT.getFormat(), Label.PROMOTION_DISCOUNT_LABEL.getLabel(), totalGiftsPrice);
    }

    private void printMembershipPrice() {
        print(Format.DISCOUNT_FORMAT.getFormat(), Label.MEMBERSHIP_DISCOUNT_LABEL.getLabel(), membershipPrice);
    }

    private void printFinalPrice() {
        print(Format.FINAL_PRICE_FORMAT.getFormat(), Label.FINAL_PRICE_LABEL.getLabel(), calculateFinalPrice());
    }

    private void headPrompt() {
        print(Format.LINE.getFormat(), Line.HEAD_LINE.getLine());
    }

    private void categoryPrompt() {
        print(Format.LINE.getFormat(), Line.CATEGORY_LINE.getLine());
    }

    private void giftsPrompt() {
        print(Format.LINE.getFormat(), Line.GIFTS_LINE.getLine());
    }

    private void footerPrompt() {
        print(Format.LINE.getFormat(), Line.FOOTER_LINE.getLine());
    }

    private int calculateDiscountPrice(int price) {
        return price * -1;
    }

    private int calculateFinalPrice() {
        return totalPrice + calculateDiscountPrice(totalGiftsPrice) + calculateDiscountPrice(membershipPrice);
    }
}
