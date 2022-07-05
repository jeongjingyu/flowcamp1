package com.example.flowcamp1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRestaurantAdapter extends RecyclerView.Adapter<MyRestaurantAdapter.ViewHolder> implements View.OnClickListener {

    MyRestaurantData[] myRestaurantData;
    RecyclerView recyclerView;
    MainActivity mainActivity;
    Context context;
    int idx = -1;

    public MyRestaurantAdapter(Context context, MyRestaurantData[] myRestaurantData, RecyclerView recyclerView) {
        this.context = context;
        this.myRestaurantData = myRestaurantData;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restaurant_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyRestaurantData myRestaurantDataList = myRestaurantData[position];
        holder.restaurantName.setText(myRestaurantDataList.getRestaurantName());
        holder.restaurantText.setText(myRestaurantDataList.getRestaurantText());
        holder.restaurantImage1.setImageResource(myRestaurantDataList.getRestaurantImage1());
        holder.restaurantImage2.setImageResource(myRestaurantDataList.getRestaurantImage2());
        holder.restaurantImage3.setImageResource(myRestaurantDataList.getRestaurantImage3());
        holder.restaurantImage4.setImageResource(myRestaurantDataList.getRestaurantImage4());

        holder.getMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.menu_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView imageView = dialog.findViewById(R.id.restaurant_menu);
                imageView.setImageResource(myRestaurantDataList.getRestaurantMenu());
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myRestaurantData.length;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardView:
                Log.d("idx", String.valueOf(idx));
                mainActivity = (MainActivity) context;
                mainActivity.showMapFragment(idx-1, new Fragment3());
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantImage1;
        ImageView restaurantImage2;
        ImageView restaurantImage3;
        ImageView restaurantImage4;
        TextView restaurantName;
        TextView restaurantText;
        TextView getMenu;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantImage1 = itemView.findViewById(R.id.restaurant_image1);
            restaurantImage2 = itemView.findViewById(R.id.restaurant_image2);
            restaurantImage3 = itemView.findViewById(R.id.restaurant_image3);
            restaurantImage4 = itemView.findViewById(R.id.restaurant_image4);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantText = itemView.findViewById(R.id.restaurant_text);
            getMenu = itemView.findViewById(R.id.get_menu);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idx = getAdapterPosition();
                    Log.d("idx_pos", String.valueOf(idx));
                    mainActivity = (MainActivity) context;
                    mainActivity.showMapFragment(idx, new Fragment3());
                }
            });
        }
    }
}
