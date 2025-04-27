import javax.swing.SwingUtilities;

import presentacion.DMaxwellGUI;

public class main {

    public static void main(String[] args) {
            int particulasRojas = 5;
            int particulasAzules = 2;
            int agujerosNegros = 10;
        
            SwingUtilities.invokeLater(() -> {
                DMaxwellGUI frame = new DMaxwellGUI(particulasRojas, particulasAzules,agujerosNegros);
                frame.setVisible(true);
            });
        }

}
