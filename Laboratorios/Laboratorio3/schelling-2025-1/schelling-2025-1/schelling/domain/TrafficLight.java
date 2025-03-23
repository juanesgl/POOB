package domain;

import java.awt.Color;

public class TrafficLight implements Item {
    private int currentState; // 0: rojo, 1: amarillo, 2: verde, 3: amarillo
    private final Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.YELLOW};
    private City city;
    private int row, column;

    public TrafficLight(City city, int row, int column) {
        this.city = city;
        this.row = row;
        this.column = column;
        this.currentState = 0; // Empieza en rojo
        this.city.setItem(row, column, this); // Se añade a la ciudad
    }

    @Override
    public void decide() {
        currentState = (currentState + 1) % colors.length; // Cambia al siguiente estado
    }

    @Override
    public Color getColor() {
        return colors[currentState]; // Devuelve el color actual
    }

    @Override
    public boolean isActive() {
        return true; // Siempre está activo
    }

    @Override
    public int shape() {
        return Item.ROUND; // Forma redonda
    }

    @Override
    public boolean isAgent() {
        return false; // No es un agente
    }

    @Override
    public void change() {
        decide(); // Cambia de estado en cada tic-tac
    }
}