package com.example.e_vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class hvermek2 extends AppCompatActivity {
    private Button b1;
    private CircleImageView image;
    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;
    private Uri imageUri=null;
    private ProgressDialog imageProgress;
    private StorageReference storageReference;
    private Boolean IsCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hvermek2);
        b1 = findViewById(R.id.dvm2);
        image = findViewById(R.id.foto);
        imageProgress = new ProgressDialog(this);
        mAuth =FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        String user_id=mAuth.getCurrentUser().getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child(user_id).child("İlan");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user_image =snapshot.child("image").getValue().toString();
                imageUri=Uri.parse(user_image);

                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.imageprofil);
                Glide.with(hvermek2.this).setDefaultRequestOptions(requestOptions).load(imageUri).into(image);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(hvermek2.this,new String []{Manifest.permission.READ_EXTERNAL_STORAGE},1);

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(hvermek2.this);


            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageProgress.setTitle("Ekleniyor");
                imageProgress.setMessage("Resim Yükleniyor Lütfen Bekleyiniz");
                imageProgress.show();

                if(imageUri!=null){
                    if(IsCheck){
                        StorageReference userImage=storageReference.child("Hayvan fotoğrafı").child(user_id+".jpg");
                        userImage.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful())
                                {

                                    Uri download_uri = null;
                                    if (task!=null){
                                        UploadTask.TaskSnapshot downloadUri = task.getResult();
                                        String downloadURL = downloadUri.toString();


                                    }
                                    else {
                                        download_uri  = imageUri;
                                    }
                                    Map userUpdateMap=new HashMap();
                                    userUpdateMap.put("image",download_uri.toString());
                                }
                            }
                        });



                    }

                }

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                image.setImageURI(imageUri);
                IsCheck = true;
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}