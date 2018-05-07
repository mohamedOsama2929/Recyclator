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

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private Context context;
    private List<Request> req;

    public RequestAdapter(Context context, List<Request> req) {
        this.context = context;
        this.req = req;
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_requests, parent, false);
        return new RequestAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ViewHolder holder, int position) {
        Request h = req.get(position);
       /* holder.name.setText(h.getName());
        holder.kind.setText(h.getKind());
        holder.location.setText(h.getLocation());
        holder.quantity.setText(h.getQuantity());*/

    }

    @Override
    public int getItemCount() {
        return req.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, kind, location, quantity;
        RatingBar rate;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            kind = itemView.findViewById(R.id.company);
            location = itemView.findViewById(R.id.location);
            quantity = itemView.findViewById(R.id.quatity);
            rate = itemView.findViewById(R.id.rate);
        }
    }
}
