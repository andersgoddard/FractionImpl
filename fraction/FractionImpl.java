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
    public FractionImpl(int numerator, int denominator) throws ArithmeticException {
		
		int tempNumerator;
		int tempDenominator;
		
		if (denominator == 0)
			throw new ArithmeticException("division by zero error");
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
		String[] elements = fraction.split("/");
		String numerator;
		int tempNumerator = 0;
		int tempDenominator = 0;
		
		try {
			if (elements.length == 1){
				numerator = elements[0].trim();
				tempNumerator = Integer.parseInt(numerator);
				tempDenominator = 1;
			} else if (elements.length == 2){
				numerator = elements[0].trim();
				String denominator = elements[1].trim();
				tempNumerator = Integer.parseInt(numerator);
				tempDenominator = Integer.parseInt(denominator);
			} 	
			
			if (tempDenominator == 0)
				throw new ArithmeticException("division by zero error");
			
			int[] fractionCandidate = {tempNumerator, tempDenominator};
			fractionCandidate = improveFractionCandidate(fractionCandidate);
			
			this.numerator = fractionCandidate[0];
			this.denominator = fractionCandidate[1];

		} catch (NumberFormatException e) {
			throw new NumberFormatException("Cannot have spaces within the numerator or denominator");
		}
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
    public Fraction add(Fraction f) {
		FractionImpl that = new FractionImpl(f.toString());
		
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
		
		int newNumerator = this.numerator * that.denominator;
		int newDenominator = this.denominator * that.numerator;
		
		Fraction fraction = new FractionImpl(newNumerator, newDenominator);
        return fraction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fraction abs() {
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
    public Fraction negate() {
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
		
		double thisDouble = this.numerator / this.denominator;
		double thatDouble = that.numerator / that.denominator;
		
		if (thatDouble > thisDouble)
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