import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La clase MaxwellContest da la solución al problema del demonio de Maxwell.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 2 (Cycle 2)
 */
public class MaxwellContest {
    private MaxwellContainer container;

    /**
     * Constructor de MaxwellContest.
     * @param width  Ancho de cada cámara (el total será 2 * width).
     * @param height Altura del contenedor.
     * @param d      Posición del demonio en la pared central.
     * @param r      Número de partículas rojas.
     * @param b      Número de partículas azules.
     */
    public MaxwellContest(int width, int height, int d, int r, int b) {
        this.container = new MaxwellContainer(width, height, d, r, b);
    }

    /**
     * Ejecuta la simulación y determina el tiempo mínimo necesario para ordenar las partículas.
     */
    public void solve() {
        double time = 0.0;
        double deltaTime = 0.01; // Small time step for precision
        int maxIterations = 1000000; // Arbitrary large limit to prevent infinite loops
        boolean sorted = false;
    
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Mover todas las partículas
            for (Particle p : container.particles()) {
                p.move(container.getWidth(), container.getHeight(), container.demons());
            }
    
            // Verificar si todas las partículas están en su cámara correcta
            sorted = checkSorted();
    
            if (sorted) {
                System.out.printf("%.6f\n", time);
                return;
            }
    
            // Controlar la apertura del demonio de forma periódica
            if (iteration % 100 == 0) {
                container.openGate(10); // Open the gate for 10 milliseconds
            }
    
            time += deltaTime;
        }
    
        System.out.println("impossible");
    }

    /**
     * Verifica si todas las partículas están en su cámara correcta.
     * Retorna true si están ordenadas, false en caso contrario.
     */
    private boolean checkSorted() {
        int middleX = container.getWidth() / 2;
        for (Particle p : container.particles()) {
            if ((p.getColor().equals("red") && p.getX() < middleX) ||
                    (p.getColor().equals("blue") && p.getX() >= middleX)) {
                return false; // Hay al menos una partícula fuera de su cámara
            }
        }
        return true;
    }

    /**
     * Método principal para leer la entrada y ejecutar la simulación.
     */
    public static void main(String[] args) {
        // Define the container dimensions and demon position
        int w = 400; // Width of each chamber
        int h = 200;  // Height of the container
        int d = 25;  // Demon's y-coordinate

        // Define the number of red and blue particles
        int r = 20;   // Number of red particles
        int b = 10;   // Number of blue particles

        // Create the container and run the simulation
        MaxwellContest contest = new MaxwellContest(w, h, d, r, b);
        contest.solve();
    }
}