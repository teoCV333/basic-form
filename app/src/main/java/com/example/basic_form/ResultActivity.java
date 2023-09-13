package com.example.basic_form;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;

public class ResultActivity extends AppCompatActivity {

    private EditText editTextNameRes, editTextCCRes, editTextBirthRes, editTextVote;
    private ConstraintLayout constraintLayout1, constraintLayout2, constraintLayout3;
    private LottieAnimationView success;
    private Button buttonConfirm, buttonEdit;
    private FormModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        editTextNameRes = findViewById(R.id.editTextNameRes);
        editTextCCRes = findViewById(R.id.editTextCCRes);
        editTextBirthRes = findViewById(R.id.editTextBirthRes);
        editTextVote = findViewById(R.id.editTextVote);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonEdit = findViewById(R.id.buttonEdit);
        constraintLayout1 = findViewById(R.id.constraintLayout1);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout3 = findViewById(R.id.constraintLayout3);
        success = findViewById(R.id.success);

        Intent intent = getIntent();
        data = (FormModel) intent.getSerializableExtra("data");

        editTextNameRes.setText(data.getName());
        editTextCCRes.setText(data.getCc());
        editTextBirthRes.setText(data.getBirth());
        editTextVote.setText(data.getVote());
    }

    public void confirm(View v) {
        buttonConfirm.setVisibility(buttonConfirm.INVISIBLE);
        buttonEdit.setVisibility(buttonEdit.INVISIBLE);
        constraintLayout1.setVisibility(constraintLayout1.INVISIBLE);
        constraintLayout2.setVisibility(constraintLayout2.INVISIBLE);
        constraintLayout3.setVisibility(constraintLayout3.INVISIBLE);
        success.setVisibility(success.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        },  5000);
    }

    public void edit(View v) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.putExtra("dataEdit", data);
        startActivity(intent);
    }
}