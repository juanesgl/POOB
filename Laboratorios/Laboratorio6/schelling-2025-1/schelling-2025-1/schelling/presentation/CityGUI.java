package presentation;

import domain.*;
import javax.swing.JMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * La clase CityGUI es la interfaz gráfica de usuario (GUI) para la simulación de la ciudad de Schelling.
 * Proporciona una visualización de la ciudad y permite interactuar con ella a través de un botón para actualizar la ciudad.
 */
public class CityGUI extends JFrame {

    /** El tamaño de un lado de la celda en la cuadrícula. */
    public static final int SIDE = 20;

    /** El tamaño de la ciudad. */
    public final int SIZE;

    /** El botón para actualizar la ciudad. */
    private JButton ticTacButton;

    /** El panel de control de la interfaz. */
    private JPanel controlPanel;

    /** El panel donde se dibuja la ciudad. */
    private PhotoCity photo;

    /** El objeto que representa la ciudad. */
    private City theCity;

    private JMenuBar menubar;

    private JMenu file;

    private JMenuItem openItem;
    private JMenuItem importItem;
    private JMenuItem newItem;
    private JMenuItem guardarComoItem;
    private JMenuItem exportarComoItem;
    private JMenuItem salirItem;

    /**
     * Constructor de la clase CityGUI. Inicializa los elementos de la interfaz y la ciudad.
     */
    private CityGUI() {
        theCity = new City();
        SIZE = theCity.getSize();
        // Inicializamos los JMenuItems aquí
        newItem = new JMenuItem("Nueva Ciudad");
        openItem = new JMenuItem("Abrir");
        guardarComoItem = new JMenuItem("Guardar como");
        importItem = new JMenuItem("Importar");
        exportarComoItem = new JMenuItem("Exportar como");
        salirItem = new JMenuItem("Salir");
        prepareElements();
        prepareActions();
        prepareElementsMenu();
        prepareActionsMenu(); // Nuevo método para los listeners del menú
    }

