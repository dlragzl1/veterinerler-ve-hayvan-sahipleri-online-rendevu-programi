package com.example.e_vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Menu extends AppCompatActivity
{
    private EditText KullanıcıAdiEt;
    private ImageButton Resim;
    private EditText ParolaEt;
    private CheckBox şifregöster;
    private Button girisyap;
    private Button kayıtol;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private TextView sifremiunuttum;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        KullanıcıAdiEt=findViewById(R.id.KullanıcıAdı);
        Resim=findViewById(R.id.Resim);
        ParolaEt=findViewById(R.id.Parola);
        şifregöster=findViewById(R.id.şifregöster);
        girisyap=findViewById(R.id.girisyap);
        kayıtol=findViewById(R.id.kayıtol);
        sifremiunuttum=findViewById(R.id.sifremiunuttum);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();


        girisyap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Login();
            }
        });

        sifremiunuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,Sifredegistir.class));
            }
        });

        kayıtol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,Kayitol.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        şifregöster.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (!isChecked)
                {
                    ParolaEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    ParolaEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    private void Login()
    {
        String KullanıcıAdı=KullanıcıAdiEt.getText().toString();
        String Parola=ParolaEt.getText().toString();
        if (TextUtils.isEmpty(KullanıcıAdı))
        {
            KullanıcıAdiEt.setError("Email Giriniz");
            return;
        }
        else if (TextUtils.isEmpty(Parola))
        {
            ParolaEt.setError("Parola Giriniz");
            return;
        }
        progressDialog.setMessage("Lütfen Bekleyiniz..");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        mAuth.signInWithEmailAndPassword(KullanıcıAdı,Parola).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(Menu.this, "Giriş Başarılı", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Menu.this, Gostergepaneli.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Menu.this, "Giriş Başarısız", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}