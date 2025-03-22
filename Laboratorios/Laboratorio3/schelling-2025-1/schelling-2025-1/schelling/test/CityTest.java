package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import domain.City;
import domain.Person;

class CityTest {

    @Test
    void testCitySize() {
        City city = new City();
        assertEquals(25, city.getSize(), "El tamaño de la ciudad debería ser 25.");
    }

    @Test
    void testSetAndGetItem() {
        City city = new City();
        Person p = new Person(city, 5, 5);
        city.setItem(5, 5, p);
        assertNotNull(city.getItem(5, 5), "El item en (5,5) no debería ser null.");
        assertEquals(p, city.getItem(5, 5), "El item en (5,5) debería ser la persona creada.");
    }


    @Test
    public void TryCityTest()
    {
    }
}



