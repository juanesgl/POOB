import java.util.List;

public class EphemeralParticle extends Particle {
    private int lifespan;

    public EphemeralParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "ephemeral");
        this.lifespan = Math.abs(vx) + Math.abs(vy); // Vida útil inicial basada en la velocidad
    }

    @Override
    public void move(int containerWidth, int containerHeight, List<Demon> demons) {
        final int middleX = containerWidth / 2;
        final int padding = 5;

        if (lifespan <= 0 || (vx == 0 && vy == 0)) {
            this.makeInvisible();
            return;
        }

        int nextX = pX + vx;
        int nextY = pY + vy;
        boolean rebote = false;

        // Rebote en paredes laterales
        if (nextX <= padding || nextX >= containerWidth - padding) {
            vx = -vx;
            rebote = true;
        }

        // Rebote en techo y suelo
        if (nextY <= padding || nextY >= containerHeight - padding) {
            vy = -vy;
            rebote = true;
        }

        // **Rebote o cruce en la pared central**
        boolean isCrossingMiddle = (pX < middleX && nextX >= middleX - padding) ||
                                   (pX > middleX && nextX <= middleX + padding);

        if (isCrossingMiddle) {
            boolean canPass = false;

            for (Demon d : demons) {
                boolean gateOpen = d.isGateOpen();
                boolean sameY = Math.abs(pY - d.getY()) <= padding;
                boolean incorrectSide = (pX < middleX && vx > 0) || (pX > middleX && vx < 0); // Debe moverse al lado correcto

                if (gateOpen && sameY && incorrectSide) {
                    canPass = true;
                    break;
                }
            }

            if (!canPass) {
                vx = -vx; // Rebote si no cumple las condiciones
                rebote = true;
            }
        }

        // Si la partícula rebotó, reducir velocidad y lifespan
        if (rebote) {
            lifespan--;
            vx = ajustarVelocidad(vx);
            vy = ajustarVelocidad(vy);
        }

        // Mover la partícula
        pX += vx;
        pY += vy;
        this.moveHorizontal(vx);
        this.moveVertical(vy);

        // Si ya no tiene movimiento, desaparecer
        if (lifespan <= 0 || (vx == 0 && vy == 0)) {
            this.makeInvisible();
        }
    }

    /**
     * Ajusta la velocidad reduciéndola de forma controlada.
     */
    private int ajustarVelocidad(int velocidad) {
        if (velocidad > 0) return Math.max(0, velocidad - 1);
        if (velocidad < 0) return Math.min(0, velocidad + 1);
        return 0;
    }

    public int getLifespan() {
        return lifespan;
    }
}
