
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
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
    public void testSolvableSimulation() {
        int h = 200, w = 100, d = 100, b = 1, r = 1;
        int[][] particles = {
            {150, 120, -5, 5}, 
            {50, 80, 5, 5}     };

        float time = MaxwellContest.Solve(h, w, d, b, r, particles);
        System.out.println("Solve Success: " + time + " segundos");
        assertTrue(time > 0, "Se esperaba que se alcanzara el objetivo en un tiempo positivo.");
    }

    @Test
    public void testInvalidParticlePosition() {
        int h = 200, w = 100, d = 100, b = 1, r = 0;
        int[][] particles = {
            {200, 100, 1, 0} 
        };

        assertThrows(
            IllegalArgumentException.class,
            () -> MaxwellContest.Solve(h, w, d, b, r, particles),
            "Debería lanzar excepción por posición inválida"
        );
    }
}
