package com.example.basic_form;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmailLogin, editTextPassLogin;
    private ProgressBar progressBarLogin;
    private FirebaseAuth mAuth;
    boolean success = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmailLogin = findViewById(R.id.editTextEmailLogin);
        editTextPassLogin = findViewById(R.id.editTextPassLogin);
        progressBarLogin = findViewById(R.id.progressBarLogin);
    }

    private boolean checkFormFields() {
        success = true;
        if(editTextEmailLogin.getText().toString().length() == 0) {
            editTextEmailLogin.setError("Este campo es requerido.");
            success = false;
        }
        if(editTextPassLogin.getText().toString().length() == 0) {
            editTextPassLogin.setError("Este campo es requerido.");
            success = false;
        }
        if(checkEmailValid(editTextEmailLogin.getText().toString()) == false) {
            editTextEmailLogin.setError("Email invalido.");
            success = false;
        }
        return success;
    }

    private boolean checkEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void register(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View v) {
        progressBarLogin.setVisibility(View.VISIBLE);
        boolean fieldsChecked = checkFormFields();
        String email, pass;
        if(fieldsChecked == true) {
                email = String.valueOf(editTextEmailLogin.getText());
                pass = String.valueOf(editTextPassLogin.getText());
                Log.d("email", email);
                Log.d("pass", pass);
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("task",task.getResult().toString());
                                    // Sign in success, update UI with the signed-in user's information
                                    progressBarLogin.setVisibility(View.GONE);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException == true) {
                                        // Handle incorrect credentials error
                                        Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        });
        }
    }
}
