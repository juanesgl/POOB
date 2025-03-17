import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public void openGate() {
        if (!demons.isEmpty()) {
            Demon lastDemon = demons.get(demons.size() - 1);
            lastDemon.setGateOpen(true);
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

    /**
     * Agrega una nueva partícula al contenedor.
     * La partícula se posiciona aleatoriamente dentro de los límites de la cámara correspondiente
     * (azul en la izquierda y roja en la derecha). También se le asigna una velocidad aleatoria.
     *
     * @param color Color de la partícula a crear. Puede ser "red" o "blue".
     *              - "red": Se crea en la mitad derecha del contenedor.
     *              - "blue": Se crea en la mitad izquierda del contenedor.
     */
    public void addParticle(String color) {
        Random rand = new Random();
        int pX, pY, vx, vy;
        int middleX = width / 2;
        int padding = 15; // Margen para evitar que se salgan

        pY = padding + rand.nextInt(height - (2 * padding));

        if (color.equals("red")) {
            pX = middleX + padding + rand.nextInt((width / 2) - (2 * padding));
        } else if (color.equals("blue")) {
            pX = padding + rand.nextInt(middleX - (2 * padding));
        } else {
            System.out.println("Color inválido: " + color);
            return;
        }

        do {
            vx = rand.nextInt(7) - 3;
        } while (vx == 0);

        do {
            vy = rand.nextInt(7) - 3;
        } while (vy == 0);

        Particle particle = new Particle(pX, pY, vx, vy, color, width, height);
        particles.add(particle);

        if (isVisible) {
            particle.makeVisible();
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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isRunning = false;
    }
}