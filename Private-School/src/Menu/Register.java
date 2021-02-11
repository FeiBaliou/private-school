/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import dbhelpers.Database;
import entities.Assignment;
import entities.Course;
import entities.Student;
import entities.Trainer;
import static java.lang.Integer.parseInt;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author feiba
 */
public class Register {
     private String serverIP = "localhost"; // ra1.anystream.eu
    // "127.0.0.1" // localhost  
    private String srvPort = "3306";
    private String databaseName = "schoolmanagementsystem";
    private String username = "root";
    private String password = "1234";
    
    
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      public static String courseType;
//    public static List<Course> coursesList = new ArrayList<>();
//    public static List<Student> studentsList = new ArrayList<>();
//    public static List<Student> studentToCourse;
//    public static List<Trainer> trainerList = new ArrayList<>();
//    public static List<Trainer> trainerPerCourse;
//    public static List<Assignment> assignmentList = new ArrayList<>();
//    public static List<Assignment>assignmentPerCourse ;
    
    public static Scanner in = new Scanner(System.in); 

    public Register() {
    }
    
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData rsmd = null;
        
        
        
        // Add new Student
        public static Student addStudents(Scanner in){
        System.out.println("Please Enter the Number of Students you want to add:");
        int numStudentsAdd = inputNumber(in);
        for (int i = 0; i < numStudentsAdd; i++) {
             Student student = new Student();
             System.out.println("Enter Firstname for Student "+ (i+1)+ ":");
             student.setFirstName(stringValidation(in));         
             System.out.println("Enter Lastname for Student "+ (i+1)+ ":");
             student.setLastName(stringValidation(in));
             System.out.println("Enter Date of Birth for Student "+ (i+1)+ " (yyyy-MM-dd) :");
             LocalDate Datedob = LocalDate.parse(dobValidation(in), formatter);
             student.setDateOfBirth(Datedob);
             System.out.println("Enter Tuition Fees for Student "+ (i+1)+ ":");
             while (!in.hasNextInt()) {
                System.out.println("Please Enter a number : ");
                in.next();
             }
             student.setTuitionFees(in.nextDouble());
           //  studentsList.add(student);
             executeUpdateStudent(student);
             System.out.println("Student added succesfully. ");
            System.out.print("Id: "); 
            int numCourses = printResultSetIntCount(executeQuery("SELECT count(*) as count FROM students;") );
                     
             }
             Menu1.addMoreStudent();
             return null;
          }
    
    
    
        //Add new Course
        public static Course addCourse(Scanner in){
            System.out.println("Please Enter the number of Courses you want to add: ");
            int numCoursesAdd = inputNumber(in);
            for(int i = 0; i < numCoursesAdd; i++) {
             Course course = new Course();
             System.out.println("Enter Title for Course "+ (i+1)+ ":");
             course.setTitle(in.next());
             System.out.println("Enter Stream for Course "+ (i+1)+ ":");
             course.setStream(in.next());
             System.out.println("Select Type for Course "+ (i+1)+ " (Please Enter PART-TIME or FULL-TIME)");
             courseType(courseType,in,course);
             System.out.println("Enter Start Date for Course "+ (i+1)+ " (yyyy-MM-dd):");
             LocalDate DateStart = LocalDate.parse(startDateCourseValidation(in), formatter);
             course.setStartDate(DateStart);
             System.out.println("Enter End Date for Course "+ (i+1)+ " (yyyy-MM-dd):");
             String endDate = in.next();
             LocalDate end = LocalDate.parse(endDate, formatter);
             if(end.isBefore(DateStart)) {
             System.out.println("The Date you entered is before Start Date.Please Enter End Date that is after " + DateStart);
             endDate = in.next();
             }
             LocalDate EndDate = LocalDate.parse(endDate, formatter);
             course.setEndDate(EndDate); 
             executeUpdateCourse(course);
             System.out.println("Courses added succesfully. ");
             System.out.print("Id: "); 
             printResultSetIntCount(executeQuery("SELECT count(*) as count FROM courses;") );
          }
             Menu1.addMoreCourse();
             return null;
      }
    
   
 
