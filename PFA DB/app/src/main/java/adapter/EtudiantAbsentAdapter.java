package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sourcey.materiallogindemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import entity.Etudiant;

/**
 * Created by SIMO on 16/04/2016.
 */
public class EtudiantAbsentAdapter extends BaseAdapter {

    private List<Etudiant> listEtudiant;
    private Context context;

    public EtudiantAbsentAdapter(List<Etudiant> listEtudiant, Context context) {
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
            convertView = inflater.inflate(R.layout.etudiant_absent_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.photo.setLayoutParams(
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));


        Picasso.with(context).load(listEtudiant.get(position).getImage())
                .placeholder(R.drawable.anonym)
                .error(R.drawable.anonym)
				  .resize(160, 160)
                .into(viewHolder.photo);


        viewHolder.nom.setText(listEtudiant.get(position).getNom());
        return convertView;
    }

    /*public void remove(int position){
        listEtudiant.remove(position);
        //notifyDataSetChanged();
    }

    public void add(Etudiant e){
        listEtudiant.add(e);
        //notifyDataSetChanged();
    }*/

    public List<Etudiant> getAll(){
        return listEtudiant;
    }


    public static class ViewHolder{

        public final TextView nom;
        public final ImageView photo;
//        public final CheckBox chek;

        public ViewHolder(View view) {
            nom=(TextView)view.findViewById(R.id.nom);
            photo=(ImageView)view.findViewById(R.id.photo);
//            chek=(CheckBox)view.findViewById(R.id.check);

        }
    }

}
