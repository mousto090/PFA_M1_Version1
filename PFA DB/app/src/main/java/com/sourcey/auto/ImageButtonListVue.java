package com.sourcey.auto;

/**
 * Created by SIMO on 10/04/2016.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sourcey.main.LoginActivity;
import com.sourcey.materiallogindemo.R;

import java.util.ArrayList;
import java.util.Arrays;

import Connexion.FetchEtudiantInfos;
import entity.Etudiant;

public class ImageButtonListVue extends ListActivity implements  FetchEtudiantInfos.AsyncEtudiantData{
    private ListView mlistViewEtudiant;
    private  MyListeEtudiantEmpAdapter mListViewEtudiantAdapter;
    private FetchEtudiantInfos mFetchEtudiantInfos ;
    private String loginName;
    private Etudiant[] etudiants;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loginName = getIntent().getStringExtra("login");

        mlistViewEtudiant = getListView();
        mListViewEtudiantAdapter = new MyListeEtudiantEmpAdapter(new ArrayList<Etudiant>(), this);


        mFetchEtudiantInfos = new FetchEtudiantInfos();
        mFetchEtudiantInfos.delegateEtudiantData = this;
        mFetchEtudiantInfos.execute(loginName);

        mlistViewEtudiant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View o = mlistViewEtudiant.getChildAt(position).findViewById(
                        R.id.layout_name);
                String nom = etudiants[position].getNom();
                String prenom = etudiants[position].getPrenom();
                String message = nom + " " + prenom ;
                Intent intent = new Intent(ImageButtonListVue.this, Empreinte.class);
                intent.putExtra("login", message);
                ImageButtonListVue.this.startActivity(intent);
            }
        });

        //Récupération automatique de la liste (l'id de cette liste est nommé obligatoirement @android:id/list afin d'être détecté)
       /* list = getListView();

        // Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        // On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        map = new HashMap<String, String>();
        map.put("nom", "JANATI IDRISSI");
        map.put("prenom", "mohammed");
        listItem.add(map);

        map = new HashMap<String, String>();
        map.put("nom", "DIALLO mamadou");
        map.put("prenom", "moustapha");
        listItem.add(map);

        map = new HashMap<String, String>();
        map.put("nom", "BENAZZOUZ");
        map.put("prenom", "khalil");
        listItem.add(map);

        map = new HashMap<String, String>();
        map.put("nom", "DARROUJI");
        map.put("prenom", "badreddine");
        listItem.add(map);

        //Utilisation de notre adaptateur qui se chargera de placer les valeurs de notre liste automatiquement et d'affecter un tag à nos checkbox

        MyListAdapter mSchedule = new MyListAdapter(this.getBaseContext(), listItem,
                R.layout.etudiant_item, new String[] { "nom" , "prenom" }, new int[] {
                R.id.nom, R.id.prenom });

        // On attribue à notre listView l'adaptateur que l'on vient de créer
        list.setAdapter(mSchedule);*/
    }

    //Fonction appelée au clic d'une des checkbox
/*    public void MyHandler(View v) {
        CheckBox cb = (CheckBox) v;
        //on récupère la position à l'aide du tag défini dans la classe MyListAdapter
        int position = Integer.parseInt(cb.getTag().toString());

        // On récupère l'élément sur lequel on va changer la couleur
        View o = list.getChildAt(position).findViewById(
                R.id.layout_name);
        //TextView nom1 = (TextView) findViewById(R.id.nom1);
        //TextView nom = (TextView) findViewById(R.id.nom);
        //TextView pre1 = (TextView) findViewById(R.id.prenom1);
        //TextView pre = (TextView) findViewById(R.id.prenom);


        //On change la couleur
//        if (cb.isChecked()) {
//            Intent intent = new Intent(ImageButtonListVue.this, Empreinte.class);
//            ImageButtonListVue.this.startActivity(intent);
//            cb.setChecked(false);
//
//        } else {
//
//
//        }
    }*/

    @Override
    public void onLoadEtudiantDataFinish(Etudiant[] result) {

        etudiants = result;
        Log.v("onLoadEtudiantDCheckLis", Arrays.toString(etudiants));
//        mListViewEtudiantAdapter = new MyListeEtudiantAdapter(Arrays.asList(result),this);
        mlistViewEtudiant.setAdapter(mListViewEtudiantAdapter);
        mListViewEtudiantAdapter.add(Arrays.asList(etudiants));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ImageButtonListVue.this, LoginActivity.class);
        ImageButtonListVue.this.startActivity(intent);
    }
}


