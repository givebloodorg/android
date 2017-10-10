package doesangue.doesangue.me.mvp.main;

import android.app.Activity;
import android.content.Context;

/**
 * Created by JM on 09/10/2017.
 */

public class MainPresenter implements Contract.PresenterImpl {

    private Contract.ViewImpl view;
    private MainModel model;

    public MainPresenter(Contract.ViewImpl view) {
        this.view = view;
        this.model = new MainModel(this);
    }

    @Override
    public void checkLoginStatus() {
        boolean isLoggedIn = model.isLoggedIn();

        // If is not logged in, start LoginActivity
        if(!isLoggedIn){
            view.startLoginActivity();
        }
    }

    @Override
    public Context getContext() {
        return (Activity) view;
    }
}
