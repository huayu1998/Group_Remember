package com.example.group_remember;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Reader {

    ArrayList<Date> datelist = new ArrayList<Date>();

    public Reader() {

    } // Reader


    public ArrayList<Date> read(String fileName) throws FileNotFoundException{

        File inputFile = new File(fileName);

        try {
            Scanner input = new Scanner(inputFile);

            int numOfLines = 0;

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineInfo = line.split("|");
                Date currentDate = new Date();

                currentDate.setYear(Integer.parseInt(lineInfo[0]));
                currentDate.setMonth(Integer.parseInt(lineInfo[1]));
                currentDate.setDay(Integer.parseInt(lineInfo[2]));
                currentDate.setImage(Integer.parseInt(lineInfo[3]));
                currentDate.setMusic(lineInfo[4]);
                currentDate.setText(lineInfo[5]);
                datelist.add(currentDate);
                numOfLines++;
            } // while

            System.out.println(Integer.toString(numOfLines - 1) + " text have been loaded");

            input.close();

        } // try

        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File " + fileName + " was not found");
            throw new FileNotFoundException("No such file");
            //return null;
        } // catch

        return datelist;

    } // read method



}