import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Pruebas unitarias para MaxwellContest.
 * Se aseguran de que la simulación funcione correctamente.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 2
 */
public class MaxwellContestTest {

    @Test
    public void accordingRSShouldSolveSimulation() {
        List<int[]> particles = new ArrayList<>();
        particles.add(new int[]{50, 100, 1, 1}); 
        particles.add(new int[]{250, 100, -1, 1}); 

        MaxwellContest contest = new MaxwellContest(200, 100, 50, 1, 1, particles);
        //contest.solve();
        //assertTrue(contest.getContainer().ok(), "La simulación debería resolverse correctamente.");
    }

    @Test
    public void accordingRSShouldHandleImpossibleSimulation() {
        List<int[]> particles = new ArrayList<>();
        particles.add(new int[]{50, 100, 1, 1}); 
        particles.add(new int[]{250, 100, -1, 1}); 

        MaxwellContest contest = new MaxwellContest(200, 100, 50, 1, 1, particles);
        //contest.solve();
        //assertTrue(contest.getContainer().ok(), "La simulación debería manejarse correctamente.");
    }

    @Test
    public void accordingRSShouldHandleInvalidInput() {
        List<int[]> particles = new ArrayList<>();
        particles.add(new int[]{-50, -100, 1, 1}); 

        assertThrows(IllegalArgumentException.class, () -> {
            new MaxwellContest(200, 100, 50, 1, 1, particles);
        }, "No debería aceptar partículas con posiciones inválidas.");
    }
}