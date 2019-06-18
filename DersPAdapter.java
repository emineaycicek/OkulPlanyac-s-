package example.emre.com.emineproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DersPAdapter extends ArrayAdapter<String> {
    ArrayList<String> dersList;
    ArrayList<String>ogretmenList;
    ArrayList<String>baslangicSaatiList;
    ArrayList<String>bitisSaatiList;
    Context context;
    FirebaseAuth mAuth;
    String key;
    DatabaseReference databaseReferenceFavorite;
    public DersPAdapter(Context context, ArrayList<String> ders, ArrayList<String> ogretmen, ArrayList<String> baslangicSaati,ArrayList<String>bitisSaati) {
        super(context, R.layout.dersp_rows,ders);
        this.dersList = ders;
        this.ogretmenList = ogretmen;
        this.baslangicSaatiList = baslangicSaati;
        this.bitisSaatiList=bitisSaati;
        this.context = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.dersp_rows,parent,false);
        TextView ders=(TextView)convertView.findViewById(R.id.dersP);
        TextView ogretmen=(TextView)convertView.findViewById(R.id.ogretmenP);
        TextView baslangic=(TextView)convertView.findViewById(R.id.baslangicP);
        TextView bitis=(TextView)convertView.findViewById(R.id.bitisP);
        baslangic.setText(baslangicSaatiList.get(position));
        bitis.setText(bitisSaatiList.get(position));
        ders.setText(dersList.get(position));
        ogretmen.setText(ogretmenList.get(position));

        mAuth = FirebaseAuth.getInstance();
        databaseReferenceFavorite = FirebaseDatabase.getInstance().getReference("DersProgrami");
        key = databaseReferenceFavorite.push().getKey();




        return convertView;
    }
}
