package fraction;

import junit.framework.TestCase;
import java.io.*;
import java.util.*;

public class FractionImplTest extends TestCase {	
	public void testCreateFromInts(){
		FractionImpl fraction = new FractionImpl(2, 3);
		assertEquals("2/3", fraction.toString());
	}
	
	public void testCreateFromSingleInt(){
		FractionImpl fraction = new FractionImpl(5);
		assertEquals("5", fraction.toString());
	}
	
	public void testCreateFromString(){
		FractionImpl fraction = new FractionImpl("2/3");
		assertEquals("2/3", fraction.toString());
		fraction = new FractionImpl (" 2 / 3 ");
		assertEquals("2/3", fraction.toString());		
		fraction = new FractionImpl("   4   ");
		assertEquals("4", fraction.toString());		
	}
	
	public void testDivisionByZero(){
		try {
			FractionImpl fraction1 = new FractionImpl("3/0");
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Division by zero error");
		}
		
		try {
			FractionImpl fraction2 = new FractionImpl(3, 0);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Division by zero error");
		}		
	}
	
	public void testZero(){
		FractionImpl fraction = new FractionImpl(0);
		assertEquals("0", fraction.toString());
		
		FractionImpl fraction2 = new FractionImpl("0/50");
		assertEquals("0", fraction2.toString());
	}
	
	public void testNegativeFractions(){
		FractionImpl fraction = new FractionImpl("-1/3");
		assertEquals("-1/3", fraction.toString());
		
		fraction = new FractionImpl("1/-3");
		assertEquals("-1/3", fraction.toString());
		
		fraction = new FractionImpl("-7");
		assertEquals("-7", fraction.toString());
	}
	
	public void testIncorrectStringConstructor(){
		try {
			FractionImpl fraction = new FractionImpl(" 4 / 1 2");
			fail();
		} catch (NumberFormatException expected){
			assertEquals(expected.getMessage(), "Cannot have spaces within the numerator or denominator");
		}		
	}
	
	public void testAbs(){
		Fraction fraction1 = new FractionImpl("-4/5");
		Fraction fraction2 = fraction1.abs();
		assertEquals("4/5", fraction2.toString());
		
		Fraction fraction3 = fraction2.abs();
		assertEquals("4/5", fraction3.toString());
	}
	
	public void testInverse(){
		Fraction fraction1 = new FractionImpl("2/5");
		Fraction fraction2 = fraction1.inverse();
		assertEquals("5/2", fraction2.toString());
		
		Fraction fraction3 = new FractionImpl("1/5");
		assertEquals("5", fraction3.inverse().toString());
		
		Fraction fraction4 = new FractionImpl("0/6");
		
		try {
			Fraction fraction5 = fraction4.inverse();
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Division by zero error");
		}
	}
	
	public void testNegate(){
		Fraction fraction = new FractionImpl("2/5");
		Fraction fraction2 = fraction.negate();
		assertEquals("-2/5", fraction2.toString());
		
		Fraction fraction3 = fraction2.negate();
		assertEquals("2/5", fraction3.toString());
	}
	
	public void testAdd(){
		Fraction fraction = new FractionImpl("1/2");
		assertEquals("1", fraction.add(fraction).toString());
		
		fraction = new FractionImpl("1/4");
		Fraction fraction2 = new FractionImpl("1/2");
		Fraction fraction3 = fraction.add(fraction2);
		assertEquals("3/4", fraction3.toString());
		
		fraction = new FractionImpl("-1/2");
		fraction2 = new FractionImpl("1/2");
		fraction3 = fraction.add(fraction2);
		assertEquals("0", fraction3.toString());
		
		fraction = new FractionImpl(5, 2);
		fraction2 = new FractionImpl(4);
		fraction3 = fraction.add(fraction2);
		assertEquals("13/2", fraction3.toString());
		
		fraction2 = new FractionImpl("0");
		fraction3 = fraction.add(fraction2);
		assertEquals("5/2", fraction3.toString());
	}
	
