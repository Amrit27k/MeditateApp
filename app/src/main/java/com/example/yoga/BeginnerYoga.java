package com.example.yoga;

public class BeginnerYoga {
    private String name,level,time,kcal;
    private int image;

    public BeginnerYoga(String name, String level, String time, String kcal, int image) {
        this.name = name;
        this.level = level;
        this.time = time;
        this.kcal = kcal;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getTime() {
        return time;
    }

    public String getKcal() {
        return kcal;
    }

    public int getImage() {
        return image;
    }
}
