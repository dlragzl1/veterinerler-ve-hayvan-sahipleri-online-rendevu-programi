package com.example.e_vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Kayitol extends AppCompatActivity {
    private EditText emailEt, ParolaKayitEt, ParolaKayit2Et;
    private Button Kayitbuton;
    private TextView Girisyapkayit;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        ParolaKayitEt = findViewById(R.id.ParolaKayit);
        ParolaKayit2Et = findViewById(R.id.ParolaKayit2);
        Kayitbuton = findViewById(R.id.Kayitbuton);
        progressDialog = new ProgressDialog(this);
        Girisyapkayit = findViewById(R.id.Girisyapkayit);
        Kayitbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        Girisyapkayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kayitol.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void Register() {
        String email = emailEt.getText().toString();
        String ParolaKayit = ParolaKayitEt.getText().toString();
        String ParolaKayit2 = ParolaKayit2Et.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEt.setError("Email Giriniz");
            return;
        } else if (TextUtils.isEmpty(ParolaKayit)) {
            ParolaKayitEt.setError("Parola Giriniz");
            return;
        } else if (TextUtils.isEmpty(ParolaKayit2)) {
            ParolaKayit2Et.setError("Parolayı Doğrulayınız");
            return;
        } else if (!ParolaKayit.equals(ParolaKayit2)) {
            ParolaKayit2Et.setError("Farklı Şifre");
            return;
        } else if (!isVallidEmail(email)) {
            emailEt.setError("Geçersiz Email");
            return;
        }
        progressDialog.setMessage("Lütfen Bekleyiniz..");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email, ParolaKayit).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(Kayitol.this, "Kayıt Başarılı", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Kayitol.this, Gostergepaneli.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Kayitol.this, "Kayıt Başarısız", Toast.LENGTH_LONG).show();
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