package com.example.group_remember;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;


public class ReaderAndWriter {

    ArrayList<Date> datelist = new ArrayList<Date>();

    public ReaderAndWriter() {

    } // ReaderAndWriter


    public ArrayList<Date> read(Context context,String fileName) throws FileNotFoundException{

        File file = new File(fileName);
        if (file.exists()) {
            try {

                BufferedReader bufferedReader = null;
                FileInputStream inputFile;
                inputFile = context.openFileInput(fileName);
                bufferedReader = new BufferedReader(new InputStreamReader(inputFile));
                String line = " ";
                int numOfLines = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    StringBuilder content = new StringBuilder();
                    content.append(line);
                    String str = content.toString();
                    String[] lineInfo = str.split("/");
                    System.out.println(str);
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
        }
        else{
            try {
                datelist = new ArrayList<Date>();
                this.write(context,fileName,datelist);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return datelist;

    } // read method

    public void write(Context context, String fileName , ArrayList<Date> datelistw) throws IOException {

        FileOutputStream outputFile = context.openFileOutput(fileName, MODE_PRIVATE);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputFile));

        for(Date date : datelistw) {
            bufferedWriter.write(date.toString());
        }
        bufferedWriter.close();

    }



}