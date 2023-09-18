package com.example.basic_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmailRegister, editTextPassRegister;
    private ProgressBar progressBarRegister;
    private LottieAnimationView registerSuccess, registerAnimation;
    private LinearLayout linearLayoutReg2, linearLayoutReg3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        editTextEmailRegister = findViewById(R.id.editTextEmailRegister);
        editTextPassRegister = findViewById(R.id.editTextPassRegister);
        progressBarRegister = findViewById(R.id.progressBarRegister);
        registerAnimation = findViewById(R.id.registerAnimation);
        registerSuccess = findViewById(R.id.registerSuccess);
        linearLayoutReg2 = findViewById(R.id.linearLayoutReg2);
        linearLayoutReg3 = findViewById(R.id.linearLayoutReg3);
    }

    public void signup(View v) {
        progressBarRegister.setVisibility(View.VISIBLE);
        String email, pass;
        email = String.valueOf(editTextEmailRegister.getText());
        pass = String.valueOf(editTextPassRegister.getText());

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(RegisterActivity.this, "Ingrese su correo electronico", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)) {
            Toast.makeText(RegisterActivity.this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBarRegister.setVisibility(View.GONE);
                            linearLayoutReg2.setVisibility(View.INVISIBLE);
                            linearLayoutReg3.setVisibility(View.INVISIBLE);
                            registerAnimation.setVisibility(View.INVISIBLE);
                            registerSuccess.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            },  4000);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registro fallido.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void cancel(View v) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}