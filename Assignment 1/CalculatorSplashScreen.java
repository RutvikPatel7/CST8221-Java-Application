/********************************************************************************************************************************************************
File name	: CalculatorSplashScreen
Author		: Rutvik Patel, Student #  :	040915445
Course	 	: CST8221-JAP, Lab Section : 	303
Assignment 	: Assignment 1	-	Part 2
Date		: March 6 2020
Professor	: Svillen Ranev
Purpose		: Splash screen creates start of the program with a progress loading bar
Class list	:      CalculatorSplashScreen
********************************************************************************************************************************************************/

package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 * This class is  to display the splash screen.
 *  
 * @author Rutvik Patel
 * @version 1
 * @see calculator
 * @since 1.8.0
 */
public class CalculatorSplashScreen extends JWindow{
		private static final long serialVersionUID = 6248477390124803341L;
		private int time;
		private JLabel current;
		private JProgressBar progressBar;
		private static final int PB_MINIMUM = 0; // min progressBar
		private static final int PB_MAXIMUM = 100; // max progresBar

		public CalculatorSplashScreen ( int time){ // constructor with duration parameter of splash screen
			this.time = time;
		}

		public void showSplashWindow() {
			//create content pane
			JPanel content = new JPanel(new BorderLayout());
			content.setBackground(Color.white);
			/*Progress Bar*/
			progressBar = new JProgressBar(); // creates the progress bar
			progressBar.setMinimum(PB_MINIMUM);
			progressBar.setMaximum(PB_MAXIMUM);
			progressBar.setString("Loading Calculator, Please wait..");
			progressBar.setStringPainted(true); // set the string on the bar
			progressBar.setForeground(Color.RED); // set the color to orange
			
			int width =  500+10;
			int height = 300+10;
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width-width)/2;
			int y = (screen.height-height)/2;
			setBounds(x,y,width,height);
			JLabel label = new JLabel(new ImageIcon(getClass().getResource("sp.jpg"))); 
			JLabel demo = new JLabel("Rutvik Patel 040915445", JLabel.CENTER);
			JLabel current = new JLabel();

			demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
			content.add(label, BorderLayout.CENTER);
			content.add(demo, BorderLayout.SOUTH);
			content.add(current, BorderLayout.NORTH);
			content.add(progressBar, BorderLayout.NORTH); // adds progress bar to the contents
			Color customColor = new Color(44, 197, 211); 
			content.setBorder(BorderFactory.createLineBorder(customColor, 10));
			setContentPane(content);
			setVisible(true);

			for (int i = PB_MINIMUM; i <= PB_MAXIMUM; i++) { // loops for the progress bar percentage%
				final int percent=i;
				try {
					progressBar.setValue(percent);
					java.lang.Thread.sleep(50);
				} catch (InterruptedException e) {;}
			} // end for loop 
			dispose();  
		 } // end of showSplashWindow
	}