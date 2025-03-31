import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * La clase Hole representa un agujero en la simulación de Maxwell's Demon.
 * Un agujero permite el paso de partículas entre las dos cámaras.
 * Se define por su posición, radio y su estado de visibilidad.
 * Atributos:
 * - `x`: Coordenada X del agujero.
 * - `y`: Coordenada Y del agujero.
 * - `radius`: Radio del agujero.
 * - `shape`: Representación gráfica del agujero.
 * - `isVisible`: Indica si el agujero es visible en la interfaz gráfica.
 * @author Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1 (Cycle 1)
 */

public class Hole {
    protected int x, y;
    protected int radius;
    protected Circle shape;
    private boolean isVisible;
    protected String type;
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
    public void makeVisible() { if (!isVisible) { shape.makeVisible(); isVisible = true; } }
    public void makeInvisible() { if (isVisible) { shape.makeInvisible(); isVisible = false; } }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) {
        this.x = x;
        shape.moveHorizontal(x);
    }
    
    public void setY(int y) {
        this.y = y;
        shape.moveVertical(y);
    }

    
    
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
