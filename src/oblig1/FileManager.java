/*
 */
package oblig1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Feb 20, 2012
 */

public class FileManager {

  public void askFile() {
    String filename = JOptionPane.showInputDialog("Skriv inn et fil- eller mappenavn");
    File file = new File(filename);
    
    if (file.exists()) {
      // happy bunny
      if (file.isDirectory()) {
        System.out.printf("%s er en mappe!\n", filename);
        int numFiles = file.list().length;
        System.out.println("numFiles = " + numFiles);
        
        FilenameFilter filefilter = new FilenameFilter() {
          @Override
          public boolean accept(File dir, String name) {
            return name.endsWith("java");
          }
        };

        String[] filenames = file.list(filefilter);
        int numJavaFiles = filenames.length;
        System.out.println("number of Java files = " + numJavaFiles);
      }
      else if (file.isFile()) {
        System.out.printf("%s er en fil!\n", filename);
        boolean success = processFile(filename);
      }
    }
    else {
      System.out.println("Filen finnes ikke!");
    }
  }
  public boolean processFile(String filename) {
    try {
      FileReader input = new FileReader(filename);
      BufferedReader bufRead = new BufferedReader(input);           
      String line;
      int count = 0;
      String newfilename = "";
      File newfile;
      FileOutputStream newfilestream;
      DataOutputStream output;
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Tekstfiler", "txt");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showSaveDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
        newfilename = chooser.getSelectedFile().getName();
        newfile = chooser.getSelectedFile();
      }
      else {
        System.out.println("Du trykket ikke ok, dust!");
        return false;
      }
      newfilestream = new FileOutputStream(newfile);
      output = new DataOutputStream(newfilestream);
      line = bufRead.readLine();
      count++;
      while (line != null) {
        //TODO: Skriv til fil!
        //System.out.printf("%d : %s\n", count, line);
        String tmp = count + " " + line + "\n";
        output.writeChars(tmp);
        line = bufRead.readLine();
        count++;
      }
      bufRead.close();
      System.out.printf("%s opprettet med innholdet fra %s\n", newfilename, filename);
      return true;
    }
    catch (Throwable t) {
      t.printStackTrace();
      return false;
    }
  }
}
