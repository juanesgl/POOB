import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * La clase Demon representa al "demonio" que se encuentra en la pared central del contenedor.
 * Puede abrir y cerrar una puerta para permitir el paso de partículas entre cámaras.
 * 
 * Atributos:
 * - `y`: La posición vertical del demonio.
 * - `shape`: Representación gráfica del demonio.
 * - `isVisible`: Indica si el demonio es visible en pantalla.
 * - `gateOpen`: Indica si la puerta del demonio está abierta.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */
public class Demon {
    public static final int DEMON_SIZE = 20;
    private int y;
    private Rectangle shape;
    private boolean isVisible;
    private boolean gateOpen=true;  

    /**
     * Constructor de la clase Demon.
     * 
     * @param d La posición vertical donde se colocará el demonio.
     * @param w La anchura de una cámara (se usa para posicionarlo en la pared central).
     */
    public Demon(int d, int w) {
        this.y = d;
        this.isVisible = false;
        this.gateOpen = false;  

        shape = new Rectangle();
        shape.changeSize(DEMON_SIZE, DEMON_SIZE);
        shape.changeColor("darkgray");

        // Posicionamiento en la pared central
        shape.moveHorizontal(w - (DEMON_SIZE / 2));
        shape.moveVertical(d - (DEMON_SIZE / 2));
    }

    /**
     * Hace visible al demonio en la interfaz gráfica.
     */
    public void makeVisible() {
        if (!isVisible) {
            shape.makeVisible();
            isVisible = true;
        }
    }

    /**
     * Hace invisible al demonio en la interfaz gráfica.
     */
    public void makeInvisible() {
        if (isVisible) {
            shape.makeInvisible();
            isVisible = false;
        }
    }

    /**
     * Obtiene la posición vertical del demonio.
     * 
     * @return La coordenada Y del demonio.
     */
    public int getY() {
        return y;
    }
    public int getSize() {
        return DEMON_SIZE;
    }
    /**
     * Abre o cierra la puerta del demonio, cambiando su color visual.
     * 
     * @param state `true` para abrir la puerta, `false` para cerrarla.
     */
    public void setGateOpen(boolean state) {
        this.gateOpen = state;
        if (state) {
            shape.changeColor("green");  // Verde indica que la puerta está abierta
        } else {
            shape.changeColor("black");  // Gris indica que la puerta está cerrada
        }
    }

    /**
     * Indica si la puerta del demonio está abierta o cerrada.
     * 
     * @return `true` si la puerta está abierta, `false` si está cerrada.
     */
    public boolean isGateOpen() {
        return gateOpen;
    }
}