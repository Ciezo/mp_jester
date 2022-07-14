/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jester.model;

public class Music {


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

    public int getMusic_ID() {
        return music_ID;
    }

    public void setMusic_ID(int music_ID) {
        this.music_ID = music_ID;
    }

    public Music(int music_ID, String music_title, String music_artist, String music_album, String music_path_to_DIR) {
        this.music_ID = music_ID;
        this.music_title = music_title;
        this.music_artist = music_artist;
        this.music_album = music_album;
        this.music_path_to_DIR = music_path_to_DIR;
    }
    private int  music_ID; 
    private String music_title;
    private String music_artist;
    private String music_album;
    private String music_path_to_DIR;
    

 
     
   


    

    
}
