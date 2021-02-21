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
		assertEquals("5/1", fraction.toString());
	}
	
	public void testCreateFromString(){
		FractionImpl fraction = new FractionImpl("2/3");
		assertEquals("2/3", fraction.toString());
		fraction = new FractionImpl (" 2 / 3 ");
		assertEquals("2/3", fraction.toString());		
		fraction = new FractionImpl("   4   ");
		assertEquals("4/1", fraction.toString());		
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
		assertEquals("5/1", fraction2.toString());
	}
}

