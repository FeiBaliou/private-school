/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Menu.Menu1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author feiba
 */
public class MainClass {
   
       
    public static Scanner in = new Scanner(System.in); 
   
    public static void main(String[] args) {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData rsmd = null;

       Menu1.MainMenu(in); 
    
        
    
}
    
}
