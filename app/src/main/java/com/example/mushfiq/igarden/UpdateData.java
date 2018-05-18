package com.example.mushfiq.igarden;

public class UpdateData {
    private int lightMode;
    private int lightValue;
    private int waterMode;
    private int waterValue;

    public UpdateData() {
    }

    public UpdateData(int lightMode, int lightValue, int waterMode, int waterValue) {
        this.lightMode = lightMode;
        this.lightValue = lightValue;
        this.waterMode = waterMode;
        this.waterValue = waterValue;
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
}
