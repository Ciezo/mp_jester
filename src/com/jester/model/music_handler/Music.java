/**
    Author     : Cloyd Van S. Secuya and Nadrin Caluya
    Filename   : Music.java
    Package	   : com.jester.model.music_handler;
    Date of Creation : June 14, 2022
    Description:
    	The Music class contains and corresponds to the attributes of the following: 
            1. music_ID             : String    the alphanumeric PRIMARY KEY and identifier 
            2. music_title          : String    the title of the music to be displayed in the View
            3. music_artist         : String    the artist to be displayed that should correspond to their music_title 
            4. music_album          : String    the album which is like a group where
            5. music_path_to_DIR    : String    this is the DIR path from src.music_dir this is stored in the database. 
                                                It is how we can retrieve and stream a specifc audio file based on the path DIR 
 */

// PACKAGE SECTION
package com.jester.model.music_handler;


// IMPORT SECTION
import com.jester.model.CodeGen;

public class Music {

    private String music_ID; 
    private String music_title;
    private String music_artist;
    private String music_album;
    private String music_path_to_DIR;
    private String lyric_path_to_DIR;

    // Alphanumeric key generator
    CodeGen generatePK = new CodeGen(); 
    
    public Music(String ID, String music_title, String music_artist, String music_album, String music_path_to_DIR, String lyric_path_to_DIR) {
        // Start setting up RGN to be mixed with String builder
        generatePK.setRGN();
        // Assign the generated alphanumeric key maximum with 5 characters
        this.music_ID = generatePK.genKEY();
        
        this.music_title = music_title;
        this.music_artist = music_artist;
        this.music_album = music_album;
        this.music_path_to_DIR = music_path_to_DIR;
        this.lyric_path_to_DIR = lyric_path_to_DIR;
    }
    
    // Default Constructor
    public Music() {
        // Start setting up RGN to be mixed with String builder
        generatePK.setRGN();
        // Assign the generated alphanumeric key maximum with 5 characters
        this.music_ID = generatePK.genKEY();
    } 
    
    public void setMusic_ID(String music_ID) {
        this.music_ID = music_ID; 
    }

    public String getMusic_ID() {
        return music_ID;
    }

    public String getMusic_title() {
        return music_title;
    }

    public void setMusic_title(String music_title) {
        this.music_title = music_title;
    }

    public String getMusic_artist() {
        return music_artist;
    }

    public void setMusic_artist(String music_artist) {
        this.music_artist = music_artist;
    }

    public String getMusic_album() {
        return music_album;
    }

    public void setMusic_album(String music_album) {
        this.music_album = music_album;
    }

    public String getMusic_path_to_DIR() {
        return music_path_to_DIR;
    }

    public void setMusic_path_to_DIR(String music_path_to_DIR) {
        this.music_path_to_DIR = music_path_to_DIR;
    }
    
    public void setLyric_path_to_DIR(String lyric_path_to_DIR) {
        this.lyric_path_to_DIR = lyric_path_to_DIR; 
    }
    
    public String getLyric_path_to_DIR() {
        return lyric_path_to_DIR;
    }
}