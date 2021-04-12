package fraction;

/**
 * Representing fractions of the form numerator and denominator
 */
public class FractionImpl implements Fraction {
    /**
     * Parameters are the <em>numerator</em> and the <em>denominator</em>.
     * The fraction is normalized as it is created.
     * For instance, if the parameters are <code>(8, -12)</code>, a <code>Fraction</code> is created with numerator
     * <code>-2</code> and denominator <code>3</code>.
     *
     * The constructor throws an <code>ArithmeticException</code> if the denominator is zero.
     *
     * @param numerator
     * @param denominator
     */
	 
	private final int numerator; 
	private final int denominator;

    /**
     * The parameter is the numerator and denominator.
     *
     * @param numerator the integer representing the numerator 
	 * @param denominator the integer representing the denominator
     */	
    public FractionImpl(int numerator, int denominator) throws ArithmeticException, NumberFormatException {
		
		int tempNumerator;
		int tempDenominator;
		
		if (denominator == 0)
			throw new ArithmeticException("Division by zero error");
		else if (denominator == -2147483648)
			throw new NumberFormatException("integer overflow error");			
		else {		
			tempNumerator = numerator;
			tempDenominator = denominator;
		}
		
		int[] fractionCandidate = {tempNumerator, tempDenominator};
		fractionCandidate = improveFractionCandidate(fractionCandidate);
			
		this.numerator = fractionCandidate[0];
		this.denominator = fractionCandidate[1];
    }	

    /**
     * The parameter is the numerator and <code>1</code> is the implicit denominator.
     *
     * @param wholeNumber representing the numerator
     */
    public FractionImpl(int wholeNumber) {
		this.numerator = wholeNumber;
		this.denominator = 1;
    }

    /**
     * The parameter is a <code>String</code> containing either a whole number, such as '5' or '-3', or a fraction,
     * such as "8/-12". (Blanks are allowed around but not within integers.)
     *
     * @param fraction the string representation of the fraction
     */
    public FractionImpl(String fraction) throws ArithmeticException, NumberFormatException {
		fraction = fraction.trim();
		
		if (fraction.charAt(0) == '/')
			throw new NumberFormatException("Numerator required before a /");
		
		if (fraction.charAt(fraction.length()-1) == '/')
			throw new NumberFormatException("Denominator required after a /");
		
		String[] elements = fraction.split("/");
		String numerator;
		int tempNumerator = 0;
		int tempDenominator = 0;
		
		if (elements.length == 1){
			numerator = elements[0].trim();
			tempNumerator = Integer.parseInt(numerator);
			tempDenominator = 1;
		} else if (elements.length == 2){
			try {					
				numerator = elements[0].trim();
				String denominator = elements[1].trim();
				tempNumerator = Integer.parseInt(numerator);
				tempDenominator = Integer.parseInt(denominator);
			} catch (NumberFormatException e){
				throw new NumberFormatException("The numerator and denominator must be integers and cannot contain spaces");
			}
		} else {
			throw new NumberFormatException("You can only enter one numerator and one denominator separated by a slash");
		}
			
		if (tempDenominator == 0)
			throw new ArithmeticException("Division by zero error");
		else if (tempDenominator == -2147483648)
			throw new NumberFormatException("Integer overflow error");
		
		int[] fractionCandidate = {tempNumerator, tempDenominator};
		fractionCandidate = improveFractionCandidate(fractionCandidate);
		
		this.numerator = fractionCandidate[0];
		this.denominator = fractionCandidate[1];
    }

    /**
     * Returns a new <code>int[] array</code> where the fraction candidate has been reduced by dividing the numerator and denominator by their greatest common divisor
	 * and ensured the denominator is positive 
	 *
	 * @param fractionCandidate an int[] array representing the fraction to be improved 
     * @return the correctly reduced fraction candidate as an int array
     */		
	private static int[] improveFractionCandidate(int[] fractionCandidate){
		
		int tempNumerator = fractionCandidate[0];
		int tempDenominator = fractionCandidate[1];
		
		// Reduce the fraction by finding out greatest common divisor and dividing the numerator and denominator by it
		int divisor = getGreatestCommonDivisor(tempNumerator, tempDenominator);
		tempNumerator = tempNumerator / divisor;
		tempDenominator = tempDenominator / divisor;
		
		// Ensure positive denominator
		if (tempDenominator < 0){
			tempNumerator = 0 - tempNumerator;
			tempDenominator = 0 - tempDenominator;
		}
		
		// Set numerator and denominator
		if (tempNumerator == 0){
			fractionCandidate[0] = tempNumerator;
			fractionCandidate[1] = 1;
		}
		else {		
			fractionCandidate[0] = tempNumerator;
			fractionCandidate[1] = tempDenominator;
		}
		
		return fractionCandidate;
	}

    /**
     * Returns a new <code>int</code> that is the greatest common divisor of a and b using 
     * Euclid's algorithm
	 *
	 * @param a the denominator field in the object 
	 * @param b the numerator field in the object
     * @return the greatest common divisor of a and b
     */	
	private static int getGreatestCommonDivisor(int a, int b){
		int remainder = a % b;
		int quotient = a / b;
		while (remainder != 0){
			a = b;
			b = remainder;
			quotient = a / b;
			remainder = a - (b * quotient);
		}
		return b;
	}

    /**
     * {@inheritDoc}
     */
	 