        //Add new Trainer
        public static Trainer addTrainer(Scanner in){
             System.out.println("Please Enter the number of Trainers you want to add:");
             int numTrainersAdd = inputNumber(in);
             for(int i = 0; i < numTrainersAdd; i++) {
             Trainer trainer = new Trainer();
             System.out.println("Enter First Name for Trainer "+ (i+1)+ ":");
             trainer.setFirstName(stringValidation(in));
             System.out.println("Enter Last Name for Trainer "+ (i+1)+ ":");
             trainer.setLastName(stringValidation(in));
             System.out.println("Enter Subject for Trainer "+ (i+1)+ ":");
             trainer.setSubject(stringValidation(in));
             executeUpdateTrainer(trainer);
             System.out.println("Trainers added succesfully. ");
             System.out.print("Id: "); 
             printResultSetIntCount(executeQuery("SELECT count(*) as count FROM trainers;") );
          }
           Menu1.addMoreTrainer();
           return null;
     }

    

    
    
        //Add new Assignment
        public static Assignment addAssignment(Scanner in){
              System.out.println("Please Enter the number of Assignments you want to add:");
              int numAssignmentAdd = inputNumber(in);
              for(int i = 0; i < numAssignmentAdd; i++) {
              in.nextLine();
              Assignment assignment = new Assignment();              
              System.out.println("Enter Title for Assignment "+ (i+1)+ ":");
              assignment.setTitle(in.nextLine());
              System.out.println("Enter Description for Assignment "+ (i+1)+ ":");
              assignment.setDescription(in.nextLine());
              System.out.println("Enter Submission Date for Assignment "+ (i+1)+ "(yyyy-MM-dd):");
              LocalDate dateSub = LocalDate.parse(subDateAssignments(in), formatter);
              assignment.setSubDateTime(dateSub);
              System.out.println("Enter Total Mark for Assignment "+ (i+1)+ " (From 0 to 100) :");
              while (!in.hasNextByte()) {
                System.out.println("Please enter a number : ");
                in.next();
            }
              assignment.setTotalMark(in.nextByte());
              System.out.println("Enter Oral Mark for Assignment "+ (i+1)+ " (From 0 to 20) :");
              while (!in.hasNextByte()) {
                System.out.println("Please enter a number : ");
                in.next();
            }
              assignment.setOralMark(in.nextByte());
              executeUpdateAssignment(assignment);
               System.out.println("Assignments added succesfully. ");
               System.out.print("Id: "); 
               printResultSetIntCount(executeQuery("SELECT count(*) as count FROM assignments;") );
    }
          Menu1.addMoreAssignement();
          return null;
    }

        
        
    
    public static void printAllcourses(){
          callStoredProcedureGetAllCourses("CALL getAllCourses();");
          System.out.println("--------------------------------------------------------------------");
   }
    
    public static void printAllStudents(){
          callStoredProcedureGetAllStudents("CALL getAllStudents();");
          System.out.println("--------------------------------------------------------------------");
    }
    
    
     public static void printAllTrainers(){
          callStoredProcedureGetAllTrainers("CALL getAllTrainers();");
          System.out.println("--------------------------------------------------------------------");
    }
     
     
     public static void printStudentsPerCourse(){
         callStoredProcedureGetStudentsPerCourse("CALL getStudentsPerCoursebyId();");
          System.out.println("--------------------------------------------------------------------");
     
     }
     
     
     public static void printStudentsWithAssignmentsNullGrades(){
        callStoredProcedureGetAssignmentsPerCoursePerStudent("CALL getAssignmentsPerCoursePerStudentNullGrades();");
        System.out.println("--------------------------------------------------------------------");
     }
     
     
     public static void printAllAssignments(){
          callStoredProcedureGetAllAssignments("CALL getAllAssignments();");
          System.out.println("--------------------------------------------------------------------");
     }
 
