package com.example.flowcamp1;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Fragment1 extends Fragment {

    public Fragment1() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* asset에서 json 파일 불러오기 */
        AssetManager assetManager = getActivity().getAssets();
        View tabOneView = inflater.inflate(R.layout.fragment1, container, false);
        ListView listview = (ListView) tabOneView.findViewById(R.id.listView);
        /*
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.
        ); */

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

            }

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
}

