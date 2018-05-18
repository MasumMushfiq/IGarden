package com.example.mushfiq.igarden;


public class MyData {
    private int lightMode;
    private int lightValue;
    private int waterMode;
    private int waterValue;
    private int moist;
    private int temp;
    private int humidity;
    private String lastUpdated;

    public MyData() {
    }

    public MyData(int lightMode, int lightValue, int waterMode, int waterValue, int moist, int temp, int humidity, String lastUpdated) {
        this.lightMode = lightMode;
        this.lightValue = lightValue;
        this.waterMode = waterMode;
        this.waterValue = waterValue;
        this.moist = moist;
        this.temp = temp;
        this.humidity=humidity;
        this.lastUpdated = lastUpdated;
    }

    public int getLightMode() {
        return lightMode;
    }

    public void setLightMode(int lightMode) {
        this.lightMode = lightMode;
    }

    public int getLightValue() {
        return lightValue;
    }

    public void setLightValue(int lightValue) {
        this.lightValue = lightValue;
    }

    public int getWaterMode() {
        return waterMode;
    }

    public void setWaterMode(int waterMode) {
        this.waterMode = waterMode;
    }

    public int getWaterValue() {
        return waterValue;
    }

    public void setWaterValue(int waterValue) {
        this.waterValue = waterValue;
    }

    public int getMoist() {
        return moist;
    }

    public void setMoist(int moist) {
        this.moist = moist;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumidity() {return humidity;}

    public void setHumidity(int humidity) { this.humidity = humidity;}

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
