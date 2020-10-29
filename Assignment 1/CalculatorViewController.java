/********************************************************************************************************************************************************
File name	: CalculatorViewController
Author		: Rutvik Patel, Student #  :	040915445
Course	 	: CST8221-JAP, Lab Section : 	303
Assignment 	: Assignment 1	-	Part 2
Date		: March 6 2020
Professor	: Svillen Ranev
Purpose		: Calculator GUI interface build with multiple different components, works in 4 different modes ( floats with different format-scientific 
and integer mode)
Class list	: Controller inner class, that is responsible for the action performed for each button and actionCommand for the GUI components
 ********************************************************************************************************************************************************/

package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CalculatorViewController extends JPanel {

	/**
	 * {@value} serial version number
	 */
	private static final long serialVersionUID = 1L;

	private JTextField display1; // the calculator display1 field reference
	private JTextField display2; // the calculator display2 field reference
	private JLabel error; // the mode/error display label reference
	private JButton dotButton; // the decimal point (dot) button reference
	private JButton[] hexButtons; // reference to container for alphabetical hex buttons
	private String temp = "";
	Controller controller = new Controller(); // controller reference (actionCommand)
	CalculatorModel calc = new CalculatorModel(); // CalculatorModel referenceCalculatorModel calc = new
													// CalculatorModel(); // CalculatorModel reference

	/**
	 * This default contempuctor is used building the GUI of calculator.
	 * 
	 * @param N/A
	 * @return N/A
	 */
	// CalculatorViewController constructor
	public CalculatorViewController() {

		setLayout(new BorderLayout());
		// set layout with black border and dimension gaps
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		// contempuct main panel
		JPanel mainPanel = new JPanel(new BorderLayout());
		// textField panel
		JPanel textFieldPanel = new JPanel(new BorderLayout());
		// radio panel
		JPanel radioPanel = new JPanel();
		// panel for radio Buttons
		JPanel addOnPanel = new JPanel(new BorderLayout());
		// panel for textfeilds
		JPanel topPanel = new JPanel(new BorderLayout());

		// add the panel to the container
		add(mainPanel);

		// setBackground
		mainPanel.setBackground(Color.BLACK);
		topPanel.setBackground(Color.BLACK);

		// display1 with 14 columns
		display1 = new JTextField(14);
		// display2 displays 0.0 at start mode float
		display2 = new JTextField("0.0", 14);
		// display1 with 14 columns and 30 rows
		display1.setPreferredSize(new Dimension(14, 30));
		// set alignment for display1
		display1.setHorizontalAlignment(JTextField.RIGHT);
		// display2 with 14 columns and 30 rows
		display2.setPreferredSize(new Dimension(14, 30));
		// display1 is not editable
		display1.setEditable(false);
		// set background
		display1.setBackground(Color.white);
		// remove border for display1
		display1.setBorder(null);
		// display2 is not editable
		display2.setEditable(false);
		// set background for display2
		display2.setBackground(Color.white);
		// remove border for display1
		display2.setBorder(null);
		// set alignment for display1
		display2.setHorizontalAlignment(JTextField.RIGHT);
		display2.setVisible(true);

		// create panel for textfield
		JPanel textFieldPanel2 = new JPanel(new BorderLayout());
		// set background for panel
		textFieldPanel2.setBackground(Color.BLACK);
		// add display1 to text fieldpanel
		textFieldPanel2.add(display1, BorderLayout.NORTH);
		// add display2 to textfield panel
		textFieldPanel2.add(display2, BorderLayout.SOUTH);
		// create new font
		Font myFont = new Font("ArialBlack", Font.BOLD, 17);
		// create new JLabel for the modes
		error = new JLabel("F", SwingConstants.CENTER);
		// add font to the label
		error.setFont(myFont);
		// set the size of label
		error.setPreferredSize(new Dimension(55, 55));
		// set border to label
		error.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.BLACK));
		// make label transparent
		error.setOpaque(true);
		// set the background for the label
		error.setBackground(Color.YELLOW);

		// add the symbol to the backspace button
		JButton backSpace = new JButton("\u21D0");
		// displays when hover over
		backSpace.setToolTipText("Backspace(Alt-B)");
		// used for keyboard input
		backSpace.setMnemonic(KeyEvent.VK_B);
		// adds the font to the backspace label
		backSpace.setFont(myFont);

		// Set Yellow Background
		backSpace.setBackground(Color.yellow);
		// backspace action command
		backSpace.setActionCommand("\u21D0");
		// add it to the controller class that holds the action performed
		backSpace.addActionListener(controller);

		backSpace.setPreferredSize(new Dimension(55, 55));
		backSpace.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.BLACK));

		// set visibility (transparentcy)
		backSpace.setOpaque(true);

		// add the label to the textfield panel
		textFieldPanel.add(error, BorderLayout.WEST);
		// add the backspace to the text field panel
		textFieldPanel.add(backSpace, BorderLayout.EAST);
		// add text field panel2 to the textfield panel
		textFieldPanel.add(textFieldPanel2);

		// add the text field panel to North
		topPanel.add(textFieldPanel, BorderLayout.NORTH);

		// set radio panel background
		radioPanel.setBackground(Color.BLACK);
		// create radio button for .0 mode
		JRadioButton zero = new JRadioButton(".0");
		// create radio button for .00 mode
		JRadioButton doubleZero = new JRadioButton(".00", true);
		// create radio button for sci mode
		JRadioButton sciButton = new JRadioButton("Sci");
		// create radio button for space
		JButton invisible = new JButton();
		// add the buttons to a group
		ButtonGroup radioGroup = new ButtonGroup();
		// set transparentcy to false
		invisible.setOpaque(false);
		// set background for buttons
		invisible.setBackground(Color.BLACK);
		// make it invisible , only used for spacing
		invisible.setVisible(false);

		// set background for sci button
		sciButton.setBackground(Color.YELLOW);
		// set background for .00 button
		doubleZero.setBackground(Color.YELLOW);
		// set background for 0 button
		zero.setBackground(Color.YELLOW);

		// add action listener to radio buttons
		sciButton.addActionListener(controller);
		sciButton.setActionCommand("Sci");
		doubleZero.setActionCommand(".00");
		doubleZero.addActionListener(controller);
		zero.setActionCommand(".0");
		zero.addActionListener(controller);

		// create checkbox for Hex mode
		JCheckBox checkBox = new JCheckBox("Hex");

		// add the checkbox to radio group buttons
		radioGroup.add(checkBox);
		// sets background for checkbox
		checkBox.setBackground(Color.green);
		checkBox.setActionCommand("Hex");
		// add action command to checkbox
		checkBox.addActionListener(controller);
		// add radio buttons and checkbox to panel

