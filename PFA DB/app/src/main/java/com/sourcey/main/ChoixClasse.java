package com.sourcey.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.sourcey.auto.MainActivity;
import com.sourcey.choix.Menu;
import com.sourcey.materiallogindemo.R;

/**
 * Created by SIMO on 21/04/2016.
 */
public class ChoixClasse extends Activity{

    private String array_spinner[];
    private String loginName;
    private String classe;
    private Spinner s;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_classe);
        Intent intent = getIntent();
        s=(Spinner)findViewById(R.id.spinner);
        loginName = intent.getStringExtra("login");

        ImageButton btnAuto=(ImageButton) findViewById(R.id.btnAuto);
        ImageButton btnSuiv=(ImageButton) findViewById(R.id.suivant);

        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixClasse.this, MainActivity.class);
                intent.putExtra("login", loginName);
                ChoixClasse.this.startActivity(intent);
            }
        });

        btnSuiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixClasse.this, Menu.class);
                intent.putExtra("login", loginName);
                classe = s.getSelectedItem().toString();
                intent.putExtra("classe", classe);
                ChoixClasse.this.startActivity(intent);
            }
        });

        array_spinner=new String[5];
        array_spinner[0]="L3 MIAGE";
        array_spinner[1]="M1 MIAGE";
        array_spinner[2]="4 ISI G1";
        array_spinner[3]="4 ISI G2";
        array_spinner[4]="1 EI";
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
    }

}
