import java.io.*;
import java.util.*;
import fraction.*;

public class FractionPractice {
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String s = "";
		printInstructions();
		Fraction fraction1;
		Fraction fraction2;
		
		while (true){
			try {
			System.out.println("Please provide your first fraction.");
			s = in.nextLine();
			fraction1 = getFraction(s);

			System.out.println("Please provide your second fraction.");
			s = in.nextLine();
			fraction2 = getFraction(s);	

			System.out.println();
			System.out.println("Your fractions are: " + fraction1.toString() + " and " + fraction2.toString());
			printCalculations(fraction1, fraction2);		
			break;
			} catch (NumberFormatException e){
				System.out.println("There is a problem with your inputs. " + e.getMessage() + ". Start again.");
			}
		}
	}
	
	private static void printInstructions(){
		System.out.println("This program creates fractions from user-provided numerators and denominators and provides a series of calculations.");
		System.out.println("There are 3 ways to create a fraction here:");
		System.out.println("1. Provide two integers separated by one space - e.g. \"1 2\" for 1/2");
		System.out.println("2. Provide one integer (for a whole number) - e.g. \"4\" for 4");
		System.out.println("3. Enter the fraction manually, separated by a / symbol - e.g. \"1/2\" for 1/2");
		System.out.println("The inputs should not contain spaces between digits and the denominator should not be zero");
		System.out.println("The range of integer values is -2147483648 to +2147483647");
		System.out.println("Explore fractions at the extremes of that range but expect exceptions :)");
		System.out.println();
	}
	
	private static Fraction getFraction(String s){
			Fraction fraction = null;
			
			if (s.indexOf('/') != -1){
				fraction = new FractionImpl(s);
			} else if (s.indexOf(' ') == -1){
				int param = Integer.parseInt(s);
				fraction = new FractionImpl(param);
			} else {
				s = s.trim();
				String[] elements = s.split(" ");
				if (elements.length >= 2 && (elements[0] == "" || elements[1] == ""))
					throw new NumberFormatException("Separate your numerator and denominator with one space");					
				else if (elements.length > 2)
					throw new NumberFormatException("You can only enter one numerator and one denominator separated by one space");
				else if (elements.length == 2) {
					fraction = new FractionImpl(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
				} else {
					fraction = new FractionImpl(Integer.parseInt(elements[0]));
				}
			}				
			return fraction;
	}
	
	private static void printCalculations(Fraction fraction1, Fraction fraction2){
		Fraction fractionCalculator;
		
		try {
		fractionCalculator = fraction1.add(fraction2);
		System.out.println(fraction1.toString() + " + " + fraction2.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println(fraction1.toString() + " + " + fraction2.toString() + " causes the following error: " + e.getMessage());
		}

		try {		
		fractionCalculator = fraction1.subtract(fraction2);
		System.out.println(fraction1.toString() + " - " + fraction2.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println(fraction1.toString() + " - " + fraction2.toString() + " causes the following error: " + e.getMessage());
		}		

		try {		
		fractionCalculator = fraction1.multiply(fraction2);
		System.out.println(fraction1.toString() + " * " + fraction2.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println(fraction1.toString() + " * " + fraction2.toString() + " causes the following error: " + e.getMessage());
		}		
		
		try {
		fractionCalculator = fraction1.divide(fraction2);
		System.out.println(fraction1.toString() + " / " + fraction2.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println(fraction1.toString() + " / " + fraction2.toString() + " causes the following error: " + e.getMessage());
		}
		
		try {
		fractionCalculator = fraction1.abs();
		System.out.println("The absolute of " + fraction1.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println("The absolute of " + fraction1.toString() + " causes the following error: " + e.getMessage());
		}

		try {
		fractionCalculator = fraction2.abs();
		System.out.println("The absolute of " + fraction2.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println("The absolute of " + fraction2.toString() + " causes the following error: " + e.getMessage());
		}

		try {		
		fractionCalculator = fraction1.negate();
		System.out.println(fraction1.toString() + " negated = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println(fraction1.toString() + " negated causes the following error: " + e.getMessage());
		}

		try {
		fractionCalculator = fraction2.negate();
		System.out.println(fraction2.toString() + " negated = " + fractionCalculator.toString());		
		} catch (ArithmeticException e) {
			System.out.println(fraction1.toString() + " negated causes the following error: " + e.getMessage());
		}

		try {
		fractionCalculator = fraction1.inverse();
		System.out.println("The inverse of " + fraction1.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println("The inverse of " + fraction1.toString() + " causes the following error: " + e.getMessage());
		}

		try {
		fractionCalculator = fraction2.inverse();
		System.out.println("The inverse of " + fraction2.toString() + " = " + fractionCalculator.toString());
		} catch (ArithmeticException e) {
			System.out.println("The inverse of " + fraction2.toString() + " causes the following error: " + e.getMessage());
		}		
		
		if (fraction1.equals(fraction2))
			System.out.println(fraction1.toString() + " is equal to " + fraction2.toString());
		else if (fraction1.compareTo(fraction2) < 0)
			System.out.println(fraction1.toString() + " is less than " + fraction2.toString());
		else 
			System.out.println(fraction1.toString() + " is greater than " + fraction2.toString());		
	}
	
}