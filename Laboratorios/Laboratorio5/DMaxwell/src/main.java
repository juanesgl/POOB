import javax.swing.SwingUtilities;

import presentacion.DMaxwellGUI;

public class main {

    public static void main(String[] args) {
        // Crear una instancia de la GUI y hacerla visible
            SwingUtilities.invokeLater(() -> {
                DMaxwellGUI frame = new DMaxwellGUI();
                frame.setVisible(true);
            });
        }

}
