package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Map;
import store.model.SaleList;
import store.validator.InputValidator;

public class StoreInputView {

    public StoreInputView() {
    }

    private String getInput() {
        return Console.readLine();
    }

    public Map<String, Integer> getPurchaseInfos(SaleList saleList) {
        while (true) {
            InputValidator inputValidator = new InputValidator();
            String purchaseInfos = getInput();
            try {
                return inputValidator.validateInput(saleList, purchaseInfos);
            } catch (IllegalArgumentException e) {
                StoreOutputView.print(e.getMessage());
            }
        }
    }
}