    // Enroll Student to Course
    public static void enrollStudentToCourse(Scanner in){
          int courseSelect = 0 ;
          int cid =0;
          printAllcourses();
          System.out.println("Number of Courses:");
          int numCourses = printResultSetIntCount(executeQuery("SELECT count(*) as count FROM courses;") );
          System.out.println("SELECT COURSE (*Enter 1 to " + numCourses + ")");
         
          while (!in.hasNextInt()) {
                System.out.println("Please Enter a valid option : ");
                in.nextLine();
             }
          courseSelect = in.nextInt();
          if(courseSelect>=1 && courseSelect<= numCourses)
          cid = courseSelect;
          else{System.out.println("Please Enter a Valid Option.");  
          enrollStudentToCourse(in);}
          int studentSelect = 0;
          int sid =0;
          System.out.println("--------------------------------------------------------------------");
          printAllStudents();
          System.out.println("Number of Students:");
          int numStudents = printResultSetIntCount(executeQuery("SELECT count(*) as count FROM students;") );
          System.out.println("SELECT STUDENT (*Enter 1 to " + numStudents + ")");
          studentSelect =  in.nextInt();
          if(studentSelect>=1 && studentSelect<= (numStudents+1))
           { sid = studentSelect;}
           else{System.out.println("Please Enter a Valid Option."); enrollStudentToCourse(in);}
           executeUpdateEnrollmentStudents(cid,sid);
           System.out.println("Student with id: " + sid + " succesfully enrolled to course with id: " + cid );
        
    }
    
    
    //Enroll Trainer to Course
    public static void enrollTrainerToCourse(Scanner in){
          int courseSelect = 0 ;
          int eid =0;
          printAllcourses();
          System.out.println("Number of Courses:");
          int numCourses = printResultSetIntCount(executeQuery("SELECT count(*) as count FROM courses;") );
          System.out.println("SELECT COURSE (*Enter 1 to " + numCourses + ")");
          
          while (!in.hasNextInt()) {
                System.out.println("Please Enter a valid option : ");
                in.nextLine();
             }
          courseSelect = in.nextInt();
          if(courseSelect>=1 && courseSelect<= numCourses)
          eid = courseSelect;
          else{System.out.println("Please Enter a Valid Option.");  
          enrollTrainerToCourse(in);}
          int trainerSelect = 0;
          int tid =0;
          System.out.println("--------------------------------------------------------------------");
          printAllTrainers();
          System.out.println("Number of Trainers:");
          int numTrainers = printResultSetIntCount(executeQuery("SELECT count(*) as count FROM trainers;") );
          System.out.println("SELECT TRAINER (*Enter 1 to " + numTrainers + ")");
          
          while (!in.hasNextInt()) {
                System.out.println("Please Enter a valid option : ");
                in.nextLine();
             }
          trainerSelect =  in.nextInt();
          if(trainerSelect>=1 && trainerSelect<= numTrainers)
           { tid = trainerSelect;}
           else{System.out.println("Please Enter a Valid Option."); enrollTrainerToCourse(in);}
           executeUpdateEnrollmentTrainer(eid,tid);
           System.out.println("Trainer with id: " + tid + " succesfully enrolled to course with id: " + eid );
    }
    

       // Enroll Assignment to Student
        public static void enrollAssignment(Scanner in){
             int courseperstudent = 0 ;
             int eid =0;
             printStudentsPerCourse();
             System.out.println("Total Enrollments to Courses:");
             int numEnroll = printResultSetIntCount(executeQuery("SELECT count(*) as count FROM enrollments;") );
             System.out.println("SELECT ID to Enroll an Assignment to Student(*Enter 1 to " + numEnroll + ")");
             
             while (!in.hasNextInt()) {
                System.out.println("Please Enter a valid option : ");
                in.nextLine();
             }
             courseperstudent= in.nextInt();
             if(courseperstudent>=1 && courseperstudent<= numEnroll)
             { eid = courseperstudent;}
             else{System.out.println("Please Enter a Valid Option.");  
             enrollAssignment(in);}
             int assignmentSelect = 0;
             int aid =0;
             System.out.println("--------------------------------------------------------------------");
             printAllAssignments();
             System.out.println("Assignments:");
             int numAss = printResultSetIntCount(executeQuery("SELECT count(*) as count FROM assignments;") );
             System.out.println("SELECT Assignment(*Enter 1 to " + numAss + ")");
              while (!in.hasNextInt()) {
                System.out.println("Please Enter a valid option : ");
                in.nextLine();
             }
              assignmentSelect= in.nextInt();
          if(assignmentSelect>=1 && assignmentSelect<= numAss)
           { aid = assignmentSelect;}
           else{System.out.println("Please Enter a Valid Option."); enrollAssignment(in);}
           executeUpdateEnrollmentAssignment(eid,aid);
           System.out.println("Assignment with id: " + aid + " succesfully enrolled to student" );
        }
    
    
        // Grade an Assignment
        public static void gradeAssignment(Scanner in){
             int idgrade = 0 ;
             int oralmark =0;
             int totalmark = 0;
             printStudentsWithAssignmentsNullGrades();
             System.out.println("");
             System.out.println("SELECT ID to Grade an Assignment");
             
             while (!in.hasNextInt()) {
                System.out.println("Please Enter a valid option : ");
                in.nextLine();
             }
             idgrade= in.nextInt();
             System.out.println("Enter Oral Mark for Assignment "+ idgrade +" (Max 20)");
             oralmark = in.nextInt();
             System.out.println("Enter Total Mark for Assignment "+ idgrade +" (Max 100)");
             totalmark = in.nextInt();
             executeUpdateGradeAssignment(totalmark,oralmark,idgrade);
             System.out.println("Grade Assignment with id: " + idgrade + "." );
        }
    
     
        public static void gradeEnrollAssignment(Scanner in){
            System.out.println("If you want to enroll an assignment press 1. For grade an assignment press 2. ");
            int enrollgrade = in.nextInt();
            switch(enrollgrade){
                case 1: enrollAssignment(in);
                break;
                case 2: gradeAssignment(in);
                break;
                default: System.out.println("Option not valid.Press 1 to enroll an assignment or 2 to grade an assignment."); gradeEnrollAssignment(in);
            
            }
    }
    

               
      // Validation
        public static String stringValidation(Scanner in) {
            String input = in.next();
            for (int i = 0; i < input.length(); i++) {
            while (((input.charAt(i) < 'A') || (input.charAt(i) > 'z'))
                    || ((input.charAt(i) > 'Z') && (input.charAt(i) < 'a'))) {
                System.out.println("Please enter an Alphabetical Input : ");
                input = in.next();
            }
        }
        return input;
    }
        
