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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button buttonLogin;
    public EditText editTextPassword;
    public EditText editTextUsername;
    private TextView textViewRegister;

    public Users registeredusers;
    public EditText EditTextUsername2;
    private ProgressDialog progressDialog;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;

    public EditText getEditTextUsername2() {
        return EditTextUsername2;
    }

    public void setEditTextUsername2(EditText editTextUsername2) {
        EditTextUsername2 = editTextUsername2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);


        buttonLogin = (Button) findViewById(R.id.button);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        textViewRegister = (TextView) findViewById(R.id.register);

        EditTextUsername2 = (EditText) findViewById(R.id.usernam2);

        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
        editTextUsername.setVisibility(View.INVISIBLE);
 }

    public void signInUser(){


        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();


        String username1 = EditTextUsername2.getText().toString().trim();

        if (TextUtils.isEmpty(username1))
        {
            Toast.makeText(MainActivity.this,"user not found, please try again", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(MainActivity.this,"enter password", Toast.LENGTH_SHORT).show();
            return;
        }


        db.collection("user").document(username1).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    String title = documentSnapshot.getString("E-Mail");
                    editTextUsername.setText(title);
                    getSignInUser();
                }

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"User not found, please try again",Toast.LENGTH_SHORT).show();

            }
        });










    }
    public void getSignInUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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
