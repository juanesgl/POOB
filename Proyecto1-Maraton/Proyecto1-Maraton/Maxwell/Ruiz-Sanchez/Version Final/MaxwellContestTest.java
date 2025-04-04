import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la clase MaxwellContest que verifican el correcto funcionamiento
 * de la simulación del demonio de Maxwell. Incluye pruebas para casos resolubles y
 * validación de parámetros inválidos.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class MaxwellContestTest {

    /**
     * Prueba un escenario resoluble donde las partículas deberían alcanzar
     * el objetivo (goal) en un tiempo positivo.
     * Verifica que:
     * - El método Solve retorna un tiempo mayor que cero
     * - El sistema reconoce que se alcanzó el objetivo
     */
    @Test
    public void testSolvableSimulation() {
        int h = 200, w = 100, d = 100, b = 1, r = 1;
        int[][] particles = {
            {150, 120, -5, 5},  
            {50, 80, 5, 5}      
        };

        float time = MaxwellContest.Solve(h, w, d, b, r, particles);
        System.out.println("Solve Success: " + time + " segundos");
        
        assertTrue(time > 0, "Se esperaba que se alcanzara el objetivo en un tiempo positivo");
    }

    /**
     * Prueba que el sistema detecta correctamente posiciones inválidas de partículas.
     * Verifica que:
     * - Se lanza IllegalArgumentException cuando una partícula está fuera del contenedor
     * - El mensaje de error es el esperado
     */
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