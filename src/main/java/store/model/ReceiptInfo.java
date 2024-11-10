package store.model;

public class ReceiptInfo {
    public enum Line {
        HEAD_LINE("==============W 편의점================"),
        GIFTS_LINE("=============증\t정==============="),
        FOOTER_LINE("===================================="),
        CATEGORY_LINE("상품명\t\t수량\t금액");

        private final String line;

        Line(String line) {
            this.line = line;
        }

        public String getLine() {
            return line;
        }
    }

    public enum Label {
        TOTAL_PRICE_LABEL("총구매액"),
        PROMOTION_DISCOUNT_LABEL("행사할인"),
        MEMBERSHIP_DISCOUNT_LABEL("멤버십할인"),
        FINAL_PRICE_LABEL("내실돈");

        private final String label;

        Label(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Format {
        CALCULATED_PRODUCTS_FORMAT("%s\t\t%d \t%,d"),
        GIFTS_PRODUCTS_FORMAT("%s\t\t%d"),
        TOTAL_PRICE_FORMAT("%s\t\t%d\t%,d"),
        DISCOUNT_FORMAT("%s\t\t\t-%,d"),
        FINAL_PRICE_FORMAT("%s\t\t\t %,d"),
        LINE("%s");

        private final String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}
