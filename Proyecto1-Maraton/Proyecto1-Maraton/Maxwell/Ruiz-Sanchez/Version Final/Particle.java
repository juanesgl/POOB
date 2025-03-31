import java.util.List;
import javax.swing.JOptionPane; 

/**
 * La clase Particle representa una partícula en la simulación de Maxwell's Demon.
 * Cada partícula tiene una posición, velocidad y representación gráfica.
 * Puede moverse y rebotar en las paredes del contenedor.
 * 
 * @version 1 (Cycle 1)
 */
public abstract class Particle extends Circle {  // 🔹 Ahora es abstracta
    protected int pX;
    protected int pY;
    protected int vx;
    protected int vy;
    private String color;
    private boolean isVisible;
    protected String type;

    /**
     * Crea una nueva partícula asegurando que está dentro de los límites correctos.
     */
    public Particle(int pX, int pY, int vx, int vy, String color, int containerWidth, int containerHeight, String type) {
        super(); // Llama al constructor de Circle

        if (!color.equals("red") && !color.equals("blue")) {
            System.out.println("Las partículas deben ser rojas o azules");
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
        this.type = type;

        this.changeSize(10);
        this.changeColor(color);
        this.moveHorizontal(pX);
        this.moveVertical(pY);
    }

    /**
     * Método abstracto para mover la partícula. Cada tipo de partícula debe definir su propia lógica.
     */
    public abstract void move(int containerWidth, int containerHeight, List<Demon> demons); // 🔹 Ahora es abstracto

    public boolean isBlue() {
        return "blue".equals(color);
    }

    public boolean isRed() {
        return "red".equals(color);
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return pX;
    }

    public int getY() {
        return pY;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void makeVisible() {
        if (!isVisible) {
            super.makeVisible();
            isVisible = true;
        }
    }
}
