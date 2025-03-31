/**
 * La clase WeakDemon representa un demonio débil en la simulación de Maxwell's Demon.
 * Este tipo de demonio solo puede permitir el paso de una única partícula antes de desaparecer.
 * Una vez utilizado, se desactiva automáticamente y se hace invisible.
 * 
 * Características especiales:
 * - Se crea con la puerta abierta por defecto
 * - Solo permite pasar una partícula en toda su existencia
 * - Se autodestruye (hace invisible) después de su único uso
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class WeakDemon extends Demon {
    private boolean used = false;
    /**
     * Constructor para crear un demonio débil en una posición específica.
     * 
     * @param d Posición vertical (coordenada Y) en el contenedor
     * @param w Posición horizontal (coordenada X) en el contenedor (normalmente la pared central)
     */
    public WeakDemon(int d, int w) {
        super(d, w, "weak");
        setGateOpen(true);
    }
    /**
     * Determina si una partícula puede pasar a través de este demonio.
     * Solo permite el paso si:
     * 1. La puerta está abierta
     * 2. El demonio no ha sido usado antes
     * 
     * @param p La partícula que intenta pasar
     * @return true si la partícula puede pasar, false en caso contrario
     */
    public boolean canPass(Particle p) {
        if(isGateOpen() && !used) {
            used = true;
            makeInvisible();
            return true;
        }
        return false;
    }
}

