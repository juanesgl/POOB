package test;
import static org.junit.Assert.*;
import domain.*;

import org.junit.Test;

public class WalkerTest {
    @Test
    public void walkerMovesUp() {
        City city = new City();
        Walker walker = new Walker(city, 5, 5);
        city.setItem(5, 5, walker);

        walker.decide();
        assertEquals(4, walker.getNextRow()); 

        walker.move();
        assertEquals(4, walker.getRow()); 
    }

    @Test
    public void walkerDoesNotMoveIfBlocked() {
        City city = new City();
        Walker walker = new Walker(city, 5, 5);
        Walker block = new Walker(city, 4, 5); 
        city.setItem(5, 5, walker);
        city.setItem(4, 5, block);

        walker.decide();
        walker.move();

        assertEquals(5, walker.getRow()); 
    }
}
