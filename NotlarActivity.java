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

public class NotlarActivity extends AppCompatActivity {
Button notEkle;
    Context context;
    ListView list;
    String key;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    NotlarAdapter notlarAdapter;
    ArrayList<String> tarihFb;
    ArrayList<String>notFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notlar);
        context=this;
        notEkle=(Button)findViewById(R.id.notEklee);
        list=(ListView)findViewById(R.id.notlarList);
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        notFb=new ArrayList<String>();
        tarihFb=new ArrayList<String>();
        getSupportActionBar().hide();
        notEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notAlert();
            }
        });

        myRef=FirebaseDatabase.getInstance().getReference().child("Notlar");

        myRef=FirebaseDatabase.getInstance().getReference("Notlar").child(mAuth.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                    System.out.println("Data Control" + ds.getValue());
                    notFb.add(hashMap.get("not"));
                    tarihFb.add(hashMap.get("tarih"));
                    notlarAdapter = new NotlarAdapter(getApplicationContext(), tarihFb, notFb);
                    list.setAdapter(notlarAdapter);
                    notlarAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    void notEkle(String tarih,String not){
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Notlar");
        key = myRef.push().getKey();
        myRef.child(mAuth.getUid()).child(key).setValue(new Not(tarih,not)).addOnCompleteListener(NotlarActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(NotlarActivity.this, "Ders Eklendi", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NotlarActivity.this, "Ekleme Başrısız", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void notAlert(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.not_ekle);
        Button ekle=(Button)dialog.findViewById(R.id.notEkleN);
        final EditText tarih=(EditText)dialog.findViewById(R.id.tarihNot);
        final EditText not=(EditText)dialog.findViewById(R.id.not);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notEkle(tarih.getText().toString(),not.getText().toString());
            }
        });
        dialog.show();
    }
}
