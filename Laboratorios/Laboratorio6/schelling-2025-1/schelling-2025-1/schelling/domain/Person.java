package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Representa una persona en la ciudad, que es un agente que puede tomar decisiones
 * basadas en el estado de sus vecinos. La persona puede cambiar su estado a feliz, indiferente
 * o insatisfecha dependiendo de la cantidad de vecinos similares.
 */
public class Person extends Agent implements Item,Serializable {
    private transient City city; 
    private int row, column; 
    protected Color color; 

    /**
     * Crea una nueva persona en una ciudad en una posición específica.
     * Inicializa el color de la persona como azul.
     *
     * @param city La ciudad en la que se encuentra la persona.
     * @param row La fila en la que está ubicada la persona.
     * @param column La columna en la que está ubicada la persona.
     */
    public Person(City city, int row, int column) {
        this.city = city;
        this.row = row;
        this.column = column;
        this.city.setItem(row, column, (Item) this); 
        color = Color.blue; 
    }

    /**
     * Devuelve la fila donde está ubicada la persona.
     *
     * @return La fila en la que está ubicada la persona.
     */
    public final int getRow() {
        return row;
    }

    /**
     * Devuelve la columna donde está ubicada la persona.
     *
     * @return La columna en la que está ubicada la persona.
     */
    public final int getColumn() {
        return column;
    }

    /**
     * Establece la ciudad en la que se encuentra la persona.
     *
     * @param city La ciudad en la que se encuentra la persona.
     */
    public void setCity(City city) {
    this.city = city;
}
    /**
     * Devuelve el color de la persona.
     *
     * @return El color de la persona.
     */
    public final Color getColor() {
        return color;
    }

    /**
     * El agente toma una decisión basándose en el número de vecinos similares.
     * Si tiene al menos 3 vecinos similares, el estado se cambia a feliz.
     * Si tiene al menos 1 vecino similar, el estado se cambia a indiferente.
     * Si no tiene vecinos similares, el estado se cambia a insatisfecho.
     */
    @Override
    public void decide() {
        int similarNeighbors = city.neighborsEquals(row, column);
        if (similarNeighbors >= 3) {
            state = Agent.HAPPY;
        } else if (similarNeighbors >= 1) {
            state = Agent.INDIFFERENT;
        } else {
            state = Agent.DISSATISFIED;
        }
        System.out.println("Agente en (" + row + ", " + column + ") está: " + state);
    }

    /**
     * Método para cambiar el estado de la persona, que incrementa los pasos.
     */
    @Override
    public final void change() {
        step(); 
    }

    /**
     * Establece una nueva posición para la persona en la ciudad.
     *
     * @param row Nueva fila donde se ubicará la persona.
     * @param column Nueva columna donde se ubicará la persona.
     */
    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Devuelve la ciudad en la que se encuentra la persona.
     *
     * @return La ciudad donde se encuentra la persona.
     */
    public City getCity() {
        return this.city;
    }
public void setColor(Color color) {
    this.color = color;
}

public void setStateFromString(String state) {
    switch (state.toUpperCase()) {
        case "HAPPY":
            this.state = HAPPY;
            break;
        case "INDIFFERENT":
            this.state = INDIFFERENT;
            break;
        case "DISSATISFIED":
            this.state = DISSATISFIED;
            break;
        default:
            this.state = INDIFFERENT;
    }
}

public String getStateString() {
    switch (state) {
        case HAPPY: return "HAPPY";
        case INDIFFERENT: return "INDIFFERENT";
        case DISSATISFIED: return "DISSATISFIED";
        default: return "UNKNOWN";
    }
}

}
