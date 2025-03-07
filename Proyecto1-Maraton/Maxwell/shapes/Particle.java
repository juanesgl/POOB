/**
 * A Particle moves inside the chambers and can pass through the demon.
 * 
 * Each particle has:
 * - A position (`px`, `py`).
 * - A velocity (`vx`, `vy`).
 * - A specific color ("red" or "blue").
 * 
 * Particles move inside their respective chambers, reflecting off the walls.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */
public class Particle {
    private int px, py;  // Position (x, y)
    private int vx, vy;  // Velocity (x, y)
    private String color; // "red" or "blue"
    private Circle shape; // Visual representation of the particle
    private boolean isVisible; // Tracks if the particle is visible

    /**
     * Creates a particle at a given position with a velocity and color.
     * 
     * @param px Initial x-coordinate of the particle.
     * @param py Initial y-coordinate of the particle.
     * @param vx Velocity in the x direction (movement per step).
     * @param vy Velocity in the y direction (movement per step).
     * @param color The color of the particle ("red" for right chamber, "blue" for left chamber).
     */
    public Particle(int px, int py, int vx, int vy, String color) {
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        this.isVisible = false;

        // Create the particle as a circle
        shape = new Circle();
        shape.changeSize(10); // Default size for particles
        shape.changeColor(color);
        shape.moveHorizontal(px);
        shape.moveVertical(py);
    }

    /**
     * Makes the particle visible.
     * If the particle is already visible, it does nothing.
     */
    public void makeVisible() {
        if (!isVisible) {
            shape.makeVisible();
            isVisible = true;
        }
    }

    /**
     * Makes the particle invisible.
     * If the particle is already invisible, it does nothing.
     */
    public void makeInvisible() {
        if (isVisible) {
            shape.makeInvisible();
            isVisible = false;
        }
    }

    /**
     * Moves the particle based on its velocity.
     * Updates both x and y coordinates.
     */
    public void move() {
        px += vx;
        py += vy;
        shape.moveHorizontal(vx);
        shape.moveVertical(vy);
    }

    /**
     * Gets the current x-position of the particle.
     * 
     * @return The x-coordinate of the particle.
     */
    public int getX() {
        return px;
    }

    /**
     * Gets the current y-position of the particle.
     * 
     * @return The y-coordinate of the particle.
     */
    public int getY() {
        return py;
    }

    /**
     * Gets the velocity in the x direction.
     * 
     * @return The x velocity of the particle.
     */
    public int getVx() {
        return vx;
    }

    /**
     * Gets the velocity in the y direction.
     * 
     * @return The y velocity of the particle.
     */
    public int getVy() {
        return vy;
    }

    /**
     * Gets the particle's color.
     * 
     * @return The color of the particle ("red" or "blue").
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets a new velocity for the particle.
     * 
     * @param newVx The new velocity in the x direction.
     * @param newVy The new velocity in the y direction.
     */
    public void setVelocity(int newVx, int newVy) {
        this.vx = newVx;
        this.vy = newVy;
    }
}
