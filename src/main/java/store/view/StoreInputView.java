package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Map;
import store.model.SaleList;
import store.validator.PurchaseInfosInputValidator;
import store.validator.YesOrNoInputValidator;

public class StoreInputView {

    public StoreInputView() {
    }

    private String getInput() {
        return Console.readLine();
    }

    public Map<String, Integer> getPurchaseInfos(SaleList saleList) {
        while (true) {
            PurchaseInfosInputValidator validator = new PurchaseInfosInputValidator();
            String purchaseInfos = getInput();
            try {
                return validator.validateInput(saleList, purchaseInfos);
            } catch (IllegalArgumentException e) {
                StoreOutputView.print(e.getMessage());
            }
        }
    }

    public boolean getYesOrNo() {
        while (true) {
            YesOrNoInputValidator validator = new YesOrNoInputValidator();
            String yesOrNo = getInput();
            try {
                return validator.validateInput(yesOrNo);
            } catch (IllegalArgumentException e) {
                StoreOutputView.print(e.getMessage());
            }
        }
    }
}
