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

public class NotlarAdapter extends ArrayAdapter<String> {
    ArrayList<String> tarihList;
    ArrayList<String>notList;
    Context context;
    FirebaseAuth mAuth;
    String key;
    DatabaseReference databaseReferenceFavorite;
    public NotlarAdapter(Context context, ArrayList<String> tarih, ArrayList<String> not) {
        super(context, R.layout.notlar_rows,tarih);
        this.tarihList = tarih;
        this.notList = not;
        this.context = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.notlar_rows,parent,false);
        TextView tarih=(TextView)convertView.findViewById(R.id.notlarTarih);
        TextView not=(TextView)convertView.findViewById(R.id.notlarNot);
        not.setText(notList.get(position));
        tarih.setText(tarihList.get(position));

        mAuth = FirebaseAuth.getInstance();
        databaseReferenceFavorite = FirebaseDatabase.getInstance().getReference("Notlar");
        key = databaseReferenceFavorite.push().getKey();




        return convertView;
    }
}
