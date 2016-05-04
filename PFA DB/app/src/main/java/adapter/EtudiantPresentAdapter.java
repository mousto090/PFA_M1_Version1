package adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sourcey.materiallogindemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import entity.Etudiant;
import utility.RoundedImage;

/**
 * Created by SIMO on 16/04/2016.
 */
public class EtudiantPresentAdapter extends BaseAdapter {

    private List<Etudiant> listEtudiant;
    private Context context;
    private ViewHolder viewHolder;

    public EtudiantPresentAdapter(List<Etudiant> listEtudiant, Context context) {
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
        viewHolder = null;
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.etudiant_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.img.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));

        Picasso.with(context).load(listEtudiant.get(position).getImage())
                .transform(new RoundedImage())
                .placeholder(R.drawable.anonym)
                .resize(120, 120)
                .into(viewHolder.img);

        viewHolder.nom.setText(listEtudiant.get(position).getNom());
        viewHolder.prenom.setText(listEtudiant.get(position).getPrenom());


        if(listEtudiant.get(position).isEstAbsent() == true){
            viewHolder.btPresent.setBackgroundColor(Color.WHITE);
            viewHolder.btAbsent.setBackgroundColor(Color.RED);
        }else{
            viewHolder.btPresent.setBackgroundColor(Color.GREEN);
            viewHolder.btAbsent.setBackgroundColor(Color.WHITE);
        }

        viewHolder.btPresent.setTag(position);
        viewHolder.btAbsent.setTag(position);

        viewHolder.btPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //linearLayout des buttton
                LinearLayout layoutButton = (LinearLayout) view.getParent();
                //button Present
                ((Button) layoutButton.getChildAt(0)).setBackgroundColor(Color.GREEN);
                //button absent
                ((Button) layoutButton.getChildAt(1)).setBackgroundColor(Color.WHITE);
                int positionEtudinant = (int) view.getTag();
                listEtudiant.get(positionEtudinant).setEstAbsent(false);
            }
        });

        viewHolder.btAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layoutButton = (LinearLayout) view.getParent();
                //button Present
                ((Button) layoutButton.getChildAt(0)).setBackgroundColor(Color.WHITE);
                //button absent
                ((Button) layoutButton.getChildAt(1)).setBackgroundColor(Color.RED);
                int positionEtudinant = (int) view.getTag();
                listEtudiant.get(positionEtudinant).setEstAbsent(true);
            }
        });
        return convertView;
    }

    public List<Etudiant> getAll(){
        return listEtudiant;
    }
    public static class ViewHolder{

        public final TextView nom;
        public final TextView prenom;
        public final ImageView img;
        public final Button btPresent;
        public final Button btAbsent;

        public ViewHolder(View view) {
            nom=(TextView)view.findViewById(R.id.nom);
            prenom=(TextView)view.findViewById(R.id.prenom);
            img=(ImageView)view.findViewById(R.id.photo);
            btPresent=(Button)view.findViewById(R.id.present_bt);
            btAbsent=(Button)view.findViewById(R.id.absent_bt);
        }
    }


}
