/**
    Author     : Cloyd Van S. Secuya
    Filename   : ViewLevelConsole.java
    Package	   : com.jester.view;
    Date of Creation : June 15, 2022
    Description:
        
*/

// PACKAGE SECTION
package com.jester.view;


// IMPORT SECTION
import com.jester.controller.Controller;
import com.jester.model.music_handler.Music;
import java.util.ArrayList;
import java.util.Scanner;


public class ViewLevelConsole {
    
    public static void main(String[] args) {
        Controller controller = new Controller(); 
        Music[] view_music_ls = null;
        
        
        String opt = ""; 
        Scanner sc = new Scanner(System.in); 
        while(true) {
            System.out.println("Client Application ");
            System.out.println("==============================================");
            System.out.println("View Music Library      [a]");
            System.out.println("View Albums             [b]");
            System.out.println("Add song                [c]");
            System.out.println("==============================================");
            System.out.println("\n\nExit                  [x]");
            System.out.print("Input: ");
            opt = sc.nextLine();
            
                switch (opt) {
                    case "a":
                        view_music_ls = controller.controller_GetAllMusic(); 
                        for (int i = 0; i < view_music_ls.length; i++) {
                            System.out.println("Music Title: " + view_music_ls[i].getMusic_title());
                            System.out.println("Music Artist: " + view_music_ls[i].getMusic_artist());
                            System.out.println("Music Album: " + view_music_ls[i].getMusic_album());
                            System.out.println("-----------------------------------------------------");
                        }
                        
                        break; 
                        
                    case "b":
                        controller.controller_GetAllAlbums(); 
                        ArrayList<String> albums = new ArrayList<>(); 
                        albums = controller.controller_GetAllAlbums();
                        
                        for (int i = 0; i < albums.size(); i++) {
                            System.out.println("Album [" + i + "]" + albums.get(i));
                        }
                        
                        
                        
                        break; 
                }
        }
    }
}
