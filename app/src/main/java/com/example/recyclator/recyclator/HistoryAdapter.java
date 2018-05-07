package com.example.recyclator.recyclator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

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
       /* holder.date.setText(h.getDate());
        holder.company.setText(h.getCompany());
        holder.location.setText(h.getLocation());
        holder.quantity.setText(h.getQuantity());*/

    }

    @Override
    public int getItemCount() {
        return hist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, company, location, quantity;
        RatingBar rate;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            company = itemView.findViewById(R.id.company);
            location = itemView.findViewById(R.id.location);
            quantity = itemView.findViewById(R.id.quatity);
            rate = itemView.findViewById(R.id.rate);
        }
    }
}
