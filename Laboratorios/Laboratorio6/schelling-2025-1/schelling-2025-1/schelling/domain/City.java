package domain;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.awt.Color;

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
        
       
        for (Agent agent : agents) {
            if (agent instanceof Person) {
                ((Person) agent).setCity(this);
            }
        }
    } catch (Exception ex) {
        throw new CityException(CityException.ERROR_AT_LOADING + ": " + ex.getMessage(), ex);
    }
    }

public void open00() throws CityException {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Abrir ciudad");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos DAT", "dat"));

    int userSelection = fileChooser.showOpenDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            open(selectedFile);
            JOptionPane.showMessageDialog(null, "Ciudad cargada exitosamente desde: " + selectedFile.getAbsolutePath());
        } catch (CityException ex) {
            throw new CityException(CityException.ERROR_AT_LOADING + ": " + ex.getMessage(), ex);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
    }
}

public void save(File file) throws CityException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(this);
    } catch (Exception ex) {
        throw new CityException("Error al guardar la ciudad: " + ex.getMessage(), ex);
    }
}

public void save00() throws CityException {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar ciudad");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos DAT", "dat"));

    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        if (!fileToSave.getAbsolutePath().endsWith(".dat")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".dat");
        }

        try {
            save(fileToSave);
            JOptionPane.showMessageDialog(null, "Ciudad guardada exitosamente en: " + fileToSave.getAbsolutePath());
        } catch (CityException ex) {
            throw new CityException("Error al guardar la ciudad: " + ex.getMessage(), ex);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
    }
}




/**
 * Exporta el estado actual de la ciudad a un archivo de texto
 * @param file Archivo donde se guardará la exportación
 * @throws CityException si ocurre un error durante la exportación
 */
public void export(File file) throws CityException {
    try (PrintWriter writer = new PrintWriter(file)) {
        
        writer.println("Schelling City Export v1.0");
        writer.println("Size: " + SIZE);
        writer.println("TicTacCount: " + ticTacCount);
        
        
        for (Agent agent : agents) {
            if (agent instanceof Person) {
                Person p = (Person) agent;
                Color c = p.getColor();
                writer.printf("Agent:%d,%d,%d,%d,%d,%s%n", 
                    p.getRow(), p.getColumn(), 
                    c.getRed(), c.getGreen(), c.getBlue(),
                    p.getStateString());
            }
        }
        
        
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Item item = locations[r][c];
                if (item != null && !item.isAgent()) {
                    writer.printf("Item:%d,%d,%s%n", 
                        r, c, item.getClass().getSimpleName());
                }
            }
        }
    } catch (IOException ex) {
        throw new CityException("Error al escribir el archivo de exportación: " + ex.getMessage());
    }
}

/**
 * Importa el estado de la ciudad desde un archivo de texto
 * @param file Archivo desde donde se importará
 * @throws CityException si ocurre un error durante la importación
 */
public void importt(File file) throws CityException {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        
        locations = new Item[SIZE][SIZE];
        agents = new ArrayList<>();
        
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Agent:")) {
                processAgentLine(line);
            } else if (line.startsWith("Item:")) {
                processItemLine(line);
            }
        }
    } catch (Exception ex) {
        throw new CityException("Error al leer el archivo de importación: " + ex.getMessage());
    }
}

private void processAgentLine(String line) {
    String[] parts = line.substring(6).split(",");
    int row = Integer.parseInt(parts[0].trim());
    int col = Integer.parseInt(parts[1].trim());
    int r = Integer.parseInt(parts[2].trim());
    int g = Integer.parseInt(parts[3].trim());
    int b = Integer.parseInt(parts[4].trim());
    String state = parts[5].trim();
    
    Person agent = new Person(this, row, col);
    agent.setColor(new Color(r, g, b));
    agent.setStateFromString(state);
}

private void processItemLine(String line) {
    String[] parts = line.substring(5).split(",");
    int row = Integer.parseInt(parts[0].trim());
    int col = Integer.parseInt(parts[1].trim());
    String type = parts[2].trim();
    
    switch (type) {
        case "TrafficLight":
            new TrafficLight(this, row, col);
            break;
       
    }
}


/**
 * Exporta la ciudad a un archivo de texto con diálogo de selección de archivo
 * @throws CityException si ocurre un error durante la exportación
 */
