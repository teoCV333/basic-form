package com.example.basic_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editTextNameRes, editTextCCRes, editTextBirthRes, editTextVote;
    private Button buttonConfirm, buttonEdit;
    private FormModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.textView);
        editTextNameRes = findViewById(R.id.editTextNameRes);
        editTextCCRes = findViewById(R.id.editTextCCRes);
        editTextBirthRes = findViewById(R.id.editTextBirthRes);
        editTextVote = findViewById(R.id.editTextVote);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonEdit = findViewById(R.id.buttonEdit);

        Intent intent = getIntent();
        data = (FormModel) intent.getSerializableExtra("data");

        editTextNameRes.setText(data.getName());
        editTextCCRes.setText(data.getCc());
        editTextBirthRes.setText(data.getBirth());
        editTextVote.setText(data.getVote());
    }

    public void confirm(View v) {
        textView.setText("Se ha confirmado tu voto.");
        buttonConfirm.setVisibility(buttonConfirm.INVISIBLE);
        buttonEdit.setVisibility(buttonEdit.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        },  3000);
    }

    public void edit(View v) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.putExtra("dataEdit", data);
        startActivity(intent);
    }
}