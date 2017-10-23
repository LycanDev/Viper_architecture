package com.cabezas.alexander.viper_architecture.control;

import android.app.Activity;

/**
 * Created by alexandercabezas on 20/10/17.
 */

public class ControlPresenter implements ControlContracts.Presenter{

    private ControlContracts.Interactor interactor;
    private ControlContracts.Router router;

    ControlPresenter(ControlContracts.View view) {
        interactor = new ControlInteractor();
        router = new ControlRouter((Activity) view);
    }

    @Override
    public void onDestroy() {
        interactor.unRegister();
        interactor = null;
        router.unRegister();
        router = null;
    }

    @Override
    public void goToLoginScreen() {
        router.presentLoginScreen();
    }
}