        public static String dobValidation(Scanner in){
            String dob=in.next();
            LocalDate Datedob = LocalDate.parse(dob, formatter);
            if(Datedob.plusYears(18).isAfter(LocalDate.now())) {
            System.out.println("Student must me at least 18 years old.Enter Date of Birth before " + LocalDate.now().minusYears(18));
            dob=in.next();
            }
             return dob;
         }
        
       
        public static String startDateCourseValidation(Scanner in){
             String startDate = in.next();
             LocalDate start = LocalDate.parse(startDate, formatter);
             if(start.isBefore(LocalDate.now())) {
             System.out.println("Please Enter Start Date for the Course after " + LocalDate.now());
                startDate = in.next();
         } 
                return startDate;
         }
        
         
           public static String subDateAssignments(Scanner in){
             String subDate = in.next();
             LocalDate sub = LocalDate.parse(subDate, formatter);
             if(sub.isBefore(LocalDate.now())) {
             System.out.println("Please Enter Submission Date after " + LocalDate.now());
                subDate = in.next();
         } 
                return subDate;
         }
        
        
        
        
        public static void courseType(String courseType,Scanner in,Course course){
         courseType = in.next();
          if(courseType.equals("PART-TIME") || courseType.equals("FULL-TIME") ||courseType.equals("part-time") || courseType.equals("full-time")){
             course.setType(courseType.toUpperCase()) ; }
          else{System.out.println("Input not Valid.Please Enter PART-TIME of FULL-TIME");
          courseType(courseType,in,course);}
        }

        
        public static int inputNumber(Scanner in){
          int number =0;
          if(in.hasNextInt()){
            number = in.nextInt();
            if(number >=1){
               return number;
            }else{
                System.out.println("Input not valid.");
            }
          }else{
            String message = in.next();
            System.out.println("You entered :"+message+". This is not valid");
          }
          return number;
    }
        
        
      
 



