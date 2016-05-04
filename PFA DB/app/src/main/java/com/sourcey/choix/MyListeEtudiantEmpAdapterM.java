package com.sourcey.choix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sourcey.materiallogindemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import entity.Etudiant;

/**
 * Created by SIMO on 16/04/2016.
 */
public class MyListeEtudiantEmpAdapterM extends BaseAdapter {

    private List<Etudiant> listEtudiant;
    private Context context;

    public MyListeEtudiantEmpAdapterM(List<Etudiant> listEtudiant, Context context) {
        this.listEtudiant = listEtudiant;
        this.context = context;
    }

    public void add(List<Etudiant> etudiants){
        listEtudiant.addAll(etudiants);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return listEtudiant.size();
    }

    @Override
    public Object getItem(int position) {
        return listEtudiant.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.etudiant_item_emp, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.img.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));



        Picasso.with(context).load(listEtudiant.get(position).getImage())
                .placeholder(R.drawable.anonym)
                .error(R.drawable.anonym)
				  .resize(160, 160)
                .into(viewHolder.img);


        viewHolder.nom.setText(listEtudiant.get(position).getNom());
        viewHolder.prenom.setText(listEtudiant.get(position).getPrenom());

        return convertView;
    }

    public static class ViewHolder{

        public final TextView nom;
        public final TextView prenom;
        public final ImageView img;
//        public final CheckBox chek;

        public ViewHolder(View view) {
            nom=(TextView)view.findViewById(R.id.nom);
            prenom=(TextView)view.findViewById(R.id.prenom);
            img=(ImageView)view.findViewById(R.id.movie_poster);
//            chek=(CheckBox)view.findViewById(R.id.check);

        }
    }

}
