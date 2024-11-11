package store.model;

public class ReceiptInfo {
    public enum Line {
        HEAD_LINE("==============W 편의점================"),
        GIFTS_LINE("=============증\t정==============="),
        FOOTER_LINE("====================================");

        private final String line;

        Line(String line) {
            this.line = line;
        }

        public String getLine() {
            return line;
        }
    }

    public enum Label {
        PRODUCT_NAME("상품명"),
        PRODUCT_QUANTITY("수량"),
        PRODUCT_PRICE("수량"),

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
        CATEGORY_FORMAT("%-10s\t%-6s\t%s"),

        CALCULATED_PRODUCTS_FORMAT("%s\t%-6d\t%-,6d"),
        GIFTS_PRODUCTS_FORMAT("%s\t%-6d"),

        TOTAL_PRICE_FORMAT("%-10s\t%-4d\t%,6d"),
        DISCOUNT_FORMAT("%-10s\t\t\t-%,-6d"),
        FINAL_PRICE_FORMAT("%-10s\t\t\t%,6d"),
        LINE("%s");

        private final String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public enum Convertor {
        KOREAN_UNICODE_START(0xAC00),
        KOREAN_UNICODE_END(0xD7A3),
        KOREAN_WIDTH(2),
        NORMAL_WIDTH(1),
        TARGET_WIDTH(14);

        private final int value;

        Convertor(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
