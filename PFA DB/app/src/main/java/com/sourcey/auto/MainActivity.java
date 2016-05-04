package com.sourcey.auto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sourcey.main.LoginActivity;
import com.sourcey.materiallogindemo.R;

import java.util.Arrays;

import Connexion.FetchEtudiantInfos;
import butterknife.Bind;
import entity.Etudiant;


public class MainActivity extends Activity implements FetchEtudiantInfos.AsyncEtudiantData{

    @Bind(R.id.imageButton3) ImageButton disconnectBtn;
    TextView loginTextView;
    private String loginName;
    private String classe;
    public  TextView nom;
    public  TextView prenom;
    public  ImageView img;
    public  CheckBox chek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginTextView = (TextView) findViewById(R.id.textViewLogin);

        nom=(TextView)findViewById(R.id.nom);
        prenom=(TextView)findViewById(R.id.prenom);
        img=(ImageView)findViewById(R.id.movie_poster);
//        chek=(CheckBox)findViewById(R.id.check);

        Intent intent = getIntent();
        loginName = intent.getStringExtra("login");
        classe = intent.getStringExtra("classe");
        loginTextView.setText("Bienvenue M/Mme : "+loginName);

        //button deconnecter
        ImageButton btnDeconnecter=(ImageButton) findViewById(R.id.imageButton3);
        btnDeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        ImageButton btnRetour=(ImageButton) findViewById(R.id.imageButton4);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //bouton marquer absences classique
        ImageButton btnClass=(ImageButton) findViewById(R.id.imageButton);
        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, EtudiantActivity.class);
                intent.putExtra("login", loginName);
                intent.putExtra("classe", classe);
                startActivity(intent);
            }
        });

        ImageButton btnEmp=(ImageButton) findViewById(R.id.imageButton2);
        btnEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageButtonListVue.class);
                intent.putExtra("login", loginName);
                intent.putExtra("classe", classe);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadEtudiantDataFinish(Etudiant[] result) {
        Log.v("onLoadEtudiantD", Arrays.toString(result));
    }
}
