package domain;

import java.awt.Color;
import java.io.*;

/**
 * Representa un agente que se mueve verticalmente hacia abajo en la ciudad.
 * Su color es magenta y decide moverse hacia abajo si el espacio está vacío.
 */
public class Bishop extends Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private int nextRow; 

    /**
     * Crea un agente Bishop en la ciudad en una posición específica.
     * El color inicial del Bishop es magenta y su estado es indiferente.
     *
     * @param city La ciudad en la que se encuentra el agente Bishop.
     * @param row Fila donde se ubica el agente Bishop.
     * @param col Columna donde se ubica el agente Bishop.
     */
    public Bishop(City city, int row, int col) {
        super(city, row, col);
        this.state = INDIFFERENT;
        this.color = Color.MAGENTA;
        this.nextRow = row;
    }

    /**
     * El Bishop decide su siguiente movimiento basado en si la fila siguiente está vacía.
     * Se mueve hacia abajo si el espacio está vacío.
     */
    @Override
    public void decide() {
        int newRow = getRow() + 1; 

        if (getCity().isEmpty(newRow, getColumn())) {
            nextRow = newRow; 
        } else {
            nextRow = getRow(); 
        }
    }

    /**
     * Verifica si el Bishop puede moverse a la fila siguiente.
     *
     * @return true si puede moverse, false si no puede.
     */
    public boolean canMove() {
        return nextRow != getRow();
    }

    /**
     * Mueve al Bishop a la fila siguiente si es posible.
     */
    public void move() {
        if (canMove() && getCity().isEmpty(nextRow, getColumn())) {
            System.out.println("Bishop bajando de (" + getRow() + "," + getColumn() + ") a (" + nextRow + "," + getColumn() + ")");
            getCity().setItem(getRow(), getColumn(), null);
            setPosition(nextRow, getColumn());
            getCity().setItem(nextRow, getColumn(), this);
        } else {
            System.out.println("Bishop en (" + getRow() + "," + getColumn() + ") NO se movió.");
        }
    }

    /**
     * Devuelve la siguiente fila a la que el Bishop quiere moverse.
     *
     * @return La siguiente fila.
     */
    public int getNextRow() {
        return nextRow;
    }

    /**
     * Devuelve la forma del agente Bishop, que es un cuadrado.
     *
     * @return SQUARE (2).
     */
    @Override
    public int shape() {
        return SQUARE;
    }
}
