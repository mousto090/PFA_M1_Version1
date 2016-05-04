package com.sourcey.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sourcey.auto.MainActivity;
import com.sourcey.materiallogindemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends Activity {
    private static final String TAG = "Authentification";
    private static final int REQUEST_SIGNUP = 0;
    private String login;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    private RequestQueue requestQueue;
    private static final String URL = Constantes.BASE_URL_PROJET + "/user_control.php";
    private StringRequest request;

    public static final  String LOGIN = "login";
    public static final  String CLASSE = "classe";

    //@Bind(R.id.btn_Deco) Button _decoButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        requestQueue = Volley.newRequestQueue(this);
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        // _decoButton.setOnClickListener(new View.OnClickListener() {

        //    @Override
        //    public void onClick(View v) {
        //        deco();
        //    }
        //});


    }

    //public void deco(){
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
    //}

    public void login() {
        Log.d(TAG, "Authentification");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Chargement...");
        progressDialog.show();

//        this.login = _emailText.getText().toString();
//        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
//                    public String password = _passwordText.getText().toString();
//                    public String login =_emailText.getText().toString();

                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed

                        authentifier();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 1500);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    //authentification reussie
    public void onLoginSuccess() {

        /*FetchProfInfosAsyncTask fetch = new FetchProfInfosAsyncTask();
        fetch.execute("");*/
        _loginButton.setEnabled(true);
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        myIntent.putExtra(LOGIN, this.login); //Optional parameters
        LoginActivity.this.startActivity(myIntent);
        //finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Nom d'utilisateur incorrect", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() ) {
            _emailText.setError("Entrez un nom d'utilisateur valide");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Entre 4 et 10 caractères alphanumériques");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    public void authentifier(){

        _loginButton.setEnabled(true);
        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.names().get(0).equals("success")){
                        Toast.makeText(getApplicationContext(),""+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Intent myIntent = new Intent(LoginActivity.this, ChoixClasse.class);
                        myIntent.putExtra(LOGIN, _emailText.getText().toString()); //Optional parameters
                        LoginActivity.this.startActivity(myIntent);
                    }else {
                        Toast.makeText(getApplicationContext(), "" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("login", _emailText.getText().toString());
                hashMap.put("password", _passwordText.getText().toString());

                return hashMap;

            }
        };
        requestQueue.add(request);}

}
