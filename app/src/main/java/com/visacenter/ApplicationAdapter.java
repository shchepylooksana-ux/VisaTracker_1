package com.visacenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

    private ArrayList<Application> applications;
    private OnApplicationClickListener listener;

    public interface OnApplicationClickListener {
        void onApplicationClick(Application application, int position);
    }

    public ApplicationAdapter(ArrayList<Application> applications, OnApplicationClickListener listener) {
        this.applications = applications;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_application, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Application application = applications.get(position);
        holder.tvName.setText(application.getName());
        holder.tvVisaType.setText(application.getVisaType());
        holder.tvPrice.setText("Ціна: " + application.getPrice());
        holder.tvPaid.setText("Оплачено: " + application.getPaid());
        holder.tvDebt.setText("Борг: " + application.getDebt());
        holder.itemView.setOnClickListener(v -> listener.onApplicationClick(application, position));
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvVisaType, tvPrice, tvPaid, tvDebt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvVisaType = itemView.findViewById(R.id.tvVisaType);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPaid = itemView.findViewById(R.id.tvPaid);
            tvDebt = itemView.findViewById(R.id.tvDebt);
        }
    }
}
