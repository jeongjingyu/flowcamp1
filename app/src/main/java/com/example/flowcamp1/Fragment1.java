package com.example.flowcamp1;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fragment1 extends Fragment {
    ListView listview;
    ListViewAdapter listViewAdapter;
    ArrayList<list_form> items = new ArrayList<list_form>();

    /*
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> nums = new ArrayList<String>();
    String[] names_list;
    String[] nums_list; */

    public Fragment1() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* asset에서 json 파일 불러오기 */
        AssetManager assetManager = getActivity().getAssets();
        View tabOneView = inflater.inflate(R.layout.fragment1, container, false);

        /*
        listview = (ListView) tabOneView.findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity().getApplicationContext(), items);
        listview.setAdapter(listViewAdapter); */

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

                /*names.add(name);
                nums.add(phone);*/
            }

            /*
            names_list = new String[names.size()];
            nums_list = new String[names.size()];

            for (int i=0; i < names.size(); i++) {
                names_list[i] = names.get(i);
                nums_list[i] = nums.get(i);
            } */

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), names_list);

            listview = (ListView) tabOneView.findViewById(R.id.listView);
            listViewAdapter = new ListViewAdapter(getContext(), items);
            listview.setAdapter(listViewAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItem = (String) view.findViewById(R.id.nameTextView).getTag().toString();
                    Log.d("selectedItem", selectedItem);
                }
            });


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

