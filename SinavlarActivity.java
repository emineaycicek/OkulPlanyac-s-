package example.emre.com.emineproject;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SinavlarActivity extends AppCompatActivity {
Button sinavEkle;
Context context;
ListView list;
String key;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    SinavlarAdapter sinavlarAdapter;
    ArrayList<String> dersFb;
    ArrayList<String>gunFb;
    ArrayList<String>saatFb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinavlar);
        sinavEkle=(Button)findViewById(R.id.sinavEkle);
        list=(ListView)findViewById(R.id.sinavlarList);
        getSupportActionBar().hide();
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        context=this;
        dersFb=new ArrayList<String>();
        gunFb=new ArrayList<String>();
        saatFb=new ArrayList<String>();
        sinavEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
sinavAlert();
            }
        });
        myRef=FirebaseDatabase.getInstance().getReference().child("Sinavlar");

        myRef=FirebaseDatabase.getInstance().getReference("Sinavlar").child(mAuth.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                    System.out.println("Data Control" + ds.getValue());
                    dersFb.add(hashMap.get("dersAdi"));
                    gunFb.add(hashMap.get("gün"));
                    saatFb.add(hashMap.get("saat"));
                    System.out.println("Data Control" + ds.getValue());
                    System.out.println("Date" + hashMap.get("date"));
                    System.out.println("Title3" + ds.child("title").getValue());
                    sinavlarAdapter = new SinavlarAdapter(getApplicationContext(), dersFb, gunFb, saatFb);
                    list.setAdapter(sinavlarAdapter);
                    sinavlarAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void sinavEkle(String dersAdi,String gun,String saat){
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Sinavlar");
        key = myRef.push().getKey();
        myRef.child(mAuth.getUid()).child(key).setValue(new Sinav(dersAdi,gun,saat)).addOnCompleteListener(SinavlarActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(SinavlarActivity.this, "Ders Eklendi", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SinavlarActivity.this, "Ekleme Başrısız", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void sinavAlert(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.sinav_ekle);
        Button ekle=(Button)dialog.findViewById(R.id.sinavEkleS);
        final EditText dersEt=(EditText)dialog.findViewById(R.id.dersAdiS);
        final EditText gunEt=(EditText)dialog.findViewById(R.id.gun);
         final EditText saat=(EditText)dialog.findViewById(R.id.saat);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
sinavEkle(dersEt.getText().toString(),gunEt.getText().toString(),saat.getText().toString());
            }
        });
        dialog.show();
    }

}
