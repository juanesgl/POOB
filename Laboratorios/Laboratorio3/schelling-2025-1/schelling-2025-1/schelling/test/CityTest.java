package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import domain.City;
import domain.Person;

/**
 * Pruebas unitarias para la clase City.
 * Estas pruebas verifican el comportamiento de la ciudad, su tamaño y la correcta
 * asignación de elementos en las celdas de la ciudad.
 */
class CityTest {

    /**
     * Verifica que el tamaño de la ciudad sea 25.
     */
    @Test
    void testCitySize() {
        City city = new City();
        assertEquals(25, city.getSize(), "El tamaño de la ciudad debería ser 25.");
    }

    /**
     * Verifica que se pueda establecer y obtener un item en una posición específica
     * de la ciudad.
     */
    @Test
    void testSetAndGetItem() {
        City city = new City();
        Person p = new Person(city, 5, 5);
        city.setItem(5, 5, p);
        assertNotNull(city.getItem(5, 5), "El item en (5,5) no debería ser null.");
        assertEquals(p, city.getItem(5, 5), "El item en (5,5) debería ser la persona creada.");
    }

    /**
     * Método de prueba vacío para experimentar con la clase City.
     */
    @Test
    public void TryCityTest()
    {
    }
}
