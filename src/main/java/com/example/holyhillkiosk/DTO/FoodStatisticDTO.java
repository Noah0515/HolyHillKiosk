package com.example.holyhillkiosk.DTO;

public class FoodStatisticDTO {
    private String foodName;
    private Long sumFoodNum;

    public FoodStatisticDTO(String foodName, Long sumFoodNum) {
        this.foodName = foodName;
        this.sumFoodNum = sumFoodNum;
    }

    // Getters and Setters
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getSumFoodNum() {
        return sumFoodNum;
    }

    public void setSumFoodNum(Long sumFoodNum) {
        this.sumFoodNum = sumFoodNum;
    }
}
