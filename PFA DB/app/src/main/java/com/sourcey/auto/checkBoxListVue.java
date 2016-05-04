package com.sourcey.auto;

/**
 * Created by SIMO on 10/04/2016.
 */

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sourcey.materiallogindemo.R;

import java.util.ArrayList;
import java.util.Arrays;

import Connexion.FetchEtudiantInfos;
import adapter.EtudiantPresentAdapter;
import entity.Etudiant;

public class checkBoxListVue extends ListActivity implements  FetchEtudiantInfos.AsyncEtudiantData{
    private ListView mlistViewEtudiant;
    private EtudiantPresentAdapter mListViewEtudiantAdapter;
    private FetchEtudiantInfos mFetchEtudiantInfos ;
    private String loginName;
    private String classe;
    private Etudiant[] etudiants;
    private ArrayList indexes = new ArrayList();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loginName = getIntent().getStringExtra("login");
        classe = getIntent().getStringExtra("classe");
        mlistViewEtudiant = getListView();
        mListViewEtudiantAdapter = new EtudiantPresentAdapter(new ArrayList<Etudiant>(), this);


        mFetchEtudiantInfos = new FetchEtudiantInfos();
        mFetchEtudiantInfos.delegateEtudiantData = this;
        mFetchEtudiantInfos.execute(loginName);



        /*mlistViewEtudiant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View o = mlistViewEtudiant.getChildAt(position).findViewById(
                        R.id.layout_name);
                String nom = etudiants[position].getNom();
                String prenom = etudiants[position].getPrenom();
                String message =nom + " " + prenom + " est marquer absent(e)";

                if(indexes.contains(""+position)){
                    o.setBackgroundResource(R.color.blanc);

                    indexes.remove(""+position);
                }
                else {

                    o.setBackgroundResource(R.color.primary_dark);
                    indexes.add(""+position);
                    Toast.makeText(checkBoxListVue.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });*/
        /*mlistViewEtudiant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Log.i("onItemClick", view.getClass().toString() + adapter.getItemAtPosition(position));
                CheckBox cb = (CheckBox) findViewById(R.id.check);
                View o = mlistViewEtudiant.getChildAt(position).findViewById(
                        R.id.layout_name);
                Log.i("onItemClick", view.getClass().toString());
                if (cb.isChecked()) {
                    o.setBackgroundResource(R.color.blanc);
                    cb.setChecked(false);

                } else {
                    o.setBackgroundResource(R.color.primary_dark);
                    cb.setChecked(true);

                }
            }
        });*/

        //Récupération automatique de la liste (l'id de cette liste est nommé obligatoirement @android:id/list afin d'être détecté)
        //list = getListView();

        // Création de la ArrayList qui nous permettra de remplir la listView
//        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
//
//        // On déclare la HashMap qui contiendra les informations pour un item
//        HashMap<String, String> map;
//
//        map = new HashMap<String, String>();
//        map.put("nom", "JANATI IDRISSI");
//        map.put("prenom", "mohammed");
//        listItem.add(map);
//
//        map = new HashMap<String, String>();
//        map.put("nom", "DIALLO mamadou");
//        map.put("prenom", "moustapha");
//        listItem.add(map);
//
//        map = new HashMap<String, String>();
//        map.put("nom", "BENAZZOUZ");
//        map.put("prenom", "khalil");
//        listItem.add(map);
//
//        map = new HashMap<String, String>();
//        map.put("nom", "DARROUJI");
//        map.put("prenom", "badreddine");
//        listItem.add(map);
//
//        //Utilisation de notre adaptateur qui se chargera de placer les valeurs de notre liste automatiquement et d'affecter un tag à nos checkbox
//
//        MyListAdapter mSchedule = new MyListAdapter(this.getBaseContext(), listItem,
//                R.layout.etudiant_item, new String[] { "nom" , "prenom" }, new int[] {
//                R.id.nom, R.id.prenom });
//
//        // On attribue à notre listView l'adaptateur que l'on vient de créer
//        mlistViewEtudiant.setAdapter(mSchedule);
    }



//
    @Override
    public void onLoadEtudiantDataFinish(Etudiant[] result) {
        etudiants = result;
        Log.v("onLoadEtudiantDCheckLis", Arrays.toString(etudiants));
//        mListViewEtudiantAdapter = new MyListeEtudiantAdapter(Arrays.asList(result),this);
        mlistViewEtudiant.setAdapter(mListViewEtudiantAdapter);
        mListViewEtudiantAdapter.add(Arrays.asList(etudiants));

    }

    public void present(View v) {

        RelativeLayout re = (RelativeLayout)v.getParent();
        Button presentBtn = (Button) re.getChildAt(1);
        presentBtn.setBackgroundColor(Color.GREEN);

        RelativeLayout re2 = (RelativeLayout)v.getParent();
        LinearLayout ln = (LinearLayout) re2.getParent();
        RelativeLayout re3=(RelativeLayout)ln.getChildAt(1);
        Button absentBtn = (Button) re3.getChildAt(1);
        absentBtn.setBackgroundColor(Color.WHITE);

     //   Button p=(Button)v;
        //int position = Integer.parseInt(v.getTag().toString());
        //View ve=mlistViewEtudiant.getChildAt(position).findViewById(R.id.test1);
        //Log.i("onItemClick", ve.getClass().toString() + position + "");
        //p.setBackgroundColor(Color.GREEN);
        //Button a=(Button)findViewById(R.id.A);
        //a.setBackgroundColor(Color.WHITE);

    }

    public void absent(View v) {

        RelativeLayout re1 = (RelativeLayout)v.getParent();
        Button absentBtn = (Button) re1.getChildAt(1);
        absentBtn.setBackgroundColor(Color.RED);
        RelativeLayout re2 = (RelativeLayout)v.getParent();
        LinearLayout ln = (LinearLayout) re2.getParent();
        RelativeLayout re3=(RelativeLayout)ln.getChildAt(0);
        Button presentBtn = (Button) re3.getChildAt(1);
        presentBtn.setBackgroundColor(Color.WHITE);
        //Button presentBtn = (Button)((LinearLayout)((RelativeLayout)((LinearLayout)((LinearLayout) re.getParent()).getParent()).getChildAt(0)).getChildAt(0)).getChildAt(0);

        //presentBtn.setBackgroundColor(Color.WHITE);

       /* Button a=(Button)findViewById(R.id.A);
        a.setBackgroundColor(Color.RED);
        Button p=(Button)findViewById(R.id.P);
        p.setBackgroundColor(Color.WHITE);
*/
    }
    //Fonction appelée au clic d'une des checkbox

}