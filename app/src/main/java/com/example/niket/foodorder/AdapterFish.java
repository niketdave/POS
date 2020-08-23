package com.example.niket.foodorder;

/**
 * Created by niket on 06-10-2017.
 */
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.widget.Toast;


public class AdapterFish extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFish> data=Collections.emptyList();
    DataFish current;
int position=0;
    // create constructor to innitilize context and data sent from MainActivity
    public AdapterFish(Context context, ArrayList<DataFish> data){

        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card, parent,false);
        MyHolder holder=new MyHolder(view,context,data);
        return holder;
    }


    // Bind data
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataFish current=data.get(position);
        int id=current.id;
        myHolder.textName.setText(current.Name);
        myHolder.textName.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        myHolder.textPrice.setText("Rs. " + current.price);
        myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        // load image into imageview using glide
        Glide.with(context).load(current.Image)
                .placeholder(R.drawable.album1)
                .error(R.drawable.album1)
                .into(myHolder.image);


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textName;
        ImageView image;
        TextView textPrice;
        Context context;
        List<DataFish> data= new ArrayList<DataFish>();

        // create constructor to get widget reference
        public MyHolder(View itemView,Context context,List<DataFish> data) {
            super(itemView);
            this.data=data;
            this.context=context;
            itemView.setOnClickListener(this);
            textName= (TextView) itemView.findViewById(R.id.name);
            image= (ImageView) itemView.findViewById(R.id.image);
            textPrice = (TextView) itemView.findViewById(R.id.rupee);
         //   data.size();
        }

        @Override
        public void onClick(View view) {
         //   int size=data.size();
             int position=getAdapterPosition();
             DataFish dataFish=data.get(position);

            Intent intent=new Intent(this.context,item_details.class);
            intent.putExtra("id",dataFish.id);
            intent.putExtra("image_id",dataFish.Image);
            intent.putExtra("Name",dataFish.Name);
            intent.putExtra("price",dataFish.price);
            this.context.startActivity(intent);

        }

    }
}

