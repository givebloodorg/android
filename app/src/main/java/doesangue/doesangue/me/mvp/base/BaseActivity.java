package doesangue.doesangue.me.mvp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import doesangue.doesangue.me.R;

public abstract class BaseActivity extends AppCompatActivity {

    // All APP activity shold implement this
    // to retrieve their layout resource id
    public abstract int getLayoutResId();

    // All APP activity shold implement this
    // to initialize their views
    public abstract void initializeViews();

    // Dialog for long running jobs
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutResId());
        this.initializeViews();
    }

    // Method to show an Toast
    // All activity that inherit of this, will have this method
    public final void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    // Method to show an Toast
    // All activity that inherit of this, will have this method
    public final void showToastMessage(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to show a simple Dialog whith some info
    public final void showDialogInfo(String title, String message) {
       final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, null);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public final void showProgress(final @StringRes int message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                progressDialog.setMessage(getString(message));
                progressDialog.show();
            }
        });

    }

    public final void dismissProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}
