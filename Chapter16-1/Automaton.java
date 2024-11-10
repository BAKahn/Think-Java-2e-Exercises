import javax.swing.JFrame;

/**
 * An abstract class for a cellular automaton
 */

//abstract class - cannot be instantiated
public abstract class Automaton {

    // protected - visible to subclasses
    protected GridCanvas grid;

    // abstract method - subclasses MUST override/implement
    public abstract void update();

    /**
     * Creates and configures JFrame. Runs simulation.
     * 
     * @param title Name of JFrame window
     * @param rate Number of frames per second
     */
    public void run(String title, int rate) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this.grid);
        frame.pack();
        frame.setVisible(true);
        this.mainloop(rate);
    }

    /**
     * The simulation loop.
     */
    private void mainloop(int rate) {
        while (true) {
            // update the drawing
            this.update();
            grid.repaint();

            // delay the simulation
            try {
                Thread.sleep(1000 / rate);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }
}