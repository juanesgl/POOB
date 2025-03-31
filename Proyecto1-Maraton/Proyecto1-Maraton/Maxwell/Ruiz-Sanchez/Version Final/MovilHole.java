public class MovilHole extends Hole {
    private int vx = 1; // Velocidad en X
    private int vy = 1; // Velocidad en Y
    private int containerWidth, containerHeight;

    public MovilHole(int containerWidth, int containerHeight) {
        super(containerWidth, containerHeight, "movil");
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
    }

    public void move() {
        int padding = radius; // Margen para evitar salir del área
        int nextX = x + vx;
        int nextY = y + vy;

        // Control de rebote en bordes laterales
        if (nextX - padding <= 0 || nextX + padding >= containerWidth) {
            vx = -vx; // Invertir dirección
            nextX = x + vx; // Aplicar nuevo movimiento después de invertir
        }

        // Control de rebote en bordes superior e inferior
        if (nextY - padding <= 0 || nextY + padding >= containerHeight) {
            vy = -vy; // Invertir dirección
            nextY = y + vy;
        }

        // Actualiza la posición después de validar los límites
        setX(nextX);
        setY(nextY);

        System.out.println("MovilHole en (" + x + ", " + y + "), vx: " + vx + ", vy: " + vy);
    }
}
