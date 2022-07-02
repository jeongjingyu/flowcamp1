package com.example.flowcamp1;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment1 extends Fragment {
    ExpandableListView listview;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListAdapter expandableListAdapter2;
    ExpandableListAdapter expandableListAdapter3;
    ArrayList<list_form> items = new ArrayList<list_form>();
    ArrayList<list_form> items2;
    HashMap<String, ArrayList<String>> child = new HashMap<String, ArrayList<String>>();
    HashMap<String, ArrayList<String>> child2;
    EditText editSearch;
    MainActivity mainActivity;
    private ArrayList<String> list;
    private List<String> arraylist;

    public Fragment1() {

    }

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* asset에서 json 파일 불러오기 */
        AssetManager assetManager = getActivity().getAssets();
        View tabOneView = inflater.inflate(R.layout.fragment1, container, false);
        View expandableListview = inflater.inflate(R.layout.listview, container, false);
        list = new ArrayList<String>();


        try {
            InputStream is = assetManager.open("phone_num.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }
            // json 파일 내용이 string으로 변환되어 담김
            String phoneNumData = buffer.toString();
            Log.d("json", phoneNumData);

            // json 데이터가 []로 시작하는 배열: JSONArray
            // 배열을 구성하는 각 요소 JSONObject
            JSONObject jsonObject = new JSONObject(phoneNumData);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            String str = "";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);

                String id = jo.getString("id");
                String name = jo.getString("name");
                String phone = jo.getString("phone");
                String food = jo.getString("food");
                String start = jo.getString("start");
                String end = jo.getString("end");
                String rate = jo.getString("rate");
                String price = jo.getString("price");

                items.add(new list_form(name, phone, food, start, end, rate, price));
                list.add(name);
            }

            for (int i = 0; i < items.size(); i ++) {
                ArrayList<String> child_list = new ArrayList<String>();
                child_list.add(items.get(i).num);
                child_list.add(items.get(i).food);
                child_list.add(items.get(i).start);
                child_list.add(items.get(i).end);
                child_list.add(items.get(i).rate);
                child_list.add(items.get(i).price);
                child.put(items.get(i).name, child_list);
            }

            arraylist = new ArrayList<String>();
            arraylist.addAll(list);

            listview = (ExpandableListView) tabOneView.findViewById(R.id.expandablelistView);
            expandableListAdapter = new ListViewAdapter(getContext(), items, child);
            listview.setAdapter(expandableListAdapter);

            editSearch = tabOneView.findViewById(R.id.searchEditText);

            if (editSearch.getText().toString().equals("") || editSearch.getText().toString() == null) {
                expandableListAdapter3 = new ListViewAdapter(getContext(), items, child);
                listview.invalidate();
                listview.setAdapter(expandableListAdapter3);
            }

            editSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String text = editSearch.getText().toString();
                    search(text);
                    checkEmpty();
                }

                public void search(String charText) {
                    list.clear();

                    if(charText.length() == 0) {
                        list.addAll(arraylist);
                    }

                    else{
                        items2 = new ArrayList<list_form>();
                        child2 = new HashMap<String, ArrayList<String>>();
                        for (int i=0; i < arraylist.size(); i++) {
                            if (arraylist.get(i).toLowerCase().contains(charText)) {
                                list.add(arraylist.get(i));
                                items2.add(new list_form(items.get(i).name, items.get(i).num,
                                        items.get(i).food, items.get(i).start, items.get(i).end,
                                        items.get(i).rate, items.get(i).price));
                            }
                        }

                        for (int i = 0; i < items2.size(); i ++) {
                            ArrayList<String> child_list = new ArrayList<String>();
                            child_list.add(items.get(i).num);
                            child_list.add(items.get(i).food);
                            child_list.add(items.get(i).start);
                            child_list.add(items.get(i).end);
                            child_list.add(items.get(i).rate);
                            child_list.add(items.get(i).price);
                            child2.put(items2.get(i).name, child_list);
                        }
                    }
                    expandableListAdapter2 = new ListViewAdapter(getContext(), items2, child2);
                    listview.invalidate();
                    listview.setAdapter(expandableListAdapter2);
                }

                public void checkEmpty() {
                    if (editSearch.getText().toString().equals("") || editSearch.getText().toString() == null) {

                        expandableListAdapter = new ListViewAdapter(getContext(), items, child);
                        listview.setAdapter(expandableListAdapter);
                    }
                }
            });
            } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        listview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            // 한 그룹이 열릴 때 다른 그룹들은 모두 닫아줌
            @Override
            public void onGroupExpand(int groupPosition) {
                // 범위 지정 잘 해 주어야 함
                for (int i = 0; i < 13; i++) {
                    if (!(i == groupPosition))
                        listview.collapseGroup(i);
                }
            }
        });

        return tabOneView; // frame view return
    }
}

class list_form {
    public String name;
    public String num;
    public String food;
    public String start;
    public String end;
    public String rate;
    public String price;

    public list_form(String name, String num, String food, String start, String end, String rate, String price) {
        this.name = name;
        this.num = num;
        this.food = food;
        this.start = start;
        this.end = end;
        this.rate = rate;
        this.price = price;
    }
}


