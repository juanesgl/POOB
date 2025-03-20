import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * La clase MaxwellContainer representa el contenedor en la simulación de Maxwell's Demon.
 * Este contenedor almacena partículas, demonios y agujeros.
 * Además, inicia la simulación de movimiento cuando se hace visible.
 * Atributos:
 * - `width`: Ancho total del contenedor.
 * - `height`: Altura total del contenedor.
 * - `particles`: Lista de partículas en el contenedor.
 * - `holes`: Lista de agujeros en el contenedor.
 * - `demons`: Lista de demonios en el contenedor.
 * - `border`: Representación gráfica del borde del contenedor.
 * - `leftChamber`: Representación gráfica de la cámara izquierda.
 * - `rightChamber`: Representación gráfica de la cámara derecha.
 * - `centralWall`: Representación gráfica de la pared central.
 * - `isVisible`: Indica si el contenedor es visible en la interfaz gráfica.
 * - `isRunning`: Indica si la simulación está en ejecución.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */
public class MaxwellContainer {
    private int width;
    private int height;
    private List<Particle> particles;
    private List<Hole> holes;
    private List<Demon> demons;
    private Rectangle border;
    private Rectangle leftChamber;
    private Rectangle rightChamber;
    private Rectangle centralWall;
    private boolean isVisible;
    private boolean isRunning;
    private boolean did;

    /**
     * Constructor de MaxwellContainer.
     * 
     * @param width Ancho de la cámara (el total será el doble).
     * @param height Altura de la cámara.
     */
    public MaxwellContainer(int width, int height) {
        this.width = 2 * width;
        this.height = height;
        this.particles = new ArrayList<>();
        this.holes = new ArrayList<>();
        this.demons = new ArrayList<>();
        this.isVisible = false;
        this.isRunning = false;

        this.border = new Rectangle();
        this.border.changeSize(height + 4, this.width + 4);
        this.border.changeColor("black");
        this.border.moveHorizontal(0);
        this.border.moveVertical(0);

        this.leftChamber = new Rectangle();
        this.leftChamber.changeSize(height, width);
        this.leftChamber.changeColor("white");
        this.leftChamber.moveHorizontal(0);

        this.rightChamber = new Rectangle();
        this.rightChamber.changeSize(height, width);
        this.rightChamber.changeColor("white");
        this.rightChamber.moveHorizontal(width);

        this.centralWall = new Rectangle();
        this.centralWall.changeSize(height, 1);
        this.centralWall.changeColor("gray");
        this.centralWall.moveHorizontal(width - 1);

        did = true;
    }
    
    /**
     * Agrega una nueva partícula al contenedor con una posición y velocidad específicas.
     *
     * @param color Color de la partícula a crear. Puede ser "red" o "blue".
     * @param px Posición inicial en X.
     * @param py Posición inicial en Y.
     * @param vx Velocidad en X.
     * @param vy Velocidad en Y.
     */
    public void addParticle(String color, int px, int py, int vx, int vy) {
        // Validate the particle's position and velocity
        if (px <= 0 || px >= width || py <= 0 || py >= height) {
            throw new IllegalArgumentException("Particle position is out of bounds.");
        }
        if (vx == 0 && vy == 0) {
            throw new IllegalArgumentException("Particle velocity cannot be zero.");
        }
    
        // Create and add the particle
        Particle particle = new Particle(px, py, vx, vy, color, width, height);
        particles.add(particle);
    
        if (isVisible) {
            particle.makeVisible();
        }
    }

    /**
     * Metodo auxiliar para pruebas unitarias.

     * Este metodo devuelve el valor del atributo `did`, permitiendo verificar
     * su estado actual durante las pruebas unitarias. Es especialmente útil
     * para validar el funcionamiento interno de la clase y asegurar que las
     * condiciones esperadas se cumplan correctamente.
     *
     * @return `true` si el atributo `did` es verdadero, indicando que la operación
     * o estado esperado ha sido alcanzado; de lo contrario, devuelve `false`.
     */
    public boolean ok() {
        return did;
    }

    /**
     * Agrega un demonio al contenedor.
     * El demonio se coloca en el centro del contenedor.
     */
    public void addDemon(int d) {
        Demon demon = new Demon(d, this.width / 2);
        demons.add(demon);
        if (isVisible) {
            demon.makeVisible();
        }
    }

    /**
     * Elimina un demonio del contenedor.
     * Si la lista está vacía, se muestra un mensaje de error.
     */
    public void deleteDemon() {
        if (!demons.isEmpty()) {
            Demon lastDemon = demons.get(demons.size() - 1);
            lastDemon.makeInvisible();
            demons.remove(demons.size() - 1);
        } else {
            System.out.println("No hay demonios para eliminar");
        }
    }

