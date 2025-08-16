package com.visacenter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ApplicationAdapter adapter;
    private List<Application> applications;

    private static final int REQUEST_EDIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applications = new ArrayList<>();
        // Додай тестову заявку
        applications.add(new Application("Іван", "Віза Польща", 5000, 2000));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ApplicationAdapter(applications, this::onApplicationClick);
        recyclerView.setAdapter(adapter);
    }

    private void onApplicationClick(int position) {
        Intent intent = new Intent(this, ApplicationEditActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("application", applications.get(position));
        startActivityForResult(intent, REQUEST_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            Application updated = (Application) data.getSerializableExtra("application");
            if (position != -1 && updated != null) {
                applications.set(position, updated);
                adapter.notifyItemChanged(position);
            }
        }
    }
}