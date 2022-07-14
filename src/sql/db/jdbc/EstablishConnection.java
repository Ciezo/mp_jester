/**
 * Author: Cloyd Van S. Secuya
 * Filename: EstablishConnection.java
 * Date of Creation: July 13, 2022
 * Description:
 *      This Java class file is responsible for establish a connection to the database 
 *      Hosted in the local machine in a MySQL Server.
 */

// =========================================================================== // 
/**
     * @NOTE: 
     *      This is used to establish an endpoint of local-host connection. 
     *      username and password credentials are needed here.
     *      
     *      Now, those credentials can be get from how you set up the MySQL database 
     *      hosted on your machine!
     * 
     *      Running the main(null) method inside another class will also work too! 
     *  @Cloyd
     */

// PACKAGE SECTION
package sql.db.jdbc; 


// IMPORT SECTION
import java.sql.*;
import java.util.Scanner;


public class EstablishConnection {
    
    Connection conn = null; 
        
    public EstablishConnection() {
        
        // Database established credentials
        String user = ""; 
        String pass = "";
        Scanner sc = new Scanner(System.in); 
        
        System.out.print("Enter user: ");
        user = sc.nextLine(); 
        System.out.print("Enter password: ");
        pass = sc.nextLine(); 
        
        // URI
        /**
        * @NOTE: Set and customize this if necessary
        * Jester_DB is the name of the database
        */
        String URI = "//localhost:3306/Jester_DB";
        
        // Begin and try establishing a connection. If error persists we handle 
        // that through the try-block
        try {   
                // Connection
                System.out.println("Setting up default connection to " + URI);
                 conn = DriverManager.getConnection("jdbc:mysql:" + URI, user, pass); 
//                conn = DriverManager.getConnection("jdbc:mysql:" + URI); 
        }
        
        catch(SQLException e) {
            System.out.println("An error occured!");
            e.printStackTrace(); 
        }

    }

    public void setConnection(Connection conn, String URI_path) {
        this.conn = conn; 
        // Connection
        try {
            System.out.println("Setting up Connection through a custom URI path");
            conn = DriverManager.getConnection("jdbc:mysql:" + URI_path);
        } 
        
        catch (SQLException e) {
            System.out.println("An error occurred when setting up a custom connection");
            e.printStackTrace();
        } 
    }

    public Connection getConnection() {
        return conn; 
    }
    
    
    
    
    
    public static void main(String[] args) {
        new EstablishConnection();
    }
}