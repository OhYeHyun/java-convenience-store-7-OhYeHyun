package store.validator;

public enum StoreErrorMessages {
    ERROR_MESSAGE_BEGINNING("[ERROR] "),
    ENTER_AGAIN(" 다시 입력해 주세요."),

    MUST_ALLOW_FORMAT("올바르지 않은 형식으로 입력했습니다."),
    MUST_BE_NAME_EXISTING("존재하지 않는 상품입니다."),
    MUST_BE_LESS_THAN_INVENTORY("재고 수량을 초과하여 구매할 수 없습니다."),

    MUST_BE_ONE_LETTER_ANSWER("잘못된 입력입니다.");

    private final String message;

    StoreErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_MESSAGE_BEGINNING.message + message + ENTER_AGAIN.message;
    }
}
