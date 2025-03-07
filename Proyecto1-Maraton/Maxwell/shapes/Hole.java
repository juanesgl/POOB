
/**
 * La clase Hole representa un agujero en la simulación de Maxwell's Demon.
 * Un agujero permite el paso de partículas entre las dos cámaras.
 * Se define por su posición, radio y su estado de visibilidad.
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */
public class Hole {
    // Atributos
    private int x, y;  
    private int radius; 
    private Circle shape; 
    private boolean isVisible; 

    /**
     * Constructor de la clase Hole.
     * 
     * @param x Coordenada X del agujero.
     * @param y Coordenada Y del agujero.
     * @param radius Radio del agujero.
     */
    public Hole(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.shape = new Circle();
        this.isVisible = false;
    }

    /**
     * Obtiene la coordenada X del agujero.
     * 
     * @return Coordenada X.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y del agujero.
     * 
     * @return Coordenada Y.
     */
    public int getY() {
        return y;
    }

    
    /**
     * Hace que el agujero sea visible.
     */
    public void makeVisible() {
        if (!isVisible) {
            shape.makeVisible();
            isVisible = true;
        }
    }

    /**
     * Hace que el agujero sea invisible.
     */
    public void makeInvisible() {
        if (isVisible) {
            shape.makeInvisible();
            isVisible = false;
        }
    }
     /**
     * Verifica si el Hole absorve la particula
     */
    public void absorbs() {
        
    }
}