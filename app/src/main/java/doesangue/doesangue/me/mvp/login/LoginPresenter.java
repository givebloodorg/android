package doesangue.doesangue.me.mvp.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;

import doesangue.doesangue.me.R;
import doesangue.doesangue.me.interfaces.LoginRequestListener;
import doesangue.doesangue.me.mvp.main.MainActivity;

/**
 * Created by JM on 05/10/2017.
 */

public class LoginPresenter implements Contract.PresenterImpl, LoginRequestListener {

    //private final String TAG = getClass().getSimpleName();
    private final String TAG = "output";

    // The view implemented by LoginActivity
    private Contract.ViewImpl view;

    // The LoginModel to handle login work
    private LoginModel model;

    // To access the android resources and network requests

    public LoginPresenter(Contract.ViewImpl view) {
        this.view = view;
        this.model = new LoginModel(this);
    }

    @Override
    public Context getContext() {
        return (Activity) view;
    }

    // Method called when Login Button is clicked
    //Here we need to validate the info, and call
    // Login work on LoginModel, if info is valid
    @Override
    public void onLoginButtonClicked() {
        String email = view.getEmail();
        String password = view.getPassword();

        // If Email is incorect, show the error, and break the proccess
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.setEmailError(getContext().getString(R.string.invalid_email));
            return;
        }

        // If email is correct, clean the previous error.
        view.setEmailError(null);

        // Check if password is not empty
        if (password.trim().length() == 0) {
            view.setPasswordError(getContext().getString(R.string.type_password));
            return;
        }

        // If password is correct, clean the previous error.
        view.setPasswordError(null);

        // The entired data is valid, so
        // we can now make the login with the server.

        // Show some progress loader
        view.showLoginInProgressMessage();

        // Call login frm Network
        model.login(email, password, this);
    }

    // This method will be called once Login work is succeded
    @Override
    public void onLoginSuccess() {
        Log.i(TAG, "onLoginSuccess");

        // Close the login progress
        view.dismissLoginInProgressMessage();

        // Start the MainActivity
        Intent i = new Intent(getContext(), MainActivity.class);
        view.startMainActivity(i);
    }

    // This method will be called once Login error occour.
    @Override
    public void onLoginError(String message) {
        Log.i(TAG, "onLoginError: " + message);

        // Close the login progress
        view.dismissLoginInProgressMessage();

        // Show the error
        //view.showLoginError(message);
        view.showLoginError(getContext().getString(R.string.someting_was_wrong));
    }
}
