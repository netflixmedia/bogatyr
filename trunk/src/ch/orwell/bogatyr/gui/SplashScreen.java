package ch.orwell.bogatyr.gui;

import java.awt.Color;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is a splash-screen for the program start
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070618
 */
public class SplashScreen extends JFrame {
	private static final long serialVersionUID = 2371384063690804476L;
	private String className;
	
	private ImageIcon image;
	private int borderWidth = 40;
	
	private int sizeX;
	private int sizeY;
	
	/**
	 * Constructs a splash-screen with picture in it.
	 * @param fileName The location where the Image is.
	 */

	public SplashScreen(String fileName) {
		super();
		init();

        URL url = getClass().getClassLoader().getResource(fileName);
        this.image = new ImageIcon(url);

		if ((this.image != null) && (this.image.getImageLoadStatus() == MediaTracker.COMPLETE)) {
			this.sizeX = this.image.getIconWidth();
			this.sizeY = this.image.getIconHeight();
			
			this.sizeX += getBorderWidth() * 2;
			this.sizeY += getBorderWidth() * 2;

			
			JLabel label = new JLabel(this.image);
			
			label.setBorder(new BevelBorder(BevelBorder.RAISED,	Color.LIGHT_GRAY, Color.WHITE, Color.DARK_GRAY, Color.GRAY));
			getContentPane().add(label);

			setUndecorated(true);
			setSize(this.sizeX, this.sizeY);
			setLocationRelativeTo(null);
		}
	}
	
	/**
     * Display the splash-screen for the desired time
     * @param duration Duration in ms
     */	
	public void displayTime(long duration) {
		setVisible(true);
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				dispose();
			}
		},duration);
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Initialize the object<p>
	 * Do some logging.
	 */
	private void init() {
		this.className = this.getClass().getName();
		Logger.getInstance().writeDebug(this.className + "::init", Context.getInstance().getLocalizer().getValue(Localizer.RES_INSTANCIATED)); //$NON-NLS-1$
	}
	
	/**
     * Returns the border width of the border around the image of the splashscreen.
     */
	public int getBorderWidth() {
        return this.borderWidth;
    }

    /**
     * Sets width of border around the image of the splashscreen. The color of the border is the background
     * color of the window.
     * 
     * The default value is 40.
     */
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
}