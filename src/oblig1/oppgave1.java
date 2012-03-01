/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212

 * @author Even Augdal <even.augdal@gmail.com>
 * @studnr 181091
 * 
 * @author Tommy Nyrud <s180487@stud.hioa.no>
 * @studnr 180487
 * 
 * @date Feb 20, 2012
 */

package oblig1;

import java.io.FileNotFoundException;


public class oppgave1 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws FileNotFoundException {
    FileManager f = new FileManager();
    
    f.askFile();
  }
}
