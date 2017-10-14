package doesangue.doesangue.me.mvp.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import doesangue.doesangue.me.interfaces.LoginRequestListener;

/**
 * Created by JM on 05/10/2017.
 */

public class LoginModel  implements Contract.ModelImpl {

    public static final String TAG = "output";
    private LoginPresenter loginPresenter;
    public static String httpOutput;


    public LoginModel(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void login(final String email, final String password, final LoginRequestListener loginRequestListener) {

        new LoginTask(email, password, loginRequestListener)
                .execute();
    }

    public String getHttpOutput(){

        return this.httpOutput;
    }
//
//    public void setHttpOutput(String parameter){
//
//        this.httpOutput=parameter;
//    }
}

class LoginTask extends AsyncTask<String, Integer, String>  {

    private WebComons wc = new WebComons();
    private Map<String, Object> params = new HashMap<>();
    private LoginRequestListener loginRequestListener;

    public LoginTask(String email, String password, LoginRequestListener loginRequestListener) {
        this.loginRequestListener = loginRequestListener;
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String loginUrl = "v1/auth/login";
            HttpURLConnection conn = wc.request("https://doesangueapi.herokuapp.com/", loginUrl, 1, params);



            return wc.resPonde(conn);

        } catch (Exception e) {

            e.printStackTrace();

            loginRequestListener.onLoginError(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            loginRequestListener.onLoginSuccess();
        }
    }
}


class WebComons  {


    public static final String WS_HOST = "http://192.168.1.100/servidor_farras";


    public static final int POST = 1;
    // public static final int GET = 2;


    public synchronized HttpURLConnection request(String host, String path, int metodo, Map<String, Object> params) throws Exception {


        HttpURLConnection connection;
        String urlString = host;
        urlString += path;

        URL url = new URL(urlString);

        connection = (HttpURLConnection) url.openConnection();

        if (metodo == 1)
            connection.setRequestMethod("POST");

        if (metodo == 2)
            connection.setRequestMethod("GET");

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setUseCaches(false);

        if (params != null && params.size() > 0) {
            String urlParameters = criarParams(params);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
        }

        connection.connect();

        return connection;
    }

    private String criarParams(Map<String, Object> paramsMap) throws Exception {
        StringBuilder paramsData = new StringBuilder();

        Log.d("text", "Parametros : " + paramsMap.toString());
        for (Map.Entry<String, Object> param : paramsMap.entrySet()) {
            if (paramsData.length() != 0) paramsData.append('&');

            paramsData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            paramsData.append('=');
            paramsData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));

        }
        return paramsData.toString();
    }

    private String getStringInputStream(InputStream is) throws Exception {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public synchronized String resPonde(HttpURLConnection httpURLConnection) throws Exception {

        String retorno;
        if (httpURLConnection != null && httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            retorno = getStringInputStream(httpURLConnection.getInputStream());
            httpURLConnection.disconnect();

            LoginModel.httpOutput=retorno;

            return retorno;

        } else {

            assert httpURLConnection != null;
            throw new Exception("  comunicação http  WebComons erro na resposta :" + httpURLConnection.getResponseMessage());
        }
    }

}
