package com.example.sourabh.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sourabh.R;

import java.util.List;

public class hi extends RecyclerView.Adapter<hi.myholder>{
    private Context mContext;


    List<String> image,name,designation;
    hi(Context mContext, List img, List nam, List des)
    {
        this.mContext = mContext;

        image =img;
        name= nam;
        designation = des;

    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acivity_hi,parent,false);

        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(myholder holder, final int position) {

        Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),  "fonts/HACKED.ttf");

        int id = mContext.getResources().getIdentifier(image.get(position), "drawable", mContext.getPackageName());
        Drawable drawable = mContext.getResources().getDrawable(id);
      holder.img.setImageDrawable(drawable);
       holder.txt3.setText(designation.get(position));
       holder.txt.setTypeface(custom_font);
       holder.txt3.setTypeface(custom_font);
       holder.txt.setText(name.get(position));



    }



    @Override
    public int getItemCount() {
        return name.size();
    }
    public  static  class myholder extends RecyclerView.ViewHolder {

        TextView txt,txt2,txt3;
        ImageView img;
        public myholder(View itemView) {
            super(itemView);

            txt = (TextView) itemView.findViewById(R.id.turns);
            img = (ImageView) itemView.findViewById(R.id.imageView);
            txt3 = (TextView) itemView.findViewById(R.id.duration);



        }
    }

}
