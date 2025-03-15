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
     * La partícula se posiciona aleatoriamente dentro de los límites de la cámara correspondiente
     * (azul en la izquierda y roja en la derecha). También se le asigna una velocidad aleatoria.
     *
     * @param color Color de la partícula a crear. Puede ser "red" o "blue".
     *              - "red":Se crea en la mitad derecha del contenedor.
     *              - "blue":Se crea en la mitad izquierda del contenedor.
     *
     * - La partícula debe estar dentro de su respectiva mitad.
     * - Se evita colocarla demasiado cerca de los bordes con un margen de seguridad (`padding`).
     * - La velocidad en `x` y `y` no puede ser 0, para asegurar movimiento.
     *
     * Funcionamiento:
     * 1. Se genera una posición aleatoria `(pX, pY)` dentro de la región correcta según el color.
     * 2. Se asigna una velocidad aleatoria `(vx, vy)`, asegurando que ninguna sea 0.
     * 3. Se crea la partícula y se agrega a la lista de partículas.
     * 4. Si la simulación es visible (`isVisible`), se hace visible la nueva partícula.
     */

    public void addParticle(String color) {
    Random rand = new Random();
    int pX, pY, vx, vy;
    int middleX = width / 2; 
    int padding = 15; // Margen para evitar que se salgan

    
    pY = padding + rand.nextInt(height - (2 * padding)); 

    
    if (color.equals("red")) {
        pX = middleX + padding + rand.nextInt((width / 2) - (2*padding)); 
    } else if (color.equals("blue")) {
        pX = padding + rand.nextInt(middleX - (2*padding)); 
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
     *
     * Funcionamiento:
     * 1. Verifica si hay partículas en la lista `particles`.
     * 2. Si la lista no está vacía:
     *    - Obtiene la última partícula agregada.
     *    - La hace invisible antes de eliminarla.
     *    - La elimina de la lista.
     * 3. Si la lista está vacía, muestra un mensaje indicando que no hay partículas para eliminar.
     *
     * - No hace nada si no hay partículas en la lista.
     * - Solo elimina la última partícula agregada (orden tipo pila, LIFO).
     */
    public void removeParticle() {
        if(!particles.isEmpty()){
        Particle lastParticle=particles.get(particles.size()-1);
        lastParticle.makeInvisible();
        particles.remove(particles.size()-1);
        }else{
        System.out.println("No hay particulas para eliminar");
        }
        
    }

    public void addHole() {
        Random rand = new Random();
        int middleX = width / 2;
        int holeX, holeY;

        do {
            holeX = rand.nextInt(width - 20) + 10;
        } while (holeX >= middleX - 10 && holeX <= middleX + 10);

        holeY = rand.nextInt(height - 20) + 10;

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
