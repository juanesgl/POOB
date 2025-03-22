package domain;
import java.util.*;

public class City {
    static private int SIZE = 25;
    private Item[][] locations;
    private List<Agent> agents;
    private int ticTacCount = 0;

    public City() {
        locations = new Item[SIZE][SIZE];
        agents = new ArrayList<>(); // Inicializar la lista de agentes
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
            agents.add((Agent) e); // Agregar el agente a la lista
        }
    }

    public void someItems() {
    new Person(this, 10, 10); // Adán
    new Person(this, 15, 15); // Eva


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
        ticTacCount++; // Incrementar el contador de pasos
        System.out.println("Tic-tac número: " + ticTacCount);

        // 1. Actualizar el estado emocional de todos los agentes
        for (Agent agent : agents) {
            agent.decide(); // El agente decide su estado emocional
        }

        // 2. Mover agentes insatisfechos
        List<Agent> agentsToMove = new ArrayList<>(agents); // Copia de la lista para evitar ConcurrentModificationException
        for (Agent agent : agentsToMove) {
            if (agent.isDissatisfied()) { // Solo mover agentes insatisfechos
                System.out.println("Intentando mover agente insatisfecho en (" + ((Person) agent).getRow() + ", " + ((Person) agent).getColumn() + ")");
                moveAgent((Person) agent); // Mover el agente a una nueva ubicación
            }
        }
    }

    private void moveAgent(Person agent) {
        Random rand = new Random();
        int newRow, newCol;
        int attempts = 0;
        boolean moved = false;

        // Intentar mover al agente hasta encontrar una ubicación vacía
        while (attempts < 100 && !moved) { // Límite de intentos para evitar bucles infinitos
            newRow = rand.nextInt(SIZE);
            newCol = rand.nextInt(SIZE);
            if (isEmpty(newRow, newCol)) {
                // Mover el agente a la nueva ubicación
                int oldRow = agent.getRow();
                int oldCol = agent.getColumn();

                // Actualizar la ciudad
                locations[oldRow][oldCol] = null; // Liberar la celda anterior
                locations[newRow][newCol] = agent; // Ocupar la nueva celda

                // Actualizar la posición del agente
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