package com.example.flowcamp1;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

public class Fragment3 extends Fragment implements Overlay.OnClickListener, NaverMap.OnMapClickListener, OnMapReadyCallback {

    private MapView mapView;
    private InfoWindow infoWindow;
    NaverMap naverMap;

    public Fragment3() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3, container, false);
        mapView = (MapView)view.findViewById(R.id.map_view);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        naverMap.setOnMapClickListener(this);

        infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.ViewAdapter() {
            @NonNull
            @Override
            public View getView(@NonNull InfoWindow infoWindow) {
                return View.inflate(getContext(), R.layout.fragment1, null);
            }
        });

        Marker marker1 = new Marker();
        marker1.setPosition(new LatLng(36.3624677, 127.358096)); //하바쿡
        marker1.setMap(naverMap);
        marker1.setOnClickListener(this);

        Marker marker2 = new Marker();
        marker2.setPosition(new LatLng(36.3525892, 127.373436)); //와타요업
        marker2.setMap(naverMap);
        marker2.setOnClickListener(this);

        Marker marker3 = new Marker();
        marker3.setPosition(new LatLng(36.3591389, 127.376888)); //토미야
        marker3.setMap(naverMap);
        marker3.setOnClickListener(this);

        Marker marker4 = new Marker();
        marker4.setPosition(new LatLng(36.3512542, 127.373831)); //할머니딸생양곱창
        marker4.setMap(naverMap);
        marker4.setOnClickListener(this);

        Marker marker5 = new Marker();
        marker5.setPosition(new LatLng(36.3574714, 127.350218)); //태평소국밥
        marker5.setMap(naverMap);
        marker5.setOnClickListener(this);

        Marker marker6 = new Marker();
        marker6.setPosition(new LatLng(36.3635414, 127.357655)); //다다카츠
        marker6.setMap(naverMap);
        marker6.setOnClickListener(this);

        Marker marker7 = new Marker();
        marker7.setPosition(new LatLng(36.3620220, 127.353403)); //달구지막창
        marker7.setMap(naverMap);
        marker7.setOnClickListener(this);

        Marker marker8 = new Marker();
        marker8.setPosition(new LatLng(36.3626669, 127.351668)); //주전자
        marker8.setMap(naverMap);
        marker8.setOnClickListener(this);

        Marker marker9 = new Marker();
        marker9.setPosition(new LatLng(36.3621901, 127.351421)); //리코타코
        marker9.setMap(naverMap);
        marker9.setOnClickListener(this);

        Marker marker10 = new Marker();
        marker10.setPosition(new LatLng(36.3636368, 127.358915)); //잇마이타이
        marker10.setMap(naverMap);
        marker10.setOnClickListener(this);
    }

    @Override
    public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
        if (infoWindow.getMarker() != null) {
            infoWindow.close();
        }
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        Marker marker = (Marker)overlay;
        if (marker.getInfoWindow() == null) {
            infoWindow.open(marker);
            CameraUpdate cameraUpdate = CameraUpdate.scrollTo(infoWindow.getPosition()).animate(CameraAnimation.Easing);
            naverMap.moveCamera(cameraUpdate);
        } else {
            infoWindow.close();
        }
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
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
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
