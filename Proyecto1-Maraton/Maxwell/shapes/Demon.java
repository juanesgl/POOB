/**
 * The Demon sits on the center wall and can allow particles to pass through.
 * 
 * It is represented as a small gray square and stays on the middle wall.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.0 (Cycle 1)
 */
    public class Demon {
    private static final int DEMON_SIZE = 10;  // Fixed size for the demon
    private int y;        // The y-coordinate (it moves only vertically)
    private Rectangle shape;
    private boolean isVisible;

    /**
     * Creates a Demon at the specified vertical position.
     * The Demon is always placed on the middle dividing line.
     * 
     * @param d The y-coordinate where the demon is positioned.
     * @param w The width of a single chamber (so the middle is at x = w).
     */
    public Demon(int d, int w) {
    this.y = d;
    this.isVisible = false;

    shape = new Rectangle();
    shape.changeSize(10, 10);
    shape.changeColor("darkgray");

    // Place demon exactly in the middle wall
    shape.moveHorizontal(w - 5);  // Always place at x = w
    shape.moveVertical(d - 5);    // Center it at y = d
    }


    /**
     * Adds the demon to the container (makes it visible).
     */
    public void addDemon() {
        if (!isVisible) {
            shape.makeVisible();
            isVisible = true;
        }
    }

    /**
     * Removes the demon from the container (makes it invisible).
     */
    public void delDemon() {
        if (isVisible) {
            shape.makeInvisible();
            isVisible = false;
        }
    }

    /**
     * Gets the Y-coordinate of the demon.
     * 
     * @return The demon's vertical position.
     */
    public int getY() {
        return y;
    }
}
