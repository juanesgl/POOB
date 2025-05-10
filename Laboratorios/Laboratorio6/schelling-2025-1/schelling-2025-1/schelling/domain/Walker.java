package domain;

import java.awt.Color;
import java.io.*;

/**
 * Representa un agente que se mueve verticalmente en la ciudad.
 * Su color es verde y decide moverse hacia arriba si el espacio está vacío.
 */
public class Walker extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int nextRow; 

    /**
     * Crea un agente Walker en la ciudad en una posición específica.
     * El color inicial del Walker es verde y su estado es indiferente.
     *
     * @param city La ciudad en la que se encuentra el agente Walker.
     * @param row Fila donde se ubica el agente Walker.
     * @param col Columna donde se ubica el agente Walker.
     */
    public Walker(City city, int row, int col) {
        super(city, row, col);
        this.state = INDIFFERENT;
        this.color = Color.GREEN;
        this.nextRow = row; 
    }

    /**
     * El Walker decide su siguiente movimiento basado en si la fila siguiente está vacía.
     */
    @Override
    public void decide() {
        int newRow = getRow() - 1; 

        if (getCity().isEmpty(newRow, getColumn())) {
            nextRow = newRow; 
        } else {
            nextRow = getRow(); 
        }
        System.out.println("Walker en (" + getRow() + "," + getColumn() + ") ha decidido moverse a (" + nextRow + "," + getColumn() + ")");
    }

    /**
     * Verifica si el Walker puede moverse a la fila siguiente.
     *
     * @return true si puede moverse, false si no puede.
     */
    public boolean canMove() {
        return nextRow != getRow(); 
    }

    /**
     * Mueve al Walker a la fila siguiente si es posible.
     */
    public void move() {
        if (canMove() && getCity().isEmpty(nextRow, getColumn())) {
            System.out.println("Walker moviéndose de (" + getRow() + "," + getColumn() + ") a (" + nextRow + "," + getColumn() + ")");
            getCity().setItem(getRow(), getColumn(), null);
            setPosition(nextRow, getColumn());
            getCity().setItem(nextRow, getColumn(), this);
        } else {
            System.out.println("Walker en (" + getRow() + "," + getColumn() + ") NO se movió.");
        }
    }

    /**
     * Devuelve la siguiente fila a la que el Walker quiere moverse.
     *
     * @return La siguiente fila.
     */
    public int getNextRow() {
        return nextRow;
    }

    /**
     * Devuelve la forma del agente Walker, que es un cuadrado.
     *
     * @return SQUARE (2).
     */
    @Override
    public int shape() {
        return SQUARE;
    }
}
