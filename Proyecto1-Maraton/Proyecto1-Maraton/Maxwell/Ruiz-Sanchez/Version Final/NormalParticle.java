import java.util.List;

/**
 * La clase NormalParticle representa una partícula básica en la simulación de Maxwell's Demon.
 * Esta partícula sigue un comportamiento clásico de rebote elástico en los bordes del contenedor
 * y solo puede atravesar la pared central cuando un demonio habilita su puerta.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class NormalParticle extends Particle {
    
    /**
     * Constructor para crear una partícula normal.
     * 
     * @param pX            Posición horizontal inicial (en píxeles).
     * @param pY            Posición vertical inicial (en píxeles).
     * @param vx            Velocidad horizontal inicial (píxeles por movimiento).
     * @param vy            Velocidad vertical inicial (píxeles por movimiento).
     * @param color         Color de la partícula ("red", "blue", etc.).
     * @param containerWidth Ancho total del contenedor (para límites de movimiento).
     * @param containerHeight Altura total del contenedor (para límites de movimiento).
     */
    public NormalParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "normal");
    }

    /**
     * Implementa el movimiento de la partícula normal:
     * 1. Rebota elásticamente en los bordes superior, inferior y laterales.
     * 2. Verifica si puede atravesar la pared central según el estado de los demonios.
     * 3. Actualiza su posición según las reglas de movimiento.
     * 
     * @param containerWidth  Ancho actual del contenedor.
     * @param containerHeight Altura actual del contenedor.
     * @param demons          Lista de demonios activos que controlan las puertas.
     */
    @Override
    public void move(int containerWidth, int containerHeight, List<Demon> demons) {
        final int middleX = containerWidth / 2;
        final int padding = 5;
        int nextX = pX + vx;
        int nextY = pY + vy;

        // Rebote en las paredes superior e inferior
        if (nextY <= padding || nextY >= containerHeight - padding) {
            vy = -vy;
            nextY = Math.max(padding, Math.min(containerHeight - padding, pY + vy));
        }

        // Rebote en las paredes laterales
        if (nextX <= padding || nextX >= containerWidth - padding) {
            vx = -vx;
            nextX = Math.max(padding, Math.min(containerWidth - padding, pX + vx));
        }

        // Lógica de cruce de demonios
        boolean isCrossingMiddle = (pX < middleX && nextX >= middleX - padding) ||
                                   (pX > middleX && nextX <= middleX + padding);

        if (isCrossingMiddle) {
            boolean canPass = false;

            for (Demon d : demons) {
                boolean gateOpen = d.isGateOpen();
                boolean sameY = Math.abs(pY - d.getY()) <= padding;
                boolean incorrectSide = (pX < middleX && vx > 0) || (pX > middleX && vx < 0); // Corregido el signo de vx

                if (gateOpen && sameY && incorrectSide) {
                    canPass = true;
                    break;
                }
            }

            if (!canPass) {
                vx = -vx; // Rebote si no cumple las condiciones
                nextX = pX + vx;
            }
        }

        pX = nextX;
        pY = nextY;
        this.moveHorizontal(vx);
        this.moveVertical(vy);
    }
}
