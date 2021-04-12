package fraction;

import junit.framework.TestCase;
import java.io.*;
import java.util.*;

public class FractionImplTest extends TestCase {	
	public void testCreateFromInts(){
		Fraction fraction = new FractionImpl(2, 3);
		assertEquals("2/3", fraction.toString());
	}
	
	public void testCreateFromSingleInt(){
		Fraction fraction = new FractionImpl(5);
		assertEquals("5", fraction.toString());
	}
	
	public void testCreateFromString(){
		Fraction fraction = new FractionImpl("2/3");
		assertEquals("2/3", fraction.toString());
		fraction = new FractionImpl (" 2 / 3 ");
		assertEquals("2/3", fraction.toString());		
		fraction = new FractionImpl("   4   ");
		assertEquals("4", fraction.toString());		
	}
	
	public void testDivisionByZero(){
		try {
			Fraction fraction1 = new FractionImpl("3/0");
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Division by zero error");
		}
		
		try {
			Fraction fraction2 = new FractionImpl(3, 0);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Division by zero error");
		}		
	}
	
	public void testZero(){
		Fraction fraction = new FractionImpl(0);
		assertEquals("0", fraction.toString());
		
		Fraction fraction2 = new FractionImpl("0/50");
		assertEquals("0", fraction2.toString());
	}
	
	public void testNegativeFractions(){
		Fraction fraction = new FractionImpl("-1/3");
		assertEquals("-1/3", fraction.toString());
		
		fraction = new FractionImpl("1/-3");
		assertEquals("-1/3", fraction.toString());
		
		fraction = new FractionImpl("-7");
		assertEquals("-7", fraction.toString());
	}
	
	public void testIncorrectStringConstructor(){
		try {
			Fraction fraction = new FractionImpl(" 4 / 1 2");
			fail();
		} catch (NumberFormatException expected){
			assertEquals(expected.getMessage(), "The numerator and denominator must be integers and cannot contain spaces");
		}	

		try {
			Fraction fraction = new FractionImpl("- 4/12");
			fail();
		} catch (NumberFormatException expected){
			assertEquals(expected.getMessage(), "The numerator and denominator must be integers and cannot contain spaces");
		}		

		try {
			Fraction fraction = new FractionImpl("1/2/3");
			fail();
		} catch (NumberFormatException expected){
			assertEquals(expected.getMessage(), "You can only enter one numerator and one denominator separated by a slash");
		}
	}
	
	public void testNoDenominator(){
		try {
			Fraction fraction = new FractionImpl("3/");
			fail();
		} catch (NumberFormatException expected){
			assertEquals(expected.getMessage(), "Denominator required after a /");
		}			
	}
	
	public void testNoNumerator(){
		try {
			Fraction fraction = new FractionImpl("/3");
			fail();
		} catch (NumberFormatException expected){
			assertEquals(expected.getMessage(), "Numerator required before a /");
		}					
	}
	
	public void testAbs(){
		Fraction fraction1 = new FractionImpl("-4/5");
		Fraction fraction2 = fraction1.abs();
		assertEquals("4/5", fraction2.toString());
		
		Fraction fraction3 = fraction2.abs();
		assertEquals("4/5", fraction3.toString());
	}
	
	public void testAbsException(){
		Fraction fraction = new FractionImpl(-2147483648);
		
		try {
			Fraction fraction2 = fraction.abs();
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}				
	}
	
	public void testInverse(){
		Fraction fraction1 = new FractionImpl("2/5");
		Fraction fraction2 = fraction1.inverse();
		assertEquals("5/2", fraction2.toString());
		
		Fraction fraction3 = new FractionImpl("1/5");
		assertEquals("5", fraction3.inverse().toString());
	}
	
	public void testInverseException(){
		Fraction fraction = new FractionImpl("0/6");
		
		try {
			Fraction fraction2 = fraction.inverse();
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Division by zero error");
		}		
		
		fraction = new FractionImpl(-2147483648);
		
		try {
			Fraction fraction2 = fraction.inverse();
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}
	}
	
