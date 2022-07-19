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
import java.util.ArrayList;


public class Controller {
    
    Interface_musichandle handle = new Database();
    
    public Controller() {
        handle = new Database(); 
    }
    
    
    public Music[] controller_GetAllMusic() {
        return handle.getMusicArrObj(); 
    }
    
    public Music controller_SetMusic(Music insert_music) {
        // music = new Music(); 
        insert_music.setMusic_title(insert_music.getMusic_title());
        insert_music.setMusic_artist(insert_music.getMusic_artist());
        insert_music.setMusic_album(insert_music.getMusic_album());
        insert_music.setMusic_path_to_DIR(insert_music.getMusic_path_to_DIR());
        
        handle.add_new_Music(insert_music);
        
        return insert_music; 
    }
    
    public String[] controller_GetAllAlbums() {
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
    
    public Object[] controller_GetAllObjAlbums() {
        ArrayList<String> music_album = new ArrayList<String>();
        Music[] music_ls; 
        
        music_ls = handle.getMusicArrObj(); 
        for (int i = 0; i < music_ls.length; i++) {
            music_album.add(music_ls[i].getMusic_album()); 
        }
        
        return (Object []) music_album.toArray(new Object[music_album.size()]);
    }
}
