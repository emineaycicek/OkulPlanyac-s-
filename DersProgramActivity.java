package example.emre.com.emineproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DersProgramActivity extends AppCompatActivity {
    LinearLayout pztLinear;
    LinearLayout saliLinear;
    LinearLayout carsambaLinear;
    LinearLayout persembeLinear;
    LinearLayout cumaLinear;
    LinearLayout cmtLinear;
    LinearLayout pazarLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_program);
        pztLinear=(LinearLayout)findViewById(R.id.pazartesi);
        saliLinear=(LinearLayout)findViewById(R.id.sali);
        carsambaLinear=(LinearLayout)findViewById(R.id.carsamba);
        persembeLinear=(LinearLayout)findViewById(R.id.persembe);
        cumaLinear=(LinearLayout)findViewById(R.id.cuma);
        cmtLinear=(LinearLayout)findViewById(R.id.cumartesi);
        pazarLinear=(LinearLayout)findViewById(R.id.pazar);
        getSupportActionBar().hide();
        pztLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPage("Pazartesi");
            }
        });
        saliLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPage("Sali");
            }
        });
        carsambaLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPage("Çarşamba");
            }
        });
        persembeLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPage("Perşembe");
            }
        });
        cumaLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPage("Cuma");
            }
        });
        cmtLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPage("Cumartesi");
            }
        });
        pazarLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewPage("Pazar");
            }
        });
    }
    public void previewPage(String gun){
        Intent i=new Intent(DersProgramActivity.this,DersPActivtiy.class);
        i.putExtra("gun",gun);
        startActivity(i);
    }
}
