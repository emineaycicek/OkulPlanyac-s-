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

public class HomeworkAdapter extends ArrayAdapter<String> {
    ArrayList<String> dersList;
    ArrayList<String>konuList;
    ArrayList<String>bitisTarihiList;
    ArrayList<String>aciklamaList;
    Context context;
    FirebaseAuth mAuth;
    String key;
    DatabaseReference databaseReferenceFavorite;
    public HomeworkAdapter(Context context, ArrayList<String> ders, ArrayList<String> konu, ArrayList<String> bitisTarihi,ArrayList<String>aciklama) {
        super(context, R.layout.odevler_rows,ders);
        this.dersList = ders;
        this.konuList = konu;
        this.bitisTarihiList = bitisTarihi;
        this.aciklamaList=aciklama;
        this.context = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.odevler_rows,parent,false);
        TextView ders=(TextView)convertView.findViewById(R.id.dersAdiOR);
        TextView konu=(TextView)convertView.findViewById(R.id.konuO);
        ImageView cancel=(ImageView)convertView.findViewById(R.id.cancel);
        TextView bitisTarihi=(TextView)convertView.findViewById(R.id.bitisTarihi);
        TextView aciklama=(TextView)convertView.findViewById(R.id.aciklama);
        ders.setText(dersList.get(position));
        konu.setText(konuList.get(position));
        bitisTarihi.setText(bitisTarihiList.get(position));
        aciklama.setText(aciklamaList.get(position));
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    }
});
        mAuth = FirebaseAuth.getInstance();
        databaseReferenceFavorite = FirebaseDatabase.getInstance().getReference("Odevler");
        key = databaseReferenceFavorite.push().getKey();




        return convertView;
    }
}
