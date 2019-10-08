package com.connectlifes.admin.ui.lead;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connectlifes.admin.R;
import com.connectlifes.admin.oauth2.response.Leads;

import java.util.ArrayList;

public class LeadListAdapter extends RecyclerView.Adapter<LeadListAdapter.LeadViewHolder>  {

    private static final String TAG = "Admin - LeadListAdapter";
    Context context;
    ArrayList<Leads> leads;

    public LeadListAdapter(Context context, ArrayList<Leads> leads) {
        this.context = context;
        this.leads = leads;
    }

    @NonNull
    @Override
    public LeadListAdapter.LeadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "I am called from onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.lead_item_adapter, parent, false);
        return new  LeadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadListAdapter.LeadViewHolder holder, int position) {
        Log.d(TAG, "I am called form onBindViewHolder");
        holder.name.setText(leads.get(position).getName());
        holder.phone.setText(leads.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "counts called "+leads.size());
        return leads.size();
    }

    public class LeadViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView email;
        TextView phone;
        TextView address;

        public LeadViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.lead_name_value);
//            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.lead_phone_value);
//            address = itemView.findViewById(R.id.address);

        }
    }
}
