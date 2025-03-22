package domain;
import java.util.*;

public class City {
    static private int SIZE = 25;
    private Item[][] locations;
    private List<Agent> agents;
    private int ticTacCount = 0;

    public City() {
        locations = new Item[SIZE][SIZE];
        agents = new ArrayList<>(); 
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                locations[r][c] = null;
            }
        }
        someItems();
    }

    public int getSize() {
        return SIZE;
    }

    public Item getItem(int r, int c) {
        return locations[r][c];
    }

    public void setItem(int r, int c, Item e) {
        locations[r][c] = e;
        if (e instanceof Agent) {
            agents.add((Agent) e); 
        }
    }

    public void someItems() {
    //new Person(this, 10, 10); // Adán
    //new Person(this, 15, 15); // Eva
    
    
    new Walker(this, 10,20); //messner 
    new Walker(this, 10,21); //kukuczka


        //Random rand = new Random();
        //for (int i = 0; i < 50; i++) { // Agregar 50 agentes
          //  int row = rand.nextInt(SIZE);
            //int col = rand.nextInt(SIZE);
            //if (isEmpty(row, col)) {
              //  new Person(this, row, col);
                //System.out.println("Agente agregado en (" + row + ", " + col + ")");
            //}
        //}
        //System.out.println("Total de agentes: " + agents.size()); // Verificar el tamaño de la lista
    }

    public int neighborsEquals(int r, int c) {
        int num = 0;
        if (inLocations(r, c) && locations[r][c] != null) {
            for (int dr = -1; dr < 2; dr++) {
                for (int dc = -1; dc < 2; dc++) {
                    if ((dr != 0 || dc != 0) && inLocations(r + dr, c + dc) &&
                            (locations[r + dr][c + dc] != null) &&
                            (locations[r][c].getClass() == locations[r + dr][c + dc].getClass())) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    public boolean isEmpty(int r, int c) {
        return (inLocations(r, c) && locations[r][c] == null);
    }

    private boolean inLocations(int r, int c) {
        return ((0 <= r) && (r < SIZE) && (0 <= c) && (c < SIZE));
    }

    public void ticTac() {
    ticTacCount++; 
    System.out.println("Tic-tac número: " + ticTacCount);

    List<Agent> agentsCopy = new ArrayList<>(agents);
    Set<String> reservedPositions = new HashSet<>(); 

   
    for (Agent agent : agentsCopy) {
        if (agent instanceof Walker) {
            ((Walker) agent).decide(); 
        }
    }

    
    for (Agent agent : agentsCopy) {
        if (agent instanceof Walker) {
            Walker w = (Walker) agent;
            String key = w.getNextRow() + "," + w.getColumn();

            if (w.canMove() && !reservedPositions.contains(key)) { 
                reservedPositions.add(key); 
                w.move();
            } else {
                System.out.println("Walker en (" + w.getRow() + "," + w.getColumn() + ") no se movió porque la posición (" + w.getNextRow() + "," + w.getColumn() + ") ya está ocupada.");
            }
        }
    }

    
    for (Agent agent : agentsCopy) {
        if (!(agent instanceof Walker) && agent.isDissatisfied()) { 
            moveAgent((Person) agent);
        }
    }
}






    private void moveAgent(Person agent) {
        Random rand = new Random();
        int newRow, newCol;
        int attempts = 0;
        boolean moved = false;

        while (attempts < 100 && !moved) { 
            newRow = rand.nextInt(SIZE);
            newCol = rand.nextInt(SIZE);
            if (isEmpty(newRow, newCol)) {
            
                int oldRow = agent.getRow();
                int oldCol = agent.getColumn();

                locations[oldRow][oldCol] = null; 
                locations[newRow][newCol] = agent; 
                agent.setPosition(newRow, newCol);
                moved = true;
                System.out.println("Agente movido de (" + oldRow + ", " + oldCol + ") a (" + newRow + ", " + newCol + ")");
            }
            attempts++;
        }
        if (!moved) {
            System.out.println("No se pudo mover el agente en (" + agent.getRow() + ", " + agent.getColumn() + ")");
        }
    }
}