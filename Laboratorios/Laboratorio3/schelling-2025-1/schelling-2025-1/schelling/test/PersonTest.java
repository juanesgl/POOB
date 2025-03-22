package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import domain.City;
import domain.Person;

class PersonTest {

    @Test
    void testPersonStateChange() {
        City city = new City();
        Person p = new Person(city, 2, 3);
        
        p.change();
        assertTrue(p.isIndifferent() || p.isDissatisfied() || p.isHappy(), "El estado debe cambiar después de `change()`.");
    }

    @Test
    void testPersonColor() {
        City city = new City();
        Person p = new Person(city, 1, 1);
        assertNotNull(p.getColor(), "La persona debe tener un color asignado.");
    }

    @Test
    public void TryPersonTest()
    {
    }
}

