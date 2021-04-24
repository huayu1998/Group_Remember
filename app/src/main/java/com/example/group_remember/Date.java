package com.example.group_remember;


import java.io.Serializable;

public class Date implements Serializable {

    int year = 0;
    int month = 0;
    int day = 0;
    int image = 0;
    String topic = "null";
    String text = "null";
    String music = "null";


    public Date() {

    }

    @Override
    public String toString() {
        return year +
                "/" + month +
                "/" + day +
                "/" + image +
                "/" + music +
                "/" + topic +
                "/" + text + "/" +"\n";
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
        return !topic.equals("null") && year != 0 && month != 0 && day != 0 && !text.equals("null") && !music.equals("null") && image != 0;
    }







}