	public void testSubtract(){
		Fraction fraction = new FractionImpl("1/2");
		assertEquals("0", fraction.subtract(fraction).toString());
		
		Fraction fraction2 = new FractionImpl("1/4");
		Fraction fraction3 = fraction.subtract(fraction2);
		assertEquals("1/4", fraction3.toString());
		
		fraction = new FractionImpl("5/4");
		fraction2 = new FractionImpl("1/4");
		fraction3 = fraction.subtract(fraction2);
		assertEquals("1", fraction3.toString());
		
		fraction2 = new FractionImpl("0");
		fraction3 = fraction.subtract(fraction2);
		assertEquals("5/4", fraction3.toString());
	}
	
	public void testMultiply(){
		Fraction fraction = new FractionImpl("1/2");
		assertEquals("1/4", fraction.multiply(fraction).toString());
		
		Fraction fraction2 = new FractionImpl("0");
		Fraction fraction3 = fraction.multiply(fraction2);
		assertEquals("0", fraction3.toString());
	}
	
	public void testDivide(){
		Fraction fraction = new FractionImpl("1");
		Fraction fraction2 = new FractionImpl("2");
		Fraction fraction3 = fraction.divide(fraction2);
		assertEquals("1/2", fraction3.toString());
		
		fraction = new FractionImpl("1/4");
		fraction2 = new FractionImpl("3/4");
		fraction3 = fraction.divide(fraction2);
		assertEquals("1/3", fraction3.toString());
		
		fraction = new FractionImpl("0");
		fraction2 = new FractionImpl("1/4");
		fraction3 = fraction.divide(fraction2);
		assertEquals("0", fraction3.toString());
		
		try {
			fraction3 = fraction2.divide(fraction);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Division by zero error");
		}
	}
	
	public void testEquals(){
		Fraction fraction1 = new FractionImpl("1/2");
		Fraction fraction2 = new FractionImpl("1/2");
		assertTrue(fraction1.equals(fraction2));
		
		fraction2 = new FractionImpl("1/3");
		assertFalse(fraction1.equals(fraction2));
		
		String test = "1/3";
		assertFalse(test.equals(fraction2)); 
	}
	
	public void testCompareTo(){
		Fraction fraction1 = new FractionImpl("1/2");
		Fraction fraction2 = new FractionImpl("1/2");
		int comparison = fraction1.compareTo(fraction2);
		assertEquals(0, comparison);
		
		fraction2 = new FractionImpl("1");
		comparison = fraction1.compareTo(fraction2);
		assertEquals(1, comparison);
		
		comparison = fraction2.compareTo(fraction1);
		assertEquals(-1, comparison);
	}
	
	// reduceFraction and ensurePositiveDenominator update the fields of a FractionImpl object but I don't want to write 
	// getters for the numerator and denominator fields just to test two small helper methods - the below tests show that they work as expected
	public void testCreateAndReduce(){
		FractionImpl fraction = new FractionImpl(48, 60);
		assertEquals("4/5", fraction.toString());
		
		fraction = new FractionImpl("25/5");
		assertEquals("5", fraction.toString());

		FractionImpl fraction = new FractionImpl("-3/-12");
		assertEquals("1/4", fraction.toString());		
	}
	
	public void testEnsurePositiveDenominator(){
		FractionImpl fraction = new FractionImpl("-1/-3");
		assertEquals("1/3", fraction.toString());
		
		fraction = new FractionImpl("1/-3");
		assertEquals("-1/3", fraction.toString());
	}
	
	// The below tests are commented out because I have made the getGreatestCommonDivisor static method private in FractionImpl.
	// To test, remove the private access modifier in FractionImpl and uncomment lines 236 to 240 below

	// public void testGetGreatestCommonDivisor(){
		// assertEquals(5, FractionImpl.getGreatestCommonDivisor(5, 20));
		// assertEquals(17, FractionImpl.getGreatestCommonDivisor(731, 2431));
		// assertEquals(4, FractionImpl.getGreatestCommonDivisor(4, 4));
	// }	
}