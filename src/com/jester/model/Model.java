/**
    Author     : Cloyd Van S. Secuya and Nadrin Caluya
    Filename   : CodeGen.java
    Package	   : com.jester.model;
    Date of Creation : June 14, 2022
    Description:
    	This is the Model class which is capable of handling data logic to add Music objects. 
        Newly inserted Music files can be pushed to our MySQL Server that is used for storage and later interaction
        such as retrieving the music title, music artist, music album, music path, and the primary key music_ID
 */

// PACKAGE SECTION
package com.jester.model;


// IMPORT SECTION
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.jester.model.music_handler.Music;
import sql.db.jdbc.EstablishConnection;
import java.util.Scanner;
        

public class Model {
    
    EstablishConnection connect = new EstablishConnection();
    Statement statement = null;
    ResultSet rs = null;
    
    
    // This method adds a new music record into the datase
    public void addMusic(Music obj) {
        connect.getConnection();
        String qry = "INSERT INTO jester_music(music_ID, music_title, music_artist, music_album, music_path_to_DIR) VALUES ROW('"+obj.getMusic_ID()+"','"+obj.getMusic_title()+"','"+ obj.getMusic_artist()+"','"+ obj.getMusic_album()+"','"+obj.getMusic_path_to_DIR()+"')"; 
        
            try {
                statement = connect.getConnection().createStatement();
                statement.executeUpdate(qry);
                System.out.println("INSERT STATEMENT: " + qry);
                statement.close();
            }

            catch(SQLException e) {
                // Print to console the possible cause of error/s
                String msg = "SQL statement may be incorrect or record/s are existing!";
                String possible_err_statement = qry; 
                System.out.println(msg);
                System.out.println(possible_err_statement);
                e.printStackTrace();
            }
        
    }
    
