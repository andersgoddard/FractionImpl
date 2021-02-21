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
	
	public void testCreateAndReduce(){
		FractionImpl fraction = new FractionImpl(48, 60);
		assertEquals("4/5", fraction.toString());
		
		FractionImpl fraction2 = new FractionImpl("25/5");
		assertEquals("5", fraction2.toString());
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
		
		FractionImpl fraction2 = new FractionImpl("1/-3");
		assertEquals("-1/3", fraction2.toString());
		
		FractionImpl fraction3 = new FractionImpl("-3/-12");
		assertEquals("1/4", fraction3.toString());
		
		FractionImpl fraction4 = new FractionImpl("-7");
		assertEquals("-7", fraction4.toString());
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
}

