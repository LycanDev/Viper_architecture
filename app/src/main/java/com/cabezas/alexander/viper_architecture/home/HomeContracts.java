package com.cabezas.alexander.viper_architecture.home;

/**
 * Created by alexandercabezas on 20/10/17.
 */

public class HomeContracts {

    interface View {
        void onDestroy();
    }

    interface Presenter {
        void goProfileScreen();
        void onDestroy();
    }

    interface Interactor {
        void unRegister();
    }

    interface Router {
        void unRegister();
        void presentProfileScreen();
    }

}
