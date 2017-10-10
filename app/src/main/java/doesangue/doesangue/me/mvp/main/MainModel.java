package doesangue.doesangue.me.mvp.main;

import doesangue.doesangue.me.settings.SessionManager;

/**
 * Created by JM on 09/10/2017.
 */

public class MainModel implements Contract.ModelImpl {

    private MainPresenter mainPresenter;
    private SessionManager sessionManager;

    public MainModel(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.sessionManager = new SessionManager(mainPresenter.getContext());
    }

    @Override
    public boolean isLoggedIn() {
        return sessionManager.getSessionToken().trim().length() > 0;
    }
}
