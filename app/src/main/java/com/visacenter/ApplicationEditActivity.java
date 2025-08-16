package com.visacenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ApplicationEditActivity extends AppCompatActivity {

    private EditText etName, etVisa, etPrice, etPaid;
    private Button btnSave;
    private Application application;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = findViewById(R.id.etName);
        etVisa = findViewById(R.id.etVisa);
        etPrice = findViewById(R.id.etPrice);
        etPaid = findViewById(R.id.etPaid);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        application = (Application) intent.getSerializableExtra("application");
        position = intent.getIntExtra("position", -1);

        if (application != null) {
            etName.setText(application.getName());
            etVisa.setText(application.getVisaType());
            etPrice.setText(String.valueOf(application.getPrice()));
            etPaid.setText(String.valueOf(application.getPaid()));
        }

        btnSave.setOnClickListener(v -> {
            application.setName(etName.getText().toString());
            application.setVisaType(etVisa.getText().toString());
            application.setPrice(Integer.parseInt(etPrice.getText().toString()));
            application.setPaid(Integer.parseInt(etPaid.getText().toString()));

            Intent result = new Intent();
            result.putExtra("position", position);
            result.putExtra("application", application);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}