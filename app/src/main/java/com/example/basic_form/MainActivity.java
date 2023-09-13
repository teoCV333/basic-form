package com.example.basic_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextCC, editTextBirth;

    public Button buttonA, buttonB, buttonC;

    private FormModel dataForEdit;

    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextCC = findViewById(R.id.editTextCC);
        editTextBirth = findViewById(R.id.editTextBirth);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("dataEdit") != null) {
            dataForEdit = (FormModel) intent.getSerializableExtra("dataEdit");

            editTextName.setText(dataForEdit.getName());
            editTextCC.setText(dataForEdit.getCc());
            editTextBirth.setText(dataForEdit.getBirth());
        }
    }

    public void vote(View v) {
        String name = editTextName.getText().toString();
        String cc = editTextCC.getText().toString();
        String birth = editTextBirth.getText().toString();
        String vote = v.getTag().toString();
        Log.d("data", vote);
        if(vote != null) {
            switch (vote) {
                case "A":
                    vote = "A";
                    break;
                case "B":
                    vote = "B";
                    break;
                case "C":
                    vote = "C";
                    break;
            }
        }

        success = checkFormFields();
        if(success) {
            FormModel data = new FormModel(name, cc, birth, vote);
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("data", data);
            startActivity(intent);
        }
    }

    private boolean checkFormFields() {
        success = true;
        if(editTextName.getText().toString().length() == 0) {
            editTextName.setError("Este campo es requerido.");
            success = false;
        }
        if(editTextCC.getText().toString().length() == 0) {
            editTextCC.setError("Este campo es requerido.");
            success = false;
        }
        if(editTextBirth.getText().toString().length() == 0) {
            editTextBirth.setError("Este campo es requerido.");
            success = false;
        }
        return success;
    }

}