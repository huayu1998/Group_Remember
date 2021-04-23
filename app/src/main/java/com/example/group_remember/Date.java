package com.example.group_remember;


public class Date {

    int year = 0;
    int month = 0;
    int day = 0;
    String text = "";
    String music = "";
    int image = 0;

    public Date() {

    }

    @Override
    public String toString() {
        return "|" + year +
                "|" + month +
                "|" + day +
                "|" + image +
                "|" + music +
                "|'" + text + "\n";
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean finish(){
        return year != 0 && month != 0 && day != 0 && !text.equals("") && !music.equals("") && image != 0;
    }







}

