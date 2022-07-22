/**
    Author     : Cloyd Van S. Secuya
    Filename   : Controller.java
    Package	   : com.jester.controller;
    Date of Creation : June 15, 2022
    Description:
        This is the controller that can access methods of the interfaces and 
        methods from the Model.
*/

// PACKAGE SECTION
package com.jester.controller;


// IMPORT SECTION
import com.jester.model.Model;
import com.jester.model.Database;
import com.jester.model.music_handler.Interface_musichandle;
import com.jester.model.music_handler.Music;
import com.jester.model.music_handler.PlayerThread;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    
    Interface_musichandle handle = new Database();
    
    public Controller() {
        handle = new Database(); 
    }
    
    
    public Music[] GetAllStoredMusic() {
        return handle.getMusicArrObj(); 
    }
    
    public Music SetNewMusic(Music insert_music) {
        // music = new Music(); 
        insert_music.setMusic_title(insert_music.getMusic_title());
        insert_music.setMusic_artist(insert_music.getMusic_artist());
        insert_music.setMusic_album(insert_music.getMusic_album());
        insert_music.setMusic_path_to_DIR(insert_music.getMusic_path_to_DIR());
        
        handle.add_new_Music(insert_music);
        
        return insert_music; 
    }
    
    public String[] GetAllStoredAlbums() {
        String[] albums = new String[5];
        Music[] music_ls; 
        
        music_ls = handle.getMusicArrObj(); 
        
        System.out.println("Retrieving Albums");
        for (int i = 0; i < music_ls.length; i++) {
            System.out.println("Retrieved Album: " + music_ls[i].getMusic_album()); 
            albums[i] = music_ls[i].getMusic_album(); 
            System.out.println("Assigned Album: " + albums[i]);
        }
        
       return albums;
    }
    
    public Object[] GetAllStoredObjAlbums() {
        ArrayList<String> music_album = new ArrayList<String>();
        Music[] music_ls; 
        
        music_ls = handle.getMusicArrObj(); 
        for (int i = 0; i < music_ls.length; i++) {
            music_album.add(music_ls[i].getMusic_album()); 
        }
        
        return (Object []) music_album.toArray(new Object[music_album.size()]);
    }
    
    public void setMusicToPlay(Music music) {
        music = handle.getMusicToPlay(music.getMusic_ID());
        handle.playSound(music.getMusic_title(), music.getMusic_path_to_DIR());
    }
    
    public void play_musicByID(String music_ID) {
        Music musicToPlay = new Music();
        musicToPlay = handle.getMusicToPlay(music_ID); 
        handle.playSound(musicToPlay.getMusic_title(), musicToPlay.getMusic_path_to_DIR());
    }
    
    public void stop_music() {
        handle.stopSound();
    }
    
}
