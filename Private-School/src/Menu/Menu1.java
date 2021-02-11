
package Menu;

//import static individualproject.Register.assignmentList;
//import static individualproject.Registration.coursesList;
//import static individualproject.Registration.studentsList;
//import static individualproject.Registration.trainerList;
import java.util.Scanner;

public class Menu1 {
    public static Scanner in = new Scanner(System.in); 
    
    static int mainMenuChoice;
    static int syntheticMenuChoice;
    static int inputMenuChoice;
    static int inputMenuReportChoice;
    static int menuAsk;
    static int addMore;
    
    
    
    
    //------------------Menus Structure------------------------
    public static void MainMenu(Scanner in){
        System.out.println("----------Private School Management System-----------");
        System.out.println("1.Reports\n"+
                           "2.Insert Information\n"
                           +"Please select option 1 or 2 :\n"
                           +"For Exit Enter any letter");
        validOptionMainMenu1(in);
    
    }
    
   
    
     public static void inputMenu(Scanner in){
        System.out.println("--------------------User's Input Menu---------------------");
        System.out.println("1.Add Courses\n"
                          +"2.Add Trainers\n"
                          +"3.Add Students\n"
                          +"4.Add Assignements\n"
                          +"5.Enroll Trainers to Courses\n"
                          +"6.Enroll Students to Courses\n"
                          +"7.Enroll/Grade Assignments to Students\n"
                          +"8.Reports\n"
                          +"Select Option 1 to 8:\n"
                          +"*Return to Main Menu (Enter 0)");

       validOptionInputMenu(in);
        
     }
    
     
     
     public static void reportsMenu(Scanner in){
        System.out.println("----------------Reports from User's Input-------------");
        System.out.println("1.Retrieve the List of all Courses\n"
                           +"2.Retrieve the List of all Students\n"
                           +"3.Retrieve the List of all Trainers\n"
                           +"4.Retrieve the List of all Assignements\n"
                           +"5.Retrieve the List of Students per Course\n"
                           +"6.Retrieve the List of Trainers per Course\n"
                           +"7.Retrieve the List of Assignements per Course\n"
                           +"8.Retrieve the List of Assignements per Student per Course\n"
                           +"9.Retrieve the List of Students that hold multiple Courses\n"
                           + "Select Option 1 to 9\n"
                           +"*Return to Main Menu (Enter 0)\n"
                           +"*Return to User's Input Menu (Enter -1)");
      
        validOptionReportsMenu(in);
     
     
     }
     
     
   
     
    
    public static void validOptionMainMenu1(Scanner in){
        if(in.hasNextInt()){
             mainMenuChoice =in.nextInt();
             switch(mainMenuChoice){
                  case 1:  reportsMenu(in);
                  break;
                  case 2:  inputMenu(in);
                  break;
                  default: System.out.println("Please Enter option 1 or 2");   validOptionMainMenu1(in);
              }
                } else{
                 System.out.println("Thank You For Using Private School Management System");
        }
  }
   
    
    
      public static void validOptionInputMenu(Scanner in){
          if(in.hasNextInt()){
             inputMenuChoice =in.nextInt();          
              switch(inputMenuChoice){
                  case 1:  Register.addCourse(in);  menuAsk(in);
                  break;
                  case 2:  Register.addTrainer(in); menuAsk(in);
                  break;
                  case 3:  Register.addStudents(in); menuAsk(in);
                  break; 
                  case 4:  Register.addAssignment(in); menuAsk(in);
                  break;
                  case 5: Register.enrollTrainerToCourse(in); menuAsk(in);
                  break;
                  case 6:  Register.enrollStudentToCourse(in); menuAsk(in);
                  break;
                  case 7: Register.gradeEnrollAssignment(in); menuAsk(in);
                  break;
                  case 8:  reportsMenu(in); 
                  break;
                  case 0:  MainMenu(in);
                  break;
                  default:validOptionInputMenu(in);
              }
           } else{
             System.out.println("Input not valid.Enter option 0 to 8");
                        in.nextLine();
                        validOptionInputMenu(in);
          }
      }
     
      
       public static void validOptionReportsMenu(Scanner in){
             if(in.hasNextInt()){
             syntheticMenuChoice =in.nextInt();
             switch(syntheticMenuChoice){
                  case 0:  MainMenu(in);
                  break;
                  case 1:  Register.callStoredProcedureGetAllCourses("CALL getAllCourses();");  menuAsk(in);
                  break;
                  case 2:  Register.callStoredProcedureGetAllStudents("CALL getAllStudents();");   menuAsk(in);
                  break;
                  case 3:  Register.callStoredProcedureGetAllTrainers("CALL getAllTrainers();");     menuAsk(in);
                  break; 
                  case 4:  Register.callStoredProcedureGetAllAssignments("CALL getAllAssignments();");   menuAsk(in);
                  break;
                  case 5:  Register.callStoredProcedureGetStudentsPerCourse("CALL getStudentsPerCourse();");;  menuAsk(in);
                  break;
                  case 6:  Register.callStoredProcedureGetTrainersPerCourse("CALL getTrainersPerCourse();");  menuAsk(in);
                  break; 
                  case 7:  Register.callStoredProcedureGetAssignmentsPerCourse("CALL getAssignmentsPerCourse();");  menuAsk(in);
                  break;
                  case 8:  Register.callStoredProcedureGetAssignmentsPerCoursePerStudent("CALL getAssignmentsPerCoursePerStudent();"); menuAsk(in);
                  break;
                  case 9:  Register.callStoredProcedureGetStudentsMultipleCourses("CALL getStudentsWithMultipleCourses();"); menuAsk(in);
                  break;
                } 
        
                    } else{
                    System.out.println("Input not valid.Enter option 0 to 9");
                               in.nextLine();
                               validOptionReportsMenu(in);
                      }
 }
      
      
      
      
      public static void menuAsk(Scanner in){
          System.out.println("*Return to Main Menu (Enter 0)\n"
                          
                             +"*Return to User's Input Menu(Enter 1)\n"
                             +"*Reurn to Reports (Enter 2)\n"
                             +"For Exit Enter any letter\n");
                             
                  if(in.hasNextInt()){
                  menuAsk =in.nextInt();          
                  switch(menuAsk){
                  case 0:  MainMenu(in);
                  break;
                  case 1:  inputMenu(in);
                  break;
                  case 2:  reportsMenu(in);
                  break;  
                  default: menuAsk(in);
               }
               
             }else{System.out.println("Thank You For Using Private School Management System.");
             }
        }
      
     
      public static void addMoreCourse(){
                System.out.println("If you want to add more Courses please Enter -1. For Exit Enter -2");
                           
                            while (!in.hasNextInt()) {
                            System.out.println("Invalid Input.Please Enter -1 to Add Courses or -2 for Exit : ");
                            in.next();
                             }
                            addMore = in.nextInt();
                           switch(addMore){
                              case -1:  Register.addCourse(in); menuAsk(in);
                              break;
                              case -2:  menuAsk(in);
                               break;
                              default: addMoreCourse();
                          }
      }
      
      
      
