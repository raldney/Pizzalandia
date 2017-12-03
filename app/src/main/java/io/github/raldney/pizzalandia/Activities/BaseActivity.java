package io.github.raldney.pizzalandia.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 03/12/2017.
 */

public class BaseActivity extends AppCompatActivity {
    @VisibleForTesting
    public ProgressDialog mProgressDialog;
    public Activity actualActivity;

    BaseActivity(Activity actualActivity){
        this.actualActivity = actualActivity;
    }
    BaseActivity(){}

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void startNewActivity(Class activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

}
