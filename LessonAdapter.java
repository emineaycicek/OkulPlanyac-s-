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

public class LessonAdapter extends ArrayAdapter<String> {
    ArrayList<String> dersList;
    ArrayList<String>ogretmenList;
    ArrayList<String>devamlilikList;
    Context context;
    FirebaseAuth mAuth;
    String key;
    DatabaseReference databaseReferenceFavorite;
    public LessonAdapter(Context context, ArrayList<String> ders, ArrayList<String> ogretmen, ArrayList<String> devamlilik) {
        super(context, R.layout.lesson_rows,ders);
        this.dersList = ders;
        this.ogretmenList = ogretmen;
        this.devamlilikList = devamlilik;
        this.context = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       convertView= LayoutInflater.from(context).inflate(R.layout.lesson_rows,parent,false);
        TextView ders=(TextView)convertView.findViewById(R.id.ders);
        TextView ogretmen=(TextView)convertView.findViewById(R.id.ogretmen);
        TextView devam=(TextView)convertView.findViewById(R.id.devamlilikDurumu);
        LinearLayout linearLayout=(LinearLayout)convertView.findViewById(R.id.linear1);
        devam.setText(devamlilikList.get(position));
        ders.setText(dersList.get(position));
        ogretmen.setText(ogretmenList.get(position));

        mAuth = FirebaseAuth.getInstance();
        databaseReferenceFavorite = FirebaseDatabase.getInstance().getReference("dersler");
        key = databaseReferenceFavorite.push().getKey();




        return convertView;
    }
}