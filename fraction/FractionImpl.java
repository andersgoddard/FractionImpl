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
		ensurePositiveDenominator();		
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
    public FractionImpl(String fraction) throws ArithmeticException, NumberFormatException {
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
			ensurePositiveDenominator();
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
	private void ensurePositiveDenominator(){
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
		FractionImpl that = new FractionImpl(f.toString());
		
		int newNumerator = (this.numerator * that.denominator)+(this.denominator * that.numerator);
		int newDenominator = this.denominator * that.denominator;
		
		Fraction fraction = new FractionImpl(newNumerator, newDenominator);
		
		return fraction;
    }

    /**
     * @inheritDoc
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
     * @inheritDoc
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
     * @inheritDoc
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
     * @inheritDoc
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
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        return new FractionImpl(0 - numerator, denominator);
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
		FractionImpl that = new FractionImpl(obj.toString()); 
		
        return this.numerator == that.numerator && this.denominator == that.denominator && obj instanceof Fraction; // should this instanceof check be done earlier in the method?
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
		Fraction fraction = new FractionImpl(denominator, numerator);
        return fraction;
    }

    /**
     * @inheritDoc
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
     * @inheritDoc
     */
    @Override
    public String toString() {
		if (denominator == 1)
			return numerator + "";
		else 
			return numerator + "/" + denominator;
    }
}