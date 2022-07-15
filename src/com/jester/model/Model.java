package com.jester.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sql.db.jdbc.EstablishConnection;


public class Model {
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;


    EstablishConnection connect;

   
    
 public void AddMusic(Music obj)
    {
        connect.getConnection();
        
        try{
            myStmt=myConn.createStatement();	
            String qry="INSERT INTO jester_music  VALUES(" + obj.getMusic_ID()+ ",'" + obj.getMusic_title()+ "','" + obj.getMusic_artist()+ "','" + obj.getMusic_album()+ "','" +obj.getMusic_path_to_DIR()+"')";
            myStmt.executeUpdate(qry);
            myStmt.close();
           
	}
	catch(SQLException se)
	{
            String msg="Cannot add. object" + obj.getMusic_ID() + " already exists!";
            JOptionPane.showMessageDialog(null,msg);		
	}
    }
     public void DeleteMusic(int oldStnum)
    {
        connect.getConnection();
      
        try{
            myStmt=myConn.createStatement();	
            String qry="DELETE FROM jester_music WHERE music_ID = " + oldStnum;
            myStmt.executeQuery(qry);
            myStmt.close();
            
	}
	catch(SQLException se)
	{
            System.out.println(se.getMessage());			
	}
    }
     //get a specific music objectvhj
      public Music getMusicObject(String MusicTitle)
    {
       connect.getConnection();
         Music selectedItem = null;

        
        try {	
          myStmt=myConn.createStatement();
          String query = "SELECT * FROM jester_music WHERE music_title='"+ MusicTitle  +"'";
	  ResultSet rs = myStmt.executeQuery(query);
         
          selectedItem = new Music(rs.getInt("music_ID"),rs.getString("music_title"),rs.getString("music_artist"),rs.getString("music_album"),rs.getString("music_path_to_DIR"));
          rs.close();
          myStmt.close();
        } catch (SQLException ex) {
           
        }
      
        return selectedItem;
         
    }
    
    
    public ArrayList<Music> getMusicRecords()
    {
        ArrayList<Music> objs=new ArrayList<Music>();
       connect.getConnection();
        try{
            myStmt=myConn.createStatement();	
            String query = "SELECT * FROM jester_music";
	    ResultSet rs = myStmt.executeQuery(query);

	    while(rs.next())
            {
                objs.add(new Music(rs.getInt("music_ID"),rs.getString("music_title"),rs.getString("music_artist"),rs.getString("music_album"),rs.getString("music_path_to_DIR")));
            }
            rs.close();
	    myStmt.close();				
        }
	catch(SQLException sex)
	{
            System.out.println(sex.getMessage());
	}
        return objs;
    }
}
