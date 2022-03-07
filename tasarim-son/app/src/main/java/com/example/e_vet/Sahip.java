package com.example.e_vet;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.cardview.widget.CardView;


public class Sahip extends AppCompatActivity {
    Button vermek;
    Button almak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahip);
        almak = findViewById(R.id.hayvanal);
        vermek = findViewById(R.id.hayvanver);

        vermek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sahip.this,hvermek.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        almak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sahip.this,HayvanAlmak.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }
}
