/**
    Author     : Cloyd Van S. Secuya
    Filename   : Database.java
    Package	   : com.jester.model;
    Date of Creation : June 15, 2022
    Description:
        The Database.java interacts with the Model logic which implements methods that get 
        the Music attributes and its assigned values either as objects and such.
*/

// PACKAGE SECTION
package com.jester.model;


// IMPORT SECTION
import java.util.ArrayList;
import com.jester.model.music_handler.Interface_musichandle;
import com.jester.model.music_handler.Music;


public class Database implements Interface_musichandle {
    @Override
    public Music[] getMusicArrObj() {
        
        return null;
    }

    @Override
    public ArrayList<Music> getMusicRecords() {
        
        return null;
    }

    @Override
    public Music getMusicToPlay(String music_ID) {
        
        return null;
    }

    @Override
    public void add_new_Music(Music music) {
        
        
    }

    @Override
    public void playSound(String path) {
        
        
    }
    
}
