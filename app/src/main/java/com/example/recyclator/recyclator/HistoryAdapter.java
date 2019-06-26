package com.example.recyclator.recyclator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclator.recyclator.models.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private List<History> hist;

    public HistoryAdapter(Context context, List<History> hist) {
        this.context = context;
        this.hist = hist;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        History h = hist.get(position);
        holder.name.setText(h.getName());
        holder.quantity.setText("quantity is " + h.getQuantity() + "KG");
        holder.date.setText(h.getCreatedAt());
        holder.price.setText(h.getSuggetedPrice() + "");


    }

    @Override
    public int getItemCount() {
        return hist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, date, price;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            quantity = itemView.findViewById(R.id.quatity);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);

        }
    }
}
