package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProgrammingAdapterSender extends RecyclerView.Adapter<ProgrammingAdapterSender.ProgrammingViewHolder> {
    private List<Sender> msenderlist;
    Context context1;


    public ProgrammingAdapterSender(List<Sender> msenderlist, Context context1) {
        this.msenderlist = msenderlist;
        this.context1 = context1;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater=LayoutInflater.from(parent.getContext());
        View view=Inflater.inflate(R.layout.sent_request,parent,false);
        return new ProgrammingAdapterSender.ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingAdapterSender.ProgrammingViewHolder holder, int position) {
        final Sender pos=msenderlist.get(position);

        holder.s1.setText(pos.getName());
        holder.s3.setText(pos.getEmail());
        holder.s2.setText(pos.getPhone());
        holder.s4.setText(pos.getDate());
    }








    @Override
    public int getItemCount()
    {
        return msenderlist.size();
    }


    public Filter getFilter() {
        return null;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {

        TextView s1,s2,s3,s4;


        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);

            s1=(TextView)itemView.findViewById(R.id.s1);
            s2=(TextView)itemView.findViewById(R.id.s2);
            s3=(TextView)itemView.findViewById(R.id.s3);
            s4=(TextView)itemView.findViewById(R.id.s4);

        }
    }

}
