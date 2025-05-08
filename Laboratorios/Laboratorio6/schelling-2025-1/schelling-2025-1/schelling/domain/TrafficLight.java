package domain;

import java.awt.Color;

/**
 * Representa un semáforo en una ciudad, con un estado cíclico (rojo, amarillo, verde, amarillo).
 * El semáforo cambia de estado en un ciclo predeterminado, y puede ser utilizado en una ciudad 
 * para gestionar el flujo del tráfico.
 * Implementa la interfaz {@link Item} para ser manipulado en el sistema de la ciudad.
 */
public class TrafficLight implements Item {
    private int currentState; 
    private final Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.YELLOW}; 
    private City city; 
    private int row, column; 

    /**
     * Constructor para crear un nuevo semáforo en la ciudad en una ubicación específica.
     * El semáforo comienza con el estado "rojo".
     *
     * @param city La ciudad en la que se encuentra el semáforo.
     * @param row La fila en la que está ubicado el semáforo en la ciudad.
     * @param column La columna en la que está ubicado el semáforo en la ciudad.
     */
    public TrafficLight(City city, int row, int column) {
        this.city = city;
        this.row = row;
        this.column = column;
        this.currentState = 0; 
        this.city.setItem(row, column, this); 
    }

    /**
     * Cambia el estado del semáforo al siguiente en el ciclo (rojo -> amarillo -> verde -> amarillo).
     * Este método se invoca en cada "tic-tac" del semáforo.
     */
    @Override
    public void decide() {
        currentState = (currentState + 1) % colors.length; 
    }

    /**
     * Devuelve el color actual del semáforo según su estado.
     *
     * @return El color actual del semáforo.
     */
    @Override
    public Color getColor() {
        return colors[currentState]; 
    }

    /**
     * Indica si el semáforo está activo. En este caso, siempre está activo.
     *
     * @return true, ya que el semáforo siempre está activo.
     */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Devuelve la forma del semáforo, que es redonda.
     *
     * @return El valor {@link Item#ROUND} que representa la forma redonda del semáforo.
     */
    @Override
    public int shape() {
        return Item.ROUND; 
    }

    /**
     * Indica si este objeto es un agente. En este caso, no es un agente.
     *
     * @return false, indicando que el semáforo no es un agente.
     */
    @Override
    public boolean isAgent() {
        return false; 
    }

    /**
     * Cambia el estado del semáforo en cada "tic-tac", invocando el método {@link #decide()}.
     */
    @Override
    public void change() {
        decide(); 
    }
}
