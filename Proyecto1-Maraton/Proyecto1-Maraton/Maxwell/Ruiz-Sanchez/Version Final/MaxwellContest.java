import java.util.ArrayList;
import java.util.List;

/**
 * La clase MaxwellContest da la solución al problema del demonio de Maxwell.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 2 (Cycle 2)
 */
import java.util.ArrayList;
import java.util.List;
public class MaxwellContest {
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
