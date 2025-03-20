import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para MaxwellContainer.
 * Se aseguran de que las funciones principales trabajen correctamente.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.1
 */
public class MaxwellContainerC1Test {

    @Test
    public void accordingRSShouldCreateValidContainer() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        assertTrue(container.ok(), "El contenedor debería crearse correctamente.");
    }

    @Test
    public void accordingRSShouldNotCreateInvalidContainer() {
        MaxwellContainer container = new MaxwellContainer(-100, -100);
        assertFalse(container.ok(), "El contenedor no debería crearse con dimensiones inválidas.");
    }

    @Test
    public void accordingRSShouldAddRedParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red", 50, 100, 1, 1);
        assertTrue(container.ok(), "La partícula roja debería agregarse correctamente.");
    }

    @Test
    public void accordingRSShouldAddBlueParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("blue", 250, 100, -1, 1);
        assertTrue(container.ok(), "La partícula azul debería agregarse correctamente.");
    }

    @Test
    public void accordingRSShouldNotAddInvalidParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        assertThrows(IllegalArgumentException.class, () -> {
            container.addParticle("green", 50, 100, 1, 1);
        }, "No debería agregarse una partícula con un color inválido.");
        assertTrue(container.ok(), "El estado del contenedor debería seguir siendo válido.");
    }

    @Test
    public void accordingRSShouldRemoveParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red", 50, 100, 1, 1);
        container.removeParticle();
        assertTrue(container.ok(), "Debería eliminarse la última partícula agregada.");
    }

    @Test
    public void accordingRSShouldAddDemon() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addDemon(100);
        assertTrue(container.ok(), "Debería agregarse un demonio correctamente.");
    }

    @Test
    public void accordingRSShouldRemoveDemon() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addDemon(100);
        container.deleteDemon();
        assertTrue(container.ok(), "El demonio debería eliminarse correctamente.");
    }

    @Test
    public void accordingRSShouldAddHole() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addHole();
        assertTrue(container.ok(), "El agujero debería agregarse correctamente.");
    }

    @Test
    public void accordingRSShouldStartSimulation() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red", 50, 100, 1, 1);
        container.addParticle("blue", 250, 100, -1, 1);
        container.makeVisible();
        assertTrue(container.ok(), "La simulación debería estar corriendo y visible.");
    }
}