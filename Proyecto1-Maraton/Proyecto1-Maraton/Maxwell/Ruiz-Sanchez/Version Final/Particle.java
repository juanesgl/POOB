import java.util.List;
import javax.swing.JOptionPane; 

/**
 * La clase Particle es una clase abstracta que representa la estructura base 
 * para todas las partículas en la simulación de Maxwell's Demon.
 * Define las propiedades comunes (posición, velocidad, color) y el comportamiento
 * básico que todas las partículas deben implementar.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public abstract class Particle extends Circle {  // Ahora es abstracta
    protected int pX;
    protected int pY;
    protected int vx;
    protected int vy;
    private String color;
    private boolean isVisible;
    protected String type;

    /**
     * Constructor base para todas las partículas.
     * Valida las condiciones iniciales y configura las propiedades gráficas.
     * 
     * @param pX Posición horizontal inicial (debe estar dentro del contenedor)
     * @param pY Posición vertical inicial (debe estar entre 5 y containerHeight-10)
     * @param vx Velocidad horizontal inicial
     * @param vy Velocidad vertical inicial
     * @param color Color de la partícula ("red" o "blue")
     * @param containerWidth Ancho del contenedor para validación
     * @param containerHeight Altura del contenedor para validación
     * @param type Tipo de partícula ("normal", "ephemeral", etc.)
     */
    public Particle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight, String type) {
        super(); // Llama al constructor de Circle

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
        this.type = type;

        this.changeSize(10);
        this.changeColor(color);
        this.moveHorizontal(pX);
        this.moveVertical(pY);
    }

    /**
     * Método abstracto que define el movimiento de la partícula.
     * Cada subclase debe implementar su propia lógica de movimiento.
     * 
     * @param containerWidth Ancho actual del contenedor
     * @param containerHeight Altura actual del contenedor
     * @param demons Lista de demonios activos en la simulación
     */
    public abstract void move(int containerWidth, int containerHeight, List<Demon> demons); // Ahora es abstracto
    /**
     * Verifica si la partícula es azul.
     * @return true si la partícula es azul, false en caso contrario
     */
    public boolean isBlue() {
        return "blue".equals(color);
    }
    /**
     * Verifica si la partícula es roja.
     * @return true si la partícula es roja, false en caso contrario
     */
    public boolean isRed() {
        return "red".equals(color);
    }
    /**
     * Obtiene el color de la partícula.
     * @return "red" o "blue"
     */
    public String getColor() {
        return color;
    }
    /**
     * Obtiene la posición horizontal actual.
     * @return Coordenada X en píxeles
     */
    public int getX() {
        return pX;
    }
    /**
     * Obtiene la posición vertical actual.
     * @return Coordenada Y en píxeles
     */
    public int getY() {
        return pY;
    }
    /**
     * Obtiene la velocidad horizontal actual.
     * @return Velocidad en el eje X (px/movimiento)
     */
    public int getVx() {
        return vx;
    }
    /**
     * Obtiene la velocidad vertical actual.
     * @return Velocidad en el eje Y (px/movimiento)
     */
    public int getVy() {
        return vy;
    }
    /**
     * Hace visible la partícula en la interfaz gráfica.
     * Si ya es visible, no realiza cambios.
     */
    public void makeVisible() {
        if (!isVisible) {
            super.makeVisible();
            isVisible = true;
        }
    }
}
