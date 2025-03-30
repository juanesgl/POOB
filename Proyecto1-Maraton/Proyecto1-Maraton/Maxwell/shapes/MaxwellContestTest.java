
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
        // Cámaras pequeñas: altura = 200, ancho por cámara = 100 (contenedor total = 200)
        // b = 1 (azul) y r = 1 (roja)
        // Demon en d = 100 (centro vertical)
        // Para que crucen, la partícula azul (primera) se crea en el lado derecho (x ≥ 100) y se mueve a la izquierda;
        // la partícula roja (segunda) se crea en el lado izquierdo (x < 100) y se mueve a la derecha.
        int h = 200, w = 100, d = 100, b = 1, r = 1;
        int[][] particles = {
            {150, 120, -5, 5},  // Azul: inicia en x=150 y se mueve a la izquierda
            {50, 80, 5, 5}      // Roja: inicia en x=50 y se mueve a la derecha
        };

        float time = MaxwellContest.Solve(h, w, d, b, r, particles);
        System.out.println("Solve Success: " + time + " segundos");
        assertTrue(time > 0, "Se esperaba que se alcanzara el objetivo en un tiempo positivo.");
    }

    @Test
    public void testInvalidParticlePosition() {
        // Partícula fuera de los límites: en un contenedor de 200 de ancho (w=100 por cámara),
        // la coordenada x debe ser menor a 200; usamos x = 200 para provocar la excepción.
        int h = 200, w = 100, d = 100, b = 1, r = 0;
        int[][] particles = {
            {200, 100, 1, 0}  // Posición inválida: x no puede ser igual al ancho total (200)
        };

        assertThrows(
            IllegalArgumentException.class,
            () -> MaxwellContest.Solve(h, w, d, b, r, particles),
            "Debería lanzar excepción por posición inválida"
        );
    }
}
