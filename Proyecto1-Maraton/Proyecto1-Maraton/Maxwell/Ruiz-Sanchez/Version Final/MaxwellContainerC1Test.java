import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la clase MaxwellContainer que verifican el correcto funcionamiento
 * de sus métodos principales según los requisitos del sistema. Incluye pruebas para:
 * - Creación del contenedor
 * - Adición/eliminación de partículas
 * - Manejo de demonios y agujeros
 * - Inicio de simulación
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class MaxwellContainerC1Test {

    /**
     * Prueba que el contenedor se crea correctamente con dimensiones válidas.
     * Verifica que el estado interno del contenedor sea válido después de la creación.
     */
    @Test
    public void accordingRSShouldCreateValidContainer() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        assertTrue(container.ok(), "El contenedor debería crearse correctamente con dimensiones válidas (400x200)");
    }

    /**
     * Prueba que el contenedor no se crea con dimensiones inválidas.
     * Verifica que el estado interno del contenedor refleje el error.
     */
    @Test
    public void accordingRSShouldNotCreateInvalidContainer() {
        MaxwellContainer container = new MaxwellContainer(-100, -100);
        assertFalse(container.ok(), "El contenedor no debería crearse con dimensiones negativas (-100x-100)");
    }

    /**
     * Prueba la adición correcta de una partícula roja.
     * Verifica que el contenedor mantenga su estado válido.
     */
    @Test
    public void accordingRSShouldAddRedParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red", 50, 100, 1, 1);
        assertTrue(container.ok(), "Debería aceptar partícula roja en posición válida (50,100) con velocidad (1,1)");
    }

    /**
     * Prueba la adición correcta de una partícula azul.
     * Verifica que el contenedor mantenga su estado válido.
     */
    @Test
    public void accordingRSShouldAddBlueParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("blue", 250, 100, -1, 1);
        assertTrue(container.ok(), "Debería aceptar partícula azul en posición válida (250,100) con velocidad (-1,1)");
    }

    /**
     * Prueba que no se puedan agregar partículas con color inválido.
     * Verifica que se lance la excepción adecuada y que el contenedor mantenga su estado válido.
     */
    @Test
    public void accordingRSShouldNotAddInvalidParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        assertThrows(IllegalArgumentException.class, () -> {
            container.addParticle("green", 50, 100, 1, 1);
        }, "Debería rechazar partícula con color inválido (green)");
        assertTrue(container.ok(), "El contenedor debería mantenerse válido después del intento fallido");
    }

    /**
     * Prueba la eliminación correcta de partículas.
     * Verifica que el contenedor mantenga su estado válido después de remover una partícula.
     */
    @Test
    public void accordingRSShouldRemoveParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red", 50, 100, 1, 1);
        container.removeParticle();
        assertTrue(container.ok(), "Debería permitir eliminar la última partícula agregada");
    }

    /**
     * Prueba la adición correcta de un demonio.
     * Verifica que el contenedor mantenga su estado válido.
     */
    @Test
    public void accordingRSShouldAddDemon() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addDemon(100);
        assertTrue(container.ok(), "Debería aceptar demonio en posición vertical válida (y=100)");
    }

    /**
     * Prueba la eliminación correcta de demonios.
     * Verifica que el contenedor mantenga su estado válido después de remover un demonio.
     */
    @Test
    public void accordingRSShouldRemoveDemon() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addDemon(100);
        container.deleteDemon();
        assertTrue(container.ok(), "Debería permitir eliminar el último demonio agregado");
    }

    /**
     * Prueba la adición correcta de un agujero.
     * Verifica que el contenedor mantenga su estado válido.
     */
    @Test
    public void accordingRSShouldAddHole() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addHole();
        assertTrue(container.ok(), "Debería aceptar la creación de un agujero en posición aleatoria válida");
    }

    /**
     * Prueba el inicio correcto de la simulación.
     * Verifica que el contenedor mantenga su estado válido con partículas y visualización activa.
     */
    @Test
    public void accordingRSShouldStartSimulation() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red", 50, 100, 1, 1);
        container.addParticle("blue", 250, 100, -1, 1);
        container.makeVisible();
        assertTrue(container.ok(), "Debería iniciar simulación correctamente con partículas y visualización activa");
    }
}