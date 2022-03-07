package com.example.e_vet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class Hayvanlaricinilkyardim extends AppCompatActivity {

    CardView cardArisokmasi;
    CardView cardGidazehirlenmesi;
    CardView cardElektrikcarpmasi;
    CardView cardYanik;
    CardView cardYabancicisim;
    CardView cardGenelyardim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hayvanlaricinilkyardim);
        cardArisokmasi = findViewById(R.id.cardArisokmasi);
        cardGidazehirlenmesi = findViewById(R.id.cardGidazehirlenmesi);
        cardElektrikcarpmasi = findViewById(R.id.cardElektrikcarpmasi);
        cardYanik = findViewById(R.id.cardYanik);
        cardYabancicisim= findViewById(R.id.cardYabancicisim);
        cardGenelyardim = findViewById(R.id.cardGenelyardim);


        cardArisokmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hayvanlaricinilkyardim.this,Arisokmasi.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });
        cardGidazehirlenmesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hayvanlaricinilkyardim.this,Gidazehirlenmesi.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        cardElektrikcarpmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hayvanlaricinilkyardim.this,Elektrikcarpmasi.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        cardYanik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hayvanlaricinilkyardim.this,Yanik.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });
        cardYabancicisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hayvanlaricinilkyardim.this,Ybancicisimyutma.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });
        cardGenelyardim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hayvanlaricinilkyardim.this,Genelilkyardim.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }
}