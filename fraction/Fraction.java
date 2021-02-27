package fraction;

/**
 * Representing fractions of the form numerator and denominator
 * The object should be immutable.
 */
public interface Fraction extends Comparable<Fraction> {

    /**
     * Returns a new <code>Fraction</code> that is the <em>sum</em> of <code>this</code> and the parameter:
     *  <code>a/b + c/d</code> is <code>(ad + bc)/bd</code>
     *
     * @param f the fraction to add to the current fraction
     * @return the result of the addition
     */
    public Fraction add(Fraction f);

    /**
     * Returns a new <code>Fraction</code> that is the <em>difference</em> of <code>this</code> minus the parameter
     * <code>a/b - c/d</code> is <code>(ad - bc)/bd</code>
     *
     * @param f the fraction to subtract from the current fraction
     * @return the result of the subtraction
     */
    public Fraction subtract(Fraction f);

    /**
     * Returns a new <code>Fraction</code> that is the <em>product</em> of <code>this</code> and the parameter
     * <code>(a/b) * (c/d)</code> is <code>(a*c)/(b*d)</code>
     *
     * @param f the fraction to multiply with the current fraction
     * @return the result of the multiplication
     */
    public Fraction multiply(Fraction f);

    /**
     * Returns a new <code>Fraction</code> that is the <em>quotient</em> of dividing <code>this</code> by the parameter
     * <code>(a/b) / (c/d)</code> is <code>(a*d)/(b*c)</code>
     *
     * @param f the fraction to take part in the division
     * @return the result of the division
     */
    public Fraction divide(Fraction f);

    /**
     * Returns a new <code>Fraction</code> that is the <em>absolute value</em> of <code>this</code> fraction
     *
     * @return the absolute value of the fraction as a new fraction
     */
    public Fraction abs();

    /**
     * Returns a new <code>Fraction</code> that has the same numeric value of <code>this</code> fraction,
     * but the opposite sign.
     *
     * @return the newly negated fraction
     */
    public Fraction negate();

    /**
     * The inverse of <code>a/b</code> is <code>b/a</code>.
     *
     * @return the newly inverted fraction
     */
    public Fraction inverse();

    /**
     * Returns <code>true</code> if <code>o</code> is a <code>Fraction</code> equal to <code>this</code>,
     * and <code>false</code> in all other cases.
     *
     * @param o the object to compare this one to
     * @return whether the true fractions are equal
     */
    @Override
    public boolean equals(Object o);

    /**
     * Returns:
     * <ul>
     *     <li>A negative <code>int</code> if <code>this</code> is less than <code>o</code>.</li>
     *     <li>Zero if <code>this</code> is equal to <code>o</code>.</li>
     *     <li>A positive <code>int</code> if <code>this</code> is greater than <code>o</code>.</li>
     * </ul>
     *
     * @param f the fraction to compare <code>this</code> to
     * @return the result of the comparison
     */
    @Override
    public int compareTo(Fraction f);

    /**
     * Returns a <code>String</code> of the form <code>n/d</code>, where <code>n</code> is the
     * <em>numerator</em> and <code>d</code> is the <em>denominator</em>.
     * However, if <code>d</code> is <code>1</code>, just return <code>n</code> (as a <code>String</code>).
     *
     * The returned <code>String</code> should not contain any blanks.
     * If the fraction represents a negative number, a minus sign should precede <code>n</code>
     *
     * @return the string representation fo the fraction
     */
    @Override
    public String toString();
}