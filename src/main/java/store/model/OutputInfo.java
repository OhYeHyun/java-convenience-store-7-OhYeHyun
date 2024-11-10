package store.model;

public class OutputInfo {
    public enum Text {
        WELCOME_TEXT("안녕하세요. W편의점입니다."),
        lIST_TEXT("현재 보유하고 있는 상품입니다."),
        INPUT_TEXT("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");

        private final String text;

        Text(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public enum SaleList {
        NO_QUANTITY(" 재고 없음"),

        SALE_LIST_HAS_PROMOTION("- %s %,d원 %d개 %s"),
        SALE_LIST_NO_PROMOTION("- %s %,d원 %d개"),
        SALE_LIST_NO_QUANTITY("- %s %,d원");

        private final String saleList;

        SaleList(String saleList) {
            this.saleList = saleList;
        }

        public String getSaleList(String productName, int price) {
            return String.format(saleList + NO_QUANTITY, productName, price);
        }

        public String getSaleList(String productName, int price, int quantity) {
            return String.format(saleList, productName, price, quantity);
        }

        public String getSaleList(String productName, int price, int quantity, String promotionName) {
            return String.format(saleList, productName, price, quantity, promotionName);
        }
    }

    public enum Instructions {
        YES_OR_NO(" (Y/N)"),

        RECEIVE_GIFTS_INSTRUCTIONS("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까?"),
        REGULAR_PRICE_INSTRUCTIONS("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까?"),
        MEMBERSHIP_INSTRUCTIONS("멤버십 할인을 받으시겠습니까?"),
        REPURCHASE_INSTRUCTIONS("감사합니다. 구매하고 싶은 다른 상품이 있나요?");

        private final String instructions;

        Instructions(String instructions) {
            this.instructions = instructions;
        }

        public String getInstructions() {
            return String.format(instructions + YES_OR_NO.instructions);
        }

        public String getInstructions(String productName, int quantity) {
            return String.format(instructions + YES_OR_NO.instructions, productName, quantity);
        }
    }
}
