package doesangue.doesangue.me.mvp.login;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import doesangue.doesangue.me.interfaces.LoginRequestListener;
import doesangue.doesangue.me.server.MySingleton;

/**
 * Created by JM on 05/10/2017.
 */

public class LoginModel implements Contract.ModelImpl {

    private final String TAG = "output";
    private LoginPresenter loginPresenter;

    public LoginModel(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }


    @Override
    public void login(final String email, final String password, final LoginRequestListener loginRequestListener) {

        String loginUrl = "https://doesangueapi.herokuapp.com/v1/auth/login";

        StringRequest loginRequest = new StringRequest(Request.Method.POST, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "login onResponse: " + response);

                //Notify the Presenter that the login as success
                loginRequestListener.onLoginSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = error.getMessage();
                Log.e(TAG, "Login error: " + message);

                // Notify the Presenter that the login as fail.
                loginRequestListener.onLoginError(message);
            }

        }) {
            // Here we pass the Login params, requested by the API
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }

            // Here we pass the requested HEADERS by the API
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Set the request to Queue, and wait for response
        MySingleton.getInstance(loginPresenter.getContext()).addToRequestQueue(loginRequest);
    }
}