    /**
     * Permite que las partículas pasen a través del demonio.
     * Abre la puerta del demonio para permitir el paso de partículas.
     */
    public void openGate(int milliseconds) {
        if (!demons.isEmpty()) {
            Demon lastDemon = demons.get(demons.size() - 1);
            lastDemon.setGateOpen(true);


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run(){
                    lastDemon.setGateOpen(false);
                }
            } , milliseconds);
        }
    }

    /**
     * No permite que las partículas pasen a través del demonio.
     * Cierra la puerta del demonio para evitar el paso de partículas.
     */
    public void closeGate() {
        if (!demons.isEmpty()) {
            Demon lastDemon = demons.get(demons.size() - 1);
            lastDemon.setGateOpen(false);
        }
    }

    public MaxwellContainer(int width, int height, int d, int r, int b, List<int[]> particles) {
    // Call the basic constructor to initialize the container
    this(width, height);

    // Add the demon at the specified position
    this.addDemon(d);

    // Add red particles
    for (int i = 0; i < r; i++) {
        int[] particleData = particles.get(i);
        int px = particleData[0]; // Initial x position
        int py = particleData[1]; // Initial y position
        int vx = particleData[2]; // Velocity in x direction
        int vy = particleData[3]; // Velocity in y direction
        this.addParticle("red", px, py, vx, vy);
    }

    // Add blue particles
    for (int i = r; i < r + b; i++) {
        int[] particleData = particles.get(i);
        int px = particleData[0]; // Initial x position
        int py = particleData[1]; // Initial y position
        int vx = particleData[2]; // Velocity in x direction
        int vy = particleData[3]; // Velocity in y direction
        this.addParticle("blue", px, py, vx, vy);
        }
    }

    /**
     * Elimina la última partícula agregada al contenedor.
     * Si no hay partículas, se muestra un mensaje de error.
     */
    public void removeParticle() {
        if (!particles.isEmpty()) {
            Particle lastParticle = particles.get(particles.size() - 1);
            lastParticle.makeInvisible();
            particles.remove(particles.size() - 1);
        } else {
            System.out.println("No hay partículas para eliminar");
        }
    }

    /**
     * Agrega un nuevo agujero (Hole) al contenedor en una posición aleatoria.
     * El agujero se genera dentro de los límites del contenedor, evitando la zona central.
     */
    public void addHole() {
        Hole newHole = new Hole(width, height);
        holes.add(newHole);

        if (isVisible) {
            newHole.makeVisible();
        }
    }

    /**
     * Elimina el último agujero (Hole) agregado al contenedor.
     * Si no hay agujeros, se muestra un mensaje de error.
     */
    public void removeHole() {
        if (!holes.isEmpty()) {
            Hole lastHole = holes.get(holes.size() - 1);
            lastHole.makeInvisible();
            holes.remove(holes.size() - 1);
        } else {
            System.out.println("No hay agujeros para eliminar");
        }
    }

    /**
     * Inicia la simulación.
     * La simulación se ejecuta en un hilo separado.
     */
    public void start() {
        System.out.println("Simulación iniciada");

        // Ejecuta la simulación en un hilo separado
        Thread simulationThread = new Thread(() -> {
            runSimulation(500);
        });
        simulationThread.start();
    }

    /**
     * Finaliza la simulación.
     * Detiene el movimiento de las partículas.
     */
    public void finish() {
        isRunning = false;
        System.out.println("Simulación terminada");
    }

    /**
     * Obtiene la lista de partículas.
     * 
     * @return Lista de partículas.
     */
    public List<Particle> particles() {
        return particles;
    }

    /**
     * Obtiene la lista de demonios.
     * 
     * @return Lista de demonios.
     */
    public List<Demon> demons() {
        return demons;
    }

    /**
     * Obtiene la lista de agujeros.
     * 
     * @return Lista de agujeros.
     */
    public List<Hole> holes() {
        return holes;
    }

    /**
     * Obtiene el ancho total del contenedor.
     * 
     * @return Ancho total del contenedor.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Obtiene la altura total del contenedor.
     * 
     * @return Altura total del contenedor.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Hace visible el contenedor y sus elementos.
     */
    public void makeVisible() {
        if (!isVisible) {
            border.makeVisible();
            leftChamber.makeVisible();
            rightChamber.makeVisible();
            centralWall.makeVisible();
            for (Demon d : demons) {
                d.makeVisible();
            }
            for (Particle p : particles) {
                p.makeVisible();
            }
            isVisible = true;
        }
    }

    /**
     * Hace invisible el contenedor y sus elementos.
     */
    public void makeInvisible() {
        if (isVisible) {
            border.makeInvisible();
            leftChamber.makeInvisible();
            rightChamber.makeInvisible();
            centralWall.makeInvisible();
            for (Demon d : demons) {
                d.makeInvisible();
            }
            for (Particle p : particles) {
                p.makeInvisible();
            }
            isVisible = false;
        }
    }

    /**
     * Obtiene el estado de visibilidad del contenedor.
     * 
     * @return true si el contenedor es visible, false en caso contrario.
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Ejecuta la simulación de movimiento de partículas durante un tiempo determinado.
     * 
     * @param steps Número de iteraciones antes de detener la simulación.
     */
    private void runSimulation(int steps) {
        isRunning = true; // La simulación está activa
        for (int i = 0; i < steps && isRunning; i++) {
            for (Particle p : particles) {
                p.move(width, height, demons);
            }

            for (Hole hole : holes) {
                hole.absorbs(particles);
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isRunning = false;
    }
}