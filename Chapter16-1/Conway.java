import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Conway's Game of Life.
 */
public class Conway extends Automaton {
    /**
     * Creates a grid from plain text file. 
     */
    public Conway(String path) {
        // set up file to be read
        File file = new File(path);

        // Create an array list to store each line from file.
        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(file); //can throw FileNotFoundExeception

            //while file has another line
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                
                //if line does NOT start with "!" add to ArrayList. i.e. ignore lines that start with "!"
                if (!line.startsWith("!")) {
                    lines.add(line);
                }
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        // set up grid using ArrayList for dimensions
        grid = new GridCanvas(lines.size(), lines.get(0).length(), 20);

        // for each string in array and for each character in each string,
        // if the character is a 'O',
        // turn on the cell that corresponds with the 'O' char.
        for (int str = 0; str < lines.size(); str++) {
            for (int chr = 0; chr < lines.get(str).length(); chr++) {
                if (lines.get(str).charAt(chr) == 'O') {
                    grid.turnOn(str, chr);
                }
            }
        }
    }

    /**
     * An extra constructor that reads a file chosen at runtime.
     */
    public Conway() {
        //prompt user for file
        System.out.print("Enter .cell file with extension: ");
        Scanner input = new Scanner(System.in);

        // set up file to be read
        File file = new File(input.nextLine());

        // Create an array list to store each line from file.
        ArrayList<String> lines = new ArrayList<String>();
        try {
            input = new Scanner(file); //can throw FileNotFoundExeception

            //while file has another line
            while (input.hasNextLine()) {
                String line = input.nextLine();
                
                //if line does NOT start with "!" add to ArrayList. i.e. ignore lines that start with "!"
                if (!line.startsWith("!")) {
                    lines.add(line);
                }
            }
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        // set up grid using ArrayList for dimensions
        grid = new GridCanvas(lines.size(), lines.get(0).length(), 20);

        // for each string in array and for each character in each string,
        // if the character is a 'O',
        // turn on the cell that corresponds with the 'O' char.
        for (int str = 0; str < lines.size(); str++) {
            for (int chr = 0; chr < lines.get(str).length(); chr++) {
                if (lines.get(str).charAt(chr) == 'O') {
                    grid.turnOn(str, chr);
                }
            }
        }
    }

    /**
     * Counts the number of live neighbors around a cell.
     * 
     * @param r row index
     * @param c column index
     * @return number of live neighbors
     */
    private int countAlive(int r, int c) {
        int count = 0;
        count += grid.test(r - 1, c - 1);
        count += grid.test(r - 1, c);
        count += grid.test(r - 1, c + 1);
        count += grid.test(r, c - 1);
        count += grid.test(r, c + 1);
        count += grid.test(r + 1, c - 1);
        count += grid.test(r + 1, c);
        count += grid.test(r + 1, c + 1);
        return count;
    }

    /**
     * Apply the update rules of Conway's Game of Life.
     * 
     * @param cell the cell to update
     * @param count number of live neighbors
     */
    private static void updateCell(Cell cell, int count) {
        if (cell.isOn()) {
            if (count < 2 || count > 3) {
                // Any live cell with fewer than two live neighbors dies,
                // as if by underpopulation.
                // Any live cell with more than three live neighbors dies,
                // as if by overpopulation.
                cell.turnOff();
            }
        } else {
            if (count == 3) {
                // Any dead cell with exactly three live neighbors
                // becomes a live cell, as if by reproduction.
                cell.turnOn();
            }
        }
    }

    /**
     * Counts the neighbors before changing anything.
     * 
     * @return number of neighbors for each cell
     */
    private int[][] countNeighbors() {
        int rows = grid.numRows();
        int cols = grid.numCols();

        int[][] counts = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                counts[r][c] = countAlive(r, c);
            }
        }
        return counts;
    }

    /**
     * Updates each cell based on neighbor counts.
     * 
     * @param counts number of neighbors for each cell
     */
    private void updateGrid(int[][] counts) {
        int rows = grid.numRows();
        int cols = grid.numCols();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid.getCell(r, c);
                updateCell(cell, counts[r][c]);
            }
        }
    }

    /**
     * Simulates one round of Conway's Game of Life.
     */
    public void update() {
        int[][] counts = countNeighbors();
        updateGrid(counts);
    }

    /**
     * Creates and runs the simulation.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String title = "Conway's Game of Life";
        Conway game = new Conway();
        game.run(title, 10);
    }
}
