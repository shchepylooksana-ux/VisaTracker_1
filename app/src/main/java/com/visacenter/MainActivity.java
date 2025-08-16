package com.visacenter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ApplicationAdapter adapter;
    private ArrayList<Application> applications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        applications = new ArrayList<>();

        // Тестові заявки
        applications.add(new Application("Іван", "Віза Польща", 5000, 2000));
        applications.add(new Application("Марія", "Віза Чехія", 6000, 3000));

        adapter = new ApplicationAdapter(applications, this::onApplicationClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void onApplicationClick(Application application, int position) {
        Intent intent = new Intent(this, ApplicationEditActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("name", application.getName());
        intent.putExtra("visaType", application.getVisaType());
        intent.putExtra("price", application.getPrice());
        intent.putExtra("paid", application.getPaid());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                applications.get(position).setName(data.getStringExtra("name"));
                applications.get(position).setVisaType(data.getStringExtra("visaType"));
                applications.get(position).setPrice(data.getIntExtra("price", 0));
                applications.get(position).setPaid(data.getIntExtra("paid", 0));
                adapter.notifyItemChanged(position);
            }
        }
    }
}
