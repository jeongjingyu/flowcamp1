package com.example.flowcamp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.flowcamp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button tab1, tab2, tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Fragment1());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.phone_number:
                    replaceFragment(new Fragment1());
                    break;
                case R.id.images:
                    replaceFragment(new Fragment2());
                    break;
                case R.id.call_by_image:
                    replaceFragment(new Fragment3());
                    break;
            }
            return true;
        });
//        tab1 = (Button)findViewById(R.id.tab1);
//        tab2 = (Button)findViewById(R.id.tab2);
//        tab3 = (Button)findViewById(R.id.tab3);
//
//        tab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                Fragment1 fragment1 = new Fragment1();
//                transaction.replace(R.id.frame, fragment1);
//                transaction.commit();
//            }
//        });
//
//        tab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                Fragment2 fragment2 = new Fragment2();
//                transaction.replace(R.id.frame, fragment2);
//                transaction.commit();
//            }
//        });
//
//        tab3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                Fragment3 fragment3 = new Fragment3();
//                transaction.replace(R.id.frame, fragment3);
//                transaction.commit();
//            }
//        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}