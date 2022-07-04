package com.example.flowcamp1;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment implements OnMapReadyCallback {

    View rootView;
    com.google.android.gms.maps.MapView mapView;
    GoogleMap googleMap;
    double longitude = 127.36583434;
    double latitude = 36.37421833;
    String str = "카이스트 IT융합빌딩";
    String snip = "대전 유성구 대학로 291";
    Dialog dialog;
    String res_name = null;
    int indx = -1;
    TextView missing;
    ArrayList<String> markerList = new ArrayList<String>();
    ArrayList<String> addressList = new ArrayList<String>();

    public Fragment3() {

    }

    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("oncreateview","oncreateview");
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
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new com.google.android.gms.maps.model.LatLng(list.get(0).getLatitude(), list.get(0).getLongitude()), 18);
                        googleMap.animateCamera(cameraUpdate);
                        googleMap.addMarker(new MarkerOptions()
                                .position(new com.google.android.gms.maps.model.LatLng(latitude, longitude))
                                .title(str));
                    }
                }
                searchEditText.getEditText().setText("");
            }
        });

        return rootView;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        Log.d("onMapReady","onMapReady");
        this.googleMap = googleMap;
        MapsInitializer.initialize(this.getActivity());
        final Geocoder geocoder = new Geocoder(this.getContext());

        //마커 리스트
        markerList.add("하바쿡");
        markerList.add("와탸요업");
        markerList.add("토미야");
        markerList.add("할머니딸생양곱창");
        markerList.add("태평소국밥");
        markerList.add("다다카츠");
        markerList.add("달구지막창");
        markerList.add("주전자");
        markerList.add("리코타코");
        markerList.add("잇마이타이");

        addressList.add("대전 유성구 대학로 227 어은빌딩2층");
        addressList.add("대전 서구 갈마역로25번길 9-8 1층 와타요업");
        addressList.add("대전 서구 청사서로 14");
        addressList.add("대전 서구 갈마역로 10 두양리체스");
        addressList.add("대전 서구 둔산로31번길 52 덕삼빌딩 1층 102호");
        addressList.add("대전 유성구 어은로52번길 7 1층");
        addressList.add("대전 유성구 어은로57번길 59");
        addressList.add("대전 유성구 궁동로18번길 78");
        addressList.add("대전 유성구 대학로163번길 37");
        addressList.add("대전 유성구 문화원로 77 그랑펠리체 상가 1층 103호");


        try {
            missing = getActivity().findViewById(R.id.missingText);
            indx = Integer.parseInt(missing.getText().toString());
            res_name = markerList.get(indx);
            str = res_name;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            List<Address> list = null;
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
            snip = addressList.get(indx);
        } catch (Exception e) {
            e.printStackTrace();
            longitude = 127.36583434;
            latitude = 36.37421833;
            str = "카이스트 IT융합빌딩";
            snip = "대전 유성구 대학로 291";
        }

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18);
        googleMap.animateCamera(cameraUpdate);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(str).snippet(snip));

        // 줌 컨트롤 활성화
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // 마커 추가
        addStarMarker(36.3624677, 127.358096, "하바쿡", "대전 유성구 대학로 227 어은빌딩2층");
        addStarMarker(36.3525892, 127.373436, "와탸요업", "대전 서구 갈마역로25번길 9-8 1층 와타요업");
        addStarMarker(36.3591389, 127.376888, "토미야", "대전 서구 청사서로 14");
        addStarMarker(36.3512542, 127.373831, "할머니딸생양곱창", "대전 서구 갈마역로 10 두양리체스");
        addStarMarker(36.3574714, 127.350218, "태평소국밥", "대전 서구 둔산로31번길 52 덕삼빌딩 1층 102호");
        addStarMarker(36.3635414, 127.357655, "다다카츠", "대전 유성구 어은로52번길 7 1층");
        addStarMarker(36.3620220, 127.353403, "달구지막창", "대전 유성구 어은로57번길 59");
        addStarMarker(36.3626669, 127.351668, "주전자", "대전 유성구 궁동로18번길 78");
        addStarMarker(36.3621901, 127.351421, "리코타코", "대전 유성구 대학로163번길 37");
        addStarMarker(36.3636368, 127.358915, "잇마이타이", "대전 유성구 문화원로 77 그랑펠리체 상가 1층 103호");

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

    public void addStarMarker(double latitude, double longitude, String title, String str) {

        // fragment1에서 넘어온 좌표는 star marker 추가 안 함
        if (res_name == title) {
            return;
        }

        BitmapDrawable bd = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.star2);
        Bitmap b = bd.getBitmap();
        Bitmap starMarker = Bitmap.createScaledBitmap(b, 80, 80, false);
        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .fromBitmap(starMarker)).position(new LatLng(latitude, longitude)).title(title).snippet(str));

        // 정보창
        googleMap.setOnInfoWindowClickListener(infoWindowClickListener);
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                LinearLayout info = new LinearLayout(getContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setGravity(Gravity.CENTER);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }

    // 정보창 클릭 리스너

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(@NonNull Marker marker) {
            String url = "www.naver.com";
            String markerId = marker.getId();
            Log.d("markerId", markerId);

            dialog = new CustomDialog(getContext());
            dialog.show();

            /*
            FeedTemplate params = FeedTemplate
                    .newBuilder(ContentObject.newBuilder("동행_지하철어플리케이션",
                                    "https://image.genie.co.kr/Y/IMAGE/IMG_ALBUM/081/191/791/81191791_1555664874860_1_600x600.JPG",
                                    LinkObject.newBuilder().setWebUrl("https://developers.kakao.com")
                                            .setMobileWebUrl("https://developers.kakao.com").build())
                            .setDescrption("목적지에 후 도착합니다.")
                            .build())
                    .addButton(new ButtonObject("웹에서 보기", LinkObject.newBuilder().setWebUrl("https://developers.kakao.com").setMobileWebUrl("https://developers.kakao.com").build()))
                    .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                            .setWebUrl("https://developers.kakao.com")
                            .setMobileWebUrl("https://developers.kakao.com")
                            .setAndroidExecutionParams("key1=value1")
                            .setIosExecutionParams("key1=value1")
                            .build()))
                    .build();

            Map<String, String> serverCallbackArgs = new HashMap<String, String>();
            serverCallbackArgs.put("user_id", "${current_user_id}");
            serverCallbackArgs.put("product_id", "${shared_product_id}");


            KakaoLinkService.getInstance().sendDefault(this, params, new ResponseCallback <KakaoLinkResponse>() {
                @Override
                public void onFailure(ErrorResult errorResult) {}

                @Override
                public void onSuccess(KakaoLinkResponse result) {
                }
            }); */

/*
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
*/
        }
    };
}




