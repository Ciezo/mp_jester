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
    
    public Music controller_SetMusic(Music music) {
        music = new Music(); 
        music.setMusic_title(music.getMusic_title());
        music.setMusic_artist(music.getMusic_artist());
        music.setMusic_album(music.getMusic_album());
        music.setMusic_path_to_DIR(music.getMusic_path_to_DIR());
        
        handle.add_new_Music(music);
        
        return music; 
    }
    
    public ArrayList<String> controller_GetAllAlbums() {
        ArrayList<String> albums = new ArrayList<String>(); 
        Music[] music_ls = handle.getMusicArrObj(); 
        String[] add_album = new String[100]; 
                
        for (int i = 0; i < music_ls.length; i++) {
            albums = new ArrayList<String>(); 
            add_album[i] = music_ls[i].getMusic_album(); 
            System.out.println("Adding album " + "[" + (i+1) + "]" + add_album[i]);
            
            albums.add(add_album.toString()); 
            return albums;
        }
        
        return albums; 
    }
    
    
}
