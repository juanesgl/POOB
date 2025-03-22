package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import domain.City;
import domain.Walker;

class WalkerTest {

    @Test
    void testWalkerMovesNorth() {
        City city = new City();
        Walker w = new Walker(city, 5, 5);

        w.decide(); 
        assertEquals(4, w.getRow(), "Walker debería haber subido una fila.");
    }

    @Test
    void testWalkerChangesState() {
        City city = new City();
        Walker w = new Walker(city, 5, 5);
        city.setItem(4, 5, new Walker(city, 4, 5)); 

        w.decide();
        assertTrue(w.isHappy() || w.isDissatisfied(), "Walker debería estar feliz o insatisfecho.");
    }

    @Test
    public void TryWalkerTest()
    {
    }
}