    /**
     * Prepara los elementos visuales de la interfaz gráfica.
     */
    private void prepareElements() {
        setTitle("Schelling City");
        photo = new PhotoCity(this);
        ticTacButton = new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo, BorderLayout.NORTH);
        add(ticTacButton, BorderLayout.SOUTH);
        setSize(new Dimension(SIDE * SIZE + 15, SIDE * SIZE + 72));
        setResizable(false);
        photo.repaint();
    }

    /**
     * Prepara las acciones asociadas a los componentes de la interfaz (botón Tic-tac).
     */
    private void prepareActions() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ticTacButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticTacButtonAction();
            }
        });
    }

    /**
     * Acción que se realiza cuando se presiona el botón "Tic-tac".
     * Actualiza la ciudad y repinta la cuadrícula.
     */
    private void ticTacButtonAction() {
        theCity.ticTac(); // Actualiza la ciudad
        photo.repaint(); // Repinta la cuadrícula
        System.out.println("Botón Tic-tac presionado"); // Verifica que el método se está ejecutando
    }

    /**
     * Obtiene el objeto City asociado a esta interfaz gráfica.
     *
     * @return El objeto City asociado a esta interfaz gráfica.
     */
    public City gettheCity() {
        return theCity;
    }

    /**
     * Método principal para ejecutar la interfaz gráfica de la ciudad.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        CityGUI cg = new CityGUI();
        cg.setVisible(true);
    }

    private void prepareElementsMenu(){
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        file = new JMenu("Archivo");

        newItem = new JMenuItem("Nueva Ciudad");
        file.add(newItem);
        file.addSeparator();
        file.add(openItem);
        file.add(guardarComoItem);
        file.addSeparator();
        file.add(importItem);
        file.add(exportarComoItem);
        file.addSeparator();
        file.add(salirItem);

        menubar.add(file);
    }

    /**
     * Crea los oyentes (listeners) para las opciones del menú.
     */
    private void prepareActionsMenu() {
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionNew();
            }
        });

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionOpen();
            }
        });

        guardarComoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionSave();
            }
        });

        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionImport();
            }
        });

        exportarComoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionExport();
            }
        });

        salirItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionExit();
            }
        });
    }

    /**
     * Método controlador para la opción "Nueva Ciudad".
     */
    private void optionNew() {
        theCity = new City(); 
        remove(photo);
        photo = new PhotoCity(this);
        
        add(photo, BorderLayout.NORTH);
        
        revalidate();
        repaint();
        System.out.println("Opción Nueva Ciudad seleccionada. Se ha creado una nueva ciudad.");
       
     
    }

    /**
     * Método controlador para la opción "Abrir".
     */
    private void optionOpen() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(CityGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                theCity.open(file);
                // Aquí iría la lógica para cargar el estado de la ciudad desde el archivo
                System.out.println("Abriendo archivo: " + file.getName());
            } catch (CityException ex) {
                JOptionPane.showMessageDialog(CityGUI.this, ex.getMessage(), "Error al abrir", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método controlador para la opción "Guardar como".
     */
    private void optionSave() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(CityGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                theCity.save(file);
                // Aquí iría la lógica para guardar el estado de la ciudad en el archivo
                System.out.println("Guardando como: " + file.getName());
            } catch (CityException ex) {
                JOptionPane.showMessageDialog(CityGUI.this, ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método controlador para la opción "Importar".
     */
    private void optionImport() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(CityGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                theCity.importt(file);
                // Aquí iría la lógica para importar datos a la ciudad desde el archivo
                System.out.println("Importando desde: " + file.getName());
            } catch (CityException ex) {
                JOptionPane.showMessageDialog(CityGUI.this, ex.getMessage(), "Error al importar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método controlador para la opción "Exportar como".
     */
    private void optionExport() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(CityGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                theCity.export(file);
                // Aquí iría la lógica para exportar datos de la ciudad al archivo
                System.out.println("Exportando a: " + file.getName());
            } catch (CityException ex) {
                JOptionPane.showMessageDialog(CityGUI.this, ex.getMessage(), "Error al exportar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método controlador para la opción "Salir".
     */
    private void optionExit() {
        System.exit(0);
        System.out.println("Opción Salir seleccionada. La aplicación se cerrará.");
    }
}

/**
 * La clase PhotoCity es un panel donde se dibuja la representación visual de la ciudad.
 * Esta clase se encarga de pintar la cuadrícula y los elementos dentro de la ciudad.
 */
class PhotoCity extends JPanel {

    /** La instancia de la GUI principal. */
    private CityGUI gui;

    /**
     * Constructor de la clase PhotoCity.
     *
     * @param gui La instancia de CityGUI que contiene la ciudad.
     */
    public PhotoCity(CityGUI gui) {
        this.gui = gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE * gui.SIZE + 10, gui.SIDE * gui.SIZE + 10));
    }

    /**
     * Método para pintar la representación gráfica de la ciudad en el panel.
     *
     * @param g El objeto Graphics utilizado para dibujar en el panel.
     */
    public void paintComponent(Graphics g) {
        City theCity = gui.gettheCity();
        super.paintComponent(g);

        // Dibuja las líneas de la cuadrícula
        for (int c = 0; c <= theCity.getSize(); c++) {
            g.drawLine(c * gui.SIDE, 0, c * gui.SIDE, theCity.getSize() * gui.SIDE);
        }
        for (int f = 0; f <= theCity.getSize(); f++) {
            g.drawLine(0, f * gui.SIDE, theCity.getSize() * gui.SIDE, f * gui.SIDE);
        }

        // Dibuja los elementos de la ciudad (items y agentes)
        for (int f = 0; f < theCity.getSize(); f++) {
            for (int c = 0; c < theCity.getSize(); c++) {
                if (theCity.getItem(f, c) != null) {
                    g.setColor(theCity.getItem(f, c).getColor());
                    if (theCity.getItem(f, c).shape() == Item.SQUARE) {
                        if (theCity.getItem(f, c).isActive()) {
                            g.fillRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        } else {
                            g.drawRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        }
                    } else {
                        if (theCity.getItem(f, c).isActive()) {
                            g.fillOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        } else {
                            g.drawOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        }
                    }
                    // Dibuja los agentes dentro de la ciudad
                    if (theCity.getItem(f, c).isAgent()) {
                        g.setColor(Color.red);
                        if (((Agent) theCity.getItem(f, c)).isHappy()) {
                            g.drawString("u", gui.SIDE * c + 6, gui.SIDE * f + 15);
                        } else if (((Agent) theCity.getItem(f, c)).isIndifferent()) {
                            g.drawString("_", gui.SIDE * c + 7, gui.SIDE * f + 10);
                        } else if (((Agent) theCity.getItem(f, c)).isDissatisfied()) {
                            g.drawString("~", gui.SIDE * c + 6, gui.SIDE * f + 17);
                        }
                    }
                }
            }
        }
    }
}