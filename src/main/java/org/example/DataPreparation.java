package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public abstract class DataPreparation {


    DataPreparation(){}

    //==================================================================================================================
    // method to prepare the students text file data to CSV students data file.
    //--------------------------------------------------------------------------

    public static void prepareStudentsData() throws Exception {

        FileReader fileReader = new FileReader("student-data.txt");
        BufferedReader br = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("student_data.csv");
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write("id,");        //to write the id column in the first row before the name
        String line;
        int studentRowNumber = 0;
        char[] character;
        while ((line = br.readLine()) != null) {
            character = line.toCharArray();
            for (int x = 0 ; x < line.length()-1 ; x++) {
                if (character[x] == '$') {         // to replace the $ with \n and put id
                    studentRowNumber++;
                    bw.write("\n" + studentRowNumber + ",");
                }

                else if ((character[x] == '#') && ((character[x+1] != ' ')&&(character[x-1] != ' '))) {
                    // to replace the # with ,
                    bw.write(",");
                }

                else bw.write(character[x]);

            }
        }
        bw.close();
        fileWriter.close();
        br.close();
        fileReader.close();
//        System.out.println("Done, Students.CSV file generated successfully");

    }

    //==================================================================================================================
    // method to prepare the course XML data to CSV course data file.
    //----------------------------------------------------------------

    public static void prepareCoursesData() throws Exception {
        FileReader fileReader = new FileReader("coursedata.xml");
        BufferedReader br = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("coursedata.csv");
        BufferedWriter bw = new BufferedWriter(fileWriter);

        // write the columns names of the file
        bw.write("id,Course Name,Instructor,Course duration,Course time,Location\n");

        String line;
        while((line=br.readLine()) != null ){

            if (line.contains("xml version"))continue;
            if (line.contains("<row>"))continue;
            if (line.contains("root"))continue;

            char[] ch = line.toCharArray();
            for (int counter = 0; counter < line.length()-1 ; counter++){
                if (ch[counter]=='>'){
                    for(int i = counter; i<line.length()-1; i++)
                        if(ch[i+1]==',') bw.write(" &");
                        else if (ch[i+1]!='<') bw.write(ch[i+1]);
                        else break;
                }
            }

            if (line.contains("</row>")) {bw.write("\n"); continue;}
            if (line.contains("<Location>"));
            else bw.write(",");
        }
        bw.close();
        fileWriter.close();
        br.close();
        fileReader.close();
//        System.out.println("Done, Courses.CSV file generated successfully");
    }

    //==================================================================================================================
    // Method to print the CSV students data
    //----------------------------------------
    public static void printStudentsData() throws Exception {
        FileReader fileReader = new FileReader("student_data.csv");
        BufferedReader br = new BufferedReader(fileReader);
        System.out.println("Student List");
        String line;
        String [] arr;
        while((line=br.readLine()) != null ){
            arr = line.split(",");

            // print id column
            System.out.print(arr[0]);
            for(int c = 0 ; c < 6-(arr[0].length());c++) System.out.print(" ");

            // print name column
            System.out.print(arr[1]);
            for(int c = 0 ; c < 21-(arr[1].length());c++) System.out.print(" ");

            // print grade column
            System.out.print(arr[2]);
            for(int c = 0 ; c < 8-(arr[2].length());c++) System.out.print(" ");

            // print email column
            System.out.print(arr[3]);
            for(int c = 0 ; c < 38-(arr[3].length());c++) System.out.print(" ");

            // print address column
            System.out.print(arr[4]);
            for(int c = 0 ; c < 38-(arr[4].length());c++) System.out.print(" ");

            // print region column
            System.out.print(arr[5]);
            for(int c = 0 ; c < 30-(arr[5].length());c++) System.out.print(" ");

            // print country column
            System.out.print(arr[6]);
            for(int c = 0 ; c < 15-(arr[6].length());c++) System.out.print(" ");

            System.out.println();
        }
    }
//======================================================================================================================

    public static void printCoursesData() throws Exception {
        FileReader fileReader = new FileReader("coursedata.csv");
        BufferedReader br = new BufferedReader(fileReader);

        System.out.println("Available Courses List");
        System.out.println("-------------------------------------------");
        String line;
        String [] arr;
        while((line=br.readLine()) != null ){
            arr = line.split(",");

            // print id column
            System.out.print(arr[0]);
            for(int c = 0 ; c < 5-(arr[0].length());c++) System.out.print(" ");

            // print course name column
            System.out.print(arr[1]);
            for(int c = 0 ; c < 34-(arr[1].length());c++) System.out.print(" ");

            // print instructor column
            System.out.print(arr[2]);
            for(int c = 0 ; c < 24-(arr[2].length());c++) System.out.print(" ");

            // print course duration column
            System.out.print(arr[3]);
            for(int c = 0 ; c < 18-(arr[3].length());c++) System.out.print(" ");

            // print course time column
            System.out.print(arr[4]);
            for(int c = 0 ; c < 14-(arr[4].length());c++) System.out.print(" ");

            // print location column
            System.out.print(arr[5]);
            for(int c = 0 ; c < 11-(arr[5].length());c++) System.out.print(" ");
            System.out.println();

        }
        System.out.println("\n-------------------------------------------------------------------------------------");
    }

    public static String getCourseName(int courseId){
        String courseName= "";
        try {
           FileReader fileReader = new FileReader("coursedata.csv");
           BufferedReader br = new BufferedReader(fileReader);


           String line;
           String[] arr;
           while ((line = br.readLine()) != null) {
               arr = line.split(",");
               if (arr[0].equals(String.valueOf(courseId))){
                   courseName = arr[1];
               }

           }
       }catch (Exception e){e.printStackTrace();}
       return courseName;
    }
}
// ===============++++++++++++++ End of the class +++++++++++++++++++++==================== \\
