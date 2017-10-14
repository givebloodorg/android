package doesangue.doesangue.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import doesangue.doesangue.me.mvp.main.MainActivity;
import doesangue.doesangue.me.server.VolleySingleton;

public class UserInfoActivity extends AppCompatActivity {

    private static final String TAG = UserInfoActivity.class.toString() ;


    TextView txtUsername, txtFirstName, txtSurname;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtFirstName = (TextView) findViewById(R.id.txtFirstName);
        txtSurname = (TextView) findViewById(R.id.txtSurname);

        getJsonAuthenticatedUser();
    }

    public void getJsonAuthenticatedUser() {
        
        String server_url = "https://doesangueapi.herokuapp.com/v1/auth/me";

        String auth_token = getIntent().getExtras().getString("access_token");

        JSONObject jsonToken = null;
        
        try {
            
            jsonToken = new JSONObject(auth_token);
            auth_token=jsonToken.getString("access_token");
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String login_token=auth_token;

        Log.i(TAG, "value of the login token " + login_token);

        JsonObjectRequest jSonRequest = new JsonObjectRequest(
            Request.Method.GET,
            server_url,
            null,
            new Response.Listener<JSONObject>() {
            
            @Override
            public void onResponse(JSONObject response) {

                Log.i(TAG, "Response+++++ " + response.toString());

                String first_name, last_name, username;

                try {
                    
                    first_name = response.getString("first_name");
                    last_name = response.getString("last_name");
                    username = response.getString("username");

                    txtFirstName.setText(first_name);
                    txtSurname.setText(last_name);
                    txtUsername.setText(username);
                    
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(
                    getApplicationContext().getApplicationContext(),
                    "Could not load authenticated user info",
                    Toast.LENGTH_SHORT).show();

                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> headers = new HashMap<>();
                headers.put("Content-type", "application/json");
                headers.put("Authorization", "Bearer " +l ogin_token);

                return headers;
            }
        };
        
        VolleySingleton.getInstance(UserInfoActivity.this).addRequest(jSonRequest);
    }
}
