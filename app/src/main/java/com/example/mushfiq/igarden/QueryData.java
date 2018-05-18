package com.example.mushfiq.igarden;

public class QueryData {
    private int moist;
    private int temp;
    private int humidity;
    private String lastUpdated;

    public QueryData() {
    }

    public QueryData(int moist, int temp, int humidity, String lastUpdated) {
        this.moist = moist;
        this.temp = temp;
        this.humidity = humidity;
        this.lastUpdated = lastUpdated;
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

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
