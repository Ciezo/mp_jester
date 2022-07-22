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
        Music new_music; 
        Music play_music;
        Music[] view_music_ls = null;
        
        String opt = ""; 
        int decision;
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
                        view_music_ls = controller.GetAllStoredMusic(); 
                        for (int i = 0; i < view_music_ls.length; i++) {
                            System.out.println("Music Title: " + view_music_ls[i].getMusic_title());
                            System.out.println("Music Artist: " + view_music_ls[i].getMusic_artist());
                            System.out.println("Music Album: " + view_music_ls[i].getMusic_album());
                            System.out.println("-----------------------------------------------------");
                        }
                        
                                System.out.print("\n\nEnter the music title you want to play: ");
                                String musicTitleToPlay = sc.nextLine();

                                    switch (musicTitleToPlay) {
                                        case "Country Roads":
                                            // controller.play_musicByID("001");
                                            
                                            play_music = new Music();
                                            play_music.setMusic_ID("001");
                                            controller.setMusicToPlay(play_music);
                                            
                                            System.out.print("\t\tEnter 0 to stop music ===>>> ");
                                            decision = sc.nextInt(); 
                                            
                                                if(decision == 0)
                                                    controller.stop_music();
                                            decision = 1;
                                            break;
                                            
                                            
                                        case "Pixel Galaxy":
                                            // controller.play_musicByID("002");

                                            play_music = new Music();
                                            play_music.setMusic_ID("002");
                                            controller.setMusicToPlay(play_music);
                                            
                                            System.out.print("\t\tEnter 0 to stop music ===>>> ");
                                            decision = sc.nextInt(); 
                                            
                                                if(decision == 0)
                                                    controller.stop_music();
                                            decision = 1;
                                            break;
                                        
                                        case "Twinklestar":
                                            // controller.play_musicByID("003");

                                            play_music = new Music();
                                            play_music.setMusic_ID("003");
                                            controller.setMusicToPlay(play_music);
                                            
                                            System.out.print("\t\tEnter 0 to stop music ===>>> ");
                                            decision = sc.nextInt(); 
                                            
                                                if(decision == 0)
                                                    controller.stop_music();
                                            decision = 1;
                                            break;
                                            
                                        case "Clair De Lune":
                                            // controller.play_musicByID("004");

                                            play_music = new Music();
                                            play_music.setMusic_ID("004");
                                            controller.setMusicToPlay(play_music);
                                            
                                            System.out.print("\t\tEnter 0 to stop music ===>>> ");
                                            decision = sc.nextInt(); 
                                            
                                                if(decision == 0)
                                                    controller.stop_music();
                                            decision = 1;
                                            break;
                                            
                                        case "Baka Mitai":
                                            // controller.play_musicByID("005");

                                            play_music = new Music();
                                            play_music.setMusic_ID("005");
                                            controller.setMusicToPlay(play_music);
                                            
                                            System.out.print("\t\tEnter 0 to stop music ===>>> ");
                                            decision = sc.nextInt(); 
                                            
                                                if(decision == 0)
                                                    controller.stop_music();
                                            decision = 1;
                                            break;
                                            
                                    }
                        
                        break;
                        
                    case "b":
                        String[] albums = controller.GetAllStoredAlbums(); 
                        
                        for (int i = 0; i < albums.length; i++) {
                            System.out.println("View Album: " + albums[i]);
                        }
                        
                        Object[] music_album = controller.GetAllStoredAlbums(); 
                        
                        for (int i = 0; i < albums.length; i++) {
                            System.out.println("View as OBJECT Album: " + music_album[i].toString());
                        }
                        
                        break; 
                    
                    case "c": 
                        new_music = new Music(); 
                        
                        String user_title; 
                        String user_artist; 
                        String user_album; 
                        String user_path; 
                        
                        System.out.print("Enter a music title: ");
                        user_title = sc.nextLine(); 
                        
                        System.out.print("Enter a music artist: ");
                        user_artist = sc.nextLine(); 
                        
                        System.out.print("Enter a music album: ");
                        user_album = sc.nextLine(); 
                        
                        System.out.print("Enter a music path: ");
                        user_path = sc.nextLine();
                        
                        new_music.setMusic_title(user_title);
                        new_music.setMusic_artist(user_artist);
                        new_music.setMusic_album(user_album);
                        new_music.setMusic_path_to_DIR(user_path);
                        
                        new_music = new Music(new_music.getMusic_ID(), user_title, user_artist, user_album, user_path);
                        
                        controller.SetNewMusic(new_music); 
                        
                        break;
                        
                    case "x":
                        System.exit(0);
                        
                        break;
                }
        }
    }
}
