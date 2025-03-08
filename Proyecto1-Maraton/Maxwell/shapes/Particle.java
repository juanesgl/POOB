import java.util.List;
import java.util.ArrayList;
/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Cada partícula tiene una posición, velocidad y representación gráfica.
 * Puede moverse y rebotar en las paredes del contenedor.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.1 (With Movement)
 */
public class Particle extends Circle {
    private int pX, pY;
    private int vx, vy;
    private boolean isVisible;

    /**
     * Constructor de la clase Particle.
     * 
     * @param pX Posición inicial en X.
     * @param pY Posición inicial en Y.
     * @param vx Velocidad en X.
     * @param vy Velocidad en Y.
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
        this.changeColor(color);
        this.moveHorizontal(pX);
        this.moveVertical(pY);
    }

    /**
     * Mueve la partícula y rebota en las paredes.
     * 
     * @param containerWidth Ancho del contenedor.
     * @param containerHeight Alto del contenedor.
     */
    public void move(int containerWidth, int containerHeight) {
        pX += vx;
        pY += vy;

        // Rebote en las paredes superior e inferior
        if (pY <= 0 || pY >= containerHeight - 10) {
            vy = -vy;
        }

        // Rebote en las paredes izquierda y derecha (sin considerar la pared central aún)
        if (pX <= 0 || pX >= containerWidth - 10) {
            vx = -vx;
        }

        // Mueve la representación gráfica
        this.moveHorizontal(vx);
        this.moveVertical(vy);
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

