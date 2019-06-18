package example.emre.com.emineproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SinavlarAdapter extends ArrayAdapter<String> {
    ArrayList<String> dersList;
    ArrayList<String>gunList;
    ArrayList<String>saatList;
    Context context;
    FirebaseAuth mAuth;
    String key;
    DatabaseReference databaseReferenceFavorite;
    public SinavlarAdapter(Context context, ArrayList<String> ders, ArrayList<String> gun, ArrayList<String> saat) {
        super(context, R.layout.sinav_rows,ders);
        this.dersList = ders;
        this.gunList = gun;
        this.saatList= saat;
        this.context = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.sinav_rows,parent,false);
        TextView ders=(TextView)convertView.findViewById(R.id.sinavDersAdi);
        TextView gun=(TextView)convertView.findViewById(R.id.sinavGun);
        TextView saat=(TextView) convertView.findViewById(R.id.sinavSaat);
        ders.setText(dersList.get(position));
        gun.setText(gunList.get(position));
        saat.setText(saatList.get(position));

        mAuth = FirebaseAuth.getInstance();
        databaseReferenceFavorite = FirebaseDatabase.getInstance().getReference("Sinavlar");
        key = databaseReferenceFavorite.push().getKey();




        return convertView;
    }
}