public void export00() throws CityException {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Exportar ciudad como texto");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos TXT", "txt"));

    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        if (!fileToSave.getAbsolutePath().toLowerCase().endsWith(".txt")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
        }

        try {
            export(fileToSave);
            JOptionPane.showMessageDialog(null, 
                "Ciudad exportada exitosamente en: " + fileToSave.getAbsolutePath(),
                "Exportación completada", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (CityException ex) {
            throw new CityException("Error al exportar la ciudad: " + ex.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, 
            "Operación de exportación cancelada por el usuario.",
            "Exportación cancelada",
            JOptionPane.WARNING_MESSAGE);
    }
}

/**
 * Importa la ciudad desde un archivo de texto con diálogo de selección de archivo
 * @throws CityException si ocurre un error durante la importación
 */
public void import00() throws CityException {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Importar ciudad desde texto");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos TXT", "txt"));

    int userSelection = fileChooser.showOpenDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            importt(selectedFile);
            JOptionPane.showMessageDialog(null,
                "Ciudad importada exitosamente desde: " + selectedFile.getAbsolutePath(),
                "Importación completada",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (CityException ex) {
            throw new CityException("Error al importar la ciudad: " + ex.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null,
            "Operación de importación cancelada por el usuario.",
            "Importación cancelada",
            JOptionPane.WARNING_MESSAGE);
    }
}







/**
 * Versión mejorada de open() con manejo detallado de excepciones
 */
public void open01(File file) throws CityException {
    if (file == null || !file.exists()) {
        throw new CityException("Archivo no existe o es inválido");
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        City ciudadCargada = (City) ois.readObject();
    
        if (ciudadCargada.locations == null || ciudadCargada.agents == null) {
            throw new CityException("Archivo corrupto: estructura de datos inválida");
        }
        
        this.locations = ciudadCargada.locations;
        this.agents = ciudadCargada.agents;
        this.ticTacCount = ciudadCargada.ticTacCount;
        
       
        for (Agent agent : agents) {
            if (agent instanceof Person) {
                ((Person) agent).setCity(this);
            }
        }
        
    } catch (ClassCastException e) {
        throw new CityException("Tipo de archivo incompatible - no es un archivo de ciudad", e);
    } catch (InvalidClassException e) {
        throw new CityException("Versión incompatible de clases serializadas", e);
    } catch (StreamCorruptedException e) {
        throw new CityException("Archivo corrupto - cabecera inválida", e);
    } catch (IOException e) {
        throw new CityException("Error de E/S al leer el archivo: " + e.getMessage(), e);
    } catch (ClassNotFoundException e) {
        throw new CityException("Clase no encontrada durante deserialización", e);
    } catch (Exception e) {
        throw new CityException("Error inesperado al cargar: " + e.getMessage(), e);
    }
}

/**
 * Versión mejorada de save() con manejo detallado de excepciones
 */
public void save01(File file) throws CityException {
    if (file == null) {
        throw new CityException("Archivo de destino no puede ser nulo");
    }

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      
        if (this.locations == null || this.agents == null) {
            throw new CityException("Estado inválido para serialización");
        }
        
        oos.writeObject(this);
    } catch (InvalidClassException e) {
        throw new CityException("Error de serialización: clase no válida", e);
    } catch (NotSerializableException e) {
        throw new CityException("Objeto no serializable: " + e.getMessage(), e);
    } catch (IOException e) {
        throw new CityException("Error de E/S al escribir el archivo: " + e.getMessage(), e);
    } catch (SecurityException e) {
        throw new CityException("Permisos insuficientes para escribir el archivo", e);
    } catch (Exception e) {
        throw new CityException("Error inesperado al guardar: " + e.getMessage(), e);
    }
}
    



/**
 * Importa el estado de la ciudad desde un archivo de texto estructurado
 * @param file Archivo a importar
 * @throws CityException Si hay errores de formato, E/S o datos inconsistentes
 */
public void import01(File file) throws CityException {
    // Validación inicial
    if (!file.exists()) {
        throw new CityException(
            "Archivo no encontrado: " + file.getName(),
            CityException.FILE_NOT_FOUND
        );
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        
        this.locations = new Item[SIZE][SIZE];
        this.agents = new ArrayList<>();
        this.ticTacCount = 0;

        String line;
        int lineNumber = 0;
        boolean formatValidated = false;

       
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            line = line.trim();

            try {
                if (line.startsWith("SCHELLING_CITY_EXPORT_V1")) {
                    formatValidated = true;
                    continue;
                }

                if (!formatValidated) {
                    throw new CityException(
                        "Formato de archivo inválido",
                        CityException.INVALID_FORMAT
                    );
                }

                
                if (line.startsWith("SIZE:")) {
                    int importedSize = Integer.parseInt(line.substring(5));
                    if (importedSize != SIZE) {
                        throw new CityException(
                            "Tamaño de ciudad incompatible",
                            CityException.INVALID_FORMAT
                        );
                    }
                } 
                else if (line.startsWith("AGENT:")) {
                    processAgentLine(line.substring(6), lineNumber);
                } 
                else if (line.startsWith("ITEM:")) {
                    processItemLine(line.substring(5), lineNumber);
                }

            } catch (NumberFormatException e) {
                throw new CityException(
                    "Valor numérico inválido en línea " + lineNumber,
                    CityException.INVALID_FORMAT,
                    e
                );
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CityException(
                    "Coordenadas inválidas en línea " + lineNumber,
                    CityException.INVALID_FORMAT,
                    e
                );
            }
        }

    } catch (IOException e) {
        throw new CityException(
            "Error de lectura del archivo",
            CityException.IO_ERROR,
            e
        );
    }
}


private void processAgentLine(String data, int lineNumber) throws CityException {
    String[] parts = data.split(",");
    if (parts.length != 6) {
        throw new CityException(
            "Formato de agente inválido en línea " + lineNumber,
            CityException.INVALID_FORMAT
        );
    }

    try {
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        Color color = new Color(
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]),
            Integer.parseInt(parts[4])
        );
        String state = parts[5];

        // Validar coordenadas
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new CityException(
                "Coordenadas fuera de rango en línea " + lineNumber,
                CityException.INVALID_FORMAT
            );
        }

        Person agent = new Person(this, row, col);
        agent.setColor(color);
        agent.setStateFromString(state);

    } catch (IllegalArgumentException e) {
        throw new CityException(
            "Valor de color o estado inválido en línea " + lineNumber,
            CityException.INVALID_FORMAT,
            e
        );
    }
}

private void processItemLine(String data, int lineNumber) throws CityException {
    String[] parts = data.split(",");
    if (parts.length != 3) {
        throw new CityException(
            "Formato de ítem inválido en línea " + lineNumber,
            CityException.INVALID_FORMAT
        );
    }

    int row = Integer.parseInt(parts[0]);
    int col = Integer.parseInt(parts[1]);
    String type = parts[2];

    
    if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
        throw new CityException(
            "Coordenadas fuera de rango en línea " + lineNumber,
            CityException.INVALID_FORMAT
        );
    }

    switch (type) {
        case "TrafficLight":
            new TrafficLight(this, row, col);
            break;
        default:
            throw new CityException(
                "Tipo de ítem desconocido: " + type,
                CityException.INVALID_FORMAT
            );
    }
}

/**
 * Exporta el estado actual de la ciudad a un archivo de texto con formato estructurado
 * @param file Archivo de destino
 * @throws CityException Si ocurre cualquier error durante la exportación
 */
public void export01(File file) throws CityException {
 
    if (file == null) {
        throw new CityException("El archivo no puede ser nulo", CityException.DATA_CORRUPTED);
    }

    try (PrintWriter writer = new PrintWriter(file)) {
        
        writer.println("SCHELLING_CITY_EXPORT_V1");
        writer.println("SIZE:" + SIZE);
        writer.println("TIC_TAC_COUNT:" + ticTacCount);
        writer.println("AGENT_COUNT:" + agents.size());

      
        for (Agent agent : agents) {
            if (agent instanceof Person) {
                Person p = (Person) agent;
                writer.printf(
                    "AGENT:%d,%d,%d,%d,%d,%s%n",
                    p.getRow(),
                    p.getColumn(),
                    p.getColor().getRed(),
                    p.getColor().getGreen(),
                    p.getColor().getBlue(),
                    p.getStateString()
                );
            }
        }

        
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Item item = locations[r][c];
                if (item != null && !item.isAgent()) {
                    writer.printf(
                        "ITEM:%d,%d,%s%n",
                        r,
                        c,
                        item.getClass().getSimpleName()
                    );
                }
            }
        }

    } catch (SecurityException e) {
        throw new CityException(
            "Permisos denegados para escribir en: " + file.getAbsolutePath(),
            CityException.IO_ERROR,
            e
        );
    } catch (IOException e) {
        throw new CityException(
            "Error de E/S al escribir el archivo",
            CityException.IO_ERROR,
            e
        );
    } catch (Exception e) {
        throw new CityException(
            "Error inesperado durante exportación",
            CityException.DATA_CORRUPTED,
            e
        );
    }
}

}