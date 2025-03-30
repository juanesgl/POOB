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
    private double timeStep;
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
        this.timeStep = 0.01;
    }

    /**
     * Ejecuta la simulación y determina el tiempo mínimo necesario para ordenar las partículas.
     */
    public void solve() {
        double time = 0.0;
        int maxIterations = 1000000;
        
        container.makeVisible();
        
        for (int i = 0; i < maxIterations; i++) {
            container.start(1, 0);
            
            if (container.isGoal()) {
                System.out.printf("%.6f\n", time);
                return;
            }
            
            controlDemon();
            time += timeStep;
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
    
    
    private void controlDemon() {
        int middleX = container.getWidth() / 2;
        int demonY = container.demons().get(0).getY();
        double tolerance = 5.0;
        
        container.particles().stream()
            .filter(p -> Math.abs(p.getX() - middleX) < tolerance && 
                        Math.abs(p.getY() - demonY) < tolerance)
            .findFirst()
            .ifPresent(p -> {
                boolean shouldSwap = (p.getColor().equals("red") && p.getX() > middleX) ||
                                   (p.getColor().equals("blue") && p.getX() < middleX);
                if (shouldSwap) {
                    container.openGate(10);
                }
            });
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