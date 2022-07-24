/**
    Author     : Cloyd Van S. Secuya
    Filename   : PlayerThread.java
    Package	   : com.jester.model.music_handler;
    Date of Creation : June 19, 2022
    Description:
    	This class handles the file streaming and audio rendering for .mp3 files.
        
 */

// PACKAGE SECTION
package com.jester.model.music_handler;


// IMPORT SECTION
import com.jester.controller.Controller;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.AdvancedPlayer;


public class PlayerThread extends Thread{
    
    private AdvancedPlayer player;
    private FileInputStream audio;
    private String music_dir; 
    private boolean loop; 
    
    public PlayerThread(String music_dir) {
        this.music_dir = music_dir; 
    }
    
    public PlayerThread() {}
    
    // Begin and invoke the start() when called
    /**
     * @NOTE: This is part of handling the thread specifically for 
     *        playing mp3 files
     */
    public void run() {
        try {
            do {
                audio = new FileInputStream(music_dir);
                player = new AdvancedPlayer(audio);
                player.play();
            } while (loop);
        } 
        
        catch (Exception e) {
            e.printStackTrace(); 
            System.out.println(e.getMessage());
        }
    }
    
    // Stop and interrupt the task of playing mp3 file
    /**
     * @NOTE: This is how we can stop the music file from playing. 
     *        It is executed in background running on a new Thread.
     *        Hence, we need the interrupt() method to forcefully stop it.
    */
    public void close() {
        loop = false;
        player.close();
        this.interrupt();
    }
}
