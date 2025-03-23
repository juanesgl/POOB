package domain;
<<<<<<< HEAD
import java.awt.Color;
/*
public class TrafficLight extends Agent {
    private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.GREEN};
    private int stateIndex;

    public TrafficLight(City city, int row, int col) {
        super(city, row, col);
        this.stateIndex = 0;
=======

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
>>>>>>> 2d45b9fd3aecf8a0e93634b3072d8488d3c1041e
    }

    @Override
    public void decide() {
<<<<<<< HEAD
        change();  // 🚦 Cambia el color en cada ciclo
    }

    public void change() {
        stateIndex = (stateIndex + 1) % COLORS.length;
=======
        currentState = (currentState + 1) % colors.length; // Cambia al siguiente estado
>>>>>>> 2d45b9fd3aecf8a0e93634b3072d8488d3c1041e
    }

    @Override
    public Color getColor() {
<<<<<<< HEAD
        return COLORS[stateIndex];
    }

    @Override
    public String toString() {
        return switch (stateIndex) {
            case 0 -> "🔴";
            case 1 -> "🟡";
            case 2 -> "🟢";
            default -> "❓";
        };
    }
}
*/
=======
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
>>>>>>> 2d45b9fd3aecf8a0e93634b3072d8488d3c1041e
