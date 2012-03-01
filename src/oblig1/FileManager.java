/*
 */
package oblig1;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Kristoffer Berdal <web@flexd.net>
 * @studnr 180212
 * @date Feb 20, 2012
 */

public class FileManager implements FileFilter {

  public void askFile() throws FileNotFoundException {
    String filename = JOptionPane.showInputDialog("Skriv inn et fil- eller mappenavn");
    File file = new File(filename);
    
    if (file.exists()) {
      // happy bunny
      if (file.isDirectory()) {
        System.out.printf("%s er en mappe!\n", filename);
        int numFiles = file.list().length;
        System.out.println("numFiles = " + numFiles);
        
        int numJavaFiles = 0;
        File[] files = file.listFiles();
        int numJavaLines = 0;
        for (int i = 0; i < files.length; i++) {
          if(files[i].getName().toLowerCase().endsWith(".java")) {
            numJavaLines += countLines(files[i]);
            numJavaFiles++;
          }    
        }
        
        System.out.println("Number of Java files = " + numJavaFiles);
        System.out.println("Number of lines Java code = " + numJavaLines);
        
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
        System.out.println("Du trykket ikke ok");
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

  public int countLines(File file)  {
    FileReader input;
    int count = 0;
    String line = "";
    
    try {
      input = new FileReader(file);
      BufferedReader bufRead = new BufferedReader(input);  
      line = bufRead.readLine();
      while (line != null) {
        line = bufRead.readLine();
        count++;
      }
      bufRead.close();
    } catch (FileNotFoundException ex) {
      Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
      return count;
    } catch (IOException ex) {
      Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
      return count;
    }
    
    return count;
  }
  
  @Override
  public boolean accept(File file) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
