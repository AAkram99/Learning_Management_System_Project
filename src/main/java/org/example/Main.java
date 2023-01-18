package org.example;



public class Main {
    public static void main(String[] args) {

        try{
           DataPreparation.prepareStudentsData();  // create studentData.csv
           DataPreparation.prepareCoursesData();   // create courseData.csv
            CoreFunctions.createJsonFile();
       }
       catch(Exception e){
           System.out.println("! Error occurred in creating data files !");
           e.printStackTrace();
       }

        System.out.println("Welcome to LMS\n>>>>>>>>>>>>> Created by {AhmedAkram_17/1/2023} <<<<<<<<<<<<<<<<<");
        while (true) {
            int studentId = LMSPages.HomePage();
            try {
                CoreFunctions.printStudentDetailsPage(studentId);
            } catch (Exception e) {
                System.out.println("! Error occurred in printing student details !");
                e.printStackTrace();
            }
            String selectedAction = LMSPages.SelectAction();
            if (selectedAction.equalsIgnoreCase("a")) {
                LMSPages.EnrollmentPage(studentId);
            }
            else if (selectedAction.equalsIgnoreCase("d")) {
                LMSPages.UnEnrollmentPage(studentId);

            }
            else if (selectedAction.equalsIgnoreCase("r")) {
                LMSPages.replacementPage(studentId);
            }


        }
    }
}