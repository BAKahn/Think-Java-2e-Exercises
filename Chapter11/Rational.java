/*
A rational number is a number that can be represented as the ratio of two integers. For example, 2/3 is a rational number, and you can think of 7 as a rational number with an implicit 1 in the denominator.

    1. Define a class called Rational. A Rational object should have two integer instance variables that store the numerator and denominator.

    2. Write a constructor that takes no arguments and that sets the numerator to 0 and denominator to 1.

    3. Write an instance method called printRational that displays a Rational in some reasonable format.

    4. Write a main method that creates a new object with type Rational, sets its instance variables to the values of your choice, and displays the object.

    5. At this stage, you have a minimal testable program. Test it and, if necessary, debug it.

    6. Write a toString method for Rational and test it using println.

    7. Write a second constructor that takes two arguments and uses them to initialize the instance variables.

    8. Write an instance method called negate that reverses the sign of a rational number. This method should be a modifier, so it should be void. Add lines to main to test the new method.

    9. Write an instance method called invert that inverts the number by swapping the numerator and denominator. It should be a modifier. Add lines to main to test the new method.

    10. Write an instance method called toDouble that converts the rational number to a double (floating-point number) and returns the result. This method is a pure method; it does not modify the object. As always, test the new method.

    11. Write an instance method named reduce that reduces a rational number to its lowest terms by finding the greatest common divisor (GCD) of the numerator and denominator and dividing through. This method should be a pure method; it should not modify the instance variables of the object on which it is invoked.

       Hint: Finding the GCD only takes a few lines of code. Search the web for “Euclidean algorithm”.

    12. Write an instance method called add that takes a Rational number as an argument, adds it to this, and returns a new Rational object. There are several ways to add fractions. You can use any one you want,  but you should make sure that the result of the operation is reduced so that the numerator and denominator have no common divisor (other than 1).

The purpose of this exercise is to write a class definition that includes a variety of methods, including constructors, static methods, instance methods, modifiers, and pure methods.
*/
public class Rational {
    
    //instance variables
    private int numerator;
    private int denominator;

    //step 2 - default constructor
    public Rational() {
        this.numerator = 0;
        this.denominator = 1;
    }

    //step 7 - constructor with arguements
    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    //step 3 - printRational
    public void printRational() {
        System.out.println(this.numerator + "/" + this.denominator);
    }

    //step 5 - toString
    @Override public String toString() {
        return (this.numerator + "/" + this.denominator);
    }

    //step 8 - negate method
    public void negate() {
        this.numerator *= -1;
    }

    //step 9 - invert method
    public void invert() {
        int temp = this.numerator;
        this.numerator = this.denominator;
        this.denominator = temp;
    }

    //step 10 - toDouble method
    public double toDouble() {
        return (double)numerator/denominator;
    }

    //step 11 - reduce method
    public Rational reduce() {
        //Find GCD using Euclidean algorithm ( a = (a/b) + b + (a%b) )
        int a = this.numerator;
        int b = this.denominator;
        int remainder;
        while (b != 0) {
            remainder = a % b;
            a = b;
            b = remainder; 
        }
        //int a should be the GCD when the loop finishes

        //create a new rational from original rational reduced by GCD
        Rational redRational = new Rational((this.numerator/a), (this.denominator/a));
        return redRational;
    }

    //step 12 - add method
    public Rational add(Rational that) {
        //if denominators match, just add numerators and return sum Rational
        if(this.denominator == that.denominator) {
            Rational sumRational = new Rational((this.numerator + that.numerator), this.denominator);
            return sumRational.reduce();
        }
        else {
            //multiply the numerator of each rational by denominator of the other
            int thisNumerator = this.numerator * that.denominator;
            int thatNumerator = that.numerator * this.denominator;

            //Find a common denominator using a method that works with all cases(not always using LCD).
            int sharedDenominator = this.denominator * that.denominator;

            //create rational using new numerator/denominator and reduce
            Rational sumRational = new Rational((thisNumerator + thatNumerator), sharedDenominator);
            return sumRational.reduce();
        } 
    }

    //step 4 - main method
    public static void main(String[] args) {

        //test default constructor and printRational
        Rational rat1 = new Rational();
        System.out.print("default constructor: ");
        rat1.printRational();

        //test toString
        System.out.println("toString Override: " + rat1);

        //test non-default constructor
        Rational rat2 = new Rational(9, 2);
        System.out.print("arguement constructor: ");
        rat2.printRational();

        //test negate method
        rat2.negate();
        System.out.print("converted to negative: ");
        rat2.printRational();
        //un-negate
        rat2.negate();

        //test invert
        rat2.invert();
        System.out.print("inverted: ");
        rat2.printRational();

        //test toDouble
        Rational rat3 = new Rational(1, 2);
        System.out.println(rat3 + " is equivalent to the double: " + rat3.toDouble());

        //test reduce
        Rational rat4 = new Rational(12, 18);
        System.out.println(rat4 + " reduces to " + rat4.reduce());
        //Verify rational hasn't changed
        //rat4.printRational();

        //test add
        Rational rat5 = new Rational(2, 18);
        System.out.println("The sum of " + rat2 + " and " + rat5 + " is " + rat2.add(rat5));
    }
}
