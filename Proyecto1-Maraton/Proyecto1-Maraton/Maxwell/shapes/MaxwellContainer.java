import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La clase MaxwellContainer representa el contenedor en la simulación de Maxwell's Demon.
 * Este contenedor almacena partículas, demonios y agujeros.
 * Además, inicia la simulación de movimiento cuando se hace visible.
 *
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1
 */
public class MaxwellContainer {
    private int width, height;
    private List<Particle> particles;
    private List<Hole> holes;
    private List<Demon> demons;
    private Rectangle border;
    private Rectangle leftChamber;
    private Rectangle rightChamber;
    private Rectangle centralWall;
    private boolean isVisible;

    /**
     * Constructor de MaxwellContainer.
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

        border = new Rectangle();
        border.changeSize(height + 4, this.width + 4);
        border.changeColor("black");
        border.moveHorizontal(-2);
        border.moveVertical(-2);

        leftChamber = new Rectangle();
        leftChamber.changeSize(height, width);
        leftChamber.changeColor("white");
        leftChamber.moveHorizontal(0);

        rightChamber = new Rectangle();
        rightChamber.changeSize(height, width);
        rightChamber.changeColor("white");
        rightChamber.moveHorizontal(width);

        centralWall = new Rectangle();
        centralWall.changeSize(height, 1);
        centralWall.changeColor("gray");
        centralWall.moveHorizontal(width - 1);
    }


       /**
     * Agrega un demonio al contenedor.
     *
     */
    public void addDemon() {
        Demon demon=new Demon(this.height/2,this.width/2);
        demons.add(demon);
        if (isVisible) {
            demon.makeVisible();
        }
    }

    /**
     * Elimina un demonio del contenedor.
     * Si la lista esta vacia sale mensaje de error
     */
    public void deleteDemon() {
        if(!demons.isEmpty()){
        Demon lastDemon=demons.get(demons.size()-1);
        lastDemon.makeInvisible();
        demons.remove(demons.size()-1);
        }else{
        System.out.println("No hay demonios para eliminar");
        }
    }

    /**
     * Permite pasar particulas a traves del demon
     */
    public void openGate(){
        Demon lastDemon=demons.get(demons.size()-1);
        lastDemon.setGateOpen(true);
    }
    /**
     * No permite pasar particulas a traves del demon
     */
    public void closeGate(){
        Demon lastDemon=demons.get(demons.size()-1);
        lastDemon.setGateOpen(false);
    }

    /**
     * Agrega una nueva partícula al contenedor.
     * @param color Color de la partícula que se quiere crear("red" o "blue").
     */


    public void addParticle(String color) {
        Random rand = new Random();

        int pX = 0; // Posición X inicial
        int pY = rand.nextInt(height - 10); // Posición Y aleatoria dentro del rango vertical
        int vx, vy;

        // Generar la posición X dependiendo del color
        if (color.equals("blue")) {
            pX = width / 2 + 10 + rand.nextInt(width / 2 - 20); // Cámara derecha
        } else if (color.equals("red")) {
            pX = rand.nextInt(width / 2 - 10); // Cámara izquierda
        } else {
            System.out.println("Color inválido: " + color);
            return; // Salir del método si el color no es válido
        }

        // Generar velocidades aleatorias (-5 a 5, evitando 0)
        do {
            vx = rand.nextInt(11) - 5; // Velocidad en X entre -5 y 5
        } while (vx == 0);

        do {
            vy = rand.nextInt(11) - 5; // Velocidad en Y entre -5 y 5
        } while (vy == 0);

        // Crear la partícula y agregarla al contenedor
        Particle particle = new Particle(pX, pY, vx, vy, color, width, height);
        particles.add(particle);

        // Hacer visible la partícula si el contenedor ya lo es
        if (isVisible) {
            particle.makeVisible();
        }
    }


    /**
     * Elimina una partícula del contenedor.
     * @param particle Partícula a eliminar.
     */
    public void removeParticle(Particle particle) {
        particles.remove(particle);
        particle.makeInvisible();
    }



    /**
     * Agrega un agujero en una posición aleatoria dentro del contenedor con radio aleatorio.
     */
    public void addHole() {
        Random rand = new Random();
        int middleX = width / 2;

        int holeX, holeY;

        // Ensure the hole does not appear on the middle wall
        do {
            holeX = rand.nextInt(width - 20) + 10;
        } while (holeX >= middleX - 10 && holeX <= middleX + 10);

        holeY = rand.nextInt(height - 20) + 10;

        // Creates a hole with a random radius
        Hole newHole = new Hole(holeX, holeY);
        holes.add(newHole);

        if (isVisible) {
            newHole.makeVisible();
        }

    }

    /**
     * Inicia la simulación.
     */
    public void start() {
        System.out.println("Simulación iniciada");
    }

    /**
     * Finaliza la simulación.
     */
    public void finish() {
        System.out.println("Simulación finalizada");
    }

    /**
     * Obtiene la lista de partículas.
     * @return Lista de partículas.
     */
    public List<Particle> particles() {
        return particles;
    }

    /**
     * Obtiene la lista de demonios.
     * @return Lista de demonios.
     */
    public List<Demon> demons() {
        return demons;
    }

    /**
     * Obtiene la lista de agujeros.
     * @return Lista de agujeros.
     */
    public List<Hole> holes() {
        return holes;
    }


    /**
     * Obtiene el ancho total del contenedor.
     * @return Ancho total del contenedor.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Obtiene la altura total del contenedor.
     * @return Altura total del contenedor.
     */
    public int getHeight() {
        return height;
    }


    /**
     * Hace visible el contenedor y sus elementos.
     * Además, inicia el movimiento de las partículas por un tiempo determinado.
     *
     * @param steps Número de iteraciones antes de detener la simulación.
     */
    public void makeVisible(int steps) {
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

            // Start simulation with a time limit
            runSimulation(steps);
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
     * Obtiene el estado de visibilidad.
     * @return booleano que indica si el contenedor es visible.
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Verifica si alguna partícula ha caído en un agujero y la elimina.
     */
    private void checkHoles() {
        for (Hole h : holes) {
            h.absorbs(particles);
        }
    }

    /**
     * Ejecuta la simulación de movimiento de partículas durante un tiempo determinado.
     *
     * @param steps Número de iteraciones antes de detener la simulación.
     */
    private void runSimulation(int steps) {
        for (int i = 0; i < steps; i++) { // Runs only for "steps" iterations
            for (Particle p : particles) {
                p.move(width, height);
            }
            try {
                Thread.sleep(10); // Controls animation speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
