/**
    Authors     : Cloyd Secuya
    Filename   : SplitTest.java
    Package	   : com.jester.view;
    Date of Creation : June 13, 2022
    Description:
        The implementation of splitting the file path for lyrics in the .txt format. 
        We split four times as we can see in the main method below.
*/

// PACKAGE SECTION
package com.jester.view;


public class SplitTest {
    
    public static void main(String[] args) {
        String path = "src/com/view/LYR_bakamitai_LYRICS.txt";
        System.out.println("BEFORE SPLIT"); 
        System.out.println(path); 
        
        System.out.println("AFTER SPLIT"); 
        String[] path_split = path.split("/", 4);
        for (int i = 0; i < path_split.length; i ++) {
            System.out.println(path_split[i]);
        }
        
        System.out.println("\n\nTEXT FILE ITSELF"); 
        System.out.println("\t===>>>" + path_split[3]);
    }
    
}
