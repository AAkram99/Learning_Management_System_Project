package org.example;
import java.util.Scanner;

public abstract class LMSPages {

    public static int HomePage(){
        System.out.println("=========================================================================================");
        System.out.println("Home Page");
        System.out.println("=========================================================================================");
        try{DataPreparation.printStudentsData();}
        catch(Exception e){e.printStackTrace();
            System.out.println("error occurred !!!!");}

        int studentID = takeStudentId();

        return studentID;
    }
//======================================================================================================================
    public static void EnrollmentPage(int studentId){
        System.out.println("=========================================================================================");
        System.out.println("Enrollment Page");
        System.out.println("=========================================================================================");
        try{DataPreparation.printCoursesData();} catch (Exception e){e.printStackTrace();}
        Scanner sc = new Scanner(System.in);
        String selectedAction ="";
        int courseId = 0;
        while(!(selectedAction.equals("b"))) {
            System.out.println("Please make one of the following:");
            System.out.println("Enter the course id that you want to enroll the student to");
            System.out.println("Enter b to go back to the home page");
            System.out.print("Please select the required action: ");
            selectedAction = sc.next();
            try{courseId = Integer.parseInt(selectedAction);} catch (Exception e){}
            try{CoreFunctions.enrollingStudent(studentId,courseId);}
            catch (Exception e){e.printStackTrace();}

        }

    }
//======================================================================================================================
    public static void UnEnrollmentPage(int studentId){
        System.out.println("=========================================================================================");
        System.out.println("UnEnroll Page");
        System.out.println("=========================================================================================");
        try{CoreFunctions.printStudentDetailsPage(studentId);} catch (Exception e ){e.printStackTrace();}
        try{CoreFunctions.unEnrollingStudent(studentId);} catch (Exception e){
            System.out.println("The input you have provided is invalid, please enter a valid input");
        }
        String selection = SelectAction();
        if (selection.equalsIgnoreCase("a")) {
            EnrollmentPage(studentId);
        } else if (selection.equalsIgnoreCase("d")) {
            UnEnrollmentPage(studentId);
        }
        else if (selection.equalsIgnoreCase("r")) {
            replacementPage(studentId);
        }

    }
//======================================================================================================================
    public static void replacementPage (int studentId){
        System.out.println("=========================================================================================");
        System.out.println("Replacement Page");
        System.out.println("=========================================================================================");
        try{
            DataPreparation.printCoursesData();
            CoreFunctions.printStudentDetailsPage(studentId);
        } catch (Exception e ){e.printStackTrace();}
        try{CoreFunctions.replaceStudentCourse(studentId);}catch(Exception e){
            System.out.println("The input you have provided is invalid, please enter a valid input");
        }
        String selection = SelectAction();

        if (selection.equalsIgnoreCase("a")) {
            EnrollmentPage(studentId);
        } else if (selection.equalsIgnoreCase("d")) {
            UnEnrollmentPage(studentId);
        }
        else if (selection.equalsIgnoreCase("r")) {
           replacementPage(studentId);
        }


    }
//======================================================================================================================
    public static String SelectAction(){

        System.out.println("Please choose from the following:");
        System.out.println("a - Enroll in a course");
        System.out.println("d - UnEnroll from an existing course");
        System.out.println("r - Replacing an existing course");
        System.out.println("b - Back to the main page");
        System.out.print("please select the required action: ");
        Scanner sc = new Scanner(System.in);
        String selectedAction = sc.next().toLowerCase();
        while(!(selectedAction.equals("a") || selectedAction.equals("d")
                || selectedAction.equals("r")|| selectedAction.equals("b")))
        {
            System.out.println("Invalid option ! ");
            System.out.println("Please choose from the following:");
            System.out.println("a - Enroll in a course");
            System.out.println("d - UnEnroll from an existing course");
            System.out.println("r - Replacing an existing course");
            System.out.println("b - Back to the main page");
            System.out.print("please select the required action: ");
            selectedAction = sc.next().toLowerCase();
        }
        return selectedAction;
    }
//======================================================================================================================
    public static int takeStudentId(){
        Scanner sc = new Scanner(System.in);
        int studentID = 0  ; int c=0;
        try {
            while(studentID < 1 || studentID >100){
                if (c>0)System.out.println("\nThe input you have provided is invalid, please enter a valid input");
                System.out.print("\nPlease select the required student: ");
                studentID = sc.nextInt();
                c++;
            }
        } catch (Exception e) {
            System.out.println("The input you have provided is invalid, please enter a valid input");
            studentID = takeStudentId();
        }
        return studentID;
    }
}
