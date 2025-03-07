import java.util.List;
import java.util.*;

/**
 * Simulation of the "Maxwell's Demons" problem from ICPC 2024 (Problem H).
 *
 * This simulator models a chamber divided into two sections where particles move 
 * and a demon controls the passage between them. The visual representation 
 * includes two chambers, a central wall, and a surrounding border.
 *
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.0 (Cycle 1)
 */

public class MaxwellContainer {
    // Instance variables 
    private int height;            
    private int width;             
    
    private Rectangle border;      
    private Rectangle leftChamber; 
    private Rectangle rightChamber;
    private Rectangle centralWall; 
    private boolean isVisible;     

    /**
     * Constructs a MaxwellContainer with specified height and width.
     * The container consists of two chambers separated by a central wall.
     *
     * @param h The height of the container.
     * @param w The width of each chamber (total width is 2 * w).
     */
    public MaxwellContainer(int h, int w) {
        this.height = h;
        this.width = 2 * w;
        this.isVisible = false;  

        // Create black border around the container
        border = new Rectangle();
        border.changeSize(height + 4, width + 4); 
        border.changeColor("black");
        border.moveHorizontal(-2);
        border.moveVertical(-2);

        // Initialize the left chamber
        leftChamber = new Rectangle();
        leftChamber.changeSize(height, w);
        leftChamber.changeColor("white");
        leftChamber.moveHorizontal(0);
        
        // Initialize the right chamber
        rightChamber = new Rectangle();
        rightChamber.changeSize(height, w);
        rightChamber.changeColor("white");
        rightChamber.moveHorizontal(w);

        // Create the central wall 
        centralWall = new Rectangle();
        centralWall.changeSize(height, 2); 
        centralWall.changeColor("gray");
        centralWall.moveHorizontal(w - 1); 

        makeVisible();  
    }

    /**
     * Makes the container visible.
     * If already visible, does nothing.
     */
    public void makeVisible() {
        if (!isVisible) {
            border.makeVisible();
            leftChamber.makeVisible();
            rightChamber.makeVisible();
            centralWall.makeVisible();
            isVisible = true;
        }
    }

    /**
     * Makes the container invisible.
     * If already invisible, does nothing.
     */
    public void makeInvisible() {
        if (isVisible) {
            border.makeInvisible();
            leftChamber.makeInvisible();
            rightChamber.makeInvisible();
            centralWall.makeInvisible();
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
