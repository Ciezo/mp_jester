/**
    Author     : Cloyd Van S. Secuya
    Filename   : Music.java
    Package	   : com.jester.model.music_handler;
    Date of Creation : June 15, 2022
    Description:
        This is an interface which implements methods that contribute to getting the Music objects.
*/

// PACKAGE SECTION
package com.jester.model.music_handler;

// IMPORT SECTION
import java.util.ArrayList;

public interface Interface_musichandle {

    public Music [] getMusicArrObj(); 
    public ArrayList<Music> getMusicRecords(); 
    public Music getMusicToPlay(String music_ID); 
    public void playSound(String music_title, String path); 
    public void stopSound(); 
    public void add_new_Music(Music music); 

}