       public static void addMoreStudent(){
                System.out.println("If you want to add more Students please Enter -1. For Exit Enter -2");
                          while (!in.hasNextInt()) {
                            System.out.println("Invalid Input.Please Enter -1 to Add Students or -2 for Exit : ");
                            in.next();
                             }   
                           addMore = in.nextInt();
                           switch(addMore){
                              case -1:  Register.addStudents(in); menuAsk(in);
                              break;
                              case -2:  menuAsk(in);
                              break;
                              default: addMoreStudent();
                          }
      }
      
      
      
      public static void addMoreTrainer(){
                System.out.println("If you want to add more Trainers please Enter -1. For Exit Enter -2");
                          while (!in.hasNextInt()) {
                            System.out.println("Invalid Input.Please Enter -1 to Add Trainers or -2 for Exit : ");
                            in.next();
                             }   
                           addMore = in.nextInt();
                           switch(addMore){
                              case -1:  Register.addTrainer(in); menuAsk(in);
                              break;
                              case -2:  menuAsk(in);
                              break;
                              default: addMoreTrainer();
                          }
      }
      
      
      public static void addMoreAssignement(){
                System.out.println("If you want to add more Assignements please Enter -1. For Exit Enter -2");
                          while (!in.hasNextInt()) {
                            System.out.println("Invalid Input.Please Enter -1 to Add Assignements or -2 for Exit : ");
                            in.next();
                             }   
                           addMore = in.nextInt();
                           switch(addMore){
                              case -1:  Register.addAssignment(in); menuAsk(in);
                              break;
                              case -2:  menuAsk(in);
                              break;
                              default: addMoreAssignement();
                          }
      }
      
      
      
      
      
      /* public static void addMoreStudentsToCourse(){
                System.out.println("If you want to add Students to Another Course please Enter -1. For Exit Enter -2");
                          while (!in.hasNextInt()) {
                            System.out.println("Invalid Input.Please Enter -1 to Add Students to Another Course or -2 for Exit : ");
                            in.next();
                             }  
                           addMore = in.nextInt();
                           switch(addMore){
                              case -1:  Register.selectStudentPerCourse(in); menuAsk(in);
                              break;
                              case -2:  menuAsk(in);
                              break;
                              default: addMoreStudentsToCourse();
                          }
      }
      
      
       
         public static void addMoreTrainesToCourse(){
                System.out.println("If you want to add Trainers to Another Course please Enter -1. For Exit Enter -2");
                          while (!in.hasNextInt()) {
                            System.out.println("Invalid Input.Please Enter -1 to Add Trainers to Another Course or -2 for Exit : ");
                            in.next();
                             }  
                           addMore = in.nextInt();
                           switch(addMore){
                              case -1:  Register.selectTrainerPerCourse(in); menuAsk(in);
                              break;
                              case -2:  menuAsk(in);
                              break;
                              default: addMoreTrainesToCourse();
                          }
      }
      
         
         
            public static void addMoreAssignmentToCourse(){
                System.out.println("If you want to add Assignments to Another Course please Enter -1. For Exit Enter -2");
                          while (!in.hasNextInt()) {
                            System.out.println("Invalid Input.Please Enter -1 to Add Assignments to Another Course or -2 for Exit : ");
                            in.next();
                             }  
                           addMore = in.nextInt();
                           switch(addMore){
                              case -1:  Register.selectAssignmentPerCourse(in); menuAsk(in);
                              break;
                              case -2:  menuAsk(in);
                              break;
                              default: addMoreAssignmentToCourse();
                          }
      }
      
      
*/
      
}


    
    
    
    


