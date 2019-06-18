package example.emre.com.emineproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TytActivity extends AppCompatActivity {
    Button tytHesapla;
    EditText diplomaNotuEt;
    EditText turkceDEt;
    EditText turkceYEt;
    EditText matematikDEt;
    EditText matematikYEt;
    EditText sosyalDEt;
    EditText sosyalYEt;
    EditText fenDEt;
    EditText fenYEt;
    EditText sonucEt;
    double yanlis,dogru,kalan,matD,matY,trD,trY,fenD,fenY,sosyalD,sosyalY;
    double trNet,matNet,sosyalNet,fenNet,diplomaNotu,diplomaSonuc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyt);
        tytHesapla=(Button)findViewById(R.id.tytHesapla);
        diplomaNotuEt=(EditText)findViewById(R.id.diplomaNotu);
        turkceDEt=(EditText)findViewById(R.id.turkceD);
        turkceYEt=(EditText)findViewById(R.id.turkceY);
        matematikDEt=(EditText)findViewById(R.id.matematikTytD);
        matematikYEt=(EditText)findViewById(R.id.matematikTytY);
        sosyalDEt=(EditText)findViewById(R.id.sosyalTytD);
        sosyalYEt=(EditText)findViewById(R.id.sosyalTytY);
        fenDEt=(EditText)findViewById(R.id.fenTytD);
        fenYEt=(EditText)findViewById(R.id.fenTytY);
        sonucEt=(EditText)findViewById(R.id.sonucTyt);
getSupportActionBar().hide();
       tytHesapla.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               diplomaNotu=Double.parseDouble(diplomaNotuEt.getText().toString());
               diplomaSonuc=diplomaNotu*0.6;
               trY=Double.parseDouble(turkceYEt.getText().toString());
               trD=Double.parseDouble(turkceDEt.getText().toString());
               trY=trY/4;
               trNet=(trD-trY)*3.3;

               matY=Double.parseDouble(matematikYEt.getText().toString());
               matD=Double.parseDouble(matematikDEt.getText().toString());
               matY=matY/4;
               matNet=(matD-matY)*3.3;

               sosyalD=Double.parseDouble(sosyalDEt.getText().toString());
               sosyalY=Double.parseDouble(sosyalYEt.getText().toString());
               sosyalY=sosyalY/4;
               sosyalNet=(sosyalD-sosyalY)*3.4;

               fenD=Double.parseDouble(fenDEt.getText().toString());
               fenY=Double.parseDouble(fenYEt.getText().toString());
               fenY=fenY/4;
               fenNet=(fenD-fenY)*3.4;
               fenNet=fenNet+matNet+sosyalNet+trNet+100+diplomaSonuc;
               sonucEt.setText(String.valueOf(fenNet));
               Log.d("dddddd",String.valueOf(fenNet));


           }
       });

    }
}
