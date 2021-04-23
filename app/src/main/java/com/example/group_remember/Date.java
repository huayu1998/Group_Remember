package com.example.group_remember;



import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
//import java.lang.Object;
//import bsh.ParseException;



public class Date {

    private ArrayList<String> listC;

    public Date() {
        listC = new ArrayList<String>();
    }

    public ArrayList<String> creating(String fileForUserContent) throws FileNotFoundException {

        Reader readMachine = new Reader();
        ArrayList<String> Array = readMachine.read(fileForUserContent);

        String firstLine = Array.get(0);

        Scanner scanner = new Scanner(firstLine);

        String [] indexInfor = new String [10];
        indexInfor = scanner.next().split(", *");

        for (int i = 0; i < 10; i++) {
            System.out.println(indexInfor[i]);
        }

        int year = 0;
        int month = 0;
        int day = 0;


        for (int x = 0; x < 10; x++) {
            if (indexInfor[x].equals("")) {
                year = x;
            }
            else if (indexInfor[x].equals("")) {
                month = x;
            }
            else if (indexInfor[x].equals("")) {
                day = x;
            }


        }



        return Array;
    }


    public ArrayList<String> getList() {

        return listC;
    }



}

