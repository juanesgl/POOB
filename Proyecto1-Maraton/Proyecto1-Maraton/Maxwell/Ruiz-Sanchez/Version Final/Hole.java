import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * La clase Hole representa un agujero en la simulación de Maxwell's Demon.
 * Este agujero puede absorber partículas que entren en su radio de acción, excepto
 * las partículas voladoras (FlyingParticle). Su posición y tamaño son aleatorios,
 * pero evita aparecer cerca de la pared central del contenedor.
 * 
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1000 (Cycle 3)
 */

public class Hole {
    protected int x, y;
    protected int radius;
    protected Circle shape;
    private boolean isVisible;
    protected String type;
    
     /**
     * Constructor que crea un agujero con posición y tamaño aleatorios.
     * El agujero nunca se genera en la zona central (10px alrededor del centro).
     * 
     * @param containerWidth  Ancho del contenedor para cálculo de posición.
     * @param containerHeight Altura del contenedor para cálculo de posición.
     * @param type            Tipo de agujero ("normal" o "movil").
     */
    
    public Hole(int containerWidth, int containerHeight,String type) {
        this.type = type.toLowerCase();
        java.util.Random rand = new java.util.Random();
        int middleX = containerWidth / 2;
        do { x = rand.nextInt(containerWidth - 20) + 10; }
        while (x >= middleX - 10 && x <= middleX + 10);
        y = rand.nextInt(containerHeight - 20) + 10;
        radius = rand.nextInt(10) + 10;
        shape = new Circle();
        shape.changeSize(radius);
        shape.changeColor("magenta");
        shape.moveHorizontal(x);
        shape.moveVertical(y);
        isVisible = false;
    }
    
    
    /**
     * Hace visible el agujero en la interfaz gráfica.
     */
    public void makeVisible() { if (!isVisible) { shape.makeVisible(); isVisible = true; } }
    
    /**
     * Hace invisible el agujero en la interfaz gráfica.
     */
    
    public void makeInvisible() { if (isVisible) { shape.makeInvisible(); isVisible = false; } }
    
    /**
     * Obtiene la posición horizontal del agujero.
     * @return Coordenada X en píxeles.
     */
    
    public int getX() { return x; }
    
    /**
     * Obtiene la posición vertical del agujero.
     * @return Coordenada Y en píxeles.
     */
    
    public int getY() { return y; }
    
    /**
     * Establece una nueva posición horizontal y actualiza la representación gráfica.
     * @param x Nueva coordenada X.
     */
       
    public void setX(int x) {
        this.x = x;
        shape.moveHorizontal(x);
    }
     /**
     * Establece una nueva posición vertical y actualiza la representación gráfica.
     * @param y Nueva coordenada Y.
     */
    public void setY(int y) {
        this.y = y;
        shape.moveVertical(y);
    }

    /**
     * Absorbe partículas que estén dentro de su radio de acción.
     * Las partículas voladoras (FlyingParticle) son inmunes a este efecto.
     * 
     * @param particles Lista de partículas a verificar para absorción.
     */
    
    public void absorbs(java.util.List<Particle> particles) {
        java.util.Iterator<Particle> it = particles.iterator();
        while(it.hasNext()){
            Particle p = it.next();
            double dist = Math.sqrt(Math.pow(p.getX()-x,2) + Math.pow(p.getY()-y,2));
            if(dist <= radius) {
                if(p instanceof FlyingParticle) continue;
                p.makeInvisible();
                it.remove();
            }
        }
    }
}
