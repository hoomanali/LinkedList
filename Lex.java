/*
 * Ali Hooman
 *
 * Lex.java
 * 
 * Lex.java - Accepts 2 files as arguments from command line,
 * takes input from input text file and outputs a text file
 * with the same data in lexicographic order.
 * 
 */

import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
public class Lex {

    public static void main( String[] args ) throws IOException {

        List lexiList = new List();
        int count = 0;
        int arrayCount = 0;
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String[] array = null;

        /*
         * Check for 2 cmd args, quit with a usage message to stderr if not exactly 2.
         */
        if(args.length != 2) {
            System.err.println("Usage: Lex fileIn fileOut");
            System.exit(1);
        }

        // Get input file from command line args
        in = new Scanner(new File( args[0] ));
        out = new PrintWriter(new FileWriter(args[1]));

        /* 
         * Count number of lines n in file named by args[0], create string array of length n,
         * read in lines as strings and place in array.
         */
        while( in.hasNextLine() ) {
            count++;
            line = in.nextLine()+" ";
        }
        in.close();
        
        // Size array to current number of lines
        array = new String[ count ];
        // Open file and start from beginning
        in = new Scanner(new File( args[0] ));

        /*
         * Create list whose elements are indices of String array. Arround them
         * in an order that sorts the array in lexicographic order.
         * String class provides compareTo() method.
         * 
         * e.g. s1.compareTo(s2) < 0 is true IFF s1 comes before s2
         * e.g. s1.compareTo(S2) > 0 is true IFF s1 comes after s2
         * e.g. s1.compareTo(s2) == 0 is true IFF s1 is identical to s2
         */
        // Create array of lines
        for(int i = 0; i < count; i++) {
            line = in.nextLine() + " ";
            array[i] = line;
        }
        in.close(); // Close input file 
        
        //Print array
        System.out.println("**********Printing input array**********");
        for(int i = 0; i < array.length; i++) {
            System.out.println( array[i] );
        }

        /*
         * Sort array in lexicographic order and add to List lexiList
         * Comparison:  < 0 -> true IFF s1 before s2
         * Comparison:  > 0 -> true IFF s1 after s2
         * Comparison:  = 0 -> true IFF s1 identical to s2
         */
        int i;
        lexiList.append(0);
        for(int j = 1; j < array.length; j++) {
            i = j - 1;
            String temp = array[j];
            lexiList.moveBack();
            
            while(i >= 0 && temp.compareTo( array[lexiList.get()] ) <= 0) {
                lexiList.movePrev();
                i--;
            }
            if(lexiList.index() >= 0) {
                lexiList.insertAfter(j);
            }
            else {
                lexiList.prepend(j);
            }
        }
              
         /*
         * Use list constructed above to print array in alphabetical order to 
         * the file named by args[1]
         * Do not sort array, simply build a list of indices in desired order.
         * FileIO.java has examples for file operations in Java
        */
        System.out.println("*********Printing output array*********");
        for(lexiList.moveFront(); lexiList.index() >= 0; lexiList.moveNext()) {
            System.out.println( array[lexiList.get()] );
            out.println( array[lexiList.get()] );
        }
        out.close(); // CLose output file

    }   // End main()
}   // End class Lex
