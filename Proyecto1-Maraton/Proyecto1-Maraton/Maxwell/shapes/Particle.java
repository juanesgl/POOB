import java.util.List;
import javax.swing.JOptionPane; 

/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Cada partícula tiene una posición, velocidad y representación gráfica.
 * Puede moverse y rebotar en las paredes del contenedor.
 * Atributos:
 * - `pX`: Coordenada X de la partícula.
 * - `pY`: Coordenada Y de la partícula.
 * - `vx`: Velocidad en el eje X.
 * - `vy`: Velocidad en el eje Y.
 * - `color`: Color de la partícula ("red" o "blue").
 * - `isVisible`: Indica si la partícula es visible en la interfaz gráfica.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */
public class Particle extends Circle {
    private int pX;
    private int pY;
    private int vx;
    private int vy;
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
        super(); // Llama al constructor de Circle

        int middleX = (containerWidth / 2) ;

        if (!color.equals("red") && !color.equals("blue")) {
            System.out.println("Las partículas deben ser rojas o azules");
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
     * Mueve la partícula dentro del contenedor, asegurándose de que no haya movimientos inválidos.
     * 
     * @param containerWidth Ancho del contenedor.
     * @param containerHeight Alto del contenedor.
     * @param demons Lista de demonios que existen.
     */
    public void move(int containerWidth, int containerHeight, List<Demon> demons) {
        int middleX = containerWidth / 2;
        int padding = 5;
        int nextX = pX + vx;
        int nextY = pY + vy;

        // Rebote en paredes superior e inferior
        if (nextY <= padding || nextY >= containerHeight - padding) {
            vy = -vy;
            nextY = Math.max(padding, Math.min(containerHeight - padding, pY + vy));
        }

        // Rebote en paredes laterales
        if (nextX <= padding || nextX >= containerWidth - padding) {
            vx = -vx;
            nextX = Math.max(padding, Math.min(containerWidth - padding, pX + vx));
        }
      // Interacción con la pared central y demonio
        if ((nextX >= middleX - padding && pX < middleX) || 
            (nextX <= middleX + padding && pX > middleX)) {
            
            boolean shouldPass = demons.stream()
                .anyMatch(d -> d.isGateOpen() && Math.abs(pY - d.getY()) < 10);
                
            if (!shouldPass) {
                vx = -vx;
                nextX = pX + vx;
            }
        }

        pX = nextX;
        pY = nextY;

        this.moveHorizontal(vx);
        this.moveVertical(vy);
    }

    
    /**
     * Obtiene el color de la partícula.
     * 
     * @return "red" o "blue".
     */
    public String getColor() {
        return color;
    }

    /**
     * Obtiene la posición X de la partícula.
     * 
     * @return Coordenada X.
     */
    public int getX() {
        return pX;
    }

    /**
     * Obtiene la posición Y de la partícula.
     * 
     * @return Coordenada Y.
     */
    public int getY() {
        return pY;
    }
    
    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
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