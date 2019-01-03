package com.crazyheads.nilami;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class UserProfile extends Activity {

    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
    private static final int CHOOSE_IMAGE = 101;
    ImageView imageView;
    EditText editTextDisplayName;
    Uri uriProfileImage;
    FirebaseUser user;
    FirebaseAuth mAuth;
    ProgressBar progressbar;
    String profileImageUrl;

    @Override
    protected void onStart() {
        super.onStart();

        user = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        imageView = (ImageView) findViewById(R.id.imageView);
        editTextDisplayName = (EditText) findViewById(R.id.editTextDisplayName);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageView.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference profileImageReference = FirebaseStorage.getInstance().getReference("profilepics/" + user.getUid() + ".jpg");

        if (uriProfileImage != null) {
            progressbar.setVisibility(View.VISIBLE);
            profileImageReference.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressbar.setVisibility(View.GONE);

                    profileImageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void oncameraclick(View view) {
        showImageChooser();
    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }

    public void saveUserInfo(View view) {

        String displayName = editTextDisplayName.getText().toString();

        if (displayName.isEmpty()) {
            editTextDisplayName.setError("Name Required");
            editTextDisplayName.requestFocus();
            return;
        }

        if (user != null && profileImageUrl != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(UserProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            updateUserCollection();


            finish();
            Intent intent = new Intent(UserProfile.this, MainActivity.class);

            //to clear stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else if (user != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(UserProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            updateUserCollection();
            finish();
            Intent intent = new Intent(UserProfile.this, MainActivity.class);

            //to clear stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    }

    private void updateUserCollection() {

        Map<String, Object> newuser = new HashMap<>();
        newuser.put("name", user.getDisplayName());
        newuser.put("email", user.getEmail());

        dbref.child("users").child(user.getUid()).setValue(newuser);
    }
}
