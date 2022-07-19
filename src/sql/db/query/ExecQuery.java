/**
 * Author: Cloyd Van S. Secuya
 * Filename: ExecQuery.java
 * Date of Creation: July 13, 2022
 * Description:
 *      This is responsible for helping us execute SQL query statements with Java.
 */

// =========================================================================== // 
/**
     * @NOTE: 
     *      The comments below is an example of how to implement custom queries to the Jester Database. 
     *      
     *      As such, this class file may only be used if necessary, in situations such as checking if 
     *      a user has registered and their data loaded into the database, or a new music file path needs to be loaded into the database.
     *      
     *      Right now, we have 5 default record users which are us developers, and at least 5 music records stored in the database. 
     *      We can do queries to see these default records when the Jester_DB is created. 
     * 
     *      This will be very helpful sometime in the future especially when we need to check newly installed records. 
     *      Remember that we only have two tables:
     *          - Jester_DB.jester_users   or   jester_users
     *          - Jester_DB.jester_music   or   jester_music
     *  @Cloyd
     *  P.S. Un-comment the main method below (SCROLL ALL THE WAY DOWN) if you want to this this in isolated run time.
     */

// PACKAGE SECTION
package sql.db.query;


// IMPORT SECTION
import java.sql.*;
import sql.db.jdbc.EstablishConnection;


public class ExecQuery {

    private EstablishConnection ok_conn; 
    private PreparedStatement stat = null; 
    private ResultSet res = null; 

    private String sql = ""; 

    public ExecQuery() {

        // Instantiate the already established connection
        this.ok_conn = new EstablishConnection(); 

    }

    public void setSQL_statement(String sql) throws SQLException {
        this.sql = sql; 

        // Set up a custom based statement 
        this.stat = ok_conn.getConnection().prepareStatement(sql);
        
        // Try to execute a user_query 
        this.res = stat.executeQuery();  
    }

    public String getSQL() {
        return sql; 
    }

    public void displayQueryResult(int table) throws SQLException {
        /* Jester_DB.jester_user TABLE */
        if (table == 1) {
            System.out.println("\n\nJester_DB.jester_users");
            System.out.println("=======================================================");
            while(res.next()) {
                int id = res.getInt("ID_auth"); 
                String f_name = res.getString("f_name"); 
                String l_name = res.getString("l_name"); 
                String username = res.getString("userName"); 
                String user_pwd = res.getString("user_pwd"); 

                System.out.printf("%d, %s, %s, %s\n", id, f_name, l_name, username, user_pwd);
            }
            System.out.println("=======================================================");
        }

        /* Jester_DB.jester_music TABLE */
        else if (table == 2) {
            System.out.println("\n\nJester_DB.jester_music");
            System.out.println("=======================================================");
            while(res.next()) {
                int id = res.getInt("music_ID"); 
                String music_title = res.getString("music_title"); 
                String music_artist = res.getString("music_artist"); 
                String music_album = res.getString("music_album"); 
                String music_path_to_DIR = res.getString("music_path_to_DIR"); 

                System.out.printf("%d, %s, %s, %s\n", id, music_title, music_artist, music_album, music_path_to_DIR);
            }
            System.out.println("=======================================================");
        }
    }
    
    
    
    
    
    // Let us see how we can utilize this class.   
//   public static void main(String[] args) throws SQLException {
//       /* Begin, creating an object */
//       ExecQuery query = new ExecQuery();      
//       // Remember that once instantiated, it begins to reconnect to the local-host of YOUR MACHINE.
//       // Because it is where the Jester_DB is hosted
//   
//       /** 
//        * Now, simply we can start creating queries like in SQL
//        * displayQueryResult() has required parameter of INT type
//        *   1  is for jester_users
//        *   2  is for jester_music
//        */
//       query.setSQL_statement("SELECT * FROM Jester_DB.jester_users WHERE ID_auth = 100");
//       query.displayQueryResult(1);
//   
//       query.setSQL_statement("SELECT * FROM Jester_DB.jester_music");
//       query.displayQueryResult(2);
//   }
}