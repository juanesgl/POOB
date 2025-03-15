import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * La clase Hole representa un agujero en la simulación de Maxwell's Demon.
 * Un agujero permite el paso de partículas entre las dos cámaras.
 * Se define por su posición, radio y su estado de visibilidad.
 * Atributos:
 * - `x`: Coordenada X del agujero.
 * - `y`: Coordenada Y del agujero.
 * - `radius`: Radio del agujero.
 * - `shape`: Representación gráfica del agujero.
 * - `isVisible`: Indica si el agujero es visible en la interfaz gráfica.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */


public class Hole {
    private int x, y;  
    private int radius; 
    private Circle shape; 
    private boolean isVisible; 

    /**
     * Constructor de la clase Hole.
     * Crea un agujero en una posición aleatoria dentro del contenedor, evitando la zona central.
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
     * Hace que el agujero sea visible en la interfaz gráfica.
     */
    public void makeVisible() {
        if (!isVisible) {
            shape.makeVisible();
            isVisible = true;
        }
    }

    /**
     * Hace que el agujero sea invisible en la interfaz gráfica.
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
     * @return Coordenada X del agujero.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y del agujero.
     *
     * @return Coordenada Y del agujero.
     */
    public int getY() {
        return y;
    }

    /**
     * Verifica si una partícula ha caído en el agujero y la elimina.
     * Si una partícula está dentro del radio del agujero, se hace invisible y se elimina de la lista.
     *
     * @param particles Lista de partículas en el contenedor.
     */
    public void absorbs(List<Particle> particles) {
        Iterator<Particle> iterator = particles.iterator();
        while (iterator.hasNext()) {
            Particle particle = iterator.next();
            double distance = Math.sqrt(Math.pow(particle.getX() - x, 2) + Math.pow(particle.getY() - y, 2));
    
            if (distance <= radius) {
                particle.makeInvisible();
                iterator.remove();
            }
        }
    }
}