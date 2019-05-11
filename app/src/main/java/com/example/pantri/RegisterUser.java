package com.example.pantri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextPassword;
    private EditText editTextUsername;
    private Button backButton;
    private ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);


        buttonRegister = (Button) findViewById(R.id.register);

        backButton = (Button) findViewById(R.id.Back);
        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);



        buttonRegister.setOnClickListener(this);

    }
    private void registerUser(){
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final Intent firstScreen = new Intent(this, MainActivity.class);


        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {  Toast.makeText(this,"enter password", Toast.LENGTH_SHORT).show();
            return;
        }



        mAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterUser.this,"Registered user.", Toast.LENGTH_SHORT).show();
                            startActivity(firstScreen);

                        }
                        else   {
                            Toast.makeText(RegisterUser.this,"Did not register user, please try again.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister)
        {
            registerUser();

        }
        if(v == backButton)
        {
            Intent firstScreen = new Intent(this, MainActivity.class);
            startActivity(firstScreen);
        }
    }
}
