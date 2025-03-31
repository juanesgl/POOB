import java.util.List;

public class NormalParticle extends Particle {
    public NormalParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "normal");
    }

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
