package com.example.group_remember;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;


public class ReaderAndWriter {

    ArrayList<Date> datelist = new ArrayList<Date>();

    public ReaderAndWriter() {

    } // ReaderAndWriter


    public ArrayList<Date> read(Context context,String fileName) throws FileNotFoundException{



        try {
            FileInputStream inputFile = null;
            inputFile = context.openFileInput(fileName);

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
                currentDate.setTopic(lineInfo[5]);
                currentDate.setText(lineInfo[6]);
                datelist.add(currentDate);
                numOfLines++;
            } // while

            System.out.println(Integer.toString(numOfLines - 1) + " text have been loaded");
            inputFile.close();
            input.close();

        } // try

        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File " + fileName + " was not found");
            throw new FileNotFoundException("No such file");
            //return null;
        } // catch
        catch (IOException e) {
            e.printStackTrace();
        }

        return datelist;

    } // read method

    public void write(String fileName , ArrayList<Date> datelistw) throws IOException {

        File outputFile = new File(fileName);

        FileWriter myWriter = new FileWriter(outputFile);
        for(Date date : datelistw) {
            myWriter.write(date.toString());
        }
        myWriter.close();

    }



}