package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class DMaxwellGUI extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JMenuItem menuItemNuevo, menuItemAbrir, menuItemSalvar, menuItemSalir;


    // Atributos privados

    private JPanel panelTablero;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel panelCentral;
    private JPanel compuertaCentral;

    private DMaxwellGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("Maxwell Discreto");
        setSize((Toolkit.getDefaultToolkit().getScreenSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 4,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 4);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        prepareElementsMenu();

        prepareElementsBoard();
    }

    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                exit();
            }
        });

        menuItemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo();
            }
        });

        menuItemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
        });

        menuItemSalir.addActionListener(e -> exit());
    }

    private void prepareElementsMenu() {
        menuBar = new JMenuBar();

        // Menú Archivo
        menuArchivo = new JMenu("Archivo");
        menuItemNuevo = new JMenuItem("Nuevo");
        menuItemAbrir = new JMenuItem("Abrir");
        menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalir = new JMenuItem("Salir");

        menuArchivo.add(menuItemNuevo);
        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemSalvar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);

        // Menú Configuración
        JMenu menuConfiguracion = new JMenu("Configuración");
        JMenuItem menuItemPreferencias = new JMenuItem("Preferencias");
        JMenuItem menuItemAjustes = new JMenuItem("Ajustes");

        menuConfiguracion.add(menuItemPreferencias);
        menuConfiguracion.add(menuItemAjustes);

        // Añadir los menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuConfiguracion);

        setJMenuBar(menuBar);
    }


    private void prepareElementsBoard() {
        panelCentral = new JPanel(new BorderLayout());

        // === Panel izquierdo: partículas rojas ===
        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridBagLayout());
        panelIzquierdo.setPreferredSize(new Dimension(50, 0)); // tamaño fijo para cámara
        panelIzquierdo.setBackground(Color.DARK_GRAY);

        JPanel grupoRojo = new JPanel();
        grupoRojo.setLayout(new BoxLayout(grupoRojo, BoxLayout.Y_AXIS));
        grupoRojo.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < 7; i++) {
            JPanel particulaRoja = new JPanel();
            particulaRoja.setBackground(Color.RED);
            particulaRoja.setPreferredSize(new Dimension(10, 10));
            grupoRojo.add(particulaRoja);
            grupoRojo.add(Box.createVerticalStrut(5));
        }
        panelIzquierdo.add(grupoRojo);
        panelCentral.add(panelIzquierdo, BorderLayout.WEST);

        // === Panel central: compuerta (negro) ===
        JPanel panelCompuerta = new JPanel();
        panelCompuerta.setBackground(Color.BLACK);
        panelCentral.add(panelCompuerta, BorderLayout.CENTER);

        // === Panel derecho: partículas azules ===
        panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridBagLayout());
        panelDerecho.setPreferredSize(new Dimension(50, 0)); // tamaño fijo para cámara
        panelDerecho.setBackground(Color.DARK_GRAY);

        JPanel grupoAzul = new JPanel();
        grupoAzul.setLayout(new BoxLayout(grupoAzul, BoxLayout.Y_AXIS));
        grupoAzul.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < 7; i++) {
            JPanel particulaAzul = new JPanel();
            particulaAzul.setBackground(Color.BLUE);
            particulaAzul.setPreferredSize(new Dimension(10, 10));
            grupoAzul.add(particulaAzul);
            grupoAzul.add(Box.createVerticalStrut(5));
        }
        panelDerecho.add(grupoAzul);
        panelCentral.add(panelDerecho, BorderLayout.EAST);

        getContentPane().add(panelCentral, BorderLayout.CENTER);
    }



    private void refresh() {
        repaint();
        revalidate();
    }


    private JLabel crearParticula(Color color) {
        JLabel particula = new JLabel();
        particula.setOpaque(true);
        particula.setBackground(color);
        particula.setPreferredSize(new Dimension(10, 10));
        return particula;
    }


    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad en construcción\nArchivo seleccionado: " + file.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    private void guardarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad en construcción\nArchivo guardado como: " + file.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    private void exit() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que quieres salir?",
                "Confirmación de salida",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    public static void main(String[] args) {
        DMaxwellGUI gui = new DMaxwellGUI();
        gui.setVisible(true);
    }
}
