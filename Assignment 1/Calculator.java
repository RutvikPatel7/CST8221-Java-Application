package calculator;
/********************************************************************************************************************************************************
File name	: Calculator
Author		: Rutvik Patel, Student #  :	040915445
Course	 	: CST8221-JAP, Lab Section : 	303
Assignment 	: Assignment 1	-	Part 1
Date		: February 21 2020
Professor	: Svillen Ranev
Purpose		: Main method that excutes the program , contains of splash screen and progress bar
********************************************************************************************************************************************************/

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculator {
	private final static String Frame_Title ="Calculator";
	public static void main(String args[]) {
		
		//Object of calculator splash screen and display the splash screen for 5 second. 
		CalculatorSplashScreen splashwin = new CalculatorSplashScreen(5);
		splashwin.showSplashWindow();
		EventQueue.invokeLater(new Runnable(){
			@Override 
			public void run(){
				JFrame frame = new JFrame(Frame_Title);
				JPanel pane = new CalculatorViewController();
				frame.add(pane);            	
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setMinimumSize(new Dimension(340,500));
				frame.pack();	
				frame.setResizable(true);
				frame.setLocationRelativeTo(null); 	
				frame.setVisible(true);
			}
		});
	}

}
