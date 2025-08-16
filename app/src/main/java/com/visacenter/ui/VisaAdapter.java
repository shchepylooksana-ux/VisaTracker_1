package com.visacenter.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.visacenter.R;
import com.visacenter.model.VisaEntry;

import java.util.List;

public class VisaAdapter extends RecyclerView.Adapter<VisaAdapter.VH> {
    private List<VisaEntry> items;

    public VisaAdapter(List<VisaEntry> items) {
        this.items = items;
    }

    public void setItems(List<VisaEntry> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visa, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        VisaEntry e = items.get(pos);
        h.txtHeader.setText(e.name + " • " + e.country + " • " + e.visaType);
        h.txtDates.setText("Заявка: " + safe(e.requestDate) + " • Подача: " + safe(e.submitDate) + " • Вихід: " + safe(e.resultDate));
        h.txtMoney.setText(String.format("Оплата: %.2f • Витрати: %.2f • Борг: %.2f • Чистий: %.2f", e.paid, e.expenses, e.debt, e.net()));
    }

    private String safe(String s){ return s == null ? "" : s; }

    @Override
    public int getItemCount() { return items == null ? 0 : items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtHeader, txtDates, txtMoney;
        VH(View v){
            super(v);
            txtHeader = v.findViewById(R.id.txtHeader);
            txtDates = v.findViewById(R.id.txtDates);
            txtMoney = v.findViewById(R.id.txtMoney);
        }
    }
}