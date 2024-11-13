package store;

import store.controller.StoreController;
import store.view.StoreInputView;

public class Application {
    public static void main(String[] args) {
        StoreInputView inputView = new StoreInputView();
        StoreController controller = new StoreController(inputView);

        controller.openStore();
    }
}
