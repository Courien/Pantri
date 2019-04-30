package com.example.pantri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogin;
    private EditText editTextPassword;
    private EditText editTextUsername;
    private TextView textViewRegister;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);


        buttonLogin = (Button) findViewById(R.id.button);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        textViewRegister = (TextView) findViewById(R.id.register);


        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);


    }

    private void signInUser(){
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {  Toast.makeText(this,"enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging user...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent((getApplicationContext()),SecondScreen.class));
                        }
                    }
                });

    }
    @Override
    public void onClick(View v) {
        if(v == buttonLogin)
        {
            signInUser();
        }
        if(v == textViewRegister)
        {
            Intent firstScreen = new Intent(this, RegisterUser.class);
            startActivity(firstScreen);        }

    }
}
