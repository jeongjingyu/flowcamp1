package com.example.flowcamp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<list_form> items;
    private HashMap<String, String> child;
    private ViewHolder viewHolder = null;
    private ExpandableListView listview = null;
    private int lastExpandedGroupPosition = -1;
    private int flag = -2;

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
        /*
        listview = (ExpandableListView) view.findViewById(R.id.expandablelistView);
        Log.d("listview", listview.toString());
        if (i != lastExpandedGroupPosition) {
            Log.d("lastExpandedGroupPosition", Integer.toString(lastExpandedGroupPosition));
            //listview.collapseGroup(lastExpandedGroupPosition);
        } */
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        /*
        if(i != lastExpandedGroupPosition && lastExpandedGroupPosition != -1){
            Log.d("dd", "dd");
            return true;
        } */
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition){

        Log.d("start", "start");
        if(groupPosition != lastExpandedGroupPosition && lastExpandedGroupPosition != -1){
            //isChildSelectable(groupPosition, 0);
            //Log.d("listview in ex", listview.toString());
            //Log.d("lastExpandedGroupPosition in if", Integer.toString(lastExpandedGroupPosition));
            //Log.d("positionnn", String.valueOf(listview.getSelectedPosition()));
            //listview.setItemChecked(lastExpandedGroupPosition, false);
        }
        Log.d("lastExpandedGroupPosition", Integer.toString(lastExpandedGroupPosition));

        super.onGroupExpanded(groupPosition);
        lastExpandedGroupPosition = groupPosition;
        Log.d("changed", "changed");
        Log.d("changed lastExpandedGroupPosition", Integer.toString(lastExpandedGroupPosition));
    }


    class ViewHolder {
        public androidx.appcompat.widget.AppCompatButton image;
        public TextView childNameText;
        public TextView childnumText;
    }

}
