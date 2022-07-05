package com.example.flowcamp1;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import com.kakao.sdk.common.util.KakaoCustomTabsClient;
import com.kakao.sdk.share.ShareClient;
import com.kakao.sdk.share.WebSharerClient;
import com.kakao.sdk.template.model.Content;
import com.kakao.sdk.template.model.FeedTemplate;
import com.kakao.sdk.template.model.ItemContent;
import com.kakao.sdk.template.model.ItemInfo;
import com.kakao.sdk.template.model.Link;
import com.kakao.sdk.template.model.Social;
import com.kakao.sdk.user.UserApiClient;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<String> textList = new ArrayList<String>();
    ArrayList<Integer> image_list = new ArrayList<Integer>();
    CardView cardView;
    TextView nameCard;
    TextView addressCard;
    TextView explainCard;
    com.makeramen.roundedimageview.RoundedImageView imageCard;
    androidx.appcompat.widget.AppCompatButton closeButton;
    androidx.appcompat.widget.AppCompatButton shareButton;

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

        rootView = inflater.inflate(R.layout.fragment3, container, false);
        mapView = (com.google.android.gms.maps.MapView) rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync((com.google.android.gms.maps.OnMapReadyCallback) this);
        cardView = rootView.findViewById(R.id.cardView);

        EditText searchEditText = rootView.findViewById(R.id.searchEditText);
        Button clickButton = rootView.findViewById(R.id.searchButton);
        final Geocoder geocoder = new Geocoder(this.getContext());

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> list = null;
                str = searchEditText.getText().toString();
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
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new com.google.android.gms.maps.model.LatLng(list.get(0).getLatitude(), list.get(0).getLongitude()), 14);
                        googleMap.animateCamera(cameraUpdate);
                        googleMap.addMarker(new MarkerOptions()
                                .position(new com.google.android.gms.maps.model.LatLng(latitude, longitude))
                                .title(str));
                    }
                }
                searchEditText.setText("");
            }
        });

        return rootView;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

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
        markerList.add("서브웨이");
        markerList.add("호우섬");
        markerList.add("최진엽등촌샤브샤브");

        addressList.add("대전 유성구 대학로 227 어은빌딩 2층");
        addressList.add("대전 서구 갈마역로25번길 9-8 1층 와타요업");
        addressList.add("대전 서구 청사서로 14");
        addressList.add("대전 서구 갈마역로 10 두양리체스");
        addressList.add("대전 유성구 온천동로65번길 50");
        addressList.add("대전 유성구 어은로52번길 7 1층");
        addressList.add("대전 유성구 어은로57번길 59");
        addressList.add("대전 유성구 궁동로18번길 78");
        addressList.add("대전 유성구 대학로163번길 37");
        addressList.add("대전 유성구 문화원로 77 그랑펠리체 상가 1층 103호");
        addressList.add("대전 유성구 대학로 291 정문술빌딩 1층");
        addressList.add("대전 유성구 대덕대로 516 5층 호우섬 대전신세계Art&Science점");
        addressList.add("대전 유성구 궁동로18번길 40 2층");


        textList.add("텍사스 스타일 바베큐와 브리스킷, 대전의 로우앤슬로우");
        textList.add("전국구 텐동 맛집, 서울에서도 흔하지 않은 퀄리티");
        textList.add("여름엔 시원한 붓카케 냉우동, 가라아게가 진짜 맛있음");
        textList.add("가성비 좋은 무한리필 곱창, 볶음밥은 필수");
        textList.add("줄서서 먹는 소국밥, 회전율이 좋아 육사시미가 신선함");
        textList.add("어은동에 있는 돈카츠 맛집, 밥과 면은 무한리필");
        textList.add("대구 막창보다 맛있다는 막창집, 된장찌개와의 궁합이 최고");
        textList.add("궁동에서 막걸리에 전은 여기, 진짜 정통 막걸리 맛집");
        textList.add("양 많고 맛있는 멕시코 음식, 소프트타코보다는 하드타코 추천");
        textList.add("한국 스타일 태국 음식의 정점, 고수 못 먹어도 상관없음");
        textList.add("카이스트에서는 1등 점심, 익숙하지만 새로운 조합의 샌드위치");
        textList.add("홍콩 느낌 제대로 나는 딤섬 맛집, 비싼 가격과 그만한 값어치");
        textList.add("충남대 앞 가성비 샤브샤브 성지, 소고기 샤브샤브가 6500원");

        image_list.add(R.drawable.img11);
        image_list.add(R.drawable.img21);
        image_list.add(R.drawable.img31);
        image_list.add(R.drawable.img41);
        image_list.add(R.drawable.img51);
        image_list.add(R.drawable.img61);
        image_list.add(R.drawable.img71);
        image_list.add(R.drawable.img81);
        image_list.add(R.drawable.img91);
        image_list.add(R.drawable.img101);
        image_list.add(R.drawable.img111);
        image_list.add(R.drawable.img121);
        image_list.add(R.drawable.img131);

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

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 14);
        googleMap.animateCamera(cameraUpdate);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(str).snippet(snip));

        // 줌 컨트롤 활성화
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // 3초 딜레이용
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (indx != -1) {
                    cardView.setVisibility(View.VISIBLE);
                    nameCard = rootView.findViewById(R.id.nameCard);
                    nameCard.setText(str);
                    addressCard = rootView.findViewById(R.id.addressCard);
                    addressCard.setText(addressList.get(indx));
                    explainCard = rootView.findViewById(R.id.explainCard);
                    explainCard.setText(textList.get(markerList.indexOf(str)));
                    imageCard = rootView.findViewById(R.id.map_image);
                    imageCard.setImageResource(image_list.get(markerList.indexOf(str)));

                    closeButton = rootView.findViewById(R.id.closeButton);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cardView.setVisibility(View.GONE);
                        }
                    });

                 shareButton = rootView.findViewById(R.id.sendButton);
                    shareButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            shareButton = rootView.findViewById(R.id.sendButton);
                            shareButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog = new CustomDialog(getContext(), str, addressList.get(indx));
                                    dialog.show();
                                }
                            });
                        }
                    });
                } else {
                    cardView.setVisibility(View.GONE);
                } // end else
        } // end run()
    },3000);

        // 마커 추가
        addStarMarker(36.3624677, 127.358096, "하바쿡", "대전 유성구 대학로 227 어은빌딩2층");
        addStarMarker(36.3525892, 127.373436, "와탸요업", "대전 서구 갈마역로25번길 9-8 1층 와타요업");
        addStarMarker(36.3591389, 127.376888, "토미야", "대전 서구 청사서로 14");
        addStarMarker(36.3512542, 127.373831, "할머니딸생양곱창", "대전 서구 갈마역로 10 두양리체스");
        addStarMarker(36.3573903, 127.350324, "태평소국밥", "대전 유성구 온천동로65번길 50");
        addStarMarker(36.3635414, 127.357655, "다다카츠", "대전 유성구 어은로52번길 7 1층");
        addStarMarker(36.3620220, 127.353403, "달구지막창", "대전 유성구 어은로57번길 59");
        addStarMarker(36.3626669, 127.351668, "주전자", "대전 유성구 궁동로18번길 78");
        addStarMarker(36.3621901, 127.351421, "리코타코", "대전 유성구 대학로163번길 37");
        addStarMarker(36.3636368, 127.358915, "잇마이타이", "대전 유성구 문화원로 77 그랑펠리체 상가 1층 103호");
        addStarMarker(36.3712149, 127.362207, "서브웨이", "대전 유성구 대학로 291 정문술빌딩 1층");
        addStarMarker(36.3749610, 127.381915, "호우섬", "대전 유성구 대덕대로 516 5층 호우섬 대전신세계Art&Science점");
        addStarMarker(36.3626823, 127.349607, "최진엽등촌샤브샤브", "대전 유성구 궁동로18번길 40 2층");
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
                info.addView(title);

                try {
                    cardView.setVisibility(View.VISIBLE);
                    nameCard = rootView.findViewById(R.id.nameCard);
                    nameCard.setText(marker.getTitle());
                    addressCard = rootView.findViewById(R.id.addressCard);
                    addressCard.setText(marker.getSnippet());
                    explainCard = rootView.findViewById(R.id.explainCard);
                    explainCard.setText(textList.get(markerList.indexOf(marker.getTitle())));
                    imageCard = rootView.findViewById(R.id.map_image);
                    imageCard.setImageResource(image_list.get(markerList.indexOf(marker.getTitle())));

                    closeButton = rootView.findViewById(R.id.closeButton);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cardView.setVisibility(View.GONE);
                        }
                    });

                    shareButton = rootView.findViewById(R.id.sendButton);
                    shareButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog = new CustomDialog(getContext(), marker.getTitle(), addressList.get(markerList.indexOf(marker.getTitle())));
                            dialog.show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    cardView.setVisibility(View.GONE);
                }

                return info;
            }
        });
    }
}




