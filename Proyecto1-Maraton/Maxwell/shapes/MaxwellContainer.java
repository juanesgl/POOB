import java.util.List;
import java.util.ArrayList;

/**
 * Simulation of the "Maxwell's Demons" problem from ICPC 2024 (Problem H).
 *
 * This simulator models a chamber divided into two sections where particles move, 
 * and a demon controls the passage between them. The visual representation 
 * includes two chambers, a central wall, and a surrounding border.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.1 (Cycle 2 - Added Particles)
 */

public class MaxwellContainer {
    // Instance variables 
    private int height;         
    private int width;          

    private Rectangle border;       
    private Rectangle leftChamber;  
    private Rectangle rightChamber; 
    private Rectangle centralWall;  
    private Demon demon;            
    private boolean isVisible;      

    private List<Particle> particles; 

    /**
     * Constructs a MaxwellContainer with a specified height and width.
     * The container consists of two chambers separated by a center wall.
     *
     * @param h The height of the container.
     * @param w The width of each chamber (total width is 2 * w).
     */
    public MaxwellContainer(int h, int w) {
        this.height = h;
        this.width = 2 * w;
        this.isVisible = false;
        this.particles = new ArrayList<>();

        border = new Rectangle();
        border.changeSize(height + 4, width + 4);
        border.changeColor("black");
        border.moveHorizontal(-2);
        border.moveVertical(-2);

        leftChamber = new Rectangle();
        leftChamber.changeSize(height, w);
        leftChamber.changeColor("white");
        leftChamber.moveHorizontal(0);
        
        rightChamber = new Rectangle();
        rightChamber.changeSize(height, w);
        rightChamber.changeColor("white");
        rightChamber.moveHorizontal(w);

        centralWall = new Rectangle();
        centralWall.changeSize(height, 1);
        centralWall.changeColor("gray");
        centralWall.moveHorizontal(w - 1);

        makeVisible();
    }

    /**
     * Adds a demon at a given height position (y-coordinate).
     * The demon is placed on the center wall and cannot move horizontally.
     * 
     * @param d The y-coordinate where the demon should be placed.
     */
    public void addDemon(int d) {
        if (demon == null) {
            demon = new Demon(d, width / 2); 
            demon.addDemon();
        }
    }

    /**
     * Removes the demon from the container.
     */
    public void delDemon() {
        if (demon != null) {
            demon.delDemon(); 
            demon = null;
        }
    }

    /**
     * Adds a particle to the container.
     * A particle must be placed in the correct chamber:
     * - "red" particles must be in the left chamber.
     * - "blue" particles must be in the right chamber.
     * 
     * @param color "red" (left chamber) or "blue" (right chamber).
     * @param px Initial x-coordinate of the particle.
     * @param py Initial y-coordinate of the particle.
     * @param vx Velocity in the x direction.
     * @param vy Velocity in the y direction.
     */
   
       
    public void addParticle(String color, int px, int py, int vx, int vy) {
  
    if ((color.equals("blue") && px < width / 2) || (color.equals("red") && px >= width / 2)) {
        Particle p = new Particle(px, py, vx, vy, color);
        particles.add(p);
        System.out.println("✅ Added " + color + " particle at (" + px + ", " + py + ")");

        if (isVisible) {
            p.makeVisible();
        }
    } else {
        System.out.println("❌ ERROR: Invalid " + color + " particle placement at (" + px + ", " + py + ")");
    }
}


    /**
     * Removes all particles of a given color from the container.
     * 
     * @param color The color of the particles to remove ("red" or "blue").
     */
    public void delParticle(String color) {
        particles.removeIf(p -> {
            if (p.getColor().equals(color)) {
                p.makeInvisible();
                return true;
            }
            return false;
        });
    }

    /**
     * Makes the container and all its elements visible.
     */
    public void makeVisible() {
        if (!isVisible) {
            border.makeVisible();
            leftChamber.makeVisible();
            rightChamber.makeVisible();
            centralWall.makeVisible();
            for (Particle p : particles) {
                p.makeVisible();
            }
            isVisible = true;
        }
    }

    /**
     * Makes the container and all its elements invisible.
     */
    public void makeInvisible() {
        if (isVisible) {
            border.makeInvisible();
            leftChamber.makeInvisible();
            rightChamber.makeInvisible();
            centralWall.makeInvisible();
            for (Particle p : particles) {
                p.makeInvisible();
            }
            isVisible = false;
        }
    }

    /**
     * Returns true if the container is currently visible.
     *
     * @return true if the container is visible, false otherwise.
     */
    public boolean isVisible() {
        return isVisible;
    }
}
