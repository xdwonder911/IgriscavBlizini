package com.example.xdwonder.igriscavblizini;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Date;

import static com.example.xdwonder.igriscavblizini.DataAll.dt;

/**
 * Created by xdwonder on 18.3.2017.
 */

 class AdapterIgrisce extends RecyclerView.Adapter<AdapterIgrisce.ViewHolder>{
    DataAll da;
    Activity ac;
    public AdapterIgrisce(DataAll da, Activity ac) {
        this.da=da;
        this.ac=ac;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtAddres;
        public TextView txtTags;
        public ImageView iv;
        public RatingBar rb;
        public TextView txtDate;
        public ViewHolder(View v) {
            super(v);
            txtAddres = (TextView) v.findViewById(R.id.firstLine);
            txtTags = (TextView) v.findViewById(R.id.secondLine);
            iv = (ImageView)v.findViewById(R.id.icon);
            rb=(RatingBar)v.findViewById(R.id.thirdLine);
            txtDate=(TextView)v.findViewById(R.id.firstLineDate);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("Size:"+da.getPlaygroundsList().size());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    private static void startDView(String igrisceID, Activity ac) {
        //  System.out.println(name+":"+position);
        Intent i = new Intent(ac.getBaseContext(), activity_playground.class);
        i.putExtra("EXTRA_SESSION_ID",igrisceID);//EXTRA_SESSION_ID//DataAll.Igrisce_ID
        ac.startActivity(i);

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Playground trenutni = da.getPlaygroundsList().get(position);
        final String name = trenutni.getAddress() +", "+trenutni.getCity();
        holder.txtAddres.setText(name+" "+trenutni.getIdUser());
        holder.txtTags.setText("Tags: ");
        holder.rb.setRating(trenutni.getRating());
        holder.rb.setIsIndicator(true);
        holder.rb.setFocusable(false);
        holder.txtDate.setText(DataAll.dt.format(new Date(trenutni.getDate_added())));
        for(int j=0;j<trenutni.getTagList().size();j++)
            holder.txtTags.setText(holder.txtTags.getText()+trenutni.getTagList().get(j).getIme()+", ");

        // holder.iv.setImageBitmap(BitmapFactory.decodeFile(trenutni.getImgPathList().get(0)));//(this.  ac.getDrawable(R.drawable.ic_airline_seat_recline_extra_black_24dp));setImageBitmap(BitmapFactory.decodeFile(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getImgPathList().get(i)));
if(trenutni.getImgPathList().isEmpty()==false)
{
    //holder.iv.setImageBitmap(BitmapFactory.decodeFile(trenutni.getImgPathList().get(0)));
    System.out.println("Picasso:"+trenutni.getImgPathList().get(0));
    File f = new File(trenutni.getImgPathList().get(0)); //
    Picasso.with(ac.getApplicationContext())
            .load(f) //URL
            .placeholder(R.drawable.downloading_icon)
            .error(R.drawable.no_image_icon)
            // To fit image into imageView
            .fit()
            // To prevent fade animation
            .noFade()
            .into(holder.iv);
}else{
    Picasso.with(ac.getApplicationContext())//URL
            .load(R.drawable.no_image_icon)
            // To fit image into imageView
            .fit()
            // To prevent fade animation
            .noFade()
            .into(holder.iv);
}


        if (position%2==1) {

            holder.txtAddres.setTextColor(Color.BLUE);
        }else  holder.txtAddres.setTextColor(Color.BLACK);
      /*  holder.txtAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterIgrisce.startDView(trenutni.getIdPlayground(),ac);
            }
        });
        holder.txtTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterIgrisce.startDView(trenutni.getIdPlayground(),ac);
            }
        });
        */holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterIgrisce.startDView(trenutni.getIdPlayground(),ac);
            }
        });

    }


    @Override
   public int getItemCount() {
        return da.getPlaygroundsList().size();
    }
}


