package com.example.e_vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Sifredegistir extends AppCompatActivity {

    private EditText mailtext;
    private Button mailgonder;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifredegistir);

        mAuth=FirebaseAuth.getInstance();

        mailtext=findViewById(R.id.mailtext);
        mailgonder=findViewById(R.id.mailgonder);

        mailgonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mailtext.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Sifredegistir.this,"E-posta Adresinizi Giriniz!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Sifredegistir.this,"Lütfen E-posta Adresinizi Kontrol Ediniz",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Sifredegistir.this,Menu.class));
                            }
                            else
                            {
                                String message =task.getException().getMessage();
                                Toast.makeText(Sifredegistir.this,"Hata Oluştu!"+message,Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }
}