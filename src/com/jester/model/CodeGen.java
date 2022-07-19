/**
    Author     : Cloyd Van S. Secuya
    Filename   : CodeGen.java
    Package	   : com.jester.model;
    Date of Creation : June 15, 2022
    Description:
    	This is our random key generator capable of generating an alphanumeric key and RGN. 
        It is used for establishing a PRIMARY KEY to be inserted in our database. 
            This is done when there are newly installed records to be inserted into the database. 
 */

 // PACKAGE SECITON
package com.jester.model;


// IMPORT SECTION
import java.util.Random;


public class CodeGen {
	
	private String item_code; 
	private String key; 
	
	// Setting the maximum number to generate
	int MAX_THRESHOLD = 1000; 
	
	// Set max salt string
	final int MAX_SALT = 5; 
	
    // Random class for implementing RGN
    private Random random = new Random(); 
    private int rgn = random.nextInt(MAX_THRESHOLD); 
    
    // A method to set the RGN; not more than the MAX_THRESHOLD
    //	then, assign the generated number to our item_code
    public void setRGN() {
        random = new Random(); 				
        rgn = random.nextInt(MAX_THRESHOLD);
    }
    
    // Return the generated RGN
    public int getRGN() {
    	return rgn;
    }
    
    // We are to create a unique String key to which will concatenate to the RGN 
    /**
     * @NOTE: This truly can have a very unique item_code
     * 		  item_code = RGN + KEY 
     * 			item_code is what we want to return
     */
    public String genKEY() {        
        String KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        		   + "abcdefghijklmnopqrstuvwxyz";
        
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        // Set it to less than 20 characters
        /**
         * We can set this other number as well.
         */
        while (salt.length() < MAX_SALT) { 
            int index = (int) (rnd.nextFloat() * KEY.length());
            salt.append(KEY.charAt(index));
        }
        String saltStr = salt.toString();        
        
        key = Integer.toString(rgn); 
        item_code = key + saltStr;
        
        return item_code;
    }
    
    
    /**
     * @NOTE: The main method below is to test the CodeGen algorithm that I wrote. 
     *        This is to make sure that there are no anomalies which are duplicate keys
     *        As these keys are acting as my item_code to be assigned on a particular Item product.
     *        
     *        UNCOMMENT THE MAIN METHOD IF NEEDED BE. 
     *        THE ITERATION IS UP UNTIL 9999
     */
    
//   public static void main(String[] args) {
//   	CodeGen generate = new CodeGen(); 
//   	int num = 0; 
//   	System.out.println("Listing all possible combinations!");
//   	while(num < 10000) {
//   		System.out.println("=========================================");
//	    	System.out.println("Iteration " + num);
//   		generate.setRGN();
//	    	System.out.println("RGN: " + generate.getRGN());
//	    	System.out.println("Item Code(key): " + generate.genKEY());
//	    	System.out.println("=========================================");
//	    	num++;
//   	}
//   }
}