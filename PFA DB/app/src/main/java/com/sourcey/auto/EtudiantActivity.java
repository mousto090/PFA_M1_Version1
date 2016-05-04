package com.sourcey.auto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sourcey.materiallogindemo.R;

public class EtudiantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.etudiant_list_container, new EtudiantFragment())
                    .commit();
        }

    }


}
