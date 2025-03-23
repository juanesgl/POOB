package test;
import static org.junit.Assert.*;
import domain.*;

import org.junit.Test;

public class HoriTest {
    @Test
    public void horiMovesRight() {
        City city = new City();
        Hori hori = new Hori(city, 5, 5);
        city.setItem(5, 5, hori);

        hori.decide();
        assertEquals(5, hori.getRow());  // 🔄 No cambia la fila
        assertEquals(6, hori.getNextCol()); // 🔜 Debe querer moverse a (5,6)

        hori.move();
        assertEquals(6, hori.getColumn()); // ✅ Se movió a la derecha
    }

    @Test
    public void horiDoesNotMoveIfBlocked() {
        City city = new City();
        Hori hori = new Hori(city, 5, 5);
        Hori block = new Hori(city, 5, 6); // 🔴 Obstáculo justo a la derecha
        city.setItem(5, 5, hori);
        city.setItem(5, 6, block);

        hori.decide();
        hori.move();

        assertEquals(5, hori.getRow()); // ❌ No debe cambiar de fila
        assertEquals(5, hori.getColumn()); // ❌ No debe moverse
    }
}
