package domain;
import java.awt.Color;

public class Hori extends Person {
    private int nextCol; 

    public Hori(City city, int row, int col) {
        super(city, row, col);
        this.state = INDIFFERENT;
        this.color = Color.ORANGE;
        this.nextCol = col; 
    }

    @Override
    public void decide() {
        int newCol = getColumn() + 1; 

        if (getCity().isEmpty(getRow(), newCol)) {
            nextCol = newCol; 
        } else {
            nextCol = getColumn(); 
        }
    }

    public boolean canMove() {
        return nextCol != getColumn();
    }

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

    @Override
    public int shape() {
        return SQUARE;
    }

    public int getNextCol() {
        return nextCol;
    }
}
