package com.example.e_vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class HayvanAlmak extends AppCompatActivity {
    Button b2;
    EditText et4,et5;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hayvan_almak);
        mAuth= FirebaseAuth.getInstance();
        b2 = findViewById(R.id.dvm2);
        et4 = findViewById(R.id.turtxt4);
        et5 = findViewById(R.id.turtxt5);
        mAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id=mAuth.getCurrentUser().getUid();
                String veritutucu4 = et4.getText().toString();
                String veritutucu5 = et5.getText().toString();
                mDatabase = FirebaseDatabase.getInstance().getReference().child(user_id).child("İlan");
                HashMap<String,String> userMap = new HashMap<>();
                userMap.put("Tür",veritutucu4);
                userMap.put("Şehir",veritutucu5);
                userMap.put("image","default");
                mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent mainIntent = new Intent(HayvanAlmak.this,Sahip.class);
                            startActivity(mainIntent);
                        }

                    }
                });


            }

        });
    }
}