package doesangue.doesangue.me.mvp.main;

import android.content.Context;

/**
 * Created by JM on 09/10/2017.
 */

class Contract {

    interface ModelImpl {

        boolean isLoggedIn();
    }

    interface ViewImpl {

        void startLoginActivity();
    }

    interface PresenterImpl {

        Context getContext();

        void checkLoginStatus();
    }
}
