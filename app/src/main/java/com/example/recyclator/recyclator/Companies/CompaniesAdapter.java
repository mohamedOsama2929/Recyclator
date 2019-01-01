package com.example.recyclator.recyclator.Companies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.companyprofile.CompanyProfileActivity;

import java.util.List;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.ViewHolder> {

    private Context context;
    private List<Company> companies;
    ;

    public CompaniesAdapter(Context context, List<Company> companies) {
        this.context = context;
        this.companies = companies;
    }


    @Override
    public CompaniesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.carditem_company, parent, false);
        return new CompaniesAdapter.ViewHolder(v);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Company c = companies.get(position);
        holder.bio.setText(c.getBio());
        holder.location.setText(c.getLocation());
        //  holder.image.setImageResource(R.id.company_image);
        holder.name.setText(c.getName());
        holder.rate.setRating((float) (c.getRate() / 20.0F));


        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, CompanyProfileActivity.class);
                in.putExtra("bio", c.getBio());
                in.putExtra("location", c.getLocation());
                in.putExtra("name", c.getName());
                in.putExtra("rate", c.getRate());
                context.startActivity(in);

            }
        });
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, bio, location;
        RatingBar rate;
        ImageView image;
        LinearLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.company_image);
            name = itemView.findViewById(R.id.company_name);
            bio = itemView.findViewById(R.id.bio);
            location = itemView.findViewById(R.id.company_loc);
            rate = itemView.findViewById(R.id.company_rate);
            parentlayout = itemView.findViewById(R.id.lin);

        }
    }
}

