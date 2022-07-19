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
import com.jester.model.music_handler.PlayerThread;
import java.io.File;
import java.util.Scanner;


public class Database implements Interface_musichandle {
    
    Model model; 
    
    @Override
    public Music[] getMusicArrObj() {
        model = new Model();
        Music[] music_ls;
        music_ls = model.view_and_get_MusicRecords(); 
       
        return music_ls;
    }

    @Override
    public ArrayList<Music> getMusicRecords() {
        model = new Model(); 
        ArrayList<Music> arrls_music = new ArrayList<Music>();
        arrls_music = model.get_ArrayList_of_MusicRecords(); 
        
        return arrls_music;
    }

    @Override
    public Music getMusicToPlay(String music_ID) {
        model = new Model(); 
        Music musicToPlay = new Music();
        musicToPlay = model.fetchMusic(music_ID); 
        
        return musicToPlay;
    }

    @Override
    public void playSound(String music_title, String path) {
        PlayerThread player; 
        player = new PlayerThread(path);
        
        /**
         * Cause the thread to begin the execution
         * See the PlayerThread.run() method block
         * @Cloyd
        */
        player.start();
        
        System.out.println("Now Playing..." + music_title);
    }
    
    @Override
    public void stopSound() {
        PlayerThread player = new PlayerThread(); 
        player.close();
    }
    
    @Override
    public void add_new_Music(Music music) {
        model = new Model(); 
        model.addMusic(music);
    }

    
    /**
     * @NOTE: 
     *      De-comment the following blocks of code for testing the
     *      Implemented methods of the interface.
     *  
     */
//    public static void main(String[] args) {
//        Interface_musichandle handle = new Database();
//        Scanner sc = new Scanner(System.in); 
//        
//        Music[] music_ls;
//        music_ls = handle.getMusicArrObj();
//        
//        System.out.println("\n\n\n");
//        System.out.println("============================================================");
//        System.out.println("                      MUSIC LIBRARY                         ");
//        System.out.println("============================================================");
//        for (int i = 0; i < music_ls.length; i ++) {
//            System.out.println("Music Title: " + music_ls[i].getMusic_title());
//            System.out.println("Music Artist: " + music_ls[i].getMusic_artist());
//            System.out.println("Music Album: " + music_ls[i].getMusic_album());
//            System.out.println("-----------------------------------------------------");
//        }
//        
//        /**
//         * @NOTE: This is how we can handle the audio streaming and play the 
//         *        .mp3 files inside the src.music_dir
//         * @Cloyd
//         */
//        // I want to play a piece of music
//        System.out.print("Enter the music title you want to play: ");
//        String musicTitleToPlay = sc.nextLine(); 
//        
//        Music musicToPlay = new Music();
//        
//        switch (musicTitleToPlay) {
//            case "Country Roads": 
//                musicToPlay = handle.getMusicToPlay("001");
//                System.out.println("DIR: " + musicToPlay.getMusic_path_to_DIR());
//                handle.playSound(musicToPlay.getMusic_title(), musicToPlay.getMusic_path_to_DIR());
//                break;
//                
//            case "Pixel Galaxy": 
//                musicToPlay = handle.getMusicToPlay("002");
//                System.out.println("DIR: " + musicToPlay.getMusic_path_to_DIR());
//                handle.playSound(musicToPlay.getMusic_title(), musicToPlay.getMusic_path_to_DIR());
//                break;
//                
//            case "Twinklestar":
//                musicToPlay = handle.getMusicToPlay("003");
//                System.out.println("DIR: " + musicToPlay.getMusic_path_to_DIR());
//                handle.playSound(musicToPlay.getMusic_title(), musicToPlay.getMusic_path_to_DIR());
//                break;
//            
//            case "Clair De Lune":
//                musicToPlay = handle.getMusicToPlay("004");
//                System.out.println("DIR: " + musicToPlay.getMusic_path_to_DIR());
//                handle.playSound(musicToPlay.getMusic_title(), musicToPlay.getMusic_path_to_DIR());
//                break; 
//            
//            case "Baka Mitai":
//                musicToPlay = handle.getMusicToPlay("005");
//                System.out.println("DIR: " + musicToPlay.getMusic_path_to_DIR());
//                handle.playSound(musicToPlay.getMusic_title(), musicToPlay.getMusic_path_to_DIR());
//                break;
//        }
//    }
}
