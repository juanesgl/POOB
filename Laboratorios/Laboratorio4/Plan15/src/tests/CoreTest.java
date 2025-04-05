package src.tests;

import src.domain.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CoreTest{

    @Test
    public void shouldAddCourseToPlan15() {
        Plan15 plan15 = new Plan15();
    
        try {
            plan15.addCourse("DOSW", "Desarrollo y Operaciones Software", "4", "4");
            Unit unidad = plan15.consult("DOSW");
    
            assertNotNull("La materia DOSW debería existir en el plan", unidad);
            assertEquals("Desarrollo y Operaciones Software", unidad.name());
            assertEquals(4, unidad.credits());
        } catch (Plan15Exception e) {
            fail("No debería lanzar excepción al adicionar materia válida");
        }
    }
    
    @Test
    public void shouldListAllUnits() {
        Plan15 plan = new Plan15();
    
        String listado = plan.toString();
        assertTrue("El listado debe contener PRI1", listado.contains("PRI1"));
        assertTrue("El listado debe contener DDYA", listado.contains("DDYA"));
        assertTrue("El listado debe contener MPIN", listado.contains("MPIN"));
        assertTrue("El listado debe contener DOSW", listado.contains("DOSW"));
        assertTrue("El listado debe contener FCC", listado.contains("FCC"));
        assertTrue("El listado debe contener NFPE", listado.contains("NFPE"));
        assertTrue("Debe indicar 6 unidades en total", listado.startsWith("6 unidades"));
    }



    @Test
    public void shouldCalculateTheCreditsOfACoreCostume(){
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        c.addCourse(new Course("PRI1","Proyecto Integrador 1", 3, 3));
        c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
        c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3, 4));
        try {
            assertEquals(10,c.credits());
        } catch (Plan15Exception e){
            fail("Threw a exception");
        }
    }


    @Test
    public void shouldThrowExceptionIfCoreHasNoCourse(){
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        try {
            int price=c.credits();
            fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.IMPOSSIBLE,e.getMessage());
        }
    }


    @Test
    public void shouldThrowExceptionIfThereIsErrorInCredits(){
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        c.addCourse(new Course("PRI1","Proyecto Integrador 1", -3, 3));
        c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
        c.addCourse(new Course("MPIN", "Matematicas para Informatica", 3, 4));
        try {
            int price=c.credits();
            fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.CREDITS_ERROR,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfCreditsIsNotKnown(){
        Core c = new Core("NFCC", "Nucleo de Formacion Comun por Campo", 50);
        c.addCourse(new Course("PRI1","Proyecto Integrador 1", 3, 3));
        c.addCourse(new Course("DDYA", "Diseño de Datos y Algoritmos", 4, 4));
        c.addCourse(new Course("MPIN", "Matematicas para Informatica"));
        try {
            int price=c.credits();
            fail("Did not throw exception");
        } catch (Plan15Exception e) {
            assertEquals(Plan15Exception.CREDITS_UNKNOWN,e.getMessage());
        }
    }

}
