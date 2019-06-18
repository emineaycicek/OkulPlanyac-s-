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

public class OdevlerActivity extends AppCompatActivity {
Button odevEkle;
Context context;
    FirebaseAuth mAuth;
    String key;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView list;
    ArrayList<String> dersFb;
    ArrayList<String>konuFb;
    ArrayList<String>bitisTarihiFb;
    ArrayList<String>aciklamaFb;
    HomeworkAdapter homeworkAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odevler);
        odevEkle=(Button)findViewById(R.id.odevEkle);
        list=(ListView)findViewById(R.id.listOdevler);
        context=this;
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        dersFb=new ArrayList<String>();
        konuFb=new ArrayList<String>();
        bitisTarihiFb=new ArrayList<String>();
        aciklamaFb=new ArrayList<String>();
        database= FirebaseDatabase.getInstance();
        myRef=FirebaseDatabase.getInstance().getReference().child("Odevler");
        myRef=FirebaseDatabase.getInstance().getReference("Odevler").child(mAuth.getUid());
myRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot ds:dataSnapshot.getChildren()){
            @SuppressWarnings("unchecked")
            HashMap<String,String> hashMap= (HashMap<String, String>) ds.getValue();
            System.out.println("Data Control"+ ds.getValue());
            dersFb.add(hashMap.get("dersAdi"));
            konuFb.add(hashMap.get("konu"));
            bitisTarihiFb.add(hashMap.get("bitisTarihi"));
            System.out.println("Data Control"+ ds.getValue());
            aciklamaFb.add(hashMap.get("aciklama"));
            homeworkAdapter=new HomeworkAdapter(getApplicationContext(),dersFb,konuFb,bitisTarihiFb,aciklamaFb);
            list.setAdapter(homeworkAdapter);
            homeworkAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
        odevEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
alert();
            }
        });
    }
    public void  alert(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.odev_ekle);
        Button btnKaydet = (Button) dialog.findViewById(R.id.addHomework);
        final EditText dersAdi = (EditText) dialog.findViewById(R.id.dersAdiO);
        final EditText konu = (EditText) dialog.findViewById(R.id.konu);
        final EditText bitisTarihi = (EditText) dialog.findViewById(R.id.bitisTarihiO);
        final EditText aciklama = (EditText) dialog.findViewById(R.id.aciklamaO);
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
addHomework(dersAdi.getText().toString(),konu.getText().toString(),bitisTarihi.getText().toString(),aciklama.getText().toString());
            }
        });
dialog.show();
    }
    public  void addHomework(String dersAdi,String konu,String bitisTarihi,String aciklama){
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Odevler");
        key = myRef.push().getKey();
        myRef.child(mAuth.getUid()).child(key).setValue(new Homework(dersAdi,konu,bitisTarihi,aciklama)).addOnCompleteListener(OdevlerActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(OdevlerActivity.this, "Ders Eklendi", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(OdevlerActivity.this, "Ekleme Başrısız", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
