/* OpenCommercial.java */

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    /* Replace this comment with your solution.  */
    
    // create url based on the input
    String url = "http://www." + inputLine + ".com/"; /* Create URL */
    URL link = new URL(url);
    
    
    // open the url stream, wrap it an a few "reader"
    BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream()));
    
    // write the output to stdout   
    String line; 

    //store the first five lines in a list
    int i=5; 
    List<String> lines = new ArrayList<String>();
    while (i>0 && (line = reader.readLine()) != null) {
        lines.add(line);
        i--;
    }  
    reader.close();

    //print the lines in reverse order
    for (i=lines.size()-1; i >= 0; i--) {
        System.out.println("Line " + i + ": " + lines.get(i));
    }   
    
    
  }
}
