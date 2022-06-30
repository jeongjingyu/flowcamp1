package com.example.flowcamp1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private Context context;
    private ArrayList<list_form> items;

    public ListViewAdapter(Context context, ArrayList<list_form> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("Clicked", "Clicked");
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview, viewGroup, false);
        }

        TextView name = (TextView) view.findViewById(R.id.nameTextView);
        TextView num = (TextView) view.findViewById(R.id.numTextView);

        Log.d("items.get(i).name", items.get(i).name);
        name.setText(items.get(i).name);
        num.setText(items.get(i).num);

        return view;
    }


}
