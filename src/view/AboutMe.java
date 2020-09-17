package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JOptionPane;


/**
 * Class to represent the about me box
 * @author Miranda Bessex 5/12/19
 *
 */
public class AboutMe {
    
    private String version;
    
    private String devolpers;
    
    /**
     * @author Miranda Bessex 5/12/19
     */
    public AboutMe () {
        version = "";
        devolpers = "";
        getVersion();
        
    }
    
    
    /**
     * @author Miranda Bessex 5/12/19
     * @author Joseph Rushford 6/13/19
     */
    private void getVersion() {

            InputStream t = getClass().getResourceAsStream("/version.txt");
            InputStreamReader r = new InputStreamReader(t);
            Scanner ver = new Scanner(r);
            version += ver.nextLine();
            devolpers += ver.nextLine();
            

        
        aboutMeButtonAction();
    }
    
    /**
     * @author Miranda Bessex 5/12/19
     */
    private void aboutMeButtonAction() {
        
        JOptionPane.showMessageDialog(null, "About Me: \n Version: " + version + "\n Developers: " + devolpers );
        
        
    }
}
