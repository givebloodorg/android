package doesangue.doesangue.me.mvp.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import doesangue.doesangue.me.R;
import doesangue.doesangue.me.mvp.base.BaseActivity;
import doesangue.doesangue.me.mvp.login.LoginActivity;

public class MainActivity extends BaseActivity implements Contract.ViewImpl {

    private MainPresenter presenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter(this);

        // Check login status
        presenter.checkLoginStatus();
    }

    @Override
    public void startLoginActivity() {

        startActivity(new Intent(this, LoginActivity.class));
        this.finish(); // Remove this activity from stack
    }
}
