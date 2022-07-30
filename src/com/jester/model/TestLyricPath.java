/**
    Authors     : Cloyd Secuya
    Filename   : TestLyricPath.java
    Package	   : com.jester.model;
    Date of Creation : June 30, 2022
    Description:
        This is the source class file that can be run in its own isolated run time
        and thread. 
        
        We can use this class to test the retrieval of the lyric file path in the 
        View directory
*/

// PACKAGE SECTION
package com.jester.model;

// IMPORT SECTION
import com.jester.controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestLyricPath {
    
    public static void main(String[] args) {
        Model model = new Model(); 
        Database db = new Database(); 
        Controller controller = new Controller();
        
        System.out.println("\n\nTESTING MODEL LOGIC"); 
        model.fetchLyricContent("Country Roads");
        model.fetchLyricContent("Pixel Galaxy");
        model.fetchLyricContent("Twinklestar");
        model.fetchLyricContent("Clair De Lune");
        model.fetchLyricContent("Baka Mitai");
        
        try {Thread.sleep(500);} catch (InterruptedException ex) {ex.printStackTrace();}
        
        System.out.println("\n\nTESTING DATABASE OBJECT MAPPER"); 
        db.getLyricContentPath("Country Roads");
        db.getLyricContentPath("Pixel Galaxy");
        db.getLyricContentPath("Twinklestar");
        db.getLyricContentPath("Clair De Lune");
        db.getLyricContentPath("Baka Mitai");
        
        try {Thread.sleep(500);} catch (InterruptedException ex) {ex.printStackTrace();}
        
        System.out.println("\n\nTESTING CONTROLLER"); 
        controller.GetLyricsPath("Country Roads");
        controller.GetLyricsPath("Pixel Galaxy");
        controller.GetLyricsPath("Twinklestar");
        controller.GetLyricsPath("Clair De Lune");
        controller.GetLyricsPath("Baka Mitai");
    }
    
}
