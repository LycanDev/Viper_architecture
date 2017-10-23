package com.cabezas.alexander.viper_architecture.login;

/**
 * Created by alexandercabezas on 20/10/17.
 */

public class LoginInteractor implements LoginContracts.Interactor{

    private LoginContracts.InteractorOutput output;

    public LoginInteractor(LoginContracts.InteractorOutput output) {
        this.output = output;
    }

    @Override
    public void loginUser(String userName, String password) {

        if(userName.equals("username") && password.equals("12345")) {
            output.loginResultSuccess();
        } else {
            output.loginResultFail();
        }

    }

    @Override
    public void unRegister() {
        output = null;
    }
}
