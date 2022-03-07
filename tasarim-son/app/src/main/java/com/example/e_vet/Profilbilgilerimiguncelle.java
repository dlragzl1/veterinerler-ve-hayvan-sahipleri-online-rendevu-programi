package com.example.e_vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profilbilgilerimiguncelle extends AppCompatActivity {
    private EditText Email1, parolakayit1;
    private Button guncellemebutton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilbilgilerimiguncelle);
        firebaseAuth = FirebaseAuth.getInstance();
        Email1 = findViewById(R.id.Email);
        parolakayit1 = findViewById(R.id.parolakayit);
        guncellemebutton = findViewById(R.id.guncellemebutton);
        progressDialog = new ProgressDialog(this);
        guncellemebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guncelle();

            }
        });

    }
    public void guncelle(){

        String Email = Email1.getText().toString();
        String parolakayit = parolakayit1.getText().toString();
        if (TextUtils.isEmpty(Email)) {
            Email1.setError("Yeni Bir Email Giriniz");
            return;
        } else if (TextUtils.isEmpty(parolakayit)) {
            parolakayit1.setError("Yeni Bir Parola Giriniz");
            return;
        }
        else if (!isVallidEmail(Email)) {
        Email1.setError("Geçersiz Bir Yeni Email Adresi Girdiniz Tekrar Deneyiniz");
        return;
        }
        progressDialog.setMessage("Lütfen Bekleyiniz..");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(Email, parolakayit).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Profilbilgilerimiguncelle.this, "Güncelleme Başarılı", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Profilbilgilerimiguncelle.this, Gostergepaneli.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Profilbilgilerimiguncelle.this, "Güncelleme Başarısız", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isVallidEmail(CharSequence target)
    {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



    }
