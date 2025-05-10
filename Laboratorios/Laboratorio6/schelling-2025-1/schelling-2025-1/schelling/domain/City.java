package domain;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Representa una ciudad que contiene una cuadrícula donde se colocan agentes y objetos como semáforos.
 * Los agentes pueden moverse y tomar decisiones en cada "tic-tac", y los semáforos cambian su estado en cada ciclo.
 */
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    /** El tamaño de la cuadrícula de la ciudad (por defecto 25x25) */
    static private int SIZE = 25;

    /** Matriz que representa las ubicaciones en la cuadrícula */
    private Item[][] locations;

    /** Lista de agentes en la ciudad */
    private List<Agent> agents;

    /** Contador de los ciclos "tic-tac" */
    private int ticTacCount = 0;

    /**
     * Constructor de la ciudad. Inicializa la cuadrícula y la lista de agentes.
     * También agrega algunos elementos iniciales (como semáforos).
     */
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

    /**
     * Devuelve el tamaño de la ciudad (tamaño de la cuadrícula).
     *
     * @return El tamaño de la ciudad.
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Devuelve el ítem en una ubicación específica de la cuadrícula.
     *
     * @param r La fila en la cuadrícula.
     * @param c La columna en la cuadrícula.
     * @return El ítem en la ubicación especificada o null si no hay ítem.
     */
    public Item getItem(int r, int c) {
        return locations[r][c];
    }

    /**
     * Establece un ítem en una ubicación específica de la cuadrícula.
     * Si el ítem es un agente, se agrega a la lista de agentes.
     *
     * @param r La fila en la cuadrícula.
     * @param c La columna en la cuadrícula.
     * @param e El ítem a establecer en la ubicación especificada.
     */
    public void setItem(int r, int c, Item e) {
        locations[r][c] = e;
        if (e instanceof Agent) {
            agents.add((Agent) e);
        }
    }

    /**
     * Agrega algunos ítems iniciales en la cuadrícula (como semáforos, agentes, etc.)
     * Este método es solo un ejemplo y puede modificarse según el escenario.
     */
        public void someItems() {
    /*Ciclo 1 */
    //new Person(this, 10, 10); // Adán
    //new Person(this, 15, 15); // Eva

    /*Ciclo 2 */
    //new Walker(this, 10,20); //messner
    //new Walker(this, 10,21); //kukuczka

    // Ciclo 3: Adicionar semáforos
    //new TrafficLight(this, 0, 0); // Semáforo "alarm" en la esquina superior izquierda (0, 0)
    //new TrafficLight(this, 0, SIZE - 1); // Semáforo "alert" en la esquina superior derecha (0, SIZE-1)


    /*Ciclo 4 */
    //new Bishop(this, 5,20); //Sánchez
    //new Bishop(this, 9,20); //Ruiz

    /*Ciclo 5 */
    new Hori(this, 5, 5); //Sánchez
    new Hori(this, 10, 10); //Ruiz



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

    /**
     * Calcula cuántos vecinos de la misma clase existen alrededor de una ubicación específica.
     *
     * @param r La fila de la ubicación.
     * @param c La columna de la ubicación.
     * @return El número de vecinos que son del mismo tipo que el ítem en la ubicación dada.
     */
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

    /**
     * Verifica si una ubicación en la cuadrícula está vacía (sin ítem).
     *
     * @param r La fila en la cuadrícula.
     * @param c La columna en la cuadrícula.
     * @return true si la ubicación está vacía, false en caso contrario.
     */
    public boolean isEmpty(int r, int c) {
        return (inLocations(r, c) && locations[r][c] == null);
    }

    /**
     * Verifica si las coordenadas dadas están dentro de los límites de la cuadrícula.
     *
     * @param r La fila a verificar.
     * @param c La columna a verificar.
     * @return true si las coordenadas están dentro de los límites, false en caso contrario.
     */
    private boolean inLocations(int r, int c) {
        return ((0 <= r) && (r < SIZE) && (0 <= c) && (c < SIZE));
    }

    /**
     * Realiza un ciclo "tic-tac", donde los agentes toman decisiones, se mueven y los semáforos cambian de estado.
     */
    public void ticTac() {
        ticTacCount++;
        System.out.println("Tic-tac número: " + ticTacCount);

        List<Agent> agentsCopy = new ArrayList<>(agents);
        Set<String> reservedPositions = new HashSet<>();

        // PASO 1: Todos deciden hacia dónde moverse
        for (Agent agent : agentsCopy) {
            if (agent instanceof Walker || agent instanceof Bishop || agent instanceof Hori) {
                ((Person) agent).decide();
            }
        }

        // PASO 2: Mover Walkers evitando colisiones
        for (Agent agent : agentsCopy) {
            if (agent instanceof Walker) {
                Walker w = (Walker) agent;
                String key = w.getNextRow() + "," + w.getColumn();
                if (w.canMove() && !reservedPositions.contains(key)) {
                    reservedPositions.add(key);
                    w.move();
                }
            }
        }

        // PASO 3: Mover Bishops evitando colisiones
        for (Agent agent : agentsCopy) {
            if (agent instanceof Bishop) {
                Bishop b = (Bishop) agent;
                String key = b.getNextRow() + "," + b.getColumn();
                if (b.canMove() && !reservedPositions.contains(key)) {
                    reservedPositions.add(key);
                    b.move();
                }
            }
        }

        // PASO 4: Mover Hori evitando colisiones
        for (Agent agent : agentsCopy) {
            if (agent instanceof Hori) {
                Hori h = (Hori) agent;
                String key = h.getRow() + "," + h.getNextCol();
                if (h.canMove() && !reservedPositions.contains(key)) {
                    reservedPositions.add(key);
                    h.move();
                }
            }
        }

        // PASO 5: Cambiar el estado de los semáforos
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Item item = locations[r][c];
                if (item != null && !item.isAgent()) { // Si es un semáforo (no es un agente)
                    item.change(); // Cambiar al siguiente estado
                }
            }
        }
    }

    /**
     * Mueve un agente a una nueva ubicación aleatoria en la ciudad.
     * Si no es posible moverlo, se intenta hasta 100 veces.
     *
     * @param agent El agente a mover.
     */
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

    /**
     * Simula la acción de abrir un archivo para cargar la ciudad.
     * Actualmente, lanza una excepción indicando que la funcionalidad está en construcción.
     *
     * @param file El archivo a abrir.
     * @throws CityException Si la operación no está implementada.
     */
    public void open(File file) throws CityException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        City ciudadCargada = (City) ois.readObject();
        this.locations = ciudadCargada.locations;
        this.agents = ciudadCargada.agents;
        this.ticTacCount = ciudadCargada.ticTacCount;
        System.out.println("Ciudad cargada exitosamente desde: " + file.getAbsolutePath());
    } catch (IOException | ClassNotFoundException e) {
        throw new CityException("Error al cargar la ciudad: " + e.getMessage());
    }
}

    
    
    public void open00(File file) throws CityException{
        throw new CityException(CityException.OPEN_IN_CONSTRUCTION, file.getName());
    }

    /**
     * Simula la acción de guardar el estado actual de la ciudad en un archivo.
     * Actualmente, lanza una excepción indicando que la funcionalidad está en construcción.
     *
     * @param file El archivo donde se guardará la ciudad.
     * @throws CityException Si la operación no está implementada.
     */
    public void save(File file) throws CityException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(this);
        System.out.println("Ciudad guardada exitosamente en: " + file.getAbsolutePath());
    } catch (IOException e) {
        throw new CityException("Error al guardar la ciudad: " + e.getMessage());
    }
}

    
    public void save00(File file) throws CityException {
        throw new CityException(CityException.SAVE_IN_CONSTRUCTION, file.getName());
    }

    /**
     * Simula la acción de importar datos a la ciudad desde un archivo.
     * Actualmente, lanza una excepción indicando que la funcionalidad está en construcción.
     *
     * @param file El archivo desde donde se importarán los datos.
     * @throws CityException Si la operación no está implementada.
     */
    public void importt(File file) throws CityException {
        throw new CityException(CityException.IMPORT_IN_CONSTRUCTION, file.getName());
    }

    /**
     * Simula la acción de exportar los datos de la ciudad a un archivo.
     * Actualmente, lanza una excepción indicando que la funcionalidad está en construcción.
     *
     * @param file El archivo donde se exportarán los datos.
     * @throws CityException Si la operación no está implementada.
     */
    public void export(File file) throws CityException {
        throw new CityException(CityException.EXPORT_IN_CONSTRUCTION, file.getName());
    }

}