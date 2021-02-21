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
	
	// Need to write a test for assertThrows

}

