import javax.swing.SwingUtilities;

import presentacion.DMaxwellGUI;

public class main {

    public static void main(String[] args) {
            int particulasRojas = 15;
            int particulasAzules = 1;
        // Crear una instancia de la GUI y hacerla visible
            SwingUtilities.invokeLater(() -> {
                DMaxwellGUI frame = new DMaxwellGUI(particulasRojas, particulasAzules);
                frame.setVisible(true);
            });
        }

}
