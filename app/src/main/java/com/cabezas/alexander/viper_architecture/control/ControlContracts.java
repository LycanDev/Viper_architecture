package com.cabezas.alexander.viper_architecture.control;

/**
 * Created by alexandercabezas on 20/10/17.
 */

public class ControlContracts {

    interface View {
        void onDestroy();
    }

    interface Presenter {
        void onDestroy();
        void goToLoginScreen();
    }

    interface Interactor {
        void unRegister();
    }


    interface Router {
        void unRegister();
        void presentLoginScreen();
    }
}
