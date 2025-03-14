import java.util.Random;
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
     * @param containerWidth Ancho total del contenedor.
     * @param containerHeight Altura total del contenedor.
     */
    public Hole(int containerWidth, int containerHeight) {
        Random rand = new Random();

        int middleX = containerWidth / 2;

        do {
            this.x = rand.nextInt(containerWidth - 20) + 10;
        } while (x >= middleX - 10 && x <= middleX + 10);

        this.y = rand.nextInt(containerHeight - 20) + 10;
        this.radius = rand.nextInt(10) + 10;
        this.shape = new Circle();
        this.shape.changeSize(radius);
        this.shape.changeColor("magenta");
        this.shape.moveHorizontal(x);
        this.shape.moveVertical(y);
        this.isVisible = false;
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
     * Verifica si el Hole absorve la particula
     */
    public void absorbs() {
        
    }
}