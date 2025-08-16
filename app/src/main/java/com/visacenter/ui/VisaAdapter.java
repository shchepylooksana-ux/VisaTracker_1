package com.visacenter.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.visacenter.Application;
import com.visacenter.R;
import java.util.List;

public class VisaAdapter extends RecyclerView.Adapter<VisaAdapter.ViewHolder> {

    private final List<Application> applications;
    private final OnApplicationClickListener listener;

    public interface OnApplicationClickListener {
        void onClick(Application app);
    }

    public VisaAdapter(List<Application> applications, OnApplicationClickListener listener) {
        this.applications = applications;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Application app = applications.get(position);
        holder.txtHeader.setText(app.getName());
        holder.txtDates.setText(app.getVisa());
        holder.txtMoney.setText("Борг: " + app.getDebt() + " грн");
        holder.itemView.setOnClickListener(v -> listener.onClick(app));
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader, txtDates, txtMoney;
        ViewHolder(View v) {
            super(v);
            txtHeader = v.findViewById(R.id.txtHeader);
            txtDates = v.findViewById(R.id.txtDates);
            txtMoney = v.findViewById(R.id.txtMoney);
        }
    }
}
