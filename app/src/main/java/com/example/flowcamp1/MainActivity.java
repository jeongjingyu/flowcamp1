package com.example.flowcamp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flowcamp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Bundle bundle;
    Fragment3 fragment3;
    String idx_str;
    TextView missing;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setSelectedItemId(R.id.image);
        replaceFragment(new Fragment2());
        missing = findViewById(R.id.missingText);

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.phone_number:
                    replaceFragment(new Fragment1());
                    break;
                case R.id.image:
                    replaceFragment(new Fragment2());
                    break;
                case R.id.map:
                    replaceFragment(new Fragment3());
                    break;
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    public void showMapFragment(int idx, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        binding.bottomNavigationView.setSelectedItemId(R.id.map);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();

        missing.setText(Integer.toString(idx));
    }
}