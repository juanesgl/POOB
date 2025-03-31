import java.util.List;
/**
 * La clase EphemeralParticle representa una partícula efímera en la simulación de Maxwell's Demon.
 * Esta partícula tiene un tiempo de vida limitado (lifespan) que disminuye con cada rebote,
 * y su velocidad se reduce gradualmente hasta desaparecer del contenedor.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */
public class EphemeralParticle extends Particle {
    private int lifespan;
    /**
     * Constructor para crear una partícula efímera.
     * Su vida inicial se calcula como la suma de los valores absolutos de sus velocidades.
     * 
     * @param pX            Posición horizontal inicial (px).
     * @param pY            Posición vertical inicial (px).
     * @param vx            Velocidad horizontal inicial (px/movimiento).
     * @param vy            Velocidad vertical inicial (px/movimiento).
     * @param color         Color identificativo ("red", "blue", etc.).
     * @param containerWidth Ancho del contenedor (para límites).
     * @param containerHeight Altura del contenedor (para límites).
     */
    public EphemeralParticle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super(pX, pY, vx, vy, color, containerWidth, containerHeight, "ephemeral");
        this.lifespan = Math.abs(vx) + Math.abs(vy); // Vida útil inicial basada en la velocidad
    }
    /**
     * Implementa el movimiento especializado para partículas efímeras:
     * 1. Reduce su lifespan en cada rebote.
     * 2. Disminuye gradualmente su velocidad.
     * 3. Desaparece cuando su lifespan llega a cero o pierde toda velocidad.
     * 
     * @param containerWidth  Ancho actual del contenedor.
     * @param containerHeight Altura actual del contenedor.
     * @param demons          Lista de demonios que controlan las puertas centrales.
     */ 
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

        
        if (nextX <= padding || nextX >= containerWidth - padding) {
            vx = -vx;
            rebote = true;
        }

        
        if (nextY <= padding || nextY >= containerHeight - padding) {
            vy = -vy;
            rebote = true;
        }

        
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
                vx = -vx; 
                rebote = true;
            }
        }

        
        if (rebote) {
            lifespan--;
            vx = ajustarVelocidad(vx);
            vy = ajustarVelocidad(vy);
        }

        
        pX += vx;
        pY += vy;
        this.moveHorizontal(vx);
        this.moveVertical(vy);

        
        if (lifespan <= 0 || (vx == 0 && vy == 0)) {
            this.makeInvisible();
        }
    }

    /**
     * Ajusta la velocidad reduciéndola en 1 unidad (manteniendo el signo).
     * 
     * @param velocidad Valor actual de la velocidad (positivo o negativo).
     * @return Nueva velocidad ajustada (más cercana a cero).
     */
    private int ajustarVelocidad(int velocidad) {
        if (velocidad > 0) return Math.max(0, velocidad - 1);
        if (velocidad < 0) return Math.min(0, velocidad + 1);
        return 0;
    }
    /**
     * Obtiene el tiempo de vida restante de la partícula.
     * 
     * @return Valor actual del lifespan (mayor o igual a cero).
     */
    public int getLifespan() {
        return lifespan;
    }
}
