package com.cabezas.alexander.viper_architecture.login;

import com.cabezas.alexander.viper_architecture.R;

import android.app.Activity;

/**
 * Created by alexandercabezas on 20/10/17.
 */

public class LoginPresenter implements LoginContracts.Presenter, LoginContracts.InteractorOutput{

    private LoginContracts.View mView;
    private LoginContracts.Interactor interactor;
    private LoginContracts.Router router;

    LoginPresenter(LoginContracts.View view) {
        interactor = new LoginInteractor(this);
        router = new LoginRouter((Activity) view);
        mView = view;
    }

    @Override
    public void onDestroy() {
        interactor.unRegister();
        interactor = null;
        router.unRegister();
        router = null;
    }

    @Override
    public void onLoginButtonPressed(String userName, String password) {
        interactor.loginUser(userName, password);
    }

    @Override
    public void loginResultSuccess() {
        router.presentHomeScreen();
    }

    @Override
    public void loginResultFail() {
        mView.showError(R.string.login_failed);
    }
}
