package com.example.recyclator.recyclator.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclator.recyclator.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import com.android.volley.Request.Method;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private Context context;
    private List<Request> req;

    String url = "https://desolate-chamber-62168.herokuapp.com/public/user/map";

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
    public void onBindViewHolder(@NonNull final RequestAdapter.ViewHolder holder, final int position) {
        final Request h = req.get(position);

        holder.name.setText(req.get(position).name);
        holder.kind.setText(req.get(position).kind);
        holder.location.setText(req.get(position).location);
        holder.quantity.setText(req.get(position).quantity);
        holder.rate.setRating((float) req.get(position).rate);

        final double longtude = req.get(position).longtude;
        final double latitude = req.get(position).latitude;
        final int user_id = req.get(position).user_id;
        final Context context = req.get(position).context;

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.acceptBtn.getText() != "Accepted"){

                    Map<String, String> params = new HashMap();
                    params.put("id",String.valueOf(user_id));
                    params.put("width", String.valueOf(longtude));
                    params.put("height", String.valueOf(latitude));
                    params.put("rate", String.valueOf((float) req.get(position).rate));

                    JSONObject parameters = new JSONObject(params);

                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Post Response", "onResponse: " + response.toString());
                            //TODO: handle success
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Post Response", "onRefuse: " + error.toString());
                            error.printStackTrace();
                            //TODO: handle failure
                        }
                    });

                    Volley.newRequestQueue(context).add(jsonRequest);

                    holder.acceptBtn.setText("Accepted");
                }

                else
                    holder.acceptBtn.setText("Accept");
            }
        });

        holder.declineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.declineBtn.getText() != "Declined")
                    holder.declineBtn.setText("Declined");
                else
                    holder.declineBtn.setText("Decline");
            }
        });

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
        Button acceptBtn, declineBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            kind = itemView.findViewById(R.id.kind);
            location = itemView.findViewById(R.id.location);
            quantity = itemView.findViewById(R.id.quatity);
            rate = itemView.findViewById(R.id.rate);
            acceptBtn = itemView.findViewById(R.id.acceptBtn);
            declineBtn = itemView.findViewById(R.id.declineBtn);
        }
    }
}
