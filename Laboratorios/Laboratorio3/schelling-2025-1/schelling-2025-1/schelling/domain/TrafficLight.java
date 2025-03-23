package domain;
import java.awt.Color;
/*
public class TrafficLight extends Agent {
    private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.GREEN};
    private int stateIndex;

    public TrafficLight(City city, int row, int col) {
        super(city, row, col);
        this.stateIndex = 0;
    }

    @Override
    public void decide() {
        change();  // 🚦 Cambia el color en cada ciclo
    }

    public void change() {
        stateIndex = (stateIndex + 1) % COLORS.length;
    }

    @Override
    public Color getColor() {
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