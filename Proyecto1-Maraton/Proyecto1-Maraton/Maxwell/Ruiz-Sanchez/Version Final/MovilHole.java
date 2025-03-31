
/**
 * La clase MovilHole representa un agujero móvil en la simulación de Maxwell's Demon.
 * Este tipo de agujero se mueve automáticamente dentro del contenedor, rebotando en los bordes
 * según su velocidad actual, y puede absorber partículas durante su movimiento.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class MovilHole extends Hole {
    private int vx = 1; 
    private int vy = 1; 
    private int containerWidth, containerHeight;
    /**
     * Constructor para crear un agujero móvil.
     * 
     * @param containerWidth  Ancho total del contenedor (para cálculo de bordes).
     * @param containerHeight Altura total del contenedor (para cálculo de bordes).
     */
    public MovilHole(int containerWidth, int containerHeight) {
        super(containerWidth, containerHeight, "movil");
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
    }
    /**
     * Actualiza la posición del agujero móvil según su velocidad actual,
     * invirtiendo la dirección cuando colisiona con los bordes del contenedor.
     * El margen de rebote se calcula según el radio del agujero.
     */
    public void move() {
        int padding = radius; 
        int nextX = x + vx;
        int nextY = y + vy;

        
        if (nextX - padding <= 0 || nextX + padding >= containerWidth) {
            vx = -vx;
            nextX = x + vx; 
        }

        
        if (nextY - padding <= 0 || nextY + padding >= containerHeight) {
            vy = -vy; 
            nextY = y + vy;
        }

        
        setX(nextX);
        setY(nextY);

        System.out.println("MovilHole en (" + x + ", " + y + "), vx: " + vx + ", vy: " + vy);
    }
}
