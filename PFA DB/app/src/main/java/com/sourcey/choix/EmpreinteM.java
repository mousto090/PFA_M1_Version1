package com.sourcey.choix;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sourcey.materiallogindemo.R;

/**
 * Created by SIMO on 10/04/2016.
 */
public class EmpreinteM extends Activity {
    private String loginName;
    TextView loginTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empreinte);
        loginTextView = (TextView) findViewById(R.id.nomPrenom);
        Intent intent = getIntent();
        loginName = intent.getStringExtra("login");
        loginTextView.setText("" + loginName);
        Button btn=(Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(EmpreinteM.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Scanning ...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                finish();
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 1500);

            }
        });

    }

    @Override
    public void onBackPressed() {

    }

}
