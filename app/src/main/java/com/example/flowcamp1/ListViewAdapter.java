package com.example.flowcamp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private Context context;
    private ArrayList<list_form> items;
    private HashMap<String, String> child;
    private ViewHolder viewHolder = null;
    private ExpandableListView listview = null;
    androidx.appcompat.widget.AppCompatButton callButton;
    String tel = null;


    public ListViewAdapter(Context context, ArrayList<list_form> items, HashMap<String, String> child) {
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

        TextView name = (TextView) view.findViewById(R.id.nameTextView);
        TextView num = (TextView) view.findViewById(R.id.numTextView);

        name.setText(items.get(i).name);
        num.setText(items.get(i).num);

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

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.callButton: {
                view.getContext().startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                break;
            }
        }
    }

    class ViewHolder {
        public androidx.appcompat.widget.AppCompatButton image;
        public TextView childNameText;
        public TextView childnumText;
    }
}
