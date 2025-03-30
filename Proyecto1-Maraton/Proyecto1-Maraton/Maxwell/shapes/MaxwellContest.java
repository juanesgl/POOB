import java.util.ArrayList;
import java.util.List;

/**
 * La clase MaxwellContest da la solución al problema del demonio de Maxwell.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 2 (Cycle 2)
 */

public class MaxwellContest {

    /**
     * Ejecuta la simulación y retorna el tiempo (en segundos) en que se alcanzó el objetivo.
     * Si no se alcanza el objetivo dentro del tiempo de simulación, retorna -1.
     *
     * @param h         Altura de la cámara.
     * @param w         Ancho de una cámara (el contenedor tendrá ancho 2*w).
     * @param d         Posición vertical del demonio.
     * @param b         Número de partículas azules.
     * @param r         Número de partículas rojas.
     * @param particles Array de partículas, cada subarreglo con {px, py, vx, vy}.
     * @return Tiempo en segundos para alcanzar el objetivo o -1 si no se alcanzó.
     */
    public static float Solve(int h, int w, int d, int b, int r, int[][] particles) {
        List<int[]> particlesList = new ArrayList<>();
        for (int[] arr : particles) {
            particlesList.add(arr);
        }
        // El constructor de MaxwellContainer espera (w, h, d, r, b, particles)
        MaxwellContainer container = new MaxwellContainer(w, h, d, r, b, particlesList);
        container.makeVisible();
        container.openGate(10000);
        long startTime = System.currentTimeMillis();
        container.start();  // Este método ejecuta la simulación y muestra paso a paso los movimientos
        long elapsedTimeMs = System.currentTimeMillis() - startTime;
        float elapsedSeconds = elapsedTimeMs / 1000.0f;

        if (container.isGoal()) {
            System.out.println("¡Objetivo alcanzado en " + elapsedSeconds + " segundos!");
            return elapsedSeconds;
        } else {
            System.out.println("¡Imposible alcanzar el objetivo en " + elapsedSeconds + " segundos!");
            return -1.0f;
        }
    }

    /**
     * Ejecuta la simulación con los parámetros dados.
     * Durante la simulación se muestran los movimientos paso a paso.
     * Al finalizar, si se alcanzó la meta se muestra el mensaje correspondiente;
     * de lo contrario, se presenta un mensaje indicando que no hay solución.
     *
     * @param h         Altura de la cámara.
     * @param w         Ancho de una cámara (el contenedor tendrá ancho 2*w).
     * @param d         Posición vertical del demonio.
     * @param b         Número de partículas azules.
     * @param r         Número de partículas rojas.
     * @param particles Array de partículas, cada subarreglo con {px, py, vx, vy}.
     */
    public static void Simulate(int h, int w, int d, int b, int r, int[][] particles) {
        List<int[]> particlesList = new ArrayList<>();
        for (int[] arr : particles) {
            particlesList.add(arr);
        }
        // Crear el contenedor. (Recordar: el constructor espera (w, h, d, r, b, particles))
        MaxwellContainer container = new MaxwellContainer(w, h, d, r, b, particlesList);
        container.makeVisible();
        
        // Iniciamos la simulación. runSimulation se encarga de mostrar paso a paso los movimientos.
        container.start();  // Si hay solución se verán los movimientos; de lo contrario, se mostrará "imposible"

        // Al finalizar la simulación se mostrará el mensaje correspondiente
        if (container.isGoal()) {
            // Ya se imprimió en runSimulation el mensaje de éxito.
        } else {
            System.out.println("¡Imposible alcanzar el objetivo!");
        }
    }
}
