package com.visacenter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ApplicationEditActivity extends AppCompatActivity {

    private EditText etName, etVisa, etPrice, etPaid;
    private Application app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = findViewById(R.id.etName);
        etVisa = findViewById(R.id.etVisa);
        etPrice = findViewById(R.id.etPrice);
        etPaid = findViewById(R.id.etPaid);
        Button btnSave = findViewById(R.id.btnSave);

        app = (Application) getIntent().getSerializableExtra("application");

        if (app != null) {
            etName.setText(app.getName());
            etVisa.setText(app.getVisa());
            etPrice.setText(String.valueOf(app.getPrice()));
            etPaid.setText(String.valueOf(app.getPaid()));
        }

        btnSave.setOnClickListener(v -> {
            if (app != null) {
                app.setName(etName.getText().toString());
                app.setVisa(etVisa.getText().toString());
                app.setPrice(Integer.parseInt(etPrice.getText().toString()));
                app.setPaid(Integer.parseInt(etPaid.getText().toString()));
            }
            finish();
        });
    }
}
