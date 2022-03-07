package com.example.e_vet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Gostergepaneli extends AppCompatActivity
{
    CardView cardProfil;
    CardView cardProfilimiGuncelle;
    CardView cardCevremdekiKlinikler;
    CardView cardIlkYardim;
    CardView cardHekimler;
    CardView cardCikisYap;
    CardView cardDestek;
    CardView cardSahip;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gostergepaneli);

        cardProfil = findViewById(R.id.cardProfil);
        cardProfilimiGuncelle = findViewById(R.id.cardProfilimiGuncelle);
        cardCevremdekiKlinikler = findViewById(R.id.cardCevremdekiKlinikler);
        cardIlkYardim = findViewById(R.id.cardIlkYardim);
        cardHekimler= findViewById(R.id.cardHekimler);
        cardCikisYap = findViewById(R.id.cardCikisYap);
        cardDestek = findViewById(R.id.cardDestek);
        cardSahip = findViewById(R.id.cardSahip);


        cardProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gostergepaneli.this,Profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        cardProfilimiGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gostergepaneli.this,Profilbilgilerimiguncelle.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        cardCevremdekiKlinikler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gostergepaneli.this,MappsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        cardIlkYardim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gostergepaneli.this,Hayvanlaricinilkyardim.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });
        cardHekimler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gostergepaneli.this,Profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });
        cardCikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gostergepaneli.this,Menu.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        cardSahip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gostergepaneli.this,Sahip.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

}