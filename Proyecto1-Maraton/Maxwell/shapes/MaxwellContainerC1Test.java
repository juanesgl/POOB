import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MaxwellContainerC1Test.
 *
 * This test suite verifies the basic functionality of MaxwellContainer.
 * Following the required naming format using initials "RS".
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.0
 */
public class MaxwellContainerC1Test {
    private MaxwellContainer container;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        container = new MaxwellContainer(200, 400); 
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        container = null;
    }

    /**
     * RS: Test that the MaxwellContainer initializes correctly.
     */
    @Test
    public void accordingRSShouldCreateContainer() {
        assertNotNull(container, "The container should not be null after creation.");
    }

    /**
     * RS: Test that the container's visibility can be toggled.
     */
    @Test
    public void accordingRSShouldToggleVisibility() {
        container.makeVisible();
       assertTrue(container.isVisible(), "The container should be visible after makeVisible().");

        container.makeInvisible();
        assertFalse(container.isVisible(), "The container should be invisible after makeInvisible().");
    }
}