 //Stored Procedures
      // All Students
      public static void callStoredProcedureGetAllStudents(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Id: " + rs.getString(1)
                        + "\t\tFirst Name: " + rs.getString(2)
                        + "\t\tLast Name: " + rs.getString(3) 
                        + "\t\tDate of Birth: " + rs.getString(4)
                        + "\t\tTuition Fees: " + rs.getString(5));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // List Courses
     public static void callStoredProcedureGetAllCourses(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            
            while (rs.next()) {
              System.out.println("Id: " + rs.getString(1)
                             +"\t\tTitle: " + rs.getString(2)
                             +"\t\tStream: " + rs.getString(3)
                             +"\t\tType: " + rs.getString(4)
                            +"\t\tStart Date: " + rs.getString(5)
                            +"\t\tEnd Date: " + rs.getString(6));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
     //List Assignments
     public static void callStoredProcedureGetAllAssignments(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            
            while (rs.next()) {
              System.out.println("Id: " + rs.getString(1)
                             +"\tTitle: " + rs.getString(2)
                             +"\tDescription: " + rs.getString(3)
                            +"\tSubmission Date: " + rs.getString(4) 
                            +"\tOral Mark: " + rs.getString(5)
                            +"\tTotal Mark: " + rs.getString(6));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    // List Trainers
    public static void callStoredProcedureGetAllTrainers(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Id: " + rs.getString(1)
                            + "\t\tFirst Name: " + rs.getString(2)
                            + "\t\tLast Name: " + rs.getString(3)
                            + "\t\tSubject: " + rs.getString(4));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // List Students Per Course
    public static void callStoredProcedureGetStudentsPerCourse(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            
            while (rs.next()) {
              System.out.println("Id: " + rs.getString(1)
                             +"\t\tTitle: " + rs.getString(2)
                             +"\t\tStream: " + rs.getString(3)
                             +"\t\tType: " + rs.getString(4) 
                             +"\t\tFirst Name: " + rs.getString(5)
                             +"\t\tLast Name: " + rs.getString(6));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //List Assignments Per Course
    public static void callStoredProcedureGetAssignmentsPerCourse(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            
            while (rs.next()) {
              System.out.println("Course Title: " + rs.getString(1)
                             +"\t\tCourse Stream: " + rs.getString(2)
                             +"\t\tCourse Type: " + rs.getString(3) 
                             +"\t\tAssignment Title: " + rs.getString(4)
                             +"\t\tDescription: " + rs.getString(5));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    //List Trainers Per Course
    public static void callStoredProcedureGetTrainersPerCourse(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            
            while (rs.next()) {
              System.out.println("Id: " + rs.getString(1)
                             +"\t\tCourse Title: " + rs.getString(2)
                             +"\t\tCourse Stream: " + rs.getString(3)
                             +"\t\tCourse Type: " + rs.getString(4) 
                             +"\t\tTrainer's First Name: " + rs.getString(5)
                              +"\t\tTrainer's Last Name: " + rs.getString(6)
                             +"\t\tTrainer's Subject: " + rs.getString(7) );
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    // Assignments Per Course Per Student
    public static void callStoredProcedureGetAssignmentsPerCoursePerStudent(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            
            while (rs.next()) {
              System.out.println("Id: " + rs.getString(1)
                                +"\t\tFirst Name: " + rs.getString(2)
                                +"\t\tLast Name: " + rs.getString(3)  
                                +"\t\tCourse Title: " + rs.getString(4) 
                                +"\t\tCourse Stream: " + rs.getString(5)
                                +"\t\tCourse Type: " + rs.getString(6)
                                +"\t\tAssignment Title: " + rs.getString(7)
                                +"\t\tOral Mark: " + rs.getString(8)
                                +"\t\tTotal mark" + rs.getString(9));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      // Students with multiple courses
    public static void callStoredProcedureGetStudentsMultipleCourses(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            
            while (rs.next()) {
              System.out.println("First Name: " + rs.getString(1)
                             +"\t\tLast Name: " + rs.getString(2)
                             +"\t\tEnrollments: " + rs.getString(3));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
   

    
    //Insert Info
    
    
    //Add new Student
    public static int executeUpdateStudent(Student s) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO students(fname, lname, dob, tuitionfees) "
                                             + "VALUES ('" + s.getFirstName() + "', '" + s.getLastName() + "', '" + s.getDateOfBirth()+ "', '" + s.getTuitionFees()+ "');");
        return (count);
    }
    
    
    
     //Add new Course
     public static int executeUpdateCourse(Course c) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO courses (title, stream, type, startdate, enddate) "
                                             + "VALUES ('" + c.getTitle()+ "', '" + c.getStream()+ "', '" + c.getType()+ "', '" + c.getStartDate()+ "', '" + c.getEndDate()+ "');");
        return (count);
    }

     //Add new Trainer
     public static int executeUpdateTrainer(Trainer t) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO trainers (fname, lname, subject) "
                                             + "VALUES ('" + t.getFirstName()+ "', '" + t.getLastName()+ "', '" + t.getSubject()+ "');");
        return (count);
    }

     //Add new Assignment
      public static int executeUpdateAssignment(Assignment a) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO assignments (title, description, subdate, oralmark, totalmark) "
                                             + "VALUES ('" + a.getTitle()+ "', '" + a.getDescription()+ "', '" + a.getSubDateTime()+ "', '"
                + a.getOralMark()+ "', '" + a.getTotalMark()+ "');");
        return (count);
    }

     //Enroll Student to Course
      public static int executeUpdateEnrollmentStudents(int cid,int sid) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO enrollments (sid, cid) "
                                             + "VALUES ((SELECT id FROM students WHERE id= '" + sid + "'),(SELECT id FROM courses WHERE id= '" + cid+ "'))" );
        return (count);
    }
      
      
       //Enroll Trainer to Course
      public static int executeUpdateEnrollmentTrainer(int eid,int tid) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO enrollmentstrainers (eid, tid) "
                                             + "VALUES ((SELECT id FROM courses WHERE id= '" + eid + "'),(SELECT id FROM trainers WHERE id= '" + tid+ "'))" );
        return (count);
    }
   
      
         //Enroll Assignment to Student
      public static int executeUpdateEnrollmentAssignment(int eid,int aid) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO enrollmentassignments (eid, aid) "
                                             + "VALUES ((SELECT id FROM enrollments WHERE id= '" + eid + "'),(SELECT id FROM assignments WHERE id= '" + aid+ "'))" );
        return (count);
    }
     
      
         //Insert Grade Assignment
      public static int executeUpdateGradeAssignment(int totalmark,int oralmark,int idgrade) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("UPDATE enrollmentassignments "
                                             + "SET oralmark = '" + oralmark + "', totalmark = '" + totalmark + "' WHERE id = '" + idgrade+ "';" );
        return (count);
    }
      
      
        
    public static ResultSet executeQuery(String sql) {
        Register dbProject = new Register();
        ResultSet rs = null;
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        rs = db.connectAndExecuteQuery(sql);
        return (rs);
    }

    public static int executeUpdate(String sql) {
        Register dbProject = new Register();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate(sql);
        return (count);
    }
       
       
     
    public static boolean printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("\n");
           // System.out.println("Number of fields: " + columnsNumber);
            while (rs.next()) {
                System.out.println("Id: " + rs.getString(1)
                //int a = parseInt(rs.getString(1));
                       + "\tFirst Name: " + rs.getString(2)
                        + "\tLast Name: " + rs.getString(3));
            }
            System.out.println("\n");
            return (true);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }
    }
    
    
     public static boolean printResultSetInt(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
           // System.out.println("\n");
           // System.out.println("Number of fields: " + columnsNumber);
            while (rs.next()) {
                System.out.println( rs.getString(1) );
                int a = parseInt(rs.getString(1));
                      
            }
            System.out.println("\n");
            return (true);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }
    }
     
     
     
     public static int printResultSetIntCount(ResultSet rs) {
         int a = 0;
        try {
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
           // System.out.println("\n");
           // System.out.println("Number of fields: " + columnsNumber);
            while (rs.next()) {
                System.out.println( rs.getString(1) );
                a = parseInt(rs.getString(1));
                      
            }
            System.out.println("\n");
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return a;
        }
    }
     
     
     
     
     
     
     
     
