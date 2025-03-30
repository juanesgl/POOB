import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
     * @param particles Lista de partículas con sus posiciones y velocidades.
     */
    public MaxwellContest(int width, int height, int d, int r, int b, List<int[]> particles) {
        this.container = new MaxwellContainer(width, height, d, r, b, particles);
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
            if ((p.getColor().equals("red") && p.getX() >= middleX) ||
                    (p.getColor().equals("blue") && p.getX() < middleX)) {
                return false; // Hay al menos una partícula fuera de su cámara
            }
        }
        return true;
    }

    /**
     * Método principal para leer la entrada y ejecutar la simulación.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer los parámetros del contenedor
        int w = scanner.nextInt(); // Ancho de cada cámara
        int h = scanner.nextInt(); // Altura del contenedor
        int d = scanner.nextInt(); // Posición del demonio en la pared central
        int r = scanner.nextInt(); // Número de partículas rojas
        int b = scanner.nextInt(); // Número de partículas azules

        // Leer las partículas
        List<int[]> particles = new ArrayList<>();
        for (int i = 0; i < r + b; i++) {
            int px = scanner.nextInt(); // Posición X
            int py = scanner.nextInt(); // Posición Y
            int vx = scanner.nextInt(); // Velocidad X
            int vy = scanner.nextInt(); // Velocidad Y
            particles.add(new int[]{px, py, vx, vy});
        }

        // Crear y ejecutar la simulación
        MaxwellContest contest = new MaxwellContest(w, h, d, r, b, particles);
        contest.solve();
    }
    
    public MaxwellContainer getContainer() {
        return container;
    }
}