package presentacion;
import javax.swing.*;
import javax.tools.Tool;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
public class DMaxwellGUI extends JFrame{

    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JMenuItem menuItemNuevo, menuItemAbrir, menuItemSalvar, menuItemSalir;
    public DMaxwellGUI() {
        
        prepareElements();
        prepareActions();
    }

    public void prepareElements() {

        //CICLO 0
        setTitle("Maxwell Discreto");
        setSize((Toolkit.getDefaultToolkit().getScreenSize().width / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4, (Toolkit.getDefaultToolkit().getScreenSize().height) / 4);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        //CICLO 1

        prepareElementsMenu();
        prepareActionsMenu();
        

    }

    public void prepareActions() {
        //CICLO 1
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                exit();
            }
        });

        
    }

    public void prepareActionsMenu() {
        //CICLO 1
        menuItemSalir.addActionListener(e -> exit());
        //CICLO 2
        menuItemAbrir.addActionListener(e -> abrirArchivo());
        menuItemSalvar.addActionListener(e -> guardarArchivo());

    }
    
    //CICLO 1
    public void exit() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "¿Estas seguro de que quieres salir?",
                "Confirmacion de salida",
                JOptionPane.YES_NO_OPTION    
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    //CICLO 2
    public void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad en construcción\nArchivo seleccionado: " + file.getAbsolutePath());
        } else {    
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    public void guardarArchivo(){

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Funcionalidad en construcción\nGuardando archivo: " + file.getAbsolutePath());
        }else{
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    private void prepareElementsMenu() { //CICLO 1
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
}

