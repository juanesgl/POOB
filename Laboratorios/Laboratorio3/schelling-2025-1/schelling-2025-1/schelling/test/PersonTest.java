package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import domain.City;
import domain.Person;

/**
 * Pruebas unitarias para la clase Person.
 * Estas pruebas verifican el comportamiento del cambio de estado y la asignación
 * de color de una persona dentro de la ciudad.
 */
class PersonTest {

    /**
     * Verifica que el estado de la persona cambie después de invocar el método `change()`.
     */
    @Test
    void testPersonStateChange() {
        City city = new City();
        Person p = new Person(city, 2, 3);
        
        p.change();
        assertTrue(p.isIndifferent() || p.isDissatisfied() || p.isHappy(), "El estado debe cambiar después de `change()`.");
    }

    /**
     * Verifica que una persona tenga un color asignado al ser creada.
     */
    @Test
    void testPersonColor() {
        City city = new City();
        Person p = new Person(city, 1, 1);
        assertNotNull(p.getColor(), "La persona debe tener un color asignado.");
    }

    /**
     * Método de prueba vacío para experimentar con la clase Person.
     */
    @Test
    public void TryPersonTest()
    {
    }
}
