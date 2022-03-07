package com.example.e_vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class hvermek extends AppCompatActivity {
    Button b1;
    EditText et1,et2,et3;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hvermek);
        mAuth= FirebaseAuth.getInstance();
        b1 = findViewById(R.id.dvm1);
        et1 = findViewById(R.id.turtxt1);
        et2 = findViewById(R.id.turtxt2);
        et3 = findViewById(R.id.turtxt3);
        mAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id=mAuth.getCurrentUser().getUid();
                String veritutucu1 = et1.getText().toString();
                String veritutucu2 = et2.getText().toString();
                String veritutucu3 = et3.getText().toString();
                mDatabase = FirebaseDatabase.getInstance().getReference().child(user_id).child("İlan");
                HashMap<String,String> userMap = new HashMap<>();
                userMap.put("Tür",veritutucu1);
                userMap.put("Yaş",veritutucu2);
                userMap.put("Açıklama",veritutucu3);
                userMap.put("image","default");
                mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent mainIntent = new Intent(hvermek.this,hvermek2.class);
                            startActivity(mainIntent);
                        }

                    }
                });


            }

        });
    }
}