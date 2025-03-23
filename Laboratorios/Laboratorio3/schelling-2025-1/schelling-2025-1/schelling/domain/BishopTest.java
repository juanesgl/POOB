package domain;
import static org.junit.Assert.*;
import domain.*;

import org.junit.Test;

public class BishopTest {
    @Test
    public void bishopMovesDown() {
        City city = new City();
        Bishop bishop = new Bishop(city, 3, 3);
        city.setItem(3, 3, bishop);

        bishop.decide();
        assertEquals(4, bishop.getNextRow()); 

        bishop.move();
        assertEquals(4, bishop.getRow()); 
    }

    @Test
    public void bishopDoesNotMoveIfBlocked() {
        City city = new City();
        Bishop bishop = new Bishop(city, 3, 3);
        Bishop block = new Bishop(city, 4, 3); 
        city.setItem(3, 3, bishop);
        city.setItem(4, 3, block);

        bishop.decide();
        bishop.move();

        assertEquals(3, bishop.getRow()); 
    }
}
