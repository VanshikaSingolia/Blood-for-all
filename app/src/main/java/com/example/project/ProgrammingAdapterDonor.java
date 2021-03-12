package com.example.project;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;


public class ProgrammingAdapterDonor extends RecyclerView.Adapter<ProgrammingAdapterDonor.ProgrammingViewHolder>   {

    private Context context;
    private List<Donor> mdonorlist;





    public ProgrammingAdapterDonor(Context context, List<Donor> mdonorlist) {
        this.context = context;
        this.mdonorlist = mdonorlist;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater=LayoutInflater.from(parent.getContext());
        View view=Inflater.inflate(R.layout.view,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {
        final Donor p=mdonorlist.get(position);
        String TAG="1";

        final String title6=p.getD_image();



        Log.e(TAG,"image name at on bind view holder"+p.getD_image());
        Log.e("TAG","in fnc"+title6);



        setimage1(title6, holder);

        holder.t1.setText(p.getD_name());
        holder.t3.setText(p.getD_blood());
        holder.t5.setText(p.getD_area());
        holder.t7.setText(p.getD_email());
        holder.t9.setText(p.getD_phone());
        holder.t11.setText(p.getD_status());












        holder.i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + p.getD_phone()));
                context.startActivity(intent);
            }
        });
        holder.i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sms_ = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", p.getD_phone(), null));
                context.startActivity(sms_);
            }
        });
        holder.i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail_ = new Intent(Intent.ACTION_SEND);
                Intent choose_ = new Intent();
                mail_.setData(Uri.parse("mailto:"));
                mail_.setType("text/plain");
                mail_.putExtra(Intent.EXTRA_EMAIL, p.getD_email());
                mail_.setType("message/rfc888");
                choose_ = Intent.createChooser(mail_, "Send Email Via");
               context.startActivity(choose_);
            }
        });

        holder.req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,CreateRequest.class);
                i.putExtra("NAME",p.getD_name());
                i.putExtra("PHONE",p.getD_phone());
                i.putExtra("EMAIL",p.getD_email());

                context.startActivity(i);
            }
        });



    }


    private void setimage1(String URL, final ProgrammingViewHolder holder) {


            Log.e("TAG","At image"+URL);
            RequestQueue rq = Volley.newRequestQueue(context);
            ImageRequest imageRequest=new ImageRequest(URL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            holder.i.setImageBitmap(response);
                            String tag="0";
                            Log.e(tag,"The image response is"+response);
                        }
                    }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(context,"Couldn't find image",Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            }
            );
            MySingleton.getInstance(context).addToRequestQueue(imageRequest);
        }



    @Override
    public int getItemCount()

    {
        return mdonorlist.size();
    }








    public void updateList(List<Donor> newList)
    {
        mdonorlist=new ArrayList<>();
        mdonorlist.addAll(newList);
        notifyDataSetChanged();
    }




    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        ImageView i,i1,i2,i3;
        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t11;
        Button req;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            i=(ImageView)itemView.findViewById(R.id.i);
            t1=(TextView)itemView.findViewById(R.id.Name_of_Donor);
            t2=(TextView)itemView.findViewById(R.id.t1);
            t3=(TextView)itemView.findViewById(R.id.Donor_BloodGrp);
            t4=(TextView)itemView.findViewById(R.id.t2);
            t5=(TextView)itemView.findViewById(R.id.donor_area);
            t6=(TextView)itemView.findViewById(R.id.t3);
            t7=(TextView)itemView.findViewById(R.id.donor_emailaddress);
            t8=(TextView)itemView.findViewById(R.id.t4);
            t9=(TextView)itemView.findViewById(R.id.donor_contact);
            i1=(ImageView)itemView.findViewById(R.id.i11);
            i2=(ImageView)itemView.findViewById(R.id.i22);
            i3=(ImageView)itemView.findViewById(R.id.i33);
            t11=(TextView)itemView.findViewById(R.id.donor_status);
            req=(Button)itemView.findViewById(R.id.rq);
        }
    }

}
