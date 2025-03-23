package domain;
import java.awt.Color;

public class Walker extends Person {
    private int nextRow; 

    public Walker(City city, int row, int col) {
        super(city, row, col);
        this.state = INDIFFERENT;
        this.color = Color.GREEN;
        this.nextRow = row; 
    }

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

    public boolean canMove() {
        return nextRow != getRow(); 
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
