import java.util.List;
import java.util.*;

/**
 * Simulation of the "Maxwell's Demons" problem from ICPC 2024 (Problem H).
 *
 * This simulator models the behavior of Maxwell's Demons a classic problem
 * in statistical physics.
 *
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.0 (Cycle 1)
 */

public class MaxwellContainer {
    // Instance variables 
    private int height;
    private int width;

    private List<Integer> demons;
    private List<int[]> particles;
    private List<int[]> holes;

    private boolean isVisible;
    private boolean lastActionOk;

    /**
     * Constructor for objects of class MaxwellContainer
     */
    public MaxwellContainer(int h, int w) {
        this.height = h;
        this.width = 2 * w;
        this.demons = new ArrayList<>();
        this.particles = new ArrayList<>();
        this.holes = new ArrayList<>();
        this.lastActionOk = true;
        this.isVisible = false;  // Inicialmente no es visible

        makeVisible();  // Activar visibilidad al crear
    }

    /**
     * Hace visible el contenedor y lo dibuja
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Hace invisible el contenedor
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Dibuja el contenedor y la pared central
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);

            // Dibujar el contenedor
            canvas.drawRectangle(0, 0, width, height, "black");

            // Dibujar la pared central en x = w
            canvas.drawLine(width / 2, 0, width / 2, height, "gray");
        }
    }

    /**
     * Borra el contenedor de la pantalla
     */
    private void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

}