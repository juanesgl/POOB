import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane; // Import for pop-up messages
/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Cada partícula tiene una posición, velocidad y representación gráfica.
 * Puede moverse y rebotar en las paredes del contenedor.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.1 (With Movement)
 */


/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Se asegura que las partículas estén en su cámara correcta y se muevan dentro del tablero.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.4 (Placement + Movement)
 */
public class Particle extends Circle {
    private int pX, pY;
    private int vx, vy;
    private String color;
    private boolean isVisible;

    /**
     * Crea una nueva partícula asegurando que está dentro de los límites correctos.
     * 
     * @param pX Posición inicial en X.
     * @param pY Posición inicial en Y.
     * @param vx Velocidad en X.
     * @param vy Velocidad en Y.
     * @param color Color de la partícula ("red" o "blue").
     * @param containerWidth Ancho total del contenedor.
     * @param containerHeight Alto total del contenedor.
     */
    public Particle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super();//sirve para llamar al constructor de circle

        int middleX = (containerWidth/2) + 5; 

        
        if (!color.equals("red") && !color.equals("blue")) {
            System.out.println("Las particulas deben ser rojas o azules");
            return;
        }

        
        if ((color.equals("blue") && pX >= middleX) || (color.equals("red") && pX < middleX)) {
            System.out.println("La partícula no va en esa cámara");
            return;
        }



        
        if (pY < 5 || pY >= (containerHeight - 10)) {  
            System.out.println("Las partículas no están dentro del contenedor");
            return;
        }   


        this.pX = pX;
        this.pY = pY;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        this.isVisible = false;

        this.changeSize(10);
        this.changeColor(color);
        this.moveHorizontal(pX);
        this.moveVertical(pY);
    }

    /**
     * Moves the particle within the container while ensuring no invalid movement.
     * 
     * @param containerWidth  The total width of the container.
     * @param containerHeight The total height of the container.
     */
    public void move(int containerWidth, int containerHeight) {
        int middleX = containerWidth / 2; // The center dividing line
    
        // Calculate next position BEFORE updating
        int nextX = pX + vx;
        int nextY = pY + vy;
    
        // Bounce off top & bottom walls
        if (nextY <= 0 || nextY >= containerHeight - 10) {
            vy = -vy;
        }
    
        // Prevent crossing the middle wall
        if (color.equals("blue") && nextX >= middleX - 5) { // Blue can't cross right
            vx = -Math.abs(vx); // Force it left
            nextX = middleX - 6; // Adjust position to stay in the left chamber
        }
        if (color.equals("red") && nextX <= middleX + 5) { // Red can't cross left
            vx = Math.abs(vx); // Force it right
            nextX = middleX + 6; // Adjust position to stay in the right chamber
        }
    
        // Prevent escaping through left & right borders
        if (nextX <= 0) { // Left wall (blue chamber)
            vx = Math.abs(vx); // Force it right
            nextX = 1; // Prevent getting stuck
        }
        if (nextX >= containerWidth - 10) { // Right wall (red chamber)
            vx = -Math.abs(vx); // Force it left
            nextX = containerWidth - 11; // Prevent getting stuck
        }
    
        // Update actual position AFTER all checks
        pX = nextX;
        pY = nextY;
    
        // Move graphical representation
        this.moveHorizontal(vx);
        this.moveVertical(vy);
    }


    /**
     * Obtiene el color de la partícula.
     * @return "red" o "blue".
     */
    public String getColor() {
        return color;
    }

    /**
     * Obtiene la posición X de la partícula.
     * @return Coordenada X.
     */
    public int getX() {
        return pX;
    }

    /**
     * Obtiene la posición Y de la partícula.
     * @return Coordenada Y.
     */
    public int getY() {
        return pY;
    }

    /**
     * Hace que la partícula sea visible.
     */
    public void makeVisible() {
        if (!isVisible) {
            super.makeVisible();
            isVisible = true;
        }
    }
}


