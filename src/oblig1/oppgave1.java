package oblig1;

import java.io.FileNotFoundException;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 */
public class oppgave1 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws FileNotFoundException {
    FileManager f = new FileManager();
    
    f.askFile();
  }
}
