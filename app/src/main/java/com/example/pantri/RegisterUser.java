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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextPassword;
    private EditText editTextUsername;
    private TextView passwordHelp;
    private Button backButton;
    private ProgressDialog progressDialog;

    private FirebaseFirestore database;
    Map<String, Object> user = new HashMap<>();



    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);


        buttonRegister = (Button) findViewById(R.id.register);

        backButton = (Button) findViewById(R.id.Back);
        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        passwordHelp = (TextView) findViewById(R.id.PasswordHint);
        passwordHelp.setVisibility(View.INVISIBLE);

        buttonRegister.setOnClickListener(this);

    }
    private void registerUser(){

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
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
                            user.put("E-Mail",username);
                            user.put("Password",password);
                            database.collection("users").document("user1")
                                    .set(user);


                            Toast.makeText(RegisterUser.this,"Registered user.", Toast.LENGTH_SHORT).show();
                            startActivity(firstScreen);

                        }
                        else   {
                            Toast.makeText(RegisterUser.this,"Did not register user, please try again.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void getCollectionID(DocumentReference reference)
    {
        DocumentReference ref = database.collection("users").document();

        ref = reference;
    }
    @Override
    public void onClick(View v) {
        if(v == buttonRegister)
        {
            passwordHelp.setVisibility(View.INVISIBLE);
            registerUser();

        }
        if(v == backButton)
        {
            Intent firstScreen = new Intent(this, MainActivity.class);
            startActivity(firstScreen);
        }
       if (editTextPassword.isFocused())
       {
           passwordHelp.setVisibility(View.VISIBLE);

       }
       if(editTextUsername.isFocused())
       {
           passwordHelp.setVisibility(View.INVISIBLE);
       }

    }
}
