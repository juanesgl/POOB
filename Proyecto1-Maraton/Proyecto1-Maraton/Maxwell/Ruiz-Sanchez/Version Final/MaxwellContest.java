import java.util.ArrayList;
import java.util.List;

/**
 * La clase MaxwellContest proporciona la solución al problema del demonio de Maxwell
 * mediante dos métodos estáticos que permiten simular y resolver el escenario planteado.
 * 
 * <p>Ofrece funcionalidades para:
 * <ul>
 *   <li>Simular el proceso completo con visualización (Simulate)</li>
 *   <li>Resolver el problema midiendo el tiempo de ejecución (Solve)</li>
 * </ul>
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
import java.util.ArrayList;
import java.util.List;
public class MaxwellContest {
    /**
     * Resuelve el problema del demonio de Maxwell midiendo el tiempo de ejecución.
     * 
     * @param h Altura del contenedor
     * @param w Ancho del contenedor
     * @param d Posición vertical del demonio
     * @param b Cantidad de partículas azules
     * @param r Cantidad de partículas rojas
     * @param particles Matriz con los datos de las partículas [px, py, vx, vy]
     * @return Tiempo en segundos si se alcanza el objetivo, -1.0 en caso contrario
     */
    public static float Solve(int h, int w, int d, int b, int r, int[][] particles) {
        List<int[]> list = new ArrayList<>();
        for(int[] a : particles) list.add(a);
        MaxwellContainer container = new MaxwellContainer(w, h, d, r, b, list);
        container.makeVisible();
        container.openGate(10000);
        long start = System.currentTimeMillis();
        container.start();
        long elapsed = System.currentTimeMillis() - start;
        float secs = elapsed / 1000.0f;
        if(container.isGoal()){
            System.out.println("Goal reached in " + secs + " seconds");
            return secs;
        } else {
            System.out.println("Impossible to reach goal in " + secs + " seconds");
            return -1.0f;
        }
    }
    
    /**
     * Simula el proceso del demonio de Maxwell con visualización pero sin medición de tiempo.
     * 
     * @param h Altura del contenedor
     * @param w Ancho del contenedor
     * @param d Posición vertical del demonio
     * @param b Cantidad de partículas azules
     * @param r Cantidad de partículas rojas
     * @param particles Matriz con los datos de las partículas [px, py, vx, vy]
     */
    public static void Simulate(int h, int w, int d, int b, int r, int[][] particles) {
        List<int[]> list = new ArrayList<>();
        for(int[] a : particles) list.add(a);
        MaxwellContainer container = new MaxwellContainer(w, h, d, r, b, list);
        container.makeVisible();
        container.start();
        if(container.isGoal()){
            System.out.println("Goal reached");
        } else {
            System.out.println("Impossible to reach goal");
        }
    }
}
