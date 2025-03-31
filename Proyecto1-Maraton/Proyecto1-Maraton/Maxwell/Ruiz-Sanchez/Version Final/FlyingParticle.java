import java.util.List;

/**
 * La clase FlyingParticle representa una partícula voladora en la simulación de Maxwell's Demon.
 * Esta partícula se caracteriza por su movimiento estándar con rebotes elásticos en los bordes del contenedor
 * y la capacidad de atravesar la pared central solo cuando un demonio abre su puerta.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class FlyingParticle extends Particle {
    
    /**
     * Constructor de la partícula voladora. Inicializa sus propiedades básicas y posición.
     * 
     * @param pX            Posición horizontal inicial (debe estar dentro del contenedor).
     * @param pY            Posición vertical inicial (debe estar dentro del contenedor).
     * @param vx            Velocidad horizontal inicial (píxeles por movimiento).
     * @param vy            Velocidad vertical inicial (píxeles por movimiento).
     * @param color         Color identificativo de la partícula (ej. "red", "blue").
     * @param containerWidth Ancho total del contenedor (para cálculo de bordes).
     * @param containerHeight Altura total del contenedor (para cálculo de bordes).
     */
    public FlyingParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "flying");
    }

    /**
     * Implementa el movimiento específico de la partícula voladora:
     * - Rebota elásticamente en los bordes del contenedor (invierte velocidad).
     * - Puede atravesar la pared central solo si un demonio habilita su puerta.
     * - Mantiene su velocidad original al no tener restricciones de rotación.
     * 
     * @param containerWidth  Ancho actual del contenedor (para verificación de bordes).
     * @param containerHeight Altura actual del contenedor (para verificación de bordes).
     * @param demons          Lista de demonios activos que controlan las puertas en la pared central.
     */
    @Override
    public void move(int containerWidth, int containerHeight, List<Demon> demons) {
        final int middleX = containerWidth / 2;
        final int padding = 5;
        int nextX = pX + vx;
        int nextY = pY + vy;

        // Rebote en paredes
        if (nextX <= padding || nextX >= containerWidth - padding) {
            vx = -vx;
            nextX = Math.max(padding, Math.min(containerWidth - padding, pX + vx));
        }
        if (nextY <= padding || nextY >= containerHeight - padding) {
            vy = -vy;
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
                vx = -vx; // Rebote si no puede pasar
                nextX = pX + vx;
            }
        }

        // Mover la partícula
        pX = nextX;
        pY = nextY;
        this.moveHorizontal(vx);
        this.moveVertical(vy);
    }
}
