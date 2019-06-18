package example.emre.com.emineproject;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.List;

public class DersPActivtiy extends AppCompatActivity {
String gun;
ListView list;
Button ekle;
Context context;
FirebaseAuth mAuth;
String key;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> dersFb;
    ArrayList<String>ogretmenFb;
    ArrayList<String>baslangicFb;
    ArrayList<String>bitisFb;
    DersPAdapter programAdapter;
    TextView secilenGun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_pactivtiy);
        getSupportActionBar().setTitle("Ders Programı");
        gun=getIntent().getExtras().getString("gun");
        mAuth = FirebaseAuth.getInstance();
        secilenGun=(TextView)findViewById(R.id.secilenGun);
        secilenGun.setText(gun);
        dersFb= new ArrayList<String>();
        ogretmenFb= new ArrayList<String>();
        baslangicFb= new ArrayList<String>();
        bitisFb=new ArrayList<String>();
        Log.d("Gün seçimi",gun);
        database= FirebaseDatabase.getInstance();
        context=this;
        ekle=(Button)findViewById(R.id.derPEkle);
        list=(ListView)findViewById(R.id.derProgramList);
        myRef=FirebaseDatabase.getInstance().getReference().child("DersProgrami");

        myRef=FirebaseDatabase.getInstance().getReference("DersProgrami").child(mAuth.getUid()).child(gun);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    @SuppressWarnings("unchecked")
                    HashMap<String,String> hashMap= (HashMap<String, String>) ds.getValue();
                    System.out.println("Data Control"+ ds.getValue());
                    dersFb.add(hashMap.get("dersAdi"));
                    ogretmenFb.add(hashMap.get("ogretmenAdi"));
                    baslangicFb.add(hashMap.get("baslangicSaati"));
                    System.out.println("Data Control"+ ds.getValue());
                    bitisFb.add(hashMap.get("bitisSaati"));
                    System.out.println("Date"+hashMap.get("date"));
                    System.out.println("Title3"+ds.child("title").getValue());
                    programAdapter=new DersPAdapter(getApplicationContext(),dersFb,ogretmenFb,baslangicFb,bitisFb);
                    list.setAdapter(programAdapter);
                    programAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });
    }
    public void alert(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.ders_programi_ekle);
        Button btnKaydet = (Button) dialog.findViewById(R.id.dersProgramiEkle);
        final EditText dersEt=(EditText)dialog.findViewById(R.id.dersAdiP);
        final EditText ogretmenEt=(EditText)dialog.findViewById(R.id.ogretmenAdiP);
        final EditText baslangicEt=(EditText)dialog.findViewById(R.id.baslangicSaati);
        final EditText bitisEt=(EditText)dialog.findViewById(R.id.bitisSaati);
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
add(dersEt.getText().toString(),ogretmenEt.getText().toString(),baslangicEt.getText().toString(),bitisEt.getText().toString());
            }
        });
        dialog.show();
    }
    public void add(String dersAdi,String ogretmenAdi,String baslangicSaati,String bitisSaati){
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("DersProgrami");
        key = myRef.push().getKey();
        myRef.child(mAuth.getUid()).child(gun).child(key).setValue(new DersP(dersAdi,ogretmenAdi,baslangicSaati,bitisSaati)).addOnCompleteListener(DersPActivtiy.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DersPActivtiy.this, "Ders Eklendi", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DersPActivtiy.this, "Ekleme Başrısız", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