    // A method that deletes a music record based on its title
    public void deleteMusic(String music_toRemove) {
        connect.getConnection();
        String qry = "DELETE FROM jester_music WHERE music_title = "+"'"+music_toRemove+"'"; 

            try {
                statement = connect.getConnection().createStatement();	
                statement.executeUpdate(qry);
                System.out.println("DELETE STATEMENT: " + qry);
                statement.close();
            }
            
            catch(SQLException e) {
                // Print to console the possible cause of error/s
                String msg = "SQL statement may be incorrect or record/s are existing!";
                String possible_err_statement = qry; 
                System.out.println(msg);
                System.out.println(possible_err_statement);
                e.printStackTrace();			
            }
    }
    
    
    // This returns a specified Music object along with its attributes
    public Music fetchMusic(String music_ID) {
        connect.getConnection();
        String qry = "SELECT * FROM jester_music WHERE music_ID = '"+music_ID+"'"; 
        
        Music music_fetch = new Music();
        String fetch_music_ID;
        String fetch_music_title;
        String fetch_music_artist;
        String fetch_music_album;
        String fetch_music_path;
        
            try {
                statement = connect.getConnection().createStatement();
                rs = statement.executeQuery(qry);
                
                while(rs.next()) {
                    System.out.println("LOOPING THROUGH THE RECORDS");
                    fetch_music_ID = rs.getString("music_ID"); 
                    fetch_music_title = rs.getString("music_title"); 
                    fetch_music_artist = rs.getString("music_artist"); 
                    fetch_music_album = rs.getString("music_album"); 
                    fetch_music_path = rs.getString("music_path_to_DIR"); 
                    
                    System.out.println("Selected ID: " + fetch_music_ID);
                    System.out.println("Fetched Title: " + fetch_music_title);
                    System.out.println("Fetched Artist: " + fetch_music_artist);
                    System.out.println("Fetched Album: " + fetch_music_album);
                    System.out.println("Fetched Path: " + fetch_music_path);
                    
                    music_fetch.setMusic_ID(fetch_music_ID);
                    music_fetch.setMusic_title(fetch_music_title);
                    music_fetch.setMusic_artist(fetch_music_artist);
                    music_fetch.setMusic_album(fetch_music_album);
                    music_fetch.setMusic_path_to_DIR(fetch_music_path);
                    System.out.println("return");
                    return music_fetch; 
                }
            }
            
            catch(SQLException e) {
                // Print to console the possible cause of error/s
                String msg = "SQL statement may be incorrect or record/s are existing!";
                String possible_err_statement = qry; 
                System.out.println(msg);
                System.out.println(possible_err_statement);
                e.printStackTrace();
            }
            
            // return new Music(); 
            return music_fetch; 
    }
    
    
    public Music[] view_and_get_MusicRecords() {
        // Declare an array list because we want to return this 
        ArrayList<Music> music_record = new ArrayList<Music>();
        
        // Create an instance of Music
        Music music_as_ls = new Music();
        
        connect.getConnection();
        String qry = "SELECT * FROM jester_music";
            
            try {
                statement = connect.getConnection().createStatement();
                rs = statement.executeQuery(qry); 
                System.out.println("SELECT STATEMENT: " + qry);
                
                while(rs.next()) {
                    String fetch_music_ID = rs.getString("music_ID"); ;
                    String fetch_music_title = rs.getString("music_title");
                    String fetch_music_artist = rs.getString("music_artist");
                    String fetch_music_album = rs.getString("music_artist");
                    String fetch_music_path = rs.getString("music_path_to_DIR");
                    
                    music_as_ls.setMusic_ID(fetch_music_ID);
                    music_as_ls.setMusic_title(fetch_music_title);
                    music_as_ls.setMusic_artist(fetch_music_artist);
                    music_as_ls.setMusic_album(fetch_music_album);
                    music_as_ls.setMusic_path_to_DIR(fetch_music_path);
                    
                    music_record.add(music_as_ls);
                    
                    // return (Music []) music_record.toArray(new Music[music_record.size()]);
                }
            
            }
            
            catch(SQLException e ) {
                // Print to console the possible cause of error/s
                String msg = "SQL statement may be incorrect or record/s are existing!";
                String possible_err_statement = qry; 
                System.out.println(msg);
                System.out.println(possible_err_statement);
                e.printStackTrace();
            }
            
        return (Music []) music_record.toArray(new Music[music_record.size()]);
    }
    
    
    // This method returns an array list appended from the Music objects
    public ArrayList<Music> get_ArrayList_of_MusicRecords() {
        connect.getConnection();
        ArrayList<Music> arr_objs = new ArrayList<Music>();
        String qry = "SELECT * FROM jester_music";
        
            try {
                statement = connect.getConnection().createStatement();
                rs = statement.executeQuery(qry);
                System.out.println("SELECT STATEMENT: " + qry);
	        
                while(rs.next()) {
                    arr_objs.add(new Music(rs.getString("music_ID"),rs.getString("music_title"),rs.getString("music_artist"),rs.getString("music_album"),rs.getString("music_path_to_DIR")));
                    return arr_objs; 
                }
                
                rs.close();
                statement.close();
            }

	    catch(SQLException e) {
                // Print to console the possible cause of error/s
                String msg = "SQL statement may be incorrect or record/s are existing!";
                String possible_err_statement = qry; 
                System.out.println(msg);
                System.out.println(possible_err_statement);
                e.printStackTrace();
	    }
        
        return arr_objs;
    }

    
    // If necessary, we can delete a row from a particular record. Just state the parameters. 
    /**
     * 
     * @param table     this corresponds to the tables: jester_music and jester_user
     * @param PK_col    this is the Primary KEY column: music_ID and ID_auth
     * @param PK_val    this is specified value of PK which should correspond to the particular record you want to delete
     */
    public void deleteRow(String table, String PK_col, String PK_val) {
        connect.getConnection();
        String qry = "DELETE FROM " + table + " WHERE " + PK_col + " = " + PK_val; 

            try {
                statement = connect.getConnection().createStatement();	
                statement.executeUpdate(qry);
                System.out.println("DELETE STATEMENT: " + qry);
                statement.close();
            }
            
            catch(SQLException e) {
                // Print to console the possible cause of error/s
                String msg = "SQL statement may be incorrect or record/s are existing!";
                String possible_err_statement = qry; 
                System.out.println(msg);
                System.out.println(possible_err_statement);
                e.printStackTrace();			
            }
    }

    
//   public static void main(String[] args) {
//       Model model = new Model(); 
//       Music music;
//       Scanner sc = new Scanner(System.in);
//        
//        // Adding new music into the database remember that music_ID is automatically generated!
//           music = new Music(); 
//           music.setMusic_title("Music_Title 3!");
//           music.setMusic_artist("Artist 4");
//           music.setMusic_album("Album 4");
//           music.setMusic_path_to_DIR("music_dir/music4.mp3");
//
//       model.addMusic(music);
//        
//        // Deleting a piece of specified music 
//            String music_title_toRemove = ""; 
//            System.out.print("Enter the title you want to remove: ");
//            music_title_toRemove = sc.nextLine();   
//            model.deleteMusic(music_title_toRemove);
//        
//        // Deleting a specified row
//        /**
//         * @NOTE:
//         *      Here I I am trying to delete a specified row from a specified table with a specific PK value
//         */
//        model.deleteRow("jester_music", "music_ID", "'37CJdbO'");
//        
//        // Returning a specific piece of a single Music object along with its records from the database
//        Music returnedMusicObj = new Music(); 
//        returnedMusicObj = model.fetchMusic("001");
//        System.out.println("Returned Specific Music Object");
//        System.out.println("Music Title: " + returnedMusicObj.getMusic_title());
//        System.out.println("Music Artist: " + returnedMusicObj.getMusic_artist());
//        System.out.println("Music Album: " + returnedMusicObj.getMusic_album());
//        System.out.println("Music Path: " + returnedMusicObj.getMusic_path_to_DIR());
//        System.out.println("Music ID: " + returnedMusicObj.getMusic_ID());
//          
//         music = new Music(); 
//         music = model.fetchMusic("001");
//         System.out.println("\n\nFetched your specific music object: ");
//         System.out.println("============================================");
//         System.out.println("Specific Returned ID: " + music.getMusic_ID());
//         System.out.println("Specific Returned Title: " + music.getMusic_title());
//         System.out.println("Specific Returned Artist: " + music.getMusic_artist());
//         System.out.println("Specific Returned Album: " + music.getMusic_album());
//         System.out.println("Specific Returned Path: " + music.getMusic_path_to_DIR());
//          
//         Music[] music_ls = model.view_and_get_MusicRecords();
//          
//         for (int i = 0; i < music_ls.length; i ++) {
//             System.out.println("Music ID: " + music_ls[i].getMusic_ID());
//             System.out.println("Music Title: " + music_ls[i].getMusic_title());
//             System.out.println("Music Artist: " + music_ls[i].getMusic_artist());
//             System.out.println("Music Album: " + music_ls[i].getMusic_album());
//             System.out.println("Music Path: " + music_ls[i].getMusic_path_to_DIR());
//             System.out.println("-----------------------------------------------------");
//          }
//         
//   }
    
}