//     
//     //COUNT STUDENTS
//       public static int executeCountStudentRows(Student s) {
//        Register dbProject = new Register();
//        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
//        int count = db.connectAndExecuteUpdate("SELECT count(*) FROM students");
//        return (count);
//    }
//     
//       
//    //count courses
//    public static int coursesCount(Connection cxn) throws SQLException {
//     try (Statement s = cxn.createStatement()) {
//     final ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM courses;");
//     rs.next();
//     return rs.getInt("count(*)");
//  }
//}

     
//    
//
//      public static void callStoredProcedureGetCountCourse(String sql) {
//        Register dbProject = new Register();
//        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
//        Connection conn = db.createConnection();
//        try {
//            CallableStatement callableStatement = conn.prepareCall(sql);
//            ResultSet rs = callableStatement.executeQuery();
//            
//            while (rs.next()) {
//              System.out.println( rs.getString(1) );
//                        
//            }
//            rs.close();
//            callableStatement.close();
//            conn.close();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
      
      
      
      
      // jdbc:mysql://5.189.135.166:3011/cb11studentsmarks?zeroDateTimeBehavior=convertToNull
    private String createJDBCConnectionString() {
        String value; //= "";
        value = "jdbc:mysql://" + serverIP + ":" + srvPort + "/" + databaseName + "?useSSL=false&serverTimezone=UTC";//"?zeroDateTimeBehavior=convertToNull";

        return (value);
    }
    
    
    
//     public static ResultSet executeQueryCount(String sql) {
//        Register dbProject = new Register();
//        ResultSet rs = null;
//        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
//        rs = db.connectAndExecuteQuery(sql);
//        return (rs);
//    }
    
    
    
    
}


