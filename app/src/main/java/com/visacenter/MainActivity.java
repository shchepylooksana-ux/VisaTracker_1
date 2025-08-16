package com.visacenter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.visacenter.ui.VisaAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VisaAdapter adapter;
    private List<Application> applications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        applications = new ArrayList<>();
        applications.add(new Application("Іван", "Шенген віза", 5000, 2000));

        adapter = new VisaAdapter(applications, app -> {
            Intent intent = new Intent(MainActivity.this, ApplicationEditActivity.class);
            intent.putExtra("application", app);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ApplicationEditActivity.class);
            startActivity(intent);
        });
    }
}
