
/**
 * La clase BlueDemon representa un demonio especializado en la simulación de Maxwell's Demon
 * que solo permite el paso de partículas azules. Este demonio mantiene su puerta permanentemente
 * abierta y no puede ser cerrada, actuando como un filtro selectivo para partículas azules.
 * 
 * Características principales:
 * - Puerta permanentemente abierta (no puede cerrarse)
 * - Solo permite el paso de partículas azules
 * - Ignora cualquier intento de cambiar el estado de la puerta
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class BlueDemon extends Demon {
     /**
     * Constructor que crea un BlueDemon en la posición especificada.
     * La puerta se inicializa permanentemente abierta.
     * 
     * @param d Posición vertical (coordenada Y) en el contenedor
     * @param w Posición horizontal (coordenada X) en el contenedor (normalmente pared central)
     */
    public BlueDemon(int d, int w) {
        super(d, w, "blue");
        setGateOpen(true);
    }
    /**
     * Sobreescribe el método para mantener la puerta siempre abierta.
     * Ignora cualquier parámetro de entrada y fuerza el estado a abierto.
     * 
     * @param state Estado solicitado (ignorado)
     */
    @Override
    public void setGateOpen(boolean state) {
        super.setGateOpen(true);
    }
    /**
     * Determina si una partícula puede pasar a través de este demonio.
     * 
     * @param p La partícula que intenta pasar
     * @return true si la puerta está abierta Y la partícula es azul, false en caso contrario
     */
    public boolean canPass(Particle p) {
        return isGateOpen() && p.isBlue();
    }
}



