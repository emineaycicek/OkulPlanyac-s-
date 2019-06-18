package example.emre.com.emineproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuMainActivity extends AppCompatActivity {
LinearLayout derslerLinear;
LinearLayout dersPLinear;
LinearLayout odevlerLinear;
LinearLayout sinavlarLinear;
LinearLayout tytLinear;
LinearLayout yksLinear;
LinearLayout notlarLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        getSupportActionBar().hide();
        derslerLinear=(LinearLayout)findViewById(R.id.dersler);
        dersPLinear=(LinearLayout)findViewById(R.id.derslerProgramı);
        odevlerLinear=(LinearLayout)findViewById(R.id.odevler);
        sinavlarLinear=(LinearLayout)findViewById(R.id.sinavlar);
        tytLinear=(LinearLayout)findViewById(R.id.tytHesaplama);
        yksLinear=(LinearLayout)findViewById(R.id.yksHesaplama);
        notlarLinear=(LinearLayout)findViewById(R.id.notlar);

        derslerLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuMainActivity.this,DerslerActivity.class);
                startActivity(i);
            }
        });
        dersPLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuMainActivity.this,DersProgramActivity.class);
                startActivity(i);
            }
        });
        odevlerLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuMainActivity.this,OdevlerActivity.class);
                startActivity(i);
            }
        });
        sinavlarLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuMainActivity.this,SinavlarActivity.class);
                startActivity(i);
            }
        });
        tytLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuMainActivity.this,TytActivity.class);
                startActivity(i);
            }
        });
        yksLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuMainActivity.this,YksActivity.class);
                startActivity(i);
            }
        });
        notlarLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuMainActivity.this,NotlarActivity.class);
                startActivity(i);
            }
        });

    }
}
