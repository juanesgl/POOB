import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para MaxwellContainer.
 * Se aseguran de que las funciones principales trabajen correctamente.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.0
 */

/*
public class MaxwellContainerC1Test {

    @Test
    public void accordingRSShouldCreateValidContainer() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        assertNotNull(container, "El contenedor debería crearse correctamente.");
    }

    @Test
    public void accordingRSShouldNotCreateInvalidContainer() {
        MaxwellContainer container = new MaxwellContainer(-100, -100);
        assertFalse(container.isVisible(), "El contenedor no debería ser visible con dimensiones inválidas.");
    }

    @Test
    public void accordingRSShouldAddRedParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red");
        assertEquals(1, container.particles().size(), "Debería haber una partícula roja en el contenedor.");
    }

    @Test
    public void accordingRSShouldAddBlueParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("blue");
        assertEquals(1, container.particles().size(), "Debería haber una partícula azul en el contenedor.");
    }

    @Test
    public void accordingRSShouldNotAddInvalidParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("green"); 
        assertEquals(0, container.particles().size(), "No debería agregarse una partícula con un color inválido.");
    }

    @Test
    public void accordingRSShouldRemoveParticle() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red");
        container.removeParticle();
        assertEquals(0, container.particles().size(), "Debería eliminarse la última partícula agregada.");
    }

    @Test
    public void accordingRSShouldAddDemon() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addDemon();
        assertEquals(1, container.demons().size(), "Debería haber un demonio en el contenedor.");
    }

    @Test
    public void accordingRSShouldRemoveDemon() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addDemon();
        container.deleteDemon();
        assertEquals(0, container.demons().size(), "El demonio debería eliminarse correctamente.");
    }
  
    @Test
    public void accordingRSShouldAddHole() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addHole();
        assertEquals(1, container.holes().size(), "Debería haber un agujero en el contenedor.");
    }

    @Test
    public void accordingRSShouldStartSimulation() {
        MaxwellContainer container = new MaxwellContainer(400, 200);
        container.addParticle("red");
        container.addParticle("blue");
        container.makeVisible(); 
        assertTrue(container.isVisible(), "La simulación debería estar corriendo y visible.");
    }
}
*/
