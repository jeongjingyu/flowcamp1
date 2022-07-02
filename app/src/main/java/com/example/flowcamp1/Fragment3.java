package com.example.flowcamp1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Fragment3 extends Fragment implements OnMapReadyCallback {

    View rootView;
    com.google.android.gms.maps.MapView mapView;
    GoogleMap googleMap;
    String where;
    double longitude = 127.36583434;
    double latitude = 36.37421833;
    String str = "카이스트 IT융합빌딩";


    public Fragment3() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment3, container, false);
        mapView = (com.google.android.gms.maps.MapView) rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync((com.google.android.gms.maps.OnMapReadyCallback) this);

        com.google.android.material.textfield.TextInputLayout searchEditText = rootView.findViewById(R.id.searchEditText);
        Button clickButton = rootView.findViewById(R.id.searchButton);
        final Geocoder geocoder = new Geocoder(this.getContext());

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> list = null;
                str = searchEditText.getEditText().getText().toString();
                try {
                    list = geocoder.getFromLocationName(
                            str, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (list != null) {
                    if (list.size() == 0) {
                        Log.v("no", "없단다");
                    } else {
                        latitude = list.get(0).getLatitude();
                        longitude = list.get(0).getLongitude();
                        com.google.android.gms.maps.CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new com.google.android.gms.maps.model.LatLng(list.get(0).getLatitude(), list.get(0).getLongitude()), 18);
                        googleMap.animateCamera(cameraUpdate);
                        googleMap.addMarker(new MarkerOptions()
                                .position(new com.google.android.gms.maps.model.LatLng(latitude, longitude))
                                .title(str));
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        MapsInitializer.initialize(this.getActivity());
        final Geocoder geocoder = new Geocoder(this.getContext());

        try {
            List<Address> list = null;
            str = where;
            Log.v("str_Cc", str);
            try {
                list = geocoder.getFromLocationName(
                        str, 10);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (list != null) {
                if (list.size() == 0) {
                    Log.v("no", "없단다");
                } else {
                    latitude = list.get(0).getLatitude();
                    longitude = list.get(0).getLongitude();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            longitude = 127.36583434;
            latitude = 36.37421833;
            str = "카이스트 IT융합빌딩";
        }

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 14);
        googleMap.animateCamera(cameraUpdate);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(str));

        // 줌 컨트롤 활성화
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // 마커 추가
        addStarMarker(36.3624677, 127.358096, "하바쿡");
        addStarMarker(36.3525892, 127.373436, "와탸요업");
        addStarMarker(36.3591389, 127.376888, "토미야");
        addStarMarker(36.3512542, 127.373831, "할머니딸생양곱창");
        addStarMarker(36.3574714, 127.350218, "태평소국밥");
        addStarMarker(36.3635414, 127.357655, "다다카츠");
        addStarMarker(36.3620220, 127.353403, "달구지막창");
        addStarMarker(36.3626669, 127.351668, "주전자");
        addStarMarker(36.3621901, 127.351421, "리코타코");
        addStarMarker(36.3636368, 127.358915, "잇마이타이");

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() { //
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() { //
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() { //
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void addStarMarker(double latitude, double longitude, String str) {
        BitmapDrawable bd = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.star2);
        Bitmap b = bd.getBitmap();
        Bitmap starMarker = Bitmap.createScaledBitmap(b, 80, 80, false);
        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .fromBitmap(starMarker)).position(new LatLng(latitude, longitude)).title(str).snippet("정보창"));

        googleMap.setOnInfoWindowClickListener(infoWindowClickListener);
    }

    // 정보창 클릭 리스너

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(@NonNull Marker marker) {
            String markerId = marker.getId();
            Log.d("markerId", markerId);
        }
    };


}
