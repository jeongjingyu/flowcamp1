package com.example.flowcamp1;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    Bitmap bitmap;
    View viewLightVibrant;
    TextView tvLightVibrantTitle;
    TextView tvLightVibrantBody;

    int r;
    int g;
    int b;
    int a;
    String hexr;
    String hexg;
    String hexb;
    String hexa;

    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    public Fragment2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        List<Drawable> imageItems = new ArrayList<Drawable>();

        sliderItems.add(new SliderItem(R.drawable.img1));
        sliderItems.add(new SliderItem(R.drawable.img2));
        sliderItems.add(new SliderItem(R.drawable.img3));
        sliderItems.add(new SliderItem(R.drawable.img4));
        sliderItems.add(new SliderItem(R.drawable.img5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Drawable d = imageItems.get(position);
                    }
                });
                /*bitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
                Palette palette=Palette.from(bitmap).generate();
                setPalette(palette);*/

            }
        });

        return view;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    /*
    private String setPalette(Palette palette) {

        if (palette == null) {
            return null;
        }

        // 우리가 선택한 색상표: LightVibrantSwatch
        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        // return 할 hex 형태 컬러 초기화
        String hex = null;

        if (lightVibrantSwatch != null) {

            // view와 두 텍스트를 이미지의 lightVibrantSwatch한 컬러로 변경
            viewLightVibrant.setBackgroundColor(lightVibrantSwatch.getRgb());
            tvLightVibrantTitle.setTextColor(lightVibrantSwatch.getTitleTextColor());
            tvLightVibrantBody.setTextColor(lightVibrantSwatch.getBodyTextColor());
            Log.d("lightVibrantSwatch", String.valueOf(lightVibrantSwatch.getRgb()));

            // rgb 값 추출
            int pixel = lightVibrantSwatch.getRgb(); // -65551 이런 형태
            r = Color.red(pixel);
            g = Color.green(pixel);
            b = Color.blue(pixel);
            a = Color.alpha(pixel); // alpha: 투명도

            // 위에서 뽑은 r, g, b, a 값을 16진수 hex로 변환
            hexr = pad(Integer.toHexString(r)); // 아래 pad 함수 정의 (16진수 패딩)
            hexg = pad(Integer.toHexString(g));
            hexb = pad(Integer.toHexString(b));
            hexa = pad(Integer.toHexString(a));

            // 16진수 형태의 r, g, b, a를 통합시켜 "#FF930284" 형태로 변환
            hex = "#" + hexa + hexr + hexg + hexb;
            hex = hex.toUpperCase();
            Log.d("hex color", hex);
        } // end if
        return hex;
    } // end setPalette

    // 16진수 패딩
    private static final String pad(String s) {
        return (s.length() == 1) ? "0" + s : s;
    }*/
}



