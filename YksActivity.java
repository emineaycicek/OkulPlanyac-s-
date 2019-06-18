package example.emre.com.emineproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class YksActivity extends AppCompatActivity {
Button aytHesapla;
EditText edebiyatD;
EditText edebiyatY;
EditText sosyalD;
EditText sosyalY;
EditText matematikD;
EditText matematikY;
EditText fenD;
EditText fenY;
EditText dilD;
EditText dilY;
EditText sonucAyt;
EditText dipNot;
EditText fizikD,fizikY;
TextView dilTx,sayisalTx,sozelTx,esitTx;
EditText biyolojiD,biyolojiY,tarihD,tarihY,kimyaD,kimyaY,felsefeD,felsefeY,cografyaD,cografyaY,dinD,dinY;
double thD,thY,felD,felY,cgrfD,cgrfY,bylD,bylY,kimD,kimY,dnD,dnY;
double esitAgirlik,sayisal,sozel,dil,matD,matY,trD,trY;
    double dlNet,fzNet,byNet,kimNet,dlD,dlY,trNet,matNet,diplomaNotu,tarihNet,felsefeNet,dinNet,cografyaNet,fzD,fzY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yks);
        edebiyatD=(EditText)findViewById(R.id.edebiyatD);
        dipNot=(EditText)findViewById(R.id.dipNot);
        fizikD=(EditText)findViewById(R.id.fizikD);
        fizikY=(EditText)findViewById(R.id.fizikY);
        sayisalTx=(TextView)findViewById(R.id.sayisalText);
        sozelTx=(TextView)findViewById(R.id.sozelText);
        dilTx=(TextView)findViewById(R.id.dilText);
        esitTx=(TextView)findViewById(R.id.esitText);
        edebiyatY=(EditText)findViewById(R.id.edebiyatY);
        cografyaD=(EditText)findViewById(R.id.cografyaD);
        cografyaY=(EditText)findViewById(R.id.cografyaY);
        biyolojiD=(EditText)findViewById(R.id.biyolojiD);
        biyolojiY=(EditText)findViewById(R.id.biyolojiY);
        tarihD=(EditText)findViewById(R.id.tarihD);
        tarihY=(EditText)findViewById(R.id.tarihY);
        kimyaD=(EditText)findViewById(R.id.kimyaD);
        kimyaY=(EditText)findViewById(R.id.kimyaY);
        felsefeD=(EditText)findViewById(R.id.felsefeD);
        dinD=(EditText)findViewById(R.id.dinD);
        dinY=(EditText)findViewById(R.id.dinY);
        felsefeY=(EditText)findViewById(R.id.felsefeY);
        sosyalD=(EditText)findViewById(R.id.sosyalYksD);
        sosyalY=(EditText)findViewById(R.id.sosyalYksY);
        matematikD=(EditText)findViewById(R.id.matematikYksD);
        matematikY=(EditText)findViewById(R.id.matematikYksY);
        dilD=(EditText)findViewById(R.id.dilD);
        dilY=(EditText)findViewById(R.id.dilY);
        sonucAyt=(EditText)findViewById(R.id.sonucYks);
        aytHesapla=(Button)findViewById(R.id.aytHesapla);
        getSupportActionBar().hide();



        aytHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thD=Double.parseDouble(tarihD.getText().toString());
                thY=Double.parseDouble(tarihY.getText().toString());
                trD=Double.parseDouble(edebiyatD.getText().toString());
                trY=Double.parseDouble(edebiyatY.getText().toString());
                cgrfD=Double.parseDouble(cografyaD.getText().toString());
                cgrfY=Double.parseDouble(cografyaY.getText().toString());
                kimD=Double.parseDouble(kimyaD.getText().toString());
                kimY=Double.parseDouble(kimyaY.getText().toString());
                bylD=Double.parseDouble(biyolojiD.getText().toString());
                bylY=Double.parseDouble(biyolojiY.getText().toString());
                felD=Double.parseDouble(felsefeD.getText().toString());
                felY=Double.parseDouble(felsefeY.getText().toString());
                dnD=Double.parseDouble(dinD.getText().toString());
                dnY=Double.parseDouble(dinY.getText().toString());
                matD=Double.parseDouble(matematikD.getText().toString());
                matY=Double.parseDouble(matematikY.getText().toString());
                fzD=Double.parseDouble(fizikD.getText().toString());
                fzY=Double.parseDouble(fizikY.getText().toString());
                dlD=Double.parseDouble(dilD.getText().toString());
                dlY=Double.parseDouble(dilY.getText().toString());
                trNet=(trD-(trY/4))*3;
                matNet=(matD-(matY/4))*3;
                tarihNet=(thD-(thY/4))*2.8;
                cografyaNet=(cgrfD-(cgrfY/4))*3.33;
                esitAgirlik=trNet+matNet+cografyaNet+tarihNet+100+(diplomaNotu*0.6);
                Log.d("Test Esit Agırlık",String.valueOf(esitAgirlik));
                matNet=(matD-(matY/4))*3;
                fzNet=(fzD-(fzY/4))*2.85;
                kimNet=(kimD-(kimY/4))*3.07;
                byNet=(bylD-(bylY/4))*3.07;
                sayisal=matNet+fzNet+kimNet+byNet+100+(diplomaNotu*0.6);
                Log.d("Test Sayisal",String.valueOf(sayisal));
                trNet=(trD-(trY/4))*3;
                tarihNet=(thD-(thY/4))*2.8;
                cografyaNet=(cgrfD-(cgrfY/4))*3.33;
                felsefeNet=(felD-(felY/4))*3;
                dinNet=(dnD-(dnY/4))*3.33;
                sozel=trNet+tarihNet+cografyaNet+felsefeNet+dinNet+100+(diplomaNotu*0.6);
                Log.d("Test Sozel",String.valueOf(sozel));
                dlNet=(dlD-(dlY/4))*3;
                dil=dlNet+100+(diplomaNotu*0.6);
                Log.d("Test dil",String.valueOf(dil));
                esitTx.setText("Eşit A."+String.valueOf(esitAgirlik));
                sozelTx.setText("Sözel"+String.valueOf(sozel));
                sayisalTx.setText("Sayısal"+String.valueOf(sayisal));
                dilTx.setText("Dil"+dil);
            }
        });
    }
}
