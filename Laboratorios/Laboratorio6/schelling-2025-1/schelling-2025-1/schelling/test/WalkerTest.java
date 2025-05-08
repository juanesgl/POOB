package test;

import static org.junit.Assert.*;
import domain.*;
import org.junit.Test;

/**
 * Pruebas unitarias para la clase Walker.
 * Estas pruebas verifican el comportamiento de los movimientos de los caminantes
 * dentro de la ciudad y la interacción con otros objetos.
 */
public class WalkerTest {

    /**
     * Verifica que el caminante se mueva hacia arriba en la ciudad.
     */
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

    /**
     * Verifica que el caminante no se mueva si está bloqueado por otro caminante.
     */
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