	public void testNegate(){
		Fraction fraction = new FractionImpl("2/5");
		Fraction fraction2 = fraction.negate();
		assertEquals("-2/5", fraction2.toString());
		
		Fraction fraction3 = fraction2.negate();
		assertEquals("2/5", fraction3.toString());
	}
	
	public void testNegateException(){
		Fraction fraction = new FractionImpl(-2147483648);
		
		try {
			Fraction fraction2 = fraction.negate();
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}		
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
		
		fraction = new FractionImpl(1);
		fraction2 = new FractionImpl(-2);
		fraction3 = fraction.add(fraction2);
		assertEquals("-1", fraction3.toString());		
	}
	
	public void testAdditionException(){
		Fraction fraction1 = new FractionImpl(2147483647);
		Fraction fraction2 = new FractionImpl(2);
		
		try {
			Fraction fraction3 = fraction1.add(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}				

		fraction1 = new FractionImpl("1/2147483647");
		fraction2 = new FractionImpl(2147483647);
		
		try {
			Fraction fraction3 = fraction1.add(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}	

		fraction1 = new FractionImpl(-2147483648);
		fraction2 = new FractionImpl(-5);
		
		try {
			Fraction fraction3 = fraction1.add(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}	

		fraction1 = new FractionImpl("1/-2147483642");
		fraction2 = new FractionImpl("4");
		
		try {
			Fraction fraction3 = fraction1.add(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}			
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
		
		fraction = new FractionImpl("-1");
		fraction2 = new FractionImpl("-2");
		fraction3 = fraction.subtract(fraction2);
		assertEquals("1", fraction3.toString());
		
		fraction = new FractionImpl("1/2147483647");
		fraction2 = fraction.subtract(fraction);
		assertEquals("0", fraction2.toString());
	}
	
	public void testSubtractionException(){
		Fraction fraction1 = new FractionImpl(-2147483648);
		Fraction fraction2 = new FractionImpl(5);
		
		try {
			Fraction fraction3 = fraction1.subtract(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}			
		
		fraction1 = new FractionImpl(2147483647);
		fraction2 = new FractionImpl(-20);		
		
		try {
			Fraction fraction3 = fraction1.subtract(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}	

		fraction1 = new FractionImpl("1/2147483647");
		fraction2 = new FractionImpl(20);		

		try {
			Fraction fraction3 = fraction1.subtract(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}	

		fraction1 = new FractionImpl("1/2147483647");
		fraction2 = new FractionImpl("-1/2147483647");		

		try {
			Fraction fraction3 = fraction1.subtract(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}			
	}
	
	public void testMultiply(){
		Fraction fraction = new FractionImpl("1/2");
		assertEquals("1/4", fraction.multiply(fraction).toString());
		
		Fraction fraction2 = new FractionImpl("0");
		Fraction fraction3 = fraction.multiply(fraction2);
		assertEquals("0", fraction3.toString());
		
		fraction = new FractionImpl("2147483647");
		fraction2 = new FractionImpl("0");
		fraction3 = fraction.multiply(fraction2);
		assertEquals("0", fraction3.toString());	

		fraction = new FractionImpl("-2147483648");
		fraction2 = new FractionImpl("0");
		fraction3 = fraction.multiply(fraction2);
		assertEquals("0", fraction3.toString());			
	}
	
	public void testMultiplicationExceptions(){
		Fraction fraction1 = new FractionImpl(2147483647);
		Fraction fraction2 = new FractionImpl(2);
		
		try {
			Fraction fraction3 = fraction1.multiply(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}		

		fraction1 = new FractionImpl(-2147483648);
		fraction2 = new FractionImpl(2);
		
		try {
			Fraction fraction3 = fraction1.multiply(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}				
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
		
		fraction = new FractionImpl(1073741823);
		fraction2 = new FractionImpl("1/2");
		fraction3 = fraction.divide(fraction2);
		assertEquals("2147483646", fraction3.toString());
	}
	
	public void testDivisionExceptions(){
		Fraction fraction = new FractionImpl(2147483647);
		Fraction fraction2 = new FractionImpl("1/2");
		
		try {
			Fraction fraction3 = fraction.divide(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}		
		
		fraction = new FractionImpl(-2147483648);

		try {
			Fraction fraction3 = fraction.divide(fraction2);
			fail();
		} catch (ArithmeticException expected){
			assertEquals(expected.getMessage(), "Integer overflow error");
		}				
		
		fraction = new FractionImpl(0);
		fraction2 = new FractionImpl(2);
		
		try {
			Fraction fraction3 = fraction2.divide(fraction);
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
		
		fraction1 = new FractionImpl("5/20");
		fraction2 = new FractionImpl("1/4");
		assertTrue(fraction1.equals(fraction2));
	}
	
	public void testCompareTo(){
		Fraction fraction1 = new FractionImpl("1/2");
		Fraction fraction2 = new FractionImpl("1/2");
		int comparison = fraction1.compareTo(fraction2);
		assertEquals(0, comparison);
		
		fraction2 = new FractionImpl("1");
		comparison = fraction1.compareTo(fraction2);
		assertEquals(-1, comparison);
		
		comparison = fraction2.compareTo(fraction1);
		assertEquals(1, comparison);

		fraction1 = new FractionImpl("1");
		fraction2 = new FractionImpl("-1");
		comparison = fraction1.compareTo(fraction2);
		assertEquals(1, comparison);
		
		fraction1 = new FractionImpl("1/2");
		fraction2 = new FractionImpl("-1/2");
		comparison = fraction1.compareTo(fraction2);
		assertEquals(1, comparison);
		
		fraction1 = new FractionImpl("1/2");
		fraction2 = new FractionImpl("1/4");
		comparison = fraction1.compareTo(fraction2);
		assertEquals(1, comparison);
	}
	
	// The following tests confirm indirectly that the improveFractionCandidate helper method works as expected
	public void testCreateAndReduce(){
		FractionImpl fraction = new FractionImpl(48, 60);
		assertEquals("4/5", fraction.toString());
		
		fraction = new FractionImpl("25/5");
		assertEquals("5", fraction.toString());

		fraction = new FractionImpl("-3/-12");
		assertEquals("1/4", fraction.toString());	

		fraction = new FractionImpl(2, 2147483646);
		assertEquals("1/1073741823", fraction.toString());
	}
	
	public void testEnsurePositiveDenominator(){
		FractionImpl fraction = new FractionImpl("-1/-3");
		assertEquals("1/3", fraction.toString());
		
		fraction = new FractionImpl("1/-3");
		assertEquals("-1/3", fraction.toString());
		
		fraction = new FractionImpl("2/-2147483646");
		assertEquals("-1/1073741823", fraction.toString());
	}
	
	// I am commenting the following methods out because I want the these helper methods to be private but I did want to test them directly
	// public void testGetGreatestCommonDivisor(){
		// assertEquals(5, FractionImpl.getGreatestCommonDivisor(5, 20));
		// assertEquals(17, FractionImpl.getGreatestCommonDivisor(731, 2431));
		// assertEquals(4, FractionImpl.getGreatestCommonDivisor(4, 4));
		// assertEquals(2, FractionImpl.getGreatestCommonDivisor(2, 2147483646));
	// }		
	
	// public void testMultiplicationOverflow(){
		// int a = 4;
		// int b = 2;
		// assertFalse(FractionImpl.multiplicationOverflows(a, b));
		
		// int c = 2147483646;
		// int d = 5;
		// assertTrue(FractionImpl.multiplicationOverflows(c, d));
	// }
}