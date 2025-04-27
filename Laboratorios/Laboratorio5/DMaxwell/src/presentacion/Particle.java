package presentacion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.util.Random;

public class Particle extends JPanel {
    private Color color;
    private int x, y;
    private static final int TAMAÑO = 1; 

    public Particle(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        setPreferredSize(new Dimension(TAMAÑO, TAMAÑO));
    }

    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(color); 
    g.fillRect(0, 0, TAMAÑO, TAMAÑO);  
    }

    public static Particle generarParticula(int panelWidth, int panelHeight, Color color) {
        Random rand = new Random();
        int x = rand.nextInt(panelWidth); 
        int y = rand.nextInt(panelHeight); 
        return new Particle(color, x, y);
    }

    


}
