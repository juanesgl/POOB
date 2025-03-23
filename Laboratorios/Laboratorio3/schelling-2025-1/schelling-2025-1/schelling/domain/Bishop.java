package domain;
import java.awt.Color;

public class Bishop extends Person {
    private int nextRow;

    public Bishop(City city, int row, int col) {
        super(city, row, col);
        this.state = INDIFFERENT;
        this.color = Color.MAGENTA;
        this.nextRow = row;
    }

    @Override
    public void decide() {
        int newRow = getRow() + 1; // 🔽 Moverse hacia abajo (sur)

        if (getCity().isEmpty(newRow, getColumn())) {
            nextRow = newRow; // ✅ Guarda la posición a la que quiere moverse
        } else {
            nextRow = getRow(); // ❌ Se queda en su lugar si no puede moverse
        }
    }

    public boolean canMove() {
        return nextRow != getRow();
    }

    public void move() {
        if (canMove() && getCity().isEmpty(nextRow, getColumn())) {
            System.out.println("♝ Bishop bajando de (" + getRow() + "," + getColumn() + ") a (" + nextRow + "," + getColumn() + ")");
            getCity().setItem(getRow(), getColumn(), null);
            setPosition(nextRow, getColumn());
            getCity().setItem(nextRow, getColumn(), this);
        } else {
            System.out.println("❌ Bishop en (" + getRow() + "," + getColumn() + ") NO se movió.");
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
