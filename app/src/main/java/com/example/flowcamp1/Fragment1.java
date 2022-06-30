package com.example.flowcamp1;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

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

public class Fragment1 extends Fragment {
    ExpandableListView listview;
    ExpandableListAdapter expandableListAdapter;
    ArrayList<list_form> items = new ArrayList<list_form>();
    HashMap<String, String> child = new HashMap<String, String>();

    public Fragment1() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* asset에서 json 파일 불러오기 */
        AssetManager assetManager = getActivity().getAssets();
        View tabOneView = inflater.inflate(R.layout.fragment1, container, false);

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

                items.add(new list_form(name, phone));
            }

            ArrayList<String> name_list = new ArrayList<String>();
            ArrayList<String> num_list = new ArrayList<String>();

            for (int i = 0; i < items.size(); i ++) {
                child.put(items.get(i).name, items.get(i).num);
            }

            listview = (ExpandableListView) tabOneView.findViewById(R.id.expandablelistView);
            expandableListAdapter = new ListViewAdapter(getContext(), items, child);
            //expandableListAdapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
            listview.setAdapter(expandableListAdapter);

            /*
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItem = (String) view.findViewById(R.id.nameTextView).getTag().toString();
                    Log.d("selectedItem", selectedItem);
                }
            }); */


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return tabOneView; // frame view return

    }
}

class list_form {
    public String name;
    public String num;

    public list_form(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }
}

