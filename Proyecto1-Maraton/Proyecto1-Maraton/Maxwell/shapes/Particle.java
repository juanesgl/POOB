import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane; // Import for pop-up messages
/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Cada partícula tiene una posición, velocidad y representación gráfica.
 * Puede moverse y rebotar en las paredes del contenedor.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.1 (With Movement)
 */


/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Se asegura que las partículas estén en su cámara correcta y se muevan dentro del tablero.
 * 
 * @author Edgar Daniel Ruiz Patiño
 * @author Juan Esteban Sánchez García
 * @version 1.4 (Placement + Movement)
 */
public class Particle extends Circle {
    private int pX, pY;
    private int vx, vy;
    private String color;
    private boolean isVisible;

    /**
     * Crea una nueva partícula asegurando que está dentro de los límites correctos.
     * 
     * @param pX Posición inicial en X.
     * @param pY Posición inicial en Y.
     * @param vx Velocidad en X.
     * @param vy Velocidad en Y.
     * @param color Color de la partícula ("red" o "blue").
     * @param containerWidth Ancho total del contenedor.
     * @param containerHeight Alto total del contenedor.
     */
    public Particle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight) {
        super();//sirve para llamar al constructor de circle

        int middleX = (containerWidth/2) + 5; 

        
        if (!color.equals("red") && !color.equals("blue")) {
            System.out.println("Las particulas deben ser rojas o azules");
            return;
        }

        
        if ((color.equals("blue") && pX >= middleX) || (color.equals("red") && pX < middleX)) {
            System.out.println("La partícula no va en esa cámara");
            return;
        }



        
        if (pY < 5 || pY >= (containerHeight - 10)) {  
            System.out.println("Las partículas no están dentro del contenedor");
            return;
        }   


        this.pX = pX;
        this.pY = pY;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        this.isVisible = false;

        this.changeSize(10);
        this.changeColor(color);
        this.moveHorizontal(pX);
        this.moveVertical(pY);
    }

    /**
     * Moves the particle within the container while ensuring no invalid movement.
     * 
     * @param containerWidth  width del contenedor.
     * @param containerHeight height del contenedor.
     * @param demons Es la lista de demonios que existen
     */
    public void move(int containerWidth, int containerHeight,List<Demon> demons) {
        int middleX = containerWidth / 2; 
        int padding = 5;
        
        int nextX = pX + vx;
        int nextY = pY + vy;
    
        
        if (nextY <= padding  || nextY >= containerHeight - padding ) {
            vy = -vy;
        }
        
        
        
        
        for (Demon demon : demons) {
        if (Math.abs(pX - middleX) <= 10 && Math.abs(pY - demon.getY()) <= 10 && demon.isGateOpen()) {
            if (color.equals("blue")) {
                color = "red";
                nextX = middleX + padding; 
            } else if (color.equals("red")) {
                color = "blue";
                nextX = middleX - padding; 
            }
            this.changeColor(color); 
            break; 
        }
        }
        
        
        
        
        
        if (color.equals("blue")) {
        if (nextX <= padding) { // Rebote en el borde izquierdo
            vx = Math.abs(vx); // Forzar movimiento hacia la derecha
            nextX = padding + 1;
        }
        if (nextX >= middleX - padding) { // Rebote en la pared central
            vx = -Math.abs(vx); // Forzar movimiento hacia la izquierda
            nextX = middleX - padding - 1;
        }
        }
       
        
        if (color.equals("red")) {
        if (nextX <= middleX + padding) { // Rebote en la pared central
            vx = Math.abs(vx); // Forzar movimiento hacia la derecha
            nextX = middleX + padding + 1;
        }
        if (nextX >= containerWidth - padding) { // Rebote en el borde derecho
            vx = -Math.abs(vx); // Forzar movimiento hacia la izquierda
            nextX = containerWidth - padding - 1;
        }
        }
        
               
        pX = nextX;
        pY = nextY;
    
        
        this.moveHorizontal(vx);
        this.moveVertical(vy);
    }


    /**
     * Obtiene el color de la partícula.
     * @return "red" o "blue".
     */
    public String getColor() {
        return color;
    }

    /**
     * Obtiene la posición X de la partícula.
     * @return Coordenada X.
     */
    public int getX() {
        return pX;
    }

    /**
     * Obtiene la posición Y de la partícula.
     * @return Coordenada Y.
     */
    public int getY() {
        return pY;
    }

    /**
     * Hace que la partícula sea visible.
     */
    public void makeVisible() {
        if (!isVisible) {
            super.makeVisible();
            isVisible = true;
        }
    }
}


