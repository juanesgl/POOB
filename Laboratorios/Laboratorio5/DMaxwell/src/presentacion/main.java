import javax.swing.SwingUtilities;

import presentacion.DMaxwellGUI;

public class main {

    public static void main(String[] args) {
            int particulasRojas = 20;
            int particulasAzules = 10;
        // Crear una instancia de la GUI y hacerla visible
            SwingUtilities.invokeLater(() -> {
                DMaxwellGUI frame = new DMaxwellGUI(particulasRojas, particulasAzules);
                frame.setVisible(true);
            });
        }

}
