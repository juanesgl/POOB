package domain;

import java.awt.Color;

public class Person extends Agent implements Item {
    private City city;
    private int row, column;
    protected Color color;

    public Person(City city, int row, int column) {
        this.city = city;
        this.row = row;
        this.column = column;
        this.city.setItem(row, column, (Item) this);
        color = Color.blue;
    }

    public final int getRow() {
        return row;
    }

    public final int getColumn() {
        return column;
    }

    public final Color getColor() {
        return color;
    }

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

    @Override
    public final void change() {
        step();
    }

    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public City getCity() {
    return this.city;
    }

}