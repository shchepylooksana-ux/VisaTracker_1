package com.visacenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.visacenter.db.DBHelper;
import com.visacenter.model.VisaEntry;
import com.visacenter.ui.VisaAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;
    private VisaAdapter adapter;
    private TextView txtTotals;

    private EditText edtFrom, edtName, edtCountry, edtVisaType, edtRequestDate, edtSubmitDate, edtResultDate, edtPaid, edtExpenses, edtDebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        edtFrom = findViewById(R.id.edtFrom);
        edtName = findViewById(R.id.edtName);
        edtCountry = findViewById(R.id.edtCountry);
        edtVisaType = findViewById(R.id.edtVisaType);
        edtRequestDate = findViewById(R.id.edtRequestDate);
        edtSubmitDate = findViewById(R.id.edtSubmitDate);
        edtResultDate = findViewById(R.id.edtResultDate);
        edtPaid = findViewById(R.id.edtPaid);
        edtExpenses = findViewById(R.id.edtExpenses);
        edtDebt = findViewById(R.id.edtDebt);
        Button btnAdd = findViewById(R.id.btnAdd);
        txtTotals = findViewById(R.id.txtTotals);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VisaAdapter(db.getAll());
        recycler.setAdapter(adapter);

        refreshTotals();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisaEntry e = new VisaEntry();
                e.from = edtFrom.getText().toString().trim();
                e.name = edtName.getText().toString().trim();
                e.country = edtCountry.getText().toString().trim();
                e.visaType = edtVisaType.getText().toString().trim();
                e.requestDate = edtRequestDate.getText().toString().trim();
                e.submitDate = edtSubmitDate.getText().toString().trim();
                e.resultDate = edtResultDate.getText().toString().trim();
                e.paid = parseDoubleSafe(edtPaid.getText().toString());
                e.expenses = parseDoubleSafe(edtExpenses.getText().toString());
                e.debt = parseDoubleSafe(edtDebt.getText().toString());

                if (TextUtils.isEmpty(e.name) || TextUtils.isEmpty(e.country)) {
                    Toast.makeText(MainActivity.this, "Заповніть хоча б Ім'я та Країну", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.insert(e);
                adapter.setItems(db.getAll());
                refreshTotals();
                clearForm();
            }
        });
    }

    private double parseDoubleSafe(String s) {
        try { return Double.parseDouble(s.replace(',', '.')); } catch (Exception ex) { return 0.0; }
    }

    private void refreshTotals() {
        DBHelper.Totals t = db.getTotals();
        txtTotals.setText("Підсумки: " + t.count + " віз • Оплат: " + String.format("%.2f", t.paid) +
                " • Витрат: " + String.format("%.2f", t.expenses) +
                " • Чистий дохід: " + String.format("%.2f", t.net));
    }

    private void clearForm() {
        edtFrom.setText("");
        edtName.setText("");
        edtCountry.setText("");
        edtVisaType.setText("");
        edtRequestDate.setText("");
        edtSubmitDate.setText("");
        edtResultDate.setText("");
        edtPaid.setText("");
        edtExpenses.setText("");
        edtDebt.setText("");
    }
}