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

public class ProgrammingAdapterReceiver extends RecyclerView.Adapter<ProgrammingAdapterReceiver.ProgrammingViewHolder> {
    private List<Receiver> mreceiverlist;
    Context context1;

    public ProgrammingAdapterReceiver(List<Receiver> mreceiverlist, Context context1) {
        this.mreceiverlist = mreceiverlist;
        this.context1 = context1;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater=LayoutInflater.from(parent.getContext());
        View view=Inflater.inflate(R.layout.received_request,parent,false);
        return new ProgrammingAdapterReceiver.ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingAdapterReceiver.ProgrammingViewHolder holder, int position) {

        final Receiver pos=mreceiverlist.get(position);
        holder.r1.setText(pos.getR_name());
        holder.r6.setText(pos.getBloodgroup());
        holder.r2.setText(pos.getPurpose());
        holder.r3.setText(pos.getR_email());
        holder.r4.setText(pos.getR_phone());
        holder.r5.setText(pos.getDate1());
        holder.r7.setText(pos.getR_Area());
    }








    @Override
    public int getItemCount()
    {
        return mreceiverlist.size();
    }


    public Filter getFilter() {
        return null;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {

        TextView r1,r2,r3,r4,r5,r6,r7;


        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);

            r1=(TextView)itemView.findViewById(R.id.r1);
            r2=(TextView)itemView.findViewById(R.id.r2);
            r3=(TextView)itemView.findViewById(R.id.r3);
            r4=(TextView)itemView.findViewById(R.id.r4);
            r5=(TextView)itemView.findViewById(R.id.r5);
            r6=(TextView)itemView.findViewById(R.id.r6);
            r7=(TextView)itemView.findViewById(R.id.r7);

        }
    }

}
