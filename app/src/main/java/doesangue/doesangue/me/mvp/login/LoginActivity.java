package doesangue.doesangue.me.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import doesangue.doesangue.me.R;
import doesangue.doesangue.me.mvp.base.BaseActivity;

public class LoginActivity extends BaseActivity implements Contract.ViewImpl {

    // To retrieve the user email
    private EditText edtEmail;

    // To retrieve the user password
    private EditText edtPassword;

    // MVP
    private LoginPresenter loginPresenter;

    // Return the layout id of this activity
    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    // Base method to initialize activity views
    @Override
    public void initializeViews() {

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        // Button login
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginPresenter.onLoginButtonClicked();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup the LoginPresenter to control the Activity requests
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public String getEmail() {
        return edtEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void setEmailError(String text) {
        edtEmail.setError(text);
    }

    @Override
    public void setPasswordError(String text) {
        edtPassword.setError(text);
    }

    @Override
    public void showLoginInProgressMessage() {
        super.showProgress(R.string.please_wait);
    }

    @Override
    public void dismissLoginInProgressMessage() {
        super.dismissProgress();
    }

    @Override
    public void startMainActivity(Intent intent) {

        startActivity(intent);
    }

    @Override
    public void showLoginError(String message) {

        super.showDialogInfo(getString(R.string.login_error_title), message);
    }
}
