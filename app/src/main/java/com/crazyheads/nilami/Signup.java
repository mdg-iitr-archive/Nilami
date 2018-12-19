package com.crazyheads.nilami;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Signup extends Activity {

    //declaring variables
    EditText editTextEmail, editTextPassword;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //intializing values
        mAuth = FirebaseAuth.getInstance();
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        progressbar=(ProgressBar)findViewById(R.id.progressbar);
    }


    //to register a new user
    private void RegisterUser()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString();

        // validating email and password

        //tocheck for empty email
        if(email.isEmpty())
        {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        //tocheck for valid email
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {   editTextEmail.setError("Please enter a valid Email!");
            editTextEmail.requestFocus();
            return;

        }

        //tocheck for empty password
        if(password.isEmpty())
        {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        //tocheck for valid email
        if(password.length()<6)
        {
            editTextPassword.setError("Password must be atleat 6 character long!");
            editTextPassword.requestFocus();
            return;
        }

        //valid email and password

        progressbar.setVisibility(View.VISIBLE);

        //creating user with inbuilt method
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressbar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {

                    Intent intent=new Intent(Signup.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "This Email is already registered!", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }



            }
                }
        );

    }



    public void onclickOpenLogin(View view) {
        startActivity(new Intent(this,Login.class));
    }

    public void signup(View view) {
        RegisterUser();

    }
}
