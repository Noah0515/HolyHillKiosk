package com.example.holyhillkiosk.DTO;

public class BeverageStatisticDTO {
    private String beverageName;
    private Long sumBeverageNum;

    public BeverageStatisticDTO(String beverageName, Long sumBeverageNum) {
        this.beverageName = beverageName;
        this.sumBeverageNum = sumBeverageNum;
    }

    // Getters and Setters
    public String getBeverageName() {
        return beverageName;
    }

    public void setBeverageName(String beverageName) {
        this.beverageName = beverageName;
    }

    public Long getSumBeverageNum() {
        return sumBeverageNum;
    }

    public void setSumBeverageNum(Long sumBeverageNum) {
        this.sumBeverageNum = sumBeverageNum;
    }
}
