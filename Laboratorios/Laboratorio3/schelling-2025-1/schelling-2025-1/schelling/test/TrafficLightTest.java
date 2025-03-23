package test;

import domain.TrafficLight;
import domain.City;
import domain.Item; 
import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Pruebas unitarias para la clase TrafficLight.
 * Estas pruebas verifican el comportamiento de los semáforos dentro de la ciudad,
 * su color inicial y los cambios de color.
 */
public class TrafficLightTest {

    /**
     * Verifica que el semáforo comience en color rojo y esté activo.
     */
    @Test
    public void testTrafficLightInitialState() {
        City city = new City();
        TrafficLight trafficLight = new TrafficLight(city, 5, 5);

        assertEquals(Color.RED, trafficLight.getColor());
        assertTrue(trafficLight.isActive());
        assertEquals(Item.ROUND, trafficLight.shape()); 
    }

    /**
     * Verifica que el semáforo cambie correctamente de color en la secuencia
     * rojo -> amarillo -> verde -> amarillo -> rojo.
     */
    @Test
    public void testTrafficLightColorChange() {
        City city = new City();
        TrafficLight trafficLight = new TrafficLight(city, 5, 5);

        trafficLight.change(); // Cambia a amarillo
        assertEquals(Color.YELLOW, trafficLight.getColor());

        trafficLight.change(); // Cambia a verde
        assertEquals(Color.GREEN, trafficLight.getColor());

        trafficLight.change(); // Cambia a amarillo
        assertEquals(Color.YELLOW, trafficLight.getColor());

        trafficLight.change(); // Cambia a rojo
        assertEquals(Color.RED, trafficLight.getColor());
    }
}
