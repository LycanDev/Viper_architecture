package com.cabezas.alexander.viper_architecture.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cabezas.alexander.viper_architecture.R;

public class ControlActivity extends AppCompatActivity implements ControlContracts.View{

    private ControlContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ControlPresenter(this);
        mPresenter.goToLoginScreen();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
