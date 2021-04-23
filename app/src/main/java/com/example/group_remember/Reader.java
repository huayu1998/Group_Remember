package com.example.group_remember;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Reader {

    // No field yet

    ArrayList<String> dateArray;

    public Reader() {
        dateArray = new ArrayList<String>();
    } // Reader


    public ArrayList<String> read(String fileName) throws FileNotFoundException{

        File inputFile = new File(fileName);
        ArrayList<String> newArray = new ArrayList<String>();
        try {
            Scanner input = new Scanner(inputFile);
            System.out.println("Finished loading " + fileName + " file");

            int numOfLines = 0;

            while (input.hasNextLine()) {
                newArray.add(input.nextLine());
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

        if (dateArray.isEmpty() == false) {

            for (int x = 1; x < newArray.size(); x++) {
                dateArray.add(newArray.get(x));
            }

        }
        else {
            dateArray = newArray;
        }

        return dateArray;

    } // read method



}