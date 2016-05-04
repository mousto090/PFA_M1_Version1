package com.sourcey.auto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sourcey.materiallogindemo.R;

import org.lucasr.twowayview.TwoWayView;

import java.util.Arrays;

import Connexion.FetchEtudiantInfos;
import adapter.EtudiantAbsentAdapter;
import adapter.EtudiantPresentAdapter;
import entity.Etudiant;
import utility.NestedListView;


public class EtudiantFragment extends Fragment implements  FetchEtudiantInfos.AsyncEtudiantData{


    private ListView mListViewEtudiantPresent = null;
    private FetchEtudiantInfos mFetchEtudiantInfos ;
    private String loginName;
    private String classe;

    public EtudiantFragment() {
        // Required empty public constructor
    }

    public static EtudiantFragment newInstance() {
        EtudiantFragment fragment = new EtudiantFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_etudiant, container, false);
        mListViewEtudiantPresent = (ListView) rootView.findViewById(R.id.listView_etudiant_present);


        Intent intent = getActivity().getIntent();
        if (null != intent && intent.hasExtra("login") && intent.hasExtra("classe")) {
            String loginName = intent.getStringExtra("login");
            String classe = intent.getStringExtra("classe");

            mFetchEtudiantInfos = new FetchEtudiantInfos();
            mFetchEtudiantInfos.delegateEtudiantData = this;
            mFetchEtudiantInfos.execute(loginName);

        }

        return rootView;

    }

    @Override
    public void onLoadEtudiantDataFinish(Etudiant[] result) {

        Log.v("onLoadEtudiantDCheckLis", Arrays.toString(result));
//        mListViewEtudiantAdapter = new MyListeEtudiantAdapter(Arrays.asList(result),this);
        final EtudiantPresentAdapter listViewEtudiantPresentAdapter = new EtudiantPresentAdapter(
                Arrays.asList(result), getActivity());
        mListViewEtudiantPresent.setAdapter(listViewEtudiantPresentAdapter);


    }
}
