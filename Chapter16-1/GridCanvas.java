import java.awt.Canvas;
import java.awt.Graphics;

/**
 * 2D array of cells representing a rectangular grid.
 * 
 * @author Chris Mayfield
 * @version 7.1.0
 */
public class GridCanvas extends Canvas {

    /** Cells stored in row-major order. */
    private Cell[][] array;

    /**
     * Constructs a grid of given size.
     * 
     * @param rows number of rows
     * @param cols number of columns
     * @param size pixels per cell
     */
    public GridCanvas(int rows, int cols, int size) {

        // build 2D array of cells
        array = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            int y = r * size;
            for (int c = 0; c < cols; c++) {
                int x = c * size;
                array[r][c] = new Cell(x, y, size);
            }
        }

        // set the canvas size
        setSize(cols * size, rows * size);
    }

    /**
     * Gets the number of rows.
     * 
     * @return number of rows
     */
    public int numRows() {
        return array.length;
    }

    /**
     * Gets the number of columns.
     * 
     * @return number of columns
     */
    public int numCols() {
        return array[0].length;
    }

    /**
     * Gets the cell at index (r, c).
     * 
     * @param r row index
     * @param c column index
     * @return the cell
     */
    public Cell getCell(int r, int c) {
        return array[r][c];
    }

    /**
     * Convenience method that turns on the cell at (r, c).
     * 
     * @param r row index
     * @param c column index
     */
    public void turnOn(int r, int c) {
        array[r][c].turnOn();
    }

    /**
     * Returns 1 if the cell at (r, c) exists and is on. Returns 0 if the cell
     * doesn't exist or is off.
     * 
     * @param r row index
     * @param c column index
     * @return 1 or 0
     */
    /* 
    public int test(int r, int c) {
        try {
            if (array[r][c].isOn()) {
                return 1;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // cell doesn't exist
        }
        return 0;
    }
    */

    /**
     * Exercise 15.2 Alternate test method that makes the grid toroidal, meaning cells wrap around to the opposite side.
     * No longer needs try/catch because if/else's takes care of out of bounds.
     * 
     * @param r row index
     * @param c column index
     * @return 1 or 0
     */
     
    public int test(int r, int c) {
        //if checking a cell that would be out of bounds for r, map to the opposite cell.
        if (r < 0) {
            r = this.numRows() - 1;
        } else if (r >= this.numRows()) {
            r = 0;
        }

        //if checking a cell that would be out of bounds for c, map to the opposite cell.
        if (c < 0) {
            c = this.numCols() - 1;
        } else if (c >= this.numCols()) {
            c = 0;
        }

        if (array[r][c].isOn()) {
            return 1;
        }
        return 0;
    }
    
    
    /**
     * Draws the grid, cell by cell.
     * 
     * @param g graphics context
     */
    public void draw(Graphics g) {
        for (Cell[] row : array) {
            for (Cell cell : row) {
                cell.draw(g);
            }
        }
    }

    /**
     * Paints the grid on the screen.
     * 
     * @param g graphics context
     */
    public void paint(Graphics g) {
        draw(g);
    }

    /**
     * Overriding this method helps the simulation run more smoothly. Normally
     * the Canvas is cleared before painting, but there is no need to clear it
     * since the paint method draws the entire grid.
     * 
     * @param g graphics context
     */
    public void update(Graphics g) {
        draw(g);
    }

    /**
     * Ex 15.1: Returns the total number of cells that are on 
     */
    public int countOn() {
        int onCount = 0;

        //for each row and for each column, if a cell is on, increment count.
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numCols(); c++) {
                if (array[r][c].isOn()) {
                    onCount++;
                }
            }
        }
        return onCount;
    }
}
