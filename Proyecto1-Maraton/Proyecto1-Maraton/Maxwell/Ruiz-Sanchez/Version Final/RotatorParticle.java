import java.util.List;

/**
 * La clase RotatorParticle representa una partícula rotatoria en la simulación de Maxwell's Demon.
 * Esta partícula tiene un comportamiento único al rebotar: intercambia su velocidad horizontal y vertical
 * al chocar con paredes o la barrera central, simulando un efecto de "rotación".
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class RotatorParticle extends Particle {
    
    /**
     * Constructor de la partícula rotatoria. Inicializa posición, velocidad, color y dimensiones del contenedor.
     * 
     * @param pX            Posición horizontal inicial.
     * @param pY            Posición vertical inicial.
     * @param vx            Velocidad horizontal inicial.
     * @param vy            Velocidad vertical inicial.
     * @param color         Color de la partícula (ej. "red", "blue").
     * @param containerWidth Ancho del contenedor.
     * @param containerHeight Altura del contenedor.
     */
    public RotatorParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "rotator");
    }

    /**
     * Define el movimiento especializado de la partícula rotatoria:
     * - Intercambia velocidades (vx ↔ vy) al chocar con paredes o la barrera central.
     * - Permite el cruce por la barrera central solo si un demonio abre su puerta.
     * 
     * @param containerWidth  Ancho del contenedor (para límites de movimiento).
     * @param containerHeight Altura del contenedor (para límites de movimiento).
     * @param demons          Lista de demonios que controlan las puertas en la barrera central.
     */

    @Override
    public void move(int containerWidth, int containerHeight, List<Demon> demons) {
        final int middleX = containerWidth / 2;
        final int padding = 5;
        int nextX = pX + vx;
        int nextY = pY + vy;
        boolean rebote = false;

        // **Rebote en paredes laterales**
        if (nextX <= padding || nextX >= containerWidth - padding) {
            int temp = vx;
            vx = vy;
            vy = temp;
            rebote = true;
            nextX = Math.max(padding, Math.min(containerWidth - padding, pX + vx));
        }

        // **Rebote en techo y suelo**
        if (nextY <= padding || nextY >= containerHeight - padding) {
            int temp = vx;
            vx = vy;
            vy = temp;
            rebote = true;
            nextY = Math.max(padding, Math.min(containerHeight - padding, pY + vy));
        }

        // **Cruce en la pared central**
        boolean isCrossingMiddle = (pX < middleX && nextX >= middleX - padding) ||
                                   (pX > middleX && nextX <= middleX + padding);

        if (isCrossingMiddle) {
            boolean canPass = false;

            for (Demon d : demons) {
                boolean gateOpen = d.isGateOpen();
                boolean sameY = Math.abs(pY - d.getY()) <= padding;
                boolean incorrectSide = (pX < middleX && vx > 0) || (pX > middleX && vx < 0);

                if (gateOpen && sameY && incorrectSide) {
                    canPass = true;
                    break;
                }
            }

            if (!canPass) {
                int temp = vx;
                vx = vy;
                vy = temp;
                rebote = true;
                nextX = pX + vx;
            }
        }

        // **Aplicar movimiento**
        pX = nextX;
        pY = nextY;
        this.moveHorizontal(vx);
        this.moveVertical(vy);
    }
}
