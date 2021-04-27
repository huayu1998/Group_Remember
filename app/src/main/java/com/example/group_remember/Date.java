package com.example.group_remember;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Date implements Serializable,Comparable {

    int year = 0;
    int month = 0;
    int day = 0;
    int image = 0;

    Random rand = new Random();

    // Generate random integers in range 0 to 999
    int photo = rand.nextInt(10000);

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


    int sort = 0;
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
                "/" + text +
                "/" + photo + "/" + "\n";
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


    public String getWeek(){

        Calendar c = Calendar.getInstance();
        c.set(this.getYear(), this.getMonth() - 1, this.getDay());
        //c.setTime(format);
        int dayOfWeek = c.get(c.DAY_OF_WEEK);
        String time = "";

        if (dayOfWeek == 1) {
            time = this.getMonth() + "/" + this.getDay() + "/" + this.getYear() + "  Sunday";
        }
        else if (dayOfWeek == 2) {
            time = this.getMonth() + "/" + this.getDay() + "/" + this.getYear() + "  Monday";
        }
        else if (dayOfWeek == 3) {
            time = this.getMonth() + "/" + this.getDay() + "/" + this.getYear() + "  Tuesday";
        }
        else if (dayOfWeek == 4) {
            time = this.getMonth() + "/" + this.getDay() + "/" + this.getYear() + "  Wednesday";
        }
        else if (dayOfWeek == 5) {
            time = this.getMonth() + "/" + this.getDay() + "/" + this.getYear() + "  Thursday";
        }
        else if (dayOfWeek == 6) {
            time = this.getMonth() + "/" + this.getDay() + "/" + this.getYear() + "  Friday";
        }
        else if (dayOfWeek == 7) {
            time = this.getMonth() + "/" + this.getDay() + "/" + this.getYear() + "  Saturday";
        }
        return time;

    }
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getlength(){
        Calendar c = Calendar.getInstance();
        c.set(this.getYear(), this.getMonth() - 1, this.getDay());
        //c.setTime(format);
        int dayOfWeek = c.get(c.DAY_OF_WEEK);

        // Find how many day passed or will be coming
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        cal1.set(this.getYear(), this.getMonth() - 1, this.getDay());
        LocalDate currentdate = LocalDate.now();
        int month = 0;
        if (currentdate.getMonth().equals(Month.JANUARY)) {
            month = 1;
        }
        else if (currentdate.getMonth().equals(Month.FEBRUARY)) {
            month = 2;
        }
        else if (currentdate.getMonth().equals(Month.MARCH)) {
            month = 3;
        }
        else if (currentdate.getMonth().equals(Month.APRIL)) {
            month = 4;
        }
        else if (currentdate.getMonth().equals(Month.MAY)) {
            month = 5;
        }
        else if (currentdate.getMonth().equals(Month.JUNE)) {
            month = 6;
        }
        else if (currentdate.getMonth().equals(Month.JULY)) {
            month = 7;
        }
        else if (currentdate.getMonth().equals(Month.AUGUST)) {
            month = 8;
        }
        else if (currentdate.getMonth().equals(Month.SEPTEMBER)) {
            month = 9;
        }
        else if (currentdate.getMonth().equals(Month.OCTOBER)) {
            month = 10;
        }
        else if (currentdate.getMonth().equals(Month.NOVEMBER)) {
            month = 11;
        }
        else if (currentdate.getMonth().equals(Month.DECEMBER)) {
            month = 12;
        }
        cal2.set(currentdate.getYear(), month - 1, currentdate.getDayOfMonth());

        long numOfDays = (cal1.getTime().getTime() - cal2.getTime().getTime()) / (1000 * 60 * 60 * 24);
        String length = "";
        if (numOfDays < 0) {
            length = Math.abs(numOfDays) + " Days Passed";
        }
        else if (numOfDays > 0) {
            length = Math.abs(numOfDays) + " Days In The Future";
        }
        else{
            length = "Today is the day !";
        }
        return length;

    }

    public int getSort(){
        sort = this.getYear()*365 + this.getMonth()*31 + this.getDay();
        return sort;
    }


    @Override
    public int compareTo(Object o) {
        if(sort == ((Date) o).getSort()){
            return 0;
        }
        else if(sort > ((Date) o).getSort()){
            return 1;
        }else{
            return -1;
        }

    }
}

