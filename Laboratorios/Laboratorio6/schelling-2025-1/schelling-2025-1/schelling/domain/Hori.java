package domain;

import java.awt.Color;

/**
 * Representa un agente que se mueve horizontalmente en la ciudad.
 * Su color es naranja y decide moverse a la derecha si el espacio está vacío.
 */
public class Hori extends Person {
    private int nextCol; 

    /**
     * Crea un agente Hori en la ciudad en una posición específica.
     * El color inicial del Hori es naranja y su estado es indiferente.
     *
     * @param city La ciudad en la que se encuentra el agente Hori.
     * @param row Fila donde se ubica el agente Hori.
     * @param col Columna donde se ubica el agente Hori.
     */
    public Hori(City city, int row, int col) {
        super(city, row, col);
        this.state = INDIFFERENT;
        this.color = Color.ORANGE;
        this.nextCol = col; 
    }

    /**
     * El Hori decide su siguiente movimiento basado en si la columna siguiente está vacía.
     */
    @Override
    public void decide() {
        int newCol = getColumn() + 1; 

        if (getCity().isEmpty(getRow(), newCol)) {
            nextCol = newCol; 
        } else {
            nextCol = getColumn(); 
        }
    }

    /**
     * Verifica si el Hori puede moverse a la columna siguiente.
     *
     * @return true si puede moverse, false si no puede.
     */
    public boolean canMove() {
        return nextCol != getColumn();
    }

    /**
     * Mueve al Hori a la columna siguiente si es posible.
     */
    public void move() {
        if (canMove() && getCity().isEmpty(getRow(), nextCol)) {
            System.out.println("Hori moviéndose de (" + getRow() + "," + getColumn() + ") a (" + getRow() + "," + nextCol + ")");
            getCity().setItem(getRow(), getColumn(), null);
            setPosition(getRow(), nextCol);
            getCity().setItem(getRow(), nextCol, this);
        } else {
            System.out.println("Hori en (" + getRow() + "," + getColumn() + ") NO se movió.");
        }
    }

    /**
     * Devuelve la forma del agente Hori, que es un cuadrado.
     *
     * @return SQUARE (2).
     */
    @Override
    public int shape() {
        return SQUARE;
    }

    /**
     * Devuelve la siguiente columna a la que el Hori quiere moverse.
     *
     * @return La siguiente columna.
     */
    public int getNextCol() {
        return nextCol;
    }
}
