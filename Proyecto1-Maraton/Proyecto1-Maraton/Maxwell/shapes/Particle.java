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
    private boolean offsetApplied = false;


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
    final int middleX = containerWidth / 2;
    final int padding = 5;
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

    // Determinar en qué cámara se encuentra la partícula
    boolean isInLeftChamber = pX < middleX;
    boolean isInRightChamber = pX > middleX;
    // Se considera "correcto" si una partícula azul está en la izquierda o roja en la derecha
    boolean isCorrectSide = (isBlue() && isInLeftChamber) || (isRed() && isInRightChamber);

    // Verificar si la partícula está intentando cruzar la pared central
    boolean isCrossingMiddle = (pX < middleX && nextX >= middleX - padding) ||
                               (pX > middleX && nextX <= middleX + padding);

    if (isCrossingMiddle) {
        boolean canPass = demons.stream()
            .anyMatch(d -> d.isGateOpen() && Math.abs(pY - d.getY()) <= d.getSize());
        // Aunque el gate esté abierto, si la partícula está en su lado correcto, se le impide el cruce.
        if (isCorrectSide) {
            canPass = false;
        }

        if (!canPass) {
            // Revertir la dirección horizontal y recalcular nextX
            vx = -vx;
            nextX = pX + vx;
            // Forzar que la partícula se quede en su lado del centro:
            if (pX < middleX) {
                // Si viene de la izquierda, se asegura que no pase a la zona central
                nextX = Math.min(nextX, middleX - padding);
            } else {
                // Si viene de la derecha, se asegura que no pase a la zona central
                nextX = Math.max(nextX, middleX + padding);
            }
        } else {
            System.out.println("Partícula " + (isBlue() ? "Azul" : "Roja") + " cruzó al otro lado");
        }
    }

    // Asegurarse de que la partícula se quede dentro de los límites del contenedor
    nextX = Math.max(padding, Math.min(containerWidth - padding, nextX));

    pX = nextX;
    pY = nextY;

    this.moveHorizontal(vx);
    this.moveVertical(vy);
    }










    public Particle(String color) {
        this.color = color;
    }

    public boolean isBlue() {
        return "blue".equals(color);
    }

    public boolean isRed() {
        return "red".equals(color);
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