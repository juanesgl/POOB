import java.util.List;

public class FlyingParticle extends Particle {
    public FlyingParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "flying");
    }

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
