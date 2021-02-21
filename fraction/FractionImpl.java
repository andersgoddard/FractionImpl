package fraction;

public class FractionImpl implements Fraction {
    /**
     * Parameters are the <em>numerator</em> and the <em>denominator</em>.
     * Normalize the fraction as you create it.
     * For instance, if the parameters are <pre>(8, -12)</pre>, create a <pre>Fraction</pre> with numerator
     * <pre>-2</pre> and denominator <pre>3</pre>.
     *
     * The constructor should throw an <pre>ArithmeticException</pre> if the denominator is zero.
     *
     * @param numerator
     * @param denominator
     */
	 
	private int numerator; 
	private int denominator;
	 
    public FractionImpl(int numerator, int denominator) throws ArithmeticException {
		if (denominator == 0)
			throw new ArithmeticException("Division by zero error");
		else {		
        this.numerator = numerator;
		this.denominator = denominator;
		}
		
		reduceFraction();
		dealWithNegativeDenominator();		
    }

    /**
     * The parameter is the numerator and <pre>1</pre> is the implicit denominator.
     *
     * @param wholeNumber representing the numerator
     */
    public FractionImpl(int wholeNumber) {
        this.numerator = wholeNumber;
		this.denominator = 1;
    }

    /**
     * The parameter is a <pre>String</pre> containing either a whole number, such as `5` or `-3`, or a fraction,
     * such as "8/-12".
     * Allow blanks around (but not within) integers.
     * The constructor should throw an <pre>ArithmeticException</pre>
     * if given a string representing a fraction whose denominator is zero.
     * <p>
     * You may find it helpful to look at the available String API methods in the Java API.
     *
     * @param fraction the string representation of the fraction
     */
    public FractionImpl(String fraction) throws ArithmeticException {
        fraction = fraction.trim();
		String[] elements = fraction.split("/");
		String numerator;
		
		try {
			if (elements.length == 1){
				numerator = elements[0].trim();
				this.numerator = Integer.parseInt(numerator);
				this.denominator = 1;
			} else if (elements.length == 2){
				numerator = elements[0].trim();
				String denominator = elements[1].trim();
				this.numerator = Integer.parseInt(numerator);
				this.denominator = Integer.parseInt(denominator);
			}		
			
			if (denominator == 0)
				throw new ArithmeticException("Division by zero error");
			
			reduceFraction();
			dealWithNegativeDenominator();
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Cannot have spaces within the numerator or denominator");
		}
    }

    /**
     * Divides the numerator and denominator in the instance of the class by the greatest common divisor
     */	
	private void reduceFraction(){
		if (numerator == 0)
			denominator = 1;
		else {
		int greatestCommonDivisor = getGreatestCommonDivisor(denominator, numerator);
		numerator = numerator / greatestCommonDivisor;
		denominator = denominator / greatestCommonDivisor;
		}
	}

    /**
     * Returns a new <pre>int</pre> that is the greatest common divisor of a and b using 
     * Euclid's algorithm
	 *
	 * @param the integers a and b that we need the greatest common divisor of
     * @return the greatest common divisor of a and b
     */	
	private int getGreatestCommonDivisor(int a, int b){
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
     * Checks if the denominator is less than 0 and, if so, reassigns the numerator and denominator to 0 subtracted by itself
	 * e.g. numerator of -1 and denominator of -3 becomes 1 and 3 respectively
     */		
	private void dealWithNegativeDenominator(){
		if (denominator < 0){
			numerator = 0 - numerator;
			denominator = 0 - denominator;
		}
	}

    /**
     * @inheritDoc
     */
    @Override
    public Fraction add(Fraction f) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction subtract(Fraction f) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction multiply(Fraction f) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction divide(Fraction f) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction abs() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction inverse() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Fraction o) {
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
		if (numerator == 0)
			return "0";
		else 
			return numerator + "/" + denominator;
    }
}


// Methods
// The following lists the non-constructor methods that should be implemented.
// In what follows, this is sometimes denoted by a/b, and an argument f is sometimes denoted by c/d.

// public Fraction add(Fraction f)	Returns a new Fraction that is the sum of this and f:
// a/b + c/d is (ad + bc)/bd

// public Fraction subtract(Fraction f)	Returns a new Fraction that is the difference of this minus f:
// a/b - c/d is (ad - bc)/bd

// public Fraction multiply(Fraction f)	Returns a new Fraction that is the product of this and f:
// (a/b) * (c/d) is (a*c)/(b*d)

// public Fraction divide(Fraction f)	Returns a new Fraction that is the quotient of dividing this by f:
// (a/b) / (c/d) is (a*d)/(b*c)

// public Fraction abs()	Returns a new Fraction that is the absolute value of this fraction.

// public Fraction negate()	Returns a new Fraction that has the same numeric value of this fraction, but the opposite sign.

// public Fraction inverse()	The inverse of a/b is b/a.

// @Override
// public boolean equals(Object o)	Returns true if o is a Fraction equal to this, and false in all other cases.

// @Override
// public int compareTo(Fraction o)	This method returns:
// A negative int if this is less than o.
// Zero if this is equal to o.
// A positive int if this is greater than o.
// 
//@Override
// public String toString()	Returns a String of the form n/d, where n is the numerator and d is the denominator.
// However, if d is 1, just return n (as a String).
// The returned String should not contain any blanks.
// If the fraction represents a negative number, a minus sign should precede n.
// This should be one of the first methods you write, because it will help you in debugging.
// Note that, for the purposes of this assignment, there is no need to implement the related clone() and hashCode() methods.