package test;

import domain.TrafficLight;
import domain.City;
import domain.Item; // Importar la interfaz Item
import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.Test;

public class TrafficLightTest {

    @Test
    public void testTrafficLightInitialState() {
        City city = new City();
        TrafficLight trafficLight = new TrafficLight(city, 5, 5);

        // Verificar que el semáforo empieza en rojo
        assertEquals(Color.RED, trafficLight.getColor());
        assertTrue(trafficLight.isActive());
        assertEquals(Item.ROUND, trafficLight.shape()); // Usar Item.ROUND
    }

    @Test
    public void testTrafficLightColorChange() {
        City city = new City();
        TrafficLight trafficLight = new TrafficLight(city, 5, 5);

        // Verificar la secuencia de colores
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