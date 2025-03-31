import java.util.List;

/**
 * La clase RotatorParticle representa una partícula que cambia su dirección
 * de movimiento al colisionar con los bordes del contenedor.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class RotatorParticle extends Particle {
    
    /**
     * Constructor de RotatorParticle.
     * @param pX Posición inicial en el eje X.
     * @param pY Posición inicial en el eje Y.
     * @param vx Velocidad en el eje X.
     * @param vy Velocidad en el eje Y.
     * @param color Color de la partícula.
     * @param containerWidth Ancho del contenedor.
     * @param containerHeight Altura del contenedor.
     */
    public RotatorParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "rotator");
    }

    /**
     * Mueve la partícula dentro de los límites del contenedor, rebotando
     * en las paredes y verificando la posibilidad de cruzar la pared central.
     * @param containerWidth Ancho del contenedor.
     * @param containerHeight Altura del contenedor.
     * @param demons Lista de demonios que pueden afectar el movimiento.
     */
    @Override
    public void move(int containerWidth, int containerHeight, List<Demon> demons) {
        final int middleX = containerWidth / 2;
        final int padding = 5;
        int nextX = pX + vx;
        int nextY = pY + vy;
        boolean rebote = false;

        if (nextX <= padding || nextX >= containerWidth - padding) {
            int temp = vx;
            vx = vy;
            vy = temp;
            rebote = true;
            nextX = Math.max(padding, Math.min(containerWidth - padding, pX + vx));
        }

        if (nextY <= padding || nextY >= containerHeight - padding) {
            int temp = vx;
            vx = vy;
            vy = temp;
            rebote = true;
            nextY = Math.max(padding, Math.min(containerHeight - padding, pY + vy));
        }

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

        pX = nextX;
        pY = nextY;
        this.moveHorizontal(vx);
        this.moveVertical(vy);
    }
}
