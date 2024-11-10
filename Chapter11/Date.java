/*
Write a class definition for Date, an object type that contains three integers: year, month, and day. This class should provide two constructors. The first should take no parameters and initialize a default date. The second should take parameters named year, month and day, and use them to initialize the instance variables.

Write a main method that creates a new Date object named birthday. The new object should contain your birth date. You can use either constructor.
*/

public class Date {
    //instance variables
    private int year;
    private int month;
    private int day;

    public Date() {
        this.year = 1900;
        this.month = 1;
        this.day = 1;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = month;
    }

    public static void main(String[] args) {
        Date birthday = new Date(1995, 2, 2);
    }
}