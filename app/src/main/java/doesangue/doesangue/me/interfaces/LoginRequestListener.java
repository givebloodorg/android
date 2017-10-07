package doesangue.doesangue.me.interfaces;

/**
 * Created by JM on 06/10/2017.
 */

/**
 * This class is a listener to handle the login request on model class
 * This will notifify the @{LoginPresenter} if errors occours or in case of success.
 */
public interface LoginRequestListener {

    // Called when login succedded
    void onLoginSuccess();

    // Called when login error occour
    void onLoginError(String message);
}
