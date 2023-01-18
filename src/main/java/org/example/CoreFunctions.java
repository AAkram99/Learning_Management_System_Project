package org.example;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public abstract class CoreFunctions {
public static void createJsonFile() throws Exception {
    FileWriter jsonFile = new FileWriter("Student_Course_Detail.json");
    JSONObject obj = new JSONObject();
    JSONArray array = new JSONArray();

    array.add(1); array.add(2); array.add(3); array.add(4);
    obj.put("1",array);
    array = new JSONArray();

    array.add(2); array.add(4); array.add(6);
    obj.put("2",array);
    array = new JSONArray();

    array.add(1); array.add(3); array.add(5);
    obj.put("3",array);

    jsonFile.write(obj.toJSONString());
    jsonFile.close();
}

//======================================================================================================================
public static void printStudentDetailsPage(int student_Id_By_User) throws Exception{
    Object obj = new JSONParser().parse(new FileReader ("Student_Course_Detail.json"));
    JSONObject jsonobj = (JSONObject)obj;

    System.out.println("----------------------------------------------------------------------------");
    int inputId = student_Id_By_User;
    String keyId = String.valueOf(inputId);
    if(inputId > 100 || inputId< 1){
        System.out.println("Invalid Student Id");
    }
    else {
            FileReader studentFileReader = new FileReader("student_data.csv");
            BufferedReader studentFileBr = new BufferedReader(studentFileReader);
            String line;
            String[] array;
            while ((line = studentFileBr.readLine()) != null) {
                array = line.split(",");
                if (array[0].equals(keyId)) {
                    System.out.println("============================================================================");
                    System.out.println("Student Details Page");
                    System.out.println("============================================================================");
                    System.out.println("Name: " + array[1] + "      " + "Grade: " + array[2] +
                            "        " + "Email: " + array[3]);
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("Enrolled Courses.");
                }

            }
            if (jsonobj.containsKey(keyId)){
                JSONArray coursesArrayJson = (JSONArray) jsonobj.get(keyId);
                for (int i=0 ; i < coursesArrayJson.size() ; i++ ){
                    String x = coursesArrayJson.get(i).toString();
                    FileReader coursesFileReader = new FileReader("coursedata.csv");
                    BufferedReader coursesFileBr = new BufferedReader(coursesFileReader);

                    while((line = coursesFileBr.readLine()) != null){
                        array = line.split(",");
                        if(array[0].equals(x)){

                            System.out.print((i+1) + "- ");
                            System.out.print(array[0]);
                            for(int c = 0 ; c < 5-(array[0].length());c++) System.out.print(" ");

                            // print course name column
                            System.out.print(array[1]);
                            for(int c = 0 ; c < 34-(array[1].length());c++) System.out.print(" ");

                            // print instructor column
                            System.out.print(array[2]);
                            for(int c = 0 ; c < 24-(array[2].length());c++) System.out.print(" ");

                            // print course duration column
                            System.out.print(array[3]);
                            for(int c = 0 ; c < 18-(array[3].length());c++) System.out.print(" ");

                            // print course time column
                            System.out.print(array[4]);
                            for(int c = 0 ; c < 14-(array[4].length());c++) System.out.print(" ");

                            // print location column
                            System.out.print(array[5]);
                            for(int c = 0 ; c < 11-(array[5].length());c++) System.out.print(" ");
                            System.out.println();
                            break;
                        }
                    }
                }

            }
            else System.out.println("This student hasn't enrolled in any courses");

        }
    System.out.println("-------------------------------------------------------------------------------------");

    }
//======================================================================================================================

public static void enrollingStudent(int student_Id_By_User, int course_Id_By_User) throws Exception{

    System.out.println("----------------------------------------------------------------------------");
    int studentId = student_Id_By_User;
    int courseId = course_Id_By_User;
    String keyId = String.valueOf(studentId);
    String courseValueId = String.valueOf(courseId);
    if(studentId > 100 || studentId< 1){
        System.out.println("Failed to enroll: The input you have provided is invalid, please enter a valid input");
    }
    else if(courseId > 17 || courseId< 1){
        System.out.println("Failed to enroll: The input you have provided is invalid, please enter a valid input");
    }
    else {
        Object obj = new JSONParser().parse(new FileReader ("Student_Course_Detail.json"));
        JSONObject jsonobj = (JSONObject)obj;
        if (jsonobj.containsKey(keyId)){
            JSONArray coursesArrayJson = (JSONArray) jsonobj.get(keyId);
            if(coursesArrayJson.size() >= 6 ){
                System.out.println("Failed to enroll: Students can’t enroll in more than 6 programs at the same time.");
            }
            else {
                int flag = 0;
                for ( int i=0 ; i <coursesArrayJson.size() ; i++) {
                    if (courseValueId.equals(coursesArrayJson.get(i).toString())) flag++;
                }
                if (flag>0) {
                    System.out.println("Failed to enroll: This student was already enrolled to the selected course.");
                }
                else {
                    coursesArrayJson.add(coursesArrayJson.size(), courseId);
                    jsonobj.put(keyId, coursesArrayJson);
                    FileWriter jsonFile = new FileWriter("Student_Course_Detail.json");
                    jsonFile.write(jsonobj.toJSONString());
                    jsonFile.close();
                    System.out.println("Enrolled to "+DataPreparation.getCourseName(course_Id_By_User)+" course successfully.");

                }
            }

        }
        else{
            JSONArray array = new JSONArray();
            array.add(courseId);
            jsonobj.put(keyId, array);
            FileWriter jsonFile = new FileWriter("Student_Course_Detail.json");
            jsonFile.write(jsonobj.toJSONString());
            jsonFile.close();
            System.out.println("Enrolled to "+DataPreparation.getCourseName(course_Id_By_User)+" course successfully.");

        }

    }


}

//======================================================================================================================
public static void unEnrollingStudent(int student_Id_By_User) throws Exception{
    Scanner sc = new Scanner(System.in);

    int studentId = student_Id_By_User;
    System.out.print("\nPlease Enter Course Id: ");
    int courseId = sc.nextInt();
    String keyId = String.valueOf(studentId);
    String courseValueId = String.valueOf(courseId);
    if(studentId > 100 || studentId< 1){
        System.out.println("The input you have provided is invalid, please enter a valid input");
    }
    else if(courseId > 17 || courseId< 1){
        System.out.println("The input you have provided is invalid, please enter a valid input");
    }
    else{
        Object obj = new JSONParser().parse(new FileReader ("Student_Course_Detail.json"));
        JSONObject jsonobj = (JSONObject)obj;
        if (jsonobj.containsKey(keyId)) {
            JSONArray coursesArrayJson = (JSONArray) jsonobj.get(keyId);
            if (coursesArrayJson.size() > 1) {
                int flag = 0;
                for (int i = 0; i < coursesArrayJson.size(); i++) {
                    if (courseValueId.equals(coursesArrayJson.get(i).toString())) {
                        coursesArrayJson.remove(i);
                        flag++;
                    }
                }
                if (flag > 0) {
                    jsonobj.put(keyId, coursesArrayJson);
                    FileWriter jsonFile = new FileWriter("Student_Course_Detail.json");
                    jsonFile.write(jsonobj.toJSONString());
                    jsonFile.close();
                    System.out.println("Unrolled successfully from course with Id (" +
                            courseValueId + ").");
                }
                else System.out.println("The student has not enrolled to this course before.");

            }
            else {
                System.out.println("This student has only one course and cannot be unEnrolled from it. ");
            }
        }
        else {
            System.out.println("This student has not enrolled to any courses before. ");
        }


    }

    }
//======================================================================================================================

    public static void replaceStudentCourse(int student_Id_By_User) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------");
        int studentId = student_Id_By_User;
        System.out.print("\nPlease Enter course Id to be replaced : ");
        int courseId = sc.nextInt();
        System.out.print("\nPlease Enter course Id to be replace : ");
        int course2Id = sc.nextInt();

        if(courseId > 17 || courseId < 1 || course2Id > 17 || course2Id < 1)
            throw new Exception ("The input you have provided is invalid, please enter a valid input") ;

        String keyId = String.valueOf(studentId);
        String courseValueId = String.valueOf(courseId);

            Object obj = new JSONParser().parse(new FileReader ("Student_Course_Detail.json"));
            JSONObject jsonobj = (JSONObject)obj;
            if (jsonobj.containsKey(keyId)) {
                JSONArray coursesArrayJson = (JSONArray) jsonobj.get(keyId);

                if (coursesArrayJson.size() > 0) {
                    int flag = 0;
                    for (int i = 0; i < coursesArrayJson.size(); i++) {
                        if (courseValueId.equals(coursesArrayJson.get(i).toString())) {
                            coursesArrayJson.remove(i);
                            coursesArrayJson.add(i, course2Id);
                            flag++;
                        }

                    }
                    if (flag > 0) {
                        jsonobj.put(keyId,coursesArrayJson);
                        FileWriter jsonFile = new FileWriter("Student_Course_Detail.json");
                        jsonFile.write(jsonobj.toJSONString());
                        jsonFile.close();
                        System.out.println("Student is unEnrolled from " +
                                DataPreparation.getCourseName(courseId)
                                + "course and enrolled to " + DataPreparation.getCourseName(course2Id) + " course successfully.");
                    }
                    else System.out.println("The student has not enrolled to this course before.");

                }
                else {
                    System.out.println("This student has only one course and cannot be unEnrolled from it. ");
                }
            }
            else {
                System.out.println("This student has not enrolled to any courses yet. ");
            }




    }
}
//======================================///End of this Class\\\=========================================================
