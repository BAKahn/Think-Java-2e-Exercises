/**
 * Langston's Ant
 */
public class Langton extends Automaton {
    
    private int xpos;
    private int ypos;
    private int head; // 0=North, 1=East, 2=South, 3=West

    /**
     * default ant position is the middle of grid facing north
     * 
     * @param rows number of rows
     * @param cols number of columns
     */
    public Langton(int rows, int cols) {
        grid = new GridCanvas(rows, cols, 10);
        xpos = rows / 2;
        ypos = cols / 2;
        head = 0;
    }

    /**
     * Simulates a turn and step for the ant
     */
    public void update() {
        flipCell();
        moveAnt();
    }

    /**
     * Gets cell at ant's location, turns ant based on cell's state and changes state of the cell.
     */
    private void flipCell() {
        Cell cell = grid.getCell(xpos, ypos);
        if (cell.isOff()) {
            head = (head + 1) % 4; // turn right
            cell.turnOn();
        } else {
            head = (head + 3) % 4; // turn left
            cell.turnOff();
        }
    }

    /**
     * Moves ant forward a square using head to determine which way is forward.
     */
    private void moveAnt() {
        if (head == 0) {
            ypos -= 1;
        } else if (head == 1) {
            xpos += 1;
        } else if (head == 2) {
            ypos += 1;
        } else {
            xpos -= 1;
        }
    }

    /**
     * Creates and runs the simulation
     * 
     * @param args
     */
    public static void main(String[] args) {
        String title = "Langton's Ant";
        Langton game = new Langton(61, 61);
        game.run(title, 10);        
    }
}
