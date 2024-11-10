/*
In the board game Scrabble, each tile contains a letter, which is used to spell words in rows and columns, and a score, which is used to determine the value of words.

    1. Write a definition for a class named Tile that represents Scrabble tiles.
       The instance variables should include a character named letter and an integer named value.

    2. Write a constructor that takes parameters named letter and value and initializes the instance variables.

    3. Write a method named printTile that takes a Tile object as a parameter and displays the instance variables in a reader-friendly format.

    4. Write a main method that creates a Tile object with the letter Z and the value 10, and then uses printTile to display the state of the object.

    5. Implement the toString and equals methods for a Tile.

    6. Create getters and setters for each of the attributes.

The point of this exercise is to practice the mechanical part of creating a new class definition.

And of course, a main program that uses the above :)
*/

public class Tile {
    //instance variables
    private char letter;
    private int value;

    //Constructor that takes a letter and value as parameters (step 2)
    public Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    //step 6 - getters and setters
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //step 3 - printTile method
    public static void printTile(Tile tile) {
        System.out.println("Tile Letter: " + tile.letter);
        System.out.println("Tile Value: " + tile.value);
    }

    //step 5 - toString and equals methods
    @Override public String toString() {
        return "Tile letter: " + this.letter + "\nTile Value: " + this.value + "\n";
    }

    //Using @Override here produced an error I could not interpret.
    public boolean equals(Tile that) {
        return this.letter == that.letter && this.value == that.value;
    }

    //step 4 - main method that creates a tile and uses printTile
    public static void main(String[] args) {
        Tile myTile = new Tile('Z', 10);
        printTile(myTile);

        //some tests for Tile methods
        Tile anotherTile = new Tile('Z', 10);

        System.out.println(myTile.equals(anotherTile));

        anotherTile.setLetter('A');
        System.out.println(myTile.equals(anotherTile));

        System.out.println("anotherTile's Letter is: " + anotherTile.getLetter());
    }
}