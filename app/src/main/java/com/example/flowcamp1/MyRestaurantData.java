package com.example.flowcamp1;

import java.util.List;

public class MyRestaurantData {
    private String restaurantName;
    private String restaurantText;
    private Integer restaurantImage1;
    private Integer restaurantImage2;
    private Integer restaurantImage3;
    private Integer restaurantImage4;
    private Integer restaurantMenu;

    public MyRestaurantData(String restaurantName, String restaurantText, Integer restaurantImage1, Integer restaurantImage2, Integer restaurantImage3, Integer restaurantImage4, Integer restaurantMenu) {
        this.restaurantName = restaurantName;
        this.restaurantText = restaurantText;
        this.restaurantImage1 = restaurantImage1;
        this.restaurantImage2 = restaurantImage2;
        this.restaurantImage3 = restaurantImage3;
        this.restaurantImage4 = restaurantImage4;
        this.restaurantMenu = restaurantMenu;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantText() {
        return restaurantText;
    }

    public void setRestaurantText(String restaurantText) {
        this.restaurantText = restaurantText;
    }

    public Integer getRestaurantImage1() {
        return restaurantImage1;
    }

    public void setRestaurantImage1(Integer restaurantImage1) {
        this.restaurantImage1 = restaurantImage1;
    }

    public Integer getRestaurantImage2() {
        return restaurantImage2;
    }

    public void setRestaurantImage2(Integer restaurantImage2) {
        this.restaurantImage2 = restaurantImage2;
    }

    public Integer getRestaurantImage3() {
        return restaurantImage3;
    }

    public void setRestaurantImage3(Integer restaurantImage3) {
        this.restaurantImage3 = restaurantImage3;
    }

    public Integer getRestaurantImage4() {
        return restaurantImage4;
    }

    public void setRestaurantImage4(Integer restaurantImage4) {
        this.restaurantImage4 = restaurantImage4;
    }

    public Integer getRestaurantMenu() {
        return restaurantMenu;
    }

    public void setRestaurantMenu(Integer restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }
}
