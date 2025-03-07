import java.util.List;
import java.util.ArrayList;
/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Cada partícula tiene una posición, velocidad, estado de visibilidad y representación gráfica.
 * Puede moverse, verificar si está en la posición del demonio y cambiar de cámara.
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */
public class Particle extends Circle {
    private int pX, pY;  
    private int vx, vy;  
    private boolean isVisible; 

    /**
     * Constructor de la clase Particle.
     * 
     * @param pX Posición inicial en el eje X.
     * @param pY Posición inicial en el eje Y.
     * @param vx Velocidad en la dirección X (movimiento por paso).
     * @param vy Velocidad en la dirección Y (movimiento por paso).
     * @param color Color de la partícula.
     */
    public Particle(int pX, int pY, int vx, int vy, String color) {
        super();
        this.pX = pX;
        this.pY = pY;
        this.vx = vx;
        this.vy = vy;
        this.isVisible = false;

        
        this.changeSize(10); 
        this.changeColor("gray");
        this.moveHorizontal(pX);
        this.moveVertical(pY);
    }

    /**
     * Verifica si la partícula está en la posición del demonio.
     * 
     * @param demonX Coordenada X del demonio.
     * @param demonY Coordenada Y del demonio.
     * @return true si la partícula está en la posición del demonio, false en caso contrario.
     */
    public boolean isInDemon(int demonX, int demonY) {
        return this.pX == demonX && this.pY == demonY;
    }

    /**
     * Mueve la partícula a la otra cámara.
     * Cambia la posición en X para reflejar el cambio de cámara.
     */
    public void moveToOtherChamber() {
        this.pX = (this.pX < 250) ? this.pX + 250 : this.pX - 250;
        this.moveHorizontal((this.pX < 250) ? 250 : -250);
    }

    /**
     * Obtiene la posición X de la partícula.
     * 
     * @return Coordenada X de la partícula.
     */
    public int getX() {
        return pX;
    }

    /**
     * Obtiene la posición Y de la partícula.
     * 
     * @return Coordenada Y de la partícula.
     */
    public int getY() {
        return pY;
    }

    /**
     * Obtiene la velocidad en la dirección X.
     * 
     * @return Velocidad en X.
     */
    public int getVx() {
        return vx;
    }

    /**
     * Obtiene la velocidad en la dirección Y.
     * 
     * @return Velocidad en Y.
     */
    public int getVy() {
        return vy;
    }

    /**
     * Establece una nueva velocidad para la partícula.
     * 
     * @param newVx Nueva velocidad en la dirección X.
     * @param newVy Nueva velocidad en la dirección Y.
     */
    public void setVelocity(int newVx, int newVy) {
        this.vx = newVx;
        this.vy = newVy;
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

    /**
     * Hace que la partícula sea invisible.
     */
    public void makeInvisible() {
        if (isVisible) {
            super.makeInvisible();
            isVisible = false;
        }
    }
}
