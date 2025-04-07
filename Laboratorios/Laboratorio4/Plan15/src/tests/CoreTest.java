package src.tests;

import src.domain.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test class for the Core class.
 */
public class CoreTest {

    /**
     * Tests the select() method with a prefix that results in an IndexOutOfBoundsException.
     * @throws Plan15Exception If any error occurs during the test execution.
     */
    @Test
    public void testSelectWithOutOfBounds() throws Plan15Exception {
        Plan15 plan = new Plan15();
        plan.addCourse("TEST1", "Test Course 1", "3", "10");
        plan.addCourse("TEST2", "Test Course 2", "3", "10");
        plan.addCourse("TEST3", "Test Course 3", "3", "10");
        plan.addCourse("TEST4", "Test Course 4", "3", "10");

        try {
            plan.select("TEST");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception was thrown
        }
    }

    /**
     * Tests the addCourse() method with user confirmation.
     */
    @Test
    public void testAddCourseWithConfirmation() {
        Plan15 plan = new Plan15();
        boolean courseAdded = false;

        String confirmResponse = "yes";

        if (confirmResponse.equalsIgnoreCase("yes")) {
            try {
                plan.addCourse("TEST", "Test Course", "3", "10");
                courseAdded = true;
            } catch (Plan15Exception e) {
                System.out.println("Error adding course: " + e.getMessage());
            }
        }

        assertTrue("Course should have been added if user confirmed", courseAdded);
        assertNotNull("Course should be in the plan after being added", plan.consult("TEST"));
    }

    /**
     * Tests the addCore() method with an invalid percentage.
     */
    @Test
    public void testAddCoreWithInvalidPercentage() {
        Plan15 plan = new Plan15();
        boolean thrown = false;

        try {
            plan.addCore("TEST", "Test Core", "150", "PRI1");
        } catch (Plan15Exception e) {
            thrown = true;
            assertEquals("El porcentaje debe estar entre 0 y 100.", e.getMessage());
        }

        assertTrue("Expected exception for out-of-range percentage", thrown);
    }

    /**
     * Tests the addCourse() method with non-integer values for credits and in-person hours.
     */
    @Test
    public void testAddCourseWithInvalidIntegers() {
        Plan15 plan = new Plan15();
        try {
            plan.addCourse("ERR1", "Invalid Course", "cuatro", "tres");
            fail("Expected exception for non-integer values.");
        } catch (Plan15Exception e) {
            assertTrue(e.getMessage().toLowerCase().contains("números enteros"));
        }
    }

    /**
     * Tests the addCore() method with a non-existent course.
     */
    @Test
    public void testUnidadNoExiste() {
        Plan15 p = new Plan15();
        try {
            p.addCore("XXX", "nombre", "80", "NOEXISTE");
            fail("Should have thrown exception for non-existent course");
        } catch (Plan15Exception e) {
            assertTrue(e.getMessage().contains("NOEXISTE"));
        }
    }

    /**
     * Tests the addCourse() method to add a valid course to the plan.
     */
    @Test
    public void shouldAddCourseToPlan15() {
        Plan15 plan15 = new Plan15();

        try {
            plan15.addCourse("DOSW", "Desarrollo y Operaciones Software", "4", "4");
            Unit unidad = plan15.consult("DOSW");

            assertNotNull("DOSW course should exist in the plan", unidad);
            assertEquals("Desarrollo y Operaciones Software", unidad.name());
            assertEquals(4, unidad.credits());
        } catch (Plan15Exception e) {
            fail("Should not throw exception when adding valid course");
        }
    }

    /**
     * Tests the toString() method to list all units in the plan.
     */
    @Test
    public void shouldListAllUnits() {
        Plan15 plan = new Plan15();

        String listado = plan.toString();
        assertTrue("Listing should contain PRI1", listado.contains("PRI1"));
        assertTrue("Listing should contain DDYA", listado.contains("DDYA"));
        assertTrue("Listing should contain MPIN", listado.contains("MPIN"));
        assertTrue("Listing should contain DOSW", listado.contains("DOSW"));
        assertTrue("Listing should contain FCC", listado.contains("FCC"));
        assertTrue("Listing should contain NFPE", listado.contains("NFPE"));
        assertTrue("Should indicate 6 units in total", listado.startsWith("6 unidades"));
    }

    /**
     * Tests the credits() method to calculate the credits of a core.
     */
    @Test
    public void shouldCalculateTheCreditsOfACoreCostume() {
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        c.addCourse(new Course("PRI1", "Proyecto Integrador 1", 3, 3));
        c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
        c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3, 4));
        try {
            assertEquals(10, c.credits());
        } catch (Plan15Exception e) {
            fail("Threw an exception");
        }
    }

    /**
     * Tests that an exception is thrown if a core has no courses.
     */
    @Test
    public void shouldThrowExceptionIfCoreHasNoCourse() {
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        try {
            c.credits();
            fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.IMPOSSIBLE, e.getMessage());
        }
    }

    /**
     * Tests that an exception is thrown if there is an error in the credits of a course.
     */
    @Test
    public void shouldThrowExceptionIfThereIsErrorInCredits() {
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        c.addCourse(new Course("PRI1", "Proyecto Integrador 1", -3, 3));
        c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
        c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3, 4));
        try {
            c.credits();
            fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.CREDITS_ERROR, e.getMessage());
        }
    }

    /**
     * Tests that an exception is thrown if the credits of a course are unknown.
     */
    @Test
    public void shouldThrowExceptionIfCreditsIsNotKnown() {
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        c.addCourse(new Course("PRI1", "Proyecto Integrador 1", 3, 3));
        c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
        c.addCourse(new Course("MPIN", "Matematicas para Informatica"));
        try {
            c.credits();
            fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.CREDITS_UNKNOWN, e.getMessage());
        }
    }
}