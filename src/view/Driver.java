/* 
 * TCSS 360
 */

package view;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Driver file runs the program.
 * 
 * @author Gehry Guest 5/11/19
 * @version 1.7.44
 */
public final class Driver {

    /**
     * Private constructor, to prevent instantiation of this class.
     * @author gehry guest 5/13/19
     */
    private Driver() {
        
        throw new IllegalStateException();
    }

    /**
     * re-textures portions of the program with a new skin.
     * @author gehry guest
     */
    public static void programSkin() {
        try {
            
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
            
            e.printStackTrace();
        }
    }
   
    /**
     * The main method, invokes the GUI. Command line arguments are ignored.
     * @author gehry guest 5/12/19
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                programSkin();
                
                new SplashScreen();
            }
        });
    }
}
