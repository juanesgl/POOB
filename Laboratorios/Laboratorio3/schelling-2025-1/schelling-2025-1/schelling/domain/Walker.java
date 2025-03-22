package domain;
import java.awt.Color;

public class Walker extends Person {
    private int nextRow; // Fila donde intentará moverse

    public Walker(City city, int row, int col) {
        super(city, row, col);
        this.state = INDIFFERENT;
        this.color = Color.GREEN;
        this.nextRow = row; // Inicialmente se queda en su posición actual
    }

    @Override
    public void decide() {
        int newRow = getRow() - 1; // Intenta moverse hacia arriba

        if (getCity().isEmpty(newRow, getColumn())) {
            nextRow = newRow; // 🔴 Solo guarda la decisión, no se mueve aún
        } else {
            nextRow = getRow(); // Se queda en su lugar si no puede moverse
        }
        System.out.println("Walker en (" + getRow() + "," + getColumn() + ") ha decidido moverse a (" + nextRow + "," + getColumn() + ")");
    }

    public boolean canMove() {
        return nextRow != getRow(); // Solo se moverá si su posición cambió
    }

    public void move() {
        if (canMove() && getCity().isEmpty(nextRow, getColumn())) { // Verifica que nadie más tomó el espacio
            System.out.println("✅ Walker moviéndose de (" + getRow() + "," + getColumn() + ") a (" + nextRow + "," + getColumn() + ")");
            getCity().setItem(getRow(), getColumn(), null);
            setPosition(nextRow, getColumn());
            getCity().setItem(nextRow, getColumn(), this);
        } else {
            System.out.println("❌ Walker en (" + getRow() + "," + getColumn() + ") NO se movió.");
        }
    }

    public int getNextRow() {
        return nextRow;
    }

    @Override
    public int shape() {
        return SQUARE;
    }
}
