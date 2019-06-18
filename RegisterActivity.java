package example.emre.com.emineproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
Button registerOk;
EditText usernameEt;
EditText genderEt;
EditText ageEt;
EditText mailEt;
EditText passwordEt;
TextView tx;
FirebaseAuth auth;
FirebaseUser firebaseUser;
DatabaseReference myRef;
FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(RegisterActivity.this);
        firebaseUser=auth.getCurrentUser();
        database= FirebaseDatabase.getInstance();
        registerOk=(Button)findViewById(R.id.registerOk);
        usernameEt=(EditText)findViewById(R.id.username);
        genderEt=(EditText)findViewById(R.id.gender);
        ageEt=(EditText)findViewById(R.id.age);
        mailEt=(EditText)findViewById(R.id.mail);
        passwordEt=(EditText)findViewById(R.id.password);
        tx=(TextView)findViewById(R.id.textToLogin);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        registerOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameEt.getText().toString().length()<1){
                    Toast.makeText(RegisterActivity.this, "Lütfen Ad Soyad Bölümünü Doldurunuz", Toast.LENGTH_SHORT).show();
                }else if(!genderEt.getText().toString().equals("Erkek")|| !genderEt.getText().toString().equals("Kadın")){
                    Toast.makeText(RegisterActivity.this, "Lütfen Erkek veya Kadın Olarak Giriniz", Toast.LENGTH_SHORT).show();
                }
                else if (ageEt.getText().toString().length()<1){
                    Toast.makeText(RegisterActivity.this, "Lütfen Yaşınızı Giriniz", Toast.LENGTH_SHORT).show();
                }else  if (!mailEt.getText().toString().contains("@")){
                    Toast.makeText(RegisterActivity.this, "Lütfen Mail Adresiniz Doğru Giriniz", Toast.LENGTH_SHORT).show();
                }else if (passwordEt.getText().toString().length()<5){
                    Toast.makeText(RegisterActivity.this, "Lütfen Parolanızı 5 haneden Fazla Giriniz", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("Msg","Register Success");
                    auth.createUserWithEmailAndPassword(mailEt.getText().toString(),passwordEt.getText().toString()).addOnSuccessListener(RegisterActivity.this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
                    myRef = database.getReference();
                    User users= new User(usernameEt.getText().toString(),genderEt.getText().toString(),ageEt.getText().toString(),mailEt.getText().toString());
                    myRef.child("users").child(auth.getUid()).setValue(users);
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Toast.makeText(RegisterActivity.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(i);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(RegisterActivity.this, "Kayıt Başarısız", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }
}
