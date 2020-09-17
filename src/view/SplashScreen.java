/* 
 * TCSS 360
 */

package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Class to start splash screen for DIY project.
 * 
 * @author gehry guest 5/13/19
 * @version 1.7.44
 */
public class SplashScreen extends JWindow {
    
    /** A number for Serialization. */
    private static final long serialVersionUID = -2102839873375270630L;
                    
    /** Constant time for each update. this controls time to change splash finish time. */
    private static final int TIMER_PAUSE = 10; 
    
    /** The width of the panel. */
    private static final int WIDTH = 600;

    /** The height of the panel. */
    private static final int HEIGHT = 700;
    
    /** count to increment bar and check progress of bar. */
    private int myCount = 1;
        
    /** Timer for bar to update to. */
    private Timer mySplashTimer;

    /** loading time. */
    private final int myLoadingTime = 500;
    
    
    /**
     * Initializes fields with reasonable values.
     * @author gehry guest 5/13/19
     */
    public SplashScreen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        mySplashTimer = new Timer(TIMER_PAUSE, new TimeListener());
        
        mySplashTimer.start();
        
        setUp();
    }
    
    
    /**
     * sets up containers, JPanels, and progress bar with layouts and colors.
     * @author gehry guest 5/13/19
     */
    private void setUp() {
        
        final Container container = getContentPane();

        final JPanel panel = new JPanel();
        
        final JLabel imgLabel = new JLabel("",
                                        new ImageIcon(getClass().getClassLoader().getResource("App Logo_without name.png")), 
                                        SwingConstants.CENTER);
        
        container.add(panel, BorderLayout.CENTER);
        
        panel.add(imgLabel, BorderLayout.CENTER);
  
        this.pack();
        
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
    }
    


    /**
    * A inner class that listens for timer events.
    * @author gehry guest 5/13/19
    */
    private class TimeListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvt) {
            
            if (myLoadingTime == myCount) {
                
                mySplashTimer.stop();
                
                SplashScreen.this.setVisible(false);
                
                new HomePage();
            }
            myCount++;
        }
    };
}
