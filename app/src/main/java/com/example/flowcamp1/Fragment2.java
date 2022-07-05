package com.example.flowcamp1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment2 extends Fragment {

    public Fragment2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyRestaurantData[] myRestaurantData = new MyRestaurantData[]{
                new MyRestaurantData("최진엽등촌샤브샤브", "충남대 앞 가성비 샤브샤브 성지, 소고기 샤브샤브가 6500원", R.drawable.img131, R.drawable.img132, R.drawable.img133, R.drawable.img134, R.drawable.img139),
                new MyRestaurantData("리코타코", "양 많고 맛있는 멕시코 음식, 소프트타코보다는 하드타코 추천", R.drawable.img91, R.drawable.img92, R.drawable.img93, R.drawable.img94, R.drawable.img99),
                new MyRestaurantData("잇마이타이", "한국 스타일 태국 음식의 정점, 고수 못 먹어도 상관없음", R.drawable.img101, R.drawable.img102, R.drawable.img103, R.drawable.img104, R.drawable.img109),
                new MyRestaurantData("달구지막창", "대구 막창보다 맛있다는 막창집, 된장찌개와의 궁합이 최고", R.drawable.img71, R.drawable.img72, R.drawable.img73, R.drawable.img74, R.drawable.img79),
                new MyRestaurantData("주전자", "궁동에서 막걸리에 전은 여기, 진짜 정통 막걸리 맛집", R.drawable.img81, R.drawable.img82, R.drawable.img83, R.drawable.img84, R.drawable.img89),
                new MyRestaurantData("태평소국밥", "줄서서 먹는 소국밥, 회전율이 좋아 육사시미가 신선함", R.drawable.img51, R.drawable.img52, R.drawable.img53, R.drawable.img54, R.drawable.img59),
                new MyRestaurantData("할머니딸생양곱창", "가성비 좋은 무한리필 곱창, 볶음밥은 필수", R.drawable.img41, R.drawable.img42, R.drawable.img43, R.drawable.img44, R.drawable.img49),
                new MyRestaurantData("호우섬", "홍콩 느낌 제대로 나는 딤섬 맛집, 비싼 가격과 그만한 값어치", R.drawable.img121, R.drawable.img122, R.drawable.img123, R.drawable.img124, R.drawable.img129),
                new MyRestaurantData("다다카츠", "어은동에 있는 돈카츠 맛집, 밥과 면은 무한리필", R.drawable.img61, R.drawable.img62, R.drawable.img63, R.drawable.img64, R.drawable.img69),
                new MyRestaurantData("와타요업", "전국구 텐동 맛집, 서울에서도 흔하지 않은 퀄리티", R.drawable.img21, R.drawable.img22, R.drawable.img23, R.drawable.img24, R.drawable.img29),
                new MyRestaurantData("토미야", "여름엔 시원한 붓카케 냉우동, 가라아게가 진짜 맛있음", R.drawable.img31, R.drawable.img32, R.drawable.img33, R.drawable.img34, R.drawable.img39),
                new MyRestaurantData("하바쿡", "텍사스 스타일 바베큐와 브리스킷, 대전의 로우앤슬로우", R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img19),
                new MyRestaurantData("서브웨이", "카이스트에서는 1등 점심, 익숙하지만 새로운 조합의 샌드위치", R.drawable.img111, R.drawable.img112, R.drawable.img113, R.drawable.img114, R.drawable.img119)
        };

        MyRestaurantAdapter myRestaurantAdapter = new MyRestaurantAdapter(getContext(), myRestaurantData, recyclerView);
        recyclerView.setAdapter(myRestaurantAdapter);

        return view;
    }
}


