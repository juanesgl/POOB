package test;

import static org.junit.Assert.*;
import domain.*;
import org.junit.Test;

/**
 * Pruebas unitarias para la clase Hori.
 * Estas pruebas verifican el comportamiento de los movimientos del objeto Hori
 * dentro de la ciudad y su interacción con obstáculos.
 */
public class HoriTest {

    /**
     * Verifica que Hori se mueva correctamente a la derecha si no está bloqueado.
     */
    @Test
    public void horiMovesRight() {
        City city = new City();
        Hori hori = new Hori(city, 5, 5);
        city.setItem(5, 5, hori);

        hori.decide();
        assertEquals(5, hori.getRow());  // No cambia la fila
        assertEquals(6, hori.getNextCol()); // Se mueve a la derecha

        hori.move();
        assertEquals(6, hori.getColumn()); // Se movió a la derecha
    }

    /**
     * Verifica que Hori no se mueva si está bloqueado por un objeto Hori en la
     * posición de destino.
     */
    @Test
    public void horiDoesNotMoveIfBlocked() {
        City city = new City();
        Hori hori = new Hori(city, 5, 5);
        Hori block = new Hori(city, 5, 6); // Obstáculo justo a la derecha
        city.setItem(5, 5, hori);
        city.setItem(5, 6, block);

        hori.decide();
        hori.move();

        assertEquals(5, hori.getRow()); // No cambia la fila
        assertEquals(5, hori.getColumn()); // No se mueve
    }
}
