package doesangue.doesangue.me.mvp.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import doesangue.doesangue.me.R;
import doesangue.doesangue.me.UserInfoActivity;
import doesangue.doesangue.me.server.VolleySingleton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg=(TextView)findViewById(R.id.txtMsg);

       // getJsonAuthenticatedUser();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_options_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.user_profile){
            Log.d(TAG,"User profile option was clicked");

            String token=getIntent().getExtras().getString("access_token");

            Intent intentToProfile=new Intent(MainActivity.this, UserInfoActivity.class);
            intentToProfile.putExtra("access_token",token);
            startActivity(intentToProfile);
        }


        return super.onOptionsItemSelected(item);
    }


}