//		radioPanel.add(Box.createHorizontalStrut(145));
//		radioPanel.add(invisible);

		checkBox.setBorderPainted(true);
		checkBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		addOnPanel.add(checkBox, BorderLayout.WEST);

		radioPanel.add(zero);
		radioPanel.add(doubleZero);
		radioPanel.add(sciButton);

		radioGroup.add(zero);
		radioGroup.add(doubleZero);
		radioGroup.add(sciButton);

		addOnPanel.setBackground(Color.black);
		addOnPanel.add(radioPanel, BorderLayout.EAST);
		topPanel.add(addOnPanel, BorderLayout.SOUTH);

		// add top panel to the main panel
		mainPanel.add(topPanel, BorderLayout.NORTH);

		JPanel arith1 = new JPanel();
		// gridLayout to make the size even
		arith1.setLayout(new GridLayout(1, 0, 8, 0));
		arith1.setBackground(Color.black);

		JPanel arith2 = new JPanel();
		// gridLayout to make the size even
		arith2.setLayout(new GridLayout(1, 0, 8, 0));
		arith2.setBackground(Color.black);

		// create new JPanel for keypad
		JPanel numric = new JPanel();
		// gridLayout to make the size even
		numric.setLayout(new GridLayout(6, 0, 3, 3));
		// set background for panel
		numric.setBackground(Color.white);
		numric.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));

		String elements[] = new String[] { "A", "B", "C", "D", "E", "F", "7", "8", "9", "4", "5", "6", "1", "2", "3",
				".", "0", "\u00B1" }; // array to display on buttons

		hexButtons = new JButton[6];
		int i = 0;
		// loops through array and create buttons
		while (i <= 17) {

			if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
				// numric.add(createButton(elements[i],elements[i],Color.white,Color.BLUE,false,
				// controller)); // create all other keypad buttons
				hexButtons[i] = createButton(elements[i], elements[i], Color.white, Color.BLUE, false, controller);
				numric.add(hexButtons[i]);

			} else if (i == 17) {
				numric.add(createButton(elements[i], elements[i], Color.black, Color.magenta, true, controller)); // create
																													// plusMinus
																													// button
			} else if (i == 15) {
				dotButton = createButton(elements[i], elements[i], Color.black, Color.magenta, true, controller); // create
																													// dotBotton
				numric.add(dotButton);
			} else {
				numric.add(createButton(elements[i], elements[i], Color.black, Color.BLUE, true, controller)); // create
																												// all
																												// other
																												// keypad
																												// buttons
			}
			i++;
		}
		String[] ari_opr = new String[] { "+", "-", "*", "/", "*", "/", "+", "-" };

		int j = 0;
		while (j <= 7) {
			if (j == 0 || j == 1 || j == 2 || j == 3) {
				arith1.add(createButton(ari_opr[j], ari_opr[j], Color.black, Color.cyan, true, controller));
			} else if (j == 4 || j == 5 || j == 6 || j == 7 || j == 7) {
				arith2.add(createButton(ari_opr[j], ari_opr[j], Color.black, Color.cyan, true, controller));
			}
			j++;
		}

		// new panel for Clear and equal button
		JPanel equal = new JPanel(new GridLayout(1, 1, 1, 1));
		equal.setFont(myFont);
		equal.add(createButton("=", "=", Color.BLACK, Color.yellow, true, controller));
		equal.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.BLACK));

		// new panel for Clear and equal button
		JPanel clear = new JPanel(new GridLayout(1, 1, 1, 1));
		clear.setFont(myFont);
		clear.add(createButton("C", "clear", Color.BLACK, Color.red, true, controller));
		clear.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.BLACK));

		// To set the location of buttons
		JPanel keyPad = new JPanel(new BorderLayout());
		keyPad.add(numric, BorderLayout.CENTER);
		keyPad.add(arith1, BorderLayout.NORTH);
		keyPad.add(arith2, BorderLayout.SOUTH);

		mainPanel.add(keyPad, BorderLayout.CENTER);
		mainPanel.add(equal, BorderLayout.EAST);
		mainPanel.add(clear, BorderLayout.WEST);
	}

	/**
	 * The method is responsible for the creation of group of related buttons with
	 * the same basic properties.default constructor is used building the GUI of
	 * calculator.
	 * 
	 * @param String         text - the button text label
	 * @param String         ac - the action command String for that button
	 * @param Color          fg - the foreground color of the button
	 * @param Color          bg - the background color of the button
	 * @param Boolean        enable - disable Hexadecimal button
	 * @param ActionListener handler - the reference to instance of the event
	 *                       handler class
	 * 
	 * @return JButton buttons - the reference to the created button
	 */

	private JButton createButton(String text, String ac, Color fg, Color bg, boolean enable, ActionListener handler) {
		JButton mybutton = new JButton(text);

		mybutton.setForeground(fg);
		mybutton.setBackground(bg);

		/** Set the action command for the button */
		if (ac != null) {
			mybutton.setActionCommand(ac);
		}

		mybutton.setFont(new Font(mybutton.getFont().getName(), mybutton.getFont().getStyle(), 17));
		if (enable == false) {
			mybutton.setEnabled(false);
		}
		mybutton.addActionListener(handler);
		return mybutton;
	}

	/**
	 * This class is a private inner class inside the Controller class.
	 * 
	 * @author Rutvik Patel
	 * @version 1
	 * @see calculator
	 * @since 1.8.0_121
	 *
	 */

	private class Controller extends AbstractAction {

		/**
		 * {@value} serial version number
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * The method is responsible for the creation of group of related buttons with
		 * the same basic properties.
		 * 
		 * @param ActionEvent event - to display the textField text when it is get the
		 *                    action
		 * @return N/A
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			CalculatorViewController viewController = new CalculatorViewController(); // reference to
																						// CalculatorViewController
																						// class
			String actionCommand = e.getActionCommand();

			int number = -1;

			try { // checks if number is > -1 and < 10
				number = Integer.parseInt(actionCommand);

			} catch (NumberFormatException e1) {
			}

			if (number >= 0 && calc.getErrorState() == false) {
				display2.setText("");
				temp += actionCommand; // adds the String to actionCommand to have more than one digit numbers
				display2.setText(temp); // display the String
			}

			if (actionCommand.matches("[A-F]") && calc.getErrorState() == false) {
				temp += actionCommand;
				display2.setText(temp);
			}

			if (".0".equals(actionCommand) && calc.getErrorState() == false) { // check for float .0 mode
				if (calc.getMode().equals("Hex")) {
					temp = "";
					display2.setText("0.0");
				}
				error.setText("F");
				calc.setMode(".0");
				error.setBackground(Color.YELLOW);
				for (int i = 0; i < 6; i++) {
					hexButtons[i].setEnabled(false);
				}
				dotButton.setEnabled(true); // enable dotButton
				dotButton.setBackground(Color.magenta);

			} else if (".00".equals(actionCommand) && calc.getErrorState() == false) { // checks for float mode .00
				if (calc.getMode().equals("Hex")) { // if Hex is selected
					temp = "";
					display2.setText("0.0");
				}
				error.setText("F");
				calc.setMode(".00");
				error.setBackground(Color.YELLOW);
				for (int i = 0; i < 6; i++) {
					hexButtons[i].setEnabled(false);
				}
				dotButton.setEnabled(true); // enable dotButton
				dotButton.setBackground(Color.magenta);

			} else if ("Hex".equals(actionCommand) && calc.getErrorState() == false) { // checks for Hexadecimal mode
				temp = "";
				display2.setText("0");
				calc.setMode("Hex");
				error.setText("H");
				error.setBackground(Color.green);
				for (int i = 0; i < 6; i++) {
					hexButtons[i].setEnabled(true);
				}
				dotButton.setEnabled(false); // disable dotButton
				dotButton.setBackground(new Color(178, 156, 250));

			} else if ("Sci".equals(actionCommand) && calc.getErrorState() == false) { // checks for scientific mode
				if (calc.getMode().equals("Hex")) { // if mode changes to Hex
					temp = "";
					display2.setText("0.0");
				}
				calc.setMode(actionCommand);
				error.setText("F");
				error.setBackground(Color.YELLOW);
				dotButton.setEnabled(true); // enable dotButton
				dotButton.setBackground(Color.magenta);
				for (int i = 0; i < 6; i++) {
					hexButtons[i].setEnabled(false);
				}
			}

			if ("+".equals(actionCommand) && calc.getErrorState() == false) { // check if addition
				display1.setText(actionCommand);
				calc.setArithmeticOp("+");

				if (!temp.isEmpty()) { // checks if String is not empty
					calc.setOperand1(temp); // sets it to both operands
					calc.setOperand2(temp);
				}
				display1.setText(calc.getOperand1() + "+");
				temp = "";

			} else if ("-".equals(actionCommand) && calc.getErrorState() == false) { // checks for subtraction
				display1.setText(actionCommand);
				calc.setArithmeticOp("-");
				if (!temp.isEmpty()) {
					calc.setOperand1(temp);
					calc.setOperand2(temp);
				}
				display1.setText(calc.getOperand1() + "-");
				temp = "";
			} else if ("*".equals(actionCommand) && calc.getErrorState() == false) { // checks for multiplication
				display1.setText(actionCommand);
				calc.setArithmeticOp("*");
				if (!temp.isEmpty()) {
					calc.setOperand1(temp);
					calc.setOperand2(temp);
				}
				display1.setText(calc.getOperand1() + "*");
				temp = "";

			} else if ("/".equals(actionCommand) && calc.getErrorState() == false) { // checks for division
				display1.setText(actionCommand);
				calc.setArithmeticOp("/");
				if (!temp.isEmpty()) {
					calc.setOperand1(temp);
					calc.setOperand2(temp);
				}
				display1.setText(calc.getOperand1() + "/");
				temp = "";

			} else if ("=".equals(actionCommand) && calc.getErrorState() == false) { // get the total for arithmetic
																						// operation
				if (calc.getTotal().equals("")) { // if only = button is clicked , do nothing
					temp = "";
					return;
				}

				if (calc.getTotal().equals("/") && temp.equals("0")) { // if any number is divided by
																		// zero , generate
					if (calc.getOperand1().equals("0")) {
						display2.setText("Result is undefined");
					} else {
						display2.setText("cannot divide by zero");
					}
					error.setText("E");
					error.setBackground(Color.RED);
					calc.setErrorState(true);

				}

				else {
					if (!temp.isEmpty()) {
						calc.setOperand2(temp);
					}
					if (calc.getMode().equals("Hex")) {
						display1.setText("");
						display2.setText(Integer.toHexString(Integer.parseInt(calc.precision())));
						calc.setOperand1(Integer.toHexString(Integer.parseInt(calc.precision())));
						temp = "";
					} else {
						display1.setText("");
						display2.setText((calc.precision()));
						calc.setOperand1(calc.precision());
						temp = "";
					}
				}

			} else if ("clear".equals(actionCommand)) { // calls reset method

				if (calc.getMode().equals("Hex")) {
					calc.reset();
					display1.setText("");
					display2.setText("0");
					error.setText("F");
					error.setBackground(Color.YELLOW);
					temp = "";
				}

				else {
					calc.reset();
					display1.setText("");
					display2.setText("0");
					temp = "";
					error.setText("F");
					error.setBackground(Color.YELLOW);
					calc.setErrorState(false);
				}
			} else if ("\u21D0".equals(actionCommand) && calc.getErrorState() == false) { /* backSpace */
				StringBuilder tempB = new StringBuilder(temp);

				if (temp.length() > 0) {

					if (tempB.toString().length() == 1
							|| tempB.toString().length() == 2 && display2.getText().contains("-")) {
						display2.setText("0");
						temp = "";
					} else {
						tempB.deleteCharAt(temp.length() - 1); // delete chars when backSpace is pressed
						temp = tempB.toString();
						display2.setText(temp);
					}
				}

			} else if (".".equals(actionCommand) && calc.getErrorState() == false) { // checks for . button
				if (temp.contains(".")) {
					return;
				}

				temp += actionCommand;
				display2.setText(temp);

			} else if ("\u00B1".equals(actionCommand) && calc.getErrorState() == false) { /* plusMinus */
				if (temp.length() == 0) {
					display2.setText("0");
					display1.setText("negate(0)");
				}

				if (temp.length() > 0) {
					StringBuilder tempB = new StringBuilder(temp);
					if (temp.contains("-")) {
						tempB.deleteCharAt(0);
						temp = tempB.toString();
						display2.setText(temp);
					} else {
						temp = "-" + temp;
						display2.setText(temp);
					}
				}
			}

		}
	}
}