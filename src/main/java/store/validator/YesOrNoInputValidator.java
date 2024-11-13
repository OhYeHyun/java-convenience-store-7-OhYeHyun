package store.validator;

import java.util.Objects;

public class YesOrNoInputValidator {
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final int ANSWER_LENGTH = 1;

    public boolean validateInput(String yesOrNo) {
        validateFormat(yesOrNo);
        validateIsYesOrNo(yesOrNo);

        return parseYesOrNo(yesOrNo);
    }

    private void validateFormat(String yesOrNo) {
        if (yesOrNo.length() != ANSWER_LENGTH) {
            throw new IllegalArgumentException(StoreErrorMessages.MUST_BE_ONE_LETTER_ANSWER.getMessage());
        }
    }

    private void validateIsYesOrNo(String yesOrNo) {
        if (!Objects.equals(yesOrNo, YES) && !Objects.equals(yesOrNo, NO)) {
            throw new IllegalArgumentException(StoreErrorMessages.MUST_BE_ONE_LETTER_ANSWER.getMessage());
        }
    }

    private boolean parseYesOrNo(String yesOrNo) {
        return Objects.equals(yesOrNo, YES);
    }
}