    @Override
    public Fraction add(Fraction f) throws ArithmeticException {		
		FractionImpl that = new FractionImpl(f.toString());	

		// Early return for a 0 result
		if ((this.numerator + that.numerator) == 0 && this.denominator == that.denominator){
			Fraction fraction = new FractionImpl(0);
			return fraction;
		}		

		// Exception handling for very large numerators and denominators	
		if ((this.numerator > 0 && that.numerator > 0) && (this.numerator + that.numerator) < 0)
			throw new ArithmeticException("Integer overflow error");
		if ((this.numerator < 0 && that.numerator < 0) && (this.numerator + that.numerator) > 0)
			throw new ArithmeticException("Integer overflow error");
		if ((this.denominator > 0 && that.denominator > 0) && (this.denominator + that.denominator) < 0)
			throw new ArithmeticException("Integer overflow error");
		if ((this.denominator < 0 && that.denominator < 0) && (this.denominator + that.denominator) > 0)
			throw new ArithmeticException("Integer overflow error");
		if (multiplicationOverflows(this.numerator, that.denominator) || multiplicationOverflows(this.denominator, that.numerator) || multiplicationOverflows(this.denominator, that.denominator))
			throw new ArithmeticException("Integer overflow error");		
		
		
		// Implementation of fraction addition
		int newNumerator = (this.numerator * that.denominator)+(this.denominator * that.numerator);
		int newDenominator = this.denominator * that.denominator;		
		
		Fraction fraction = new FractionImpl(newNumerator, newDenominator);
		
		return fraction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fraction subtract(Fraction f) {
        FractionImpl that = new FractionImpl(f.toString());
		
		// Early return for a 0 result
		if (this.numerator == that.numerator && this.denominator == that.denominator){
			Fraction fraction = new FractionImpl(0);
			return fraction;
		}		
		
		// Exception handling for very large numerators and denominators
		if ((this.numerator > 0 && that.numerator < 0) && (this.numerator - that.numerator) < 0)
			throw new ArithmeticException("Integer overflow error");
		if ((this.numerator < 0 && that.numerator > 0) && (this.numerator - that.numerator) > 0)
			throw new ArithmeticException("Integer overflow error");
		
		if (multiplicationOverflows(this.numerator, that.denominator) || multiplicationOverflows(this.denominator, that.numerator) || multiplicationOverflows(this.denominator, that.denominator))
			throw new ArithmeticException("Integer overflow error");

		// Implementation of fraction subtraction
		int newNumerator = (this.numerator * that.denominator)-(this.denominator * that.numerator);
		int newDenominator = this.denominator * that.denominator;
		
		Fraction fraction = new FractionImpl(newNumerator, newDenominator);
		
		return fraction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fraction multiply(Fraction f) {
        FractionImpl that = new FractionImpl(f.toString());
		
		// Exception handling for very large numerators and denominators
		if (multiplicationOverflows(this.numerator, that.numerator) || multiplicationOverflows(this.denominator, that.denominator))
			throw new ArithmeticException("Integer overflow error");
		
		// Implementation of fraction multiplication
		int newNumerator = this.numerator * that.numerator;
		int newDenominator = this.denominator * that.denominator;
		
		Fraction fraction = new FractionImpl(newNumerator, newDenominator);
        return fraction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fraction divide(Fraction f) {
        FractionImpl that = new FractionImpl(f.toString());
		
		// Exception handling for very large numerators and denominators				
		if (multiplicationOverflows(this.numerator, that.denominator) || multiplicationOverflows(this.denominator, that.numerator))
			throw new ArithmeticException("Integer overflow error");
		
		// Implementation of fraction division
		int newNumerator = this.numerator * that.denominator;
		int newDenominator = this.denominator * that.numerator;
		
		Fraction fraction = new FractionImpl(newNumerator, newDenominator);
        return fraction;
    }
	
	
	  /**
     * Returns a boolean to indicate whether the product of the parameters is larger than a 32-bit integer 
	 *
	 * @param a the first int 
	 * @param b the second int
     * @return whether or not the product overflows
     */		
	private static boolean multiplicationOverflows(int a, int b){
		int prod = a * b;
		return b != 0 && (prod / b) != a;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public Fraction abs() throws ArithmeticException {
		if (numerator == -2147483648)
			throw new ArithmeticException("Integer overflow error"); 

		Fraction fraction;
		
		if (numerator < 0){
			fraction = new FractionImpl(0 - numerator, denominator);
		} else {
			fraction = new FractionImpl(numerator, denominator);
		}
		
		return fraction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fraction negate() throws ArithmeticException {
		if (numerator == -2147483648)
			throw new ArithmeticException("Integer overflow error");
		
		Fraction negated = new FractionImpl(0 - numerator, denominator);		
        return negated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
		if (obj instanceof Fraction){
			FractionImpl that = new FractionImpl(obj.toString()); 			
			return this.numerator == that.numerator && this.denominator == that.denominator;			
		} else {
			return false;
		}	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fraction inverse() {
		if (numerator == -2147483648)
			throw new ArithmeticException("Integer overflow error");
		
		Fraction fraction = new FractionImpl(denominator, numerator);
        return fraction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Fraction o) {
		FractionImpl that = new FractionImpl(o.toString());
		
		if (this.equals(that))
			return 0;
		
		double thisDouble = (double)this.numerator / this.denominator;
		double thatDouble = (double)that.numerator / that.denominator;
		
		if (thisDouble > thatDouble)
			return 1;
		else 
			return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
		if (denominator == 1)
			return numerator + "";
		else 
			return numerator + "/" + denominator;
    }
}