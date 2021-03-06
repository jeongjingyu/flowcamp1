package com.example.flowcamp1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private Context context;
    private ArrayList<list_form> items;
    private HashMap<String, ArrayList<String>> child;
    MainActivity mainActivity;
    private ExpandableListView listview = null;
    androidx.appcompat.widget.AppCompatButton callButton;
    androidx.appcompat.widget.AppCompatButton mapButton;
    ArrayList<String> child_text;
    String tel = null;
    int position = -1;
    ArrayList<Integer> image_list = new ArrayList<Integer>();


    public ListViewAdapter(Context context, ArrayList<list_form> items, HashMap<String, ArrayList<String>> child) {
        this.context = context;
        this.items = items;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return items.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(items.get(i).name);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview, viewGroup, false);
        }

        image_list.add(R.drawable.img131);
        image_list.add(R.drawable.img91);
        image_list.add(R.drawable.img101);
        image_list.add(R.drawable.img71);
        image_list.add(R.drawable.img81);
        image_list.add(R.drawable.img51);
        image_list.add(R.drawable.img41);
        image_list.add(R.drawable.img121);
        image_list.add(R.drawable.img61);
        image_list.add(R.drawable.img21);
        image_list.add(R.drawable.img31);
        image_list.add(R.drawable.img11);
        image_list.add(R.drawable.img111);

        RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        TextView name = (TextView) view.findViewById(R.id.nameTextView);
        TextView num = (TextView) view.findViewById(R.id.numTextView);
        de.hdodenhof.circleimageview.CircleImageView restaurantImageView = view.findViewById(R.id.restaurantImageView);

        name.setText(items.get(i).name);
        num.setText(items.get(i).num);
        ratingBar.setRating(Float.parseFloat(items.get(i).rate));
        restaurantImageView.setImageResource(image_list.get(i));

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.childlistview, viewGroup, false);
        }
        callButton = view.findViewById(R.id.callButton);
        callButton.setOnClickListener(this);
        tel = "tel:" + items.get(i).num;
        position = i;

        child_text = child.get(items.get(i).name);

        TextView time = (TextView) view.findViewById(R.id.timeTextView);
        TextView food = (TextView) view.findViewById(R.id.foodTextView);
        TextView price = (TextView) view.findViewById(R.id.priceTextView);

        food.setText(items.get(i).food);
        time.setText("???????????? : " + items.get(i).start + " ~ " + items.get(i).end);
        price.setText("?????? : " + items.get(i).price);

        mapButton = view.findViewById(R.id.mapButton);
        mapButton.setOnClickListener(this);

        /*
        if (food.getText().toString() == "??????") {
            food.setTextColor(Integer.parseInt(352));
        } */

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.callButton:
                view.getContext().startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                break;
            case R.id.mapButton:
                // fragment ??????
                mainActivity = (MainActivity) context;
                mainActivity.showMapFragment(position, new Fragment3());
                break;
        }
    }
}
