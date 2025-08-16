package com.visacenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ApplicationEditActivity extends AppCompatActivity {

    private EditText etName, etVisa, etPrice, etPaid;
    private Button btnSave;
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
        position = intent.getIntExtra("position", -1);
        etName.setText(intent.getStringExtra("name"));
        etVisa.setText(intent.getStringExtra("visaType"));
        etPrice.setText(String.valueOf(intent.getIntExtra("price", 0)));
        etPaid.setText(String.valueOf(intent.getIntExtra("paid", 0)));

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("position", position);
            resultIntent.putExtra("name", etName.getText().toString());
            resultIntent.putExtra("visaType", etVisa.getText().toString());
            resultIntent.putExtra("price", Integer.parseInt(etPrice.getText().toString()));
            resultIntent.putExtra("paid", Integer.parseInt(etPaid.getText().toString()));
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
