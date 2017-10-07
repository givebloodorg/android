package doesangue.doesangue.me.mvp.login;

import android.content.Context;
import android.content.Intent;

import doesangue.doesangue.me.interfaces.LoginRequestListener;

/**
 * Created by JM on 05/10/2017.
 */

public class Contract {

    // The conract for LoginModel
    interface ModelImpl {

        void login(String email, String password, LoginRequestListener loginRequestListener);
    }

    // The conract for LoginActivity
    interface ViewImpl{

        String getEmail();
        String getPassword();

        void setEmailError(String text);
        void setPasswordError(String text);

        void showLoginInProgressMessage();

        void dismissLoginInProgressMessage();

        void startMainActivity(Intent intent);

        void showLoginError(String message);
    }

    // The conract for LoginPresenter
    interface PresenterImpl{

        Context getContext();

        void onLoginButtonClicked();
    }
}
