
/**
 * La clase de MaxwellContest da la solución al problema del demonio de Maxwell previamente planeado en el ciclo 1
 *@author Daniel Ruiz Patiño
 *@author Juan Esteban Sánchez García
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
        double deltaTime = 0.01;
        int maxIterations = 100000;
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
                container.openGate(10);
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
}

