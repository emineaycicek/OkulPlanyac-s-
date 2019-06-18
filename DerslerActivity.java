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

public class DerslerActivity extends AppCompatActivity {
Button dersEkle;
Context context;
String key;
    ListView list;
    FirebaseAuth mAuth;
    ArrayList<String> dersFb;
    ArrayList<String>ogretmenFb;
    ArrayList<String>devamlilikFb;
    LessonAdapter lessonAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersler);
        dersEkle=(Button)findViewById(R.id.addLesson);
        list=(ListView)findViewById(R.id.listDersler);
        mAuth = FirebaseAuth.getInstance();
        myRef=FirebaseDatabase.getInstance().getReference().child("Dersler");
        dersFb= new ArrayList<String>();
        ogretmenFb= new ArrayList<String>();
        devamlilikFb= new ArrayList<String>();
        context=this;
        getSupportActionBar().hide();
        myRef=FirebaseDatabase.getInstance().getReference("Dersler").child(mAuth.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    @SuppressWarnings("unchecked")
                    HashMap <String,String>hashMap= (HashMap<String, String>) ds.getValue();
                    System.out.println("Data Control"+ ds.getValue());
                    dersFb.add(hashMap.get("dersAdi"));
                    ogretmenFb.add(hashMap.get("ogretmenAdi"));
                    devamlilikFb.add(hashMap.get("devamDurumu"));
                    lessonAdapter=new LessonAdapter(getApplicationContext(),dersFb,ogretmenFb,devamlilikFb);
                    list.setAdapter(lessonAdapter);
                    lessonAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dersEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });
    }
    public void showAlert(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.lesson_add);
        Button btnKaydet = (Button) dialog.findViewById(R.id.dersEkle);
        final EditText dersEt=(EditText)dialog.findViewById(R.id.dersAdi);
        final EditText ogretmenEt=(EditText)dialog.findViewById(R.id.ogretmenAdi);
        final EditText durumEt=(EditText)dialog.findViewById(R.id.devamlilik);
btnKaydet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        addLesson(dersEt.getText().toString(),ogretmenEt.getText().toString(),durumEt.getText().toString());
    }
});
        dialog.show();
    }
    public void addLesson(String ders,String ogretmen,String durum){
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Dersler");
        key = myRef.push().getKey();
        myRef.child(mAuth.getUid()).child(key).setValue(new Lesson(ders,ogretmen,durum)).addOnCompleteListener(DerslerActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DerslerActivity.this, "Ders Eklendi", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DerslerActivity.this, "Ekleme Başrısız", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getData(){

    }
}
