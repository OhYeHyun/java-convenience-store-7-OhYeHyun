package store.validator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import store.model.SaleList;

public class InputValidator {
    private static final String PURCHASE_INFOS_DELIMITER = ",";
    private static final String PURCHASE_INFO_DELIMITER = "^\\[|\\]$";
    private static final String PURCHASE_INFO_ATTRIBUTES_DELIMITER = "-";
    private static final int NUMBER_OF_PURCHASE_INFO_ATTRIBUTES = 2;

    private List<String[]> purchaseInfos;

    public Map<String, Integer> validateInput(SaleList saleList, String purchaseInfosInput) {
        this.purchaseInfos = parsePurchaseInfos(purchaseInfosInput);

        validateFormat();
        validateQuantityIsNumeric();
        validateNameIsExist(saleList);
        validateQuantityIsAvailable(saleList);

        return parseTotalPurchaseList();
    }

    private static List<String[]> parsePurchaseInfos(String purchaseInfosInput) {
        return Arrays.stream(purchaseInfosInput.split(PURCHASE_INFOS_DELIMITER, -1))
                .map(info -> info.replaceAll(PURCHASE_INFO_DELIMITER, "")
                        .split(PURCHASE_INFO_ATTRIBUTES_DELIMITER, -1))
                .collect(Collectors.toList());
    }

    private void validateFormat() {
        purchaseInfos.forEach((info) -> {
            if (info.length != NUMBER_OF_PURCHASE_INFO_ATTRIBUTES) {
                throw new IllegalArgumentException(StoreErrorMessages.MUST_ALLOW_FORMAT.getMessage());
            }
        });
    }

    private void validateQuantityIsNumeric() {
        purchaseInfos.forEach((info) -> {
            String quantity = info[1];
            try {
                Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(StoreErrorMessages.MUST_ALLOW_FORMAT.getMessage());
            }
        });
    }

    private void validateNameIsExist(SaleList saleList) {
        purchaseInfos.forEach((info) -> {
            String name = info[0];
            if (!saleList.isValidateName(name)) {
                throw new IllegalArgumentException(StoreErrorMessages.MUST_BE_NAME_EXISTING.getMessage());
            }
        });
    }

    private void validateQuantityIsAvailable(SaleList saleList) {
        purchaseInfos.forEach((info) -> {
            String name = info[0];
            int quantity = Integer.parseInt(info[1]);

            if (!saleList.isValidateQuantity(name, quantity)) {
                throw new IllegalArgumentException(StoreErrorMessages.MUST_BE_LESS_THAN_INVENTORY.getMessage());
            }
        });
    }

    private Map<String, Integer> parseTotalPurchaseList() {
        Map<String, Integer> totalPurchaseList = new LinkedHashMap<>();
        purchaseInfos.forEach((info) -> {
            String name = info[0];
            int quantity = Integer.parseInt(info[1]);

            totalPurchaseList.put(name, quantity);
        });
        return totalPurchaseList;
    }
}
