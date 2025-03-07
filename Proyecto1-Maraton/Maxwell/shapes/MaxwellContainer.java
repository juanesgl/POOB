
/**
 * La clase MaxwellContainer representa el contenedor en la simulación de Maxwell's Demon.
 * Este contenedor almacena partículas y puede contener múltiples demonios que controlan el paso de partículas.
 * También define las cámaras separadas por una pared central y múltiples agujeros que permiten el paso de partículas.
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * Versión: 1.1
 */

import java.util.ArrayList;
import java.util.List;

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
     * Agrega una partícula al contenedor.
     * @param particle Partícula a agregar.
     */
    public void addParticle(Particle particle) {
        particles.add(particle);
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
     * Agrega un agujero al contenedor.
     * @param hole Agujero a agregar.
     */
    public void addHole(Hole hole) {
        holes.add(hole);
    }

    /**
     * Agrega un demonio al contenedor.
     * @param demon Demonio a agregar.
     */
    public void addDemon(Demon demon) {
        demons.add(demon);
        if (isVisible) {
            demon.makeVisible();
        }
    }

    /**
     * Elimina un demonio del contenedor.
     * @param demon Demonio a eliminar.
     */
    public void deleteDemon(Demon demon) {
        demons.remove(demon);
        demon.makeInvisible();
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
     * Obtiene el estado
     * 
     * @return booleano de visible
     */
    public boolean isVisible(){
    return isVisible;
    }
    
    
}
