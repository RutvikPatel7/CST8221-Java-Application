/********************************************************************************************************************************************************
File name	: CalculatorModel
Author		: Rutvik Patel, Student #  :	040915445
Course	 	: CST8221-JAP, Lab Section : 	303
Assignment 	: Assignment 1	-	Part 2
Date		: March 6 2020
Professor	: Svillen Ranev
Purpose		: Model class that is responsible for getting the operands and all the calculations.
 ********************************************************************************************************************************************************/

package calculator;

import java.text.DecimalFormat;

public class CalculatorModel {

	private String operand1 = "";
	private String operand2 = "";
	private String mode = ".00";
	private String arithmeticOp = "";
	private boolean errorState = false;

	public void setOperand1(String op) { // sets operand1
		this.operand1 = op;
	}

	public void setOperand2(String op) { // sets operand2
		this.operand2 = op;
	}

	public String getOperand1() { // gets operand1
		return operand1;
	}

	public String getOperand2() { // gets operand2
		return operand2;
	}

	public String getTotal() { // get the total from calculate
		return arithmeticOp;
	}

	public boolean getErrorState() { // get the error state
		return errorState;
	}

	public void setErrorState(boolean state) { // set the error state
		this.errorState = state;
	}

	public void setMode(String mode) { // sets mode
		this.mode = mode;
	}

	public String getMode() { // get mode
		return mode;
	}

	public void setArithmeticOp(String arithmeticOp) { // set arithmetic operation (/, *, -, +)
		this.arithmeticOp = arithmeticOp;
	}

	private float calculate() { // returns the result of the calculations of operand1&2 based on the arithmetic
								// operation
		if (operand1.isEmpty()) {
			operand1 = "0";
		}
		if (operand2.isEmpty()) { // sets op2 to op1 if it's empty
			this.operand2 = this.operand1;
		}
		if (arithmeticOp.isEmpty()) { // checks if arithmetic is empty then convert op2 to float
			return converToFloat(operand2);
		}

		if (mode.equals("Hex")) {
			this.operand2 = String.valueOf(Integer.parseInt(this.operand2, 16));
			this.operand1 = String.valueOf(Integer.parseInt(this.operand1, 16));
		}

		switch (arithmeticOp) {

		case "+":
			return converToFloat(operand1) + converToFloat(operand2); // case addition
		case "-":
			return converToFloat(operand1) - converToFloat(operand2); // case subtraction
		case "*":
			return converToFloat(operand1) * converToFloat(operand2); // case multiplication
		case "/":
			return converToFloat(operand1) / converToFloat(operand2); // case division
		default:
		}
		return 0;
	} // end of calculate()

	public String precision() { // gets the format after calling calculate method based on the mode

		DecimalFormat format;

		if (mode.equals(".0")) {
			format = new DecimalFormat("#0.0");
			return format.format(calculate()); // calls calculate function and returns result based on mode

		} else if (mode.equals(".00")) {
			format = new DecimalFormat("#0.00");
			return format.format(calculate());
		} else if (("Hex".equals(mode))) {
			return String.valueOf(Math.round(calculate())); // calls Math Class to round the integer value
		} else if (mode.equals("Sci")) {
			format = new DecimalFormat("0.######E00");
			return format.format(calculate());
		}
		return "";
	} // end of precision()

	public void reset() { // resets the calculator
		operand1 = "";
		operand2 = "";
		arithmeticOp = "";
	} // end of reset

	public float converToFloat(String n) { // converts strings to float
		return Float.parseFloat(n);
	} // end of convertToFloat
} // end of CalculatorModel class