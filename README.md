# Viper_architecture
In this article I will explain how to create a basic application with VIPER architecture, all the code is available here: https://github.com/LycanDev/Viper_architecture.

For this tutorial I asume that you are already familiar with Android development and the java programming lenguaje, so I won't explain the most basic things about creation an Android application or basics in java programming. For basic information there are many good sources of information in internet like Udacity.

What is VIPER?

VIPER stands for View Interactor Presenter Entity Router, all these clases that have a well defined responsibility according to the Single Responsibility Principle.

Creating the application

First of all, create an application in AndroidStudio, and create the following package structure

```
package com.cabezas.alexander.viper_architecture.control;
package com.cabezas.alexander.viper_architecture.login;
package com.cabezas.alexander.viper_architecture.home;
package com.cabezas.alexander.viper_architecture.profile;
```

As you can see in the image we are going to work with four packages, each package will contain all the structure required for each of the functionalities.

After all packages are created, lets create the first Activity called ControlActivity, this activity will be the entrance point for our application. Also create the following classes in the same package.

```
ControlActivity
ControlContracts
ControlInteractor
ControlPresenter
ControlRouter
```
Now lets start explaining the VIPER architecture , ControlActivity will be the View part in VIPER, it will contain all required methods to handle changes in the layout. Then we have 4 other classes which will be use to complete the architecture.

The ControlContracts is a class that contains all the interfaces that must be implemented by the other classes, this is important since this will define all the methods that each class will expose. Our ControlContract class will look like this:

```
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
```
Each one of those interfaces stands for a letter in the VIPER architecture. As I mentioned before the ControlActivity will implement the View interface which will expose the methods available to handle changes in the layout.

After creating ControlContract class we need to create the ControlPresenter which will be the middle man between the view and the Interactor. This class will mostly contain method to transform data from the Interactor to objects that will be shown in the layout.

```
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

```

Now we are going to create the ControlInteractor class, this class implements The Interactor interface.

```
public class ControlInteractor implements ControlContracts.Interactor{
    @Override
    public void unRegister() {

    }
}

```
And finally we create the ControlRouter class which implements the Router interface. This class contains all the method required to navigate other screens.

```
public class ControlRouter implements ControlContracts.Router {

    private Activity activity;

    public ControlRouter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void unRegister() {
        activity = null;
    }

    @Override
    public void presentLoginScreen() {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
}
```

That's the basic structure for an application that uses VIPER, but some questions arise, like how to handle responses from the Interactor for example, to answer this kind of questions i have provided a basic login functionality which will show how to handle errors from the login and responses from the Interactor.

As for the ControlActivity lets create the exact same structure for the Login.

All the classes will have the exact same responsibilities as in the Control package, but now the LoginControl class will have a new Interface and the Interactor will actually have some functionality.

This is the new LoginContracs class

```
public class LoginContracts {

    interface View {
        void onDestroy();
        void showError(int id);
    }

    interface Presenter {
        void onDestroy();
        void onLoginButtonPressed(String userName, String password);
    }

    interface Interactor {
        void loginUser(String userName, String password);
        void unRegister();
    }

    interface InteractorOutput {
        void loginResultSuccess();
        void loginResultFail();
    }


    interface Router {
        void unRegister();
        void presentHomeScreen();
    }
}
```

As you can see there is a new Interface called InteractorOutput, this interface will contain the necessary methods to communicate the Interactor and the presenter, so, which class will implement the InteractorOuput? The answer is: the presenter.

```
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

```
The presenter will pass itself as a parameter to the Interactor so, the Interactor will use the methods defined in the InteractorOutput to notify the result of the Login process.

```
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
```
