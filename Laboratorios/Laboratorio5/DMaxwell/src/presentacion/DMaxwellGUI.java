package presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;

import javax.swing.border.EmptyBorder;

public class DMaxwellGUI extends JFrame{

    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JMenuItem menuItemNuevo, menuItemAbrir, menuItemSalvar, menuItemSalir;
    private JPanel boardPanel;
    private JPanel controlPanel;
    private JPanel leftPanel, rightPanel; 
    private int r, b,h;
    private double windowSizeFactor = 0.5; 
    private JMenu menuConfiguracion;
    private JMenuItem menuItemPreferencias, menuItemAjustes;
    private JMenuItem menuItemColores;

    public DMaxwellGUI(int rojas, int azules,int agujeros){
        this.r = rojas;
        this.b = azules;
        this.h=agujeros;
        prepareElements();
        prepareActions();
    }

    public void prepareElements() {
        //CICLO 0
        setTitle("Maxwell Discreto");
        ajustarTamañoVentana();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //CICLO 1
        prepareElementsMenu();
        prepareActionsMenu();

        //CICLO 3
        setLayout(new BorderLayout());

        prepareElementsBoard();        
        prepareElementsControls();     

        add(boardPanel, BorderLayout.CENTER); 
        add(controlPanel, BorderLayout.SOUTH); 
    }

    
    private void ajustarTamañoVentana() {
        int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().width * windowSizeFactor);
        int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().height * windowSizeFactor);
        setSize(width, height);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2);
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

        //CICLO 4
        menuItemColores.addActionListener(e -> mostrarDialogoColores());

        //CICLO 7
        menuItemNuevo.addActionListener(e -> reiniciar());
        //CICLO 8
        menuItemAjustes.addActionListener(e -> mostrarDialogoCambiarTamaño());
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

      
        menuArchivo = new JMenu("Archivo");
        menuItemNuevo = new JMenuItem("Nuevo");
        menuItemAbrir = new JMenuItem("Abrir");
        menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalir = new JMenuItem("Salir");
        menuItemColores = new JMenuItem("Colores");

        menuArchivo.add(menuItemNuevo);
        menuArchivo.add(menuItemAbrir);
        menuArchivo.add(menuItemSalvar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);

        
        menuConfiguracion = new JMenu("Configuración");
        menuItemPreferencias = new JMenuItem("Preferencias");
        menuItemAjustes = new JMenuItem("Ajustes");

        menuConfiguracion.add(menuItemPreferencias);
        menuConfiguracion.add(menuItemColores);
        menuConfiguracion.add(menuItemAjustes);

       
        menuBar.add(menuArchivo);
        menuBar.add(menuConfiguracion);

        setJMenuBar(menuBar);
    }

    //CICLO 3
    private void prepareElementsBoard(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());

        
        leftPanel = createParticlePanel(new Color(205, 205, 205));
        rightPanel = createParticlePanel(new Color(156, 156, 156));

        JPanel demonPanel = new JPanel();
    demonPanel.setBackground(Color.BLACK);
    demonPanel.setPreferredSize(new Dimension(20, 20)); 

   
    JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    container.add(leftPanel);
    
   
    JLabel demon = new JLabel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 40, 40); 
            g.setColor(Color.YELLOW);
            g.drawRect(0, 0, 39, 39); 
        }
    };
    demon.setPreferredSize(new Dimension(40, 40));
    container.add(demon); 
    
    container.add(rightPanel);
    boardPanel.add(container, BorderLayout.CENTER);
        

        SwingUtilities.invokeLater(() -> {
            agregarParticulasAleatorias(leftPanel, rightPanel, r, Color.RED);
            agregarParticulasAleatorias(leftPanel, rightPanel, b, Color.BLUE);
            agregarAgujerosNegros(leftPanel, h / 2);
            agregarAgujerosNegros(rightPanel, h - (h / 2));
        });
    }

    private JPanel createParticlePanel(Color backgroundColor) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(backgroundColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null); 
        panel.setPreferredSize(new Dimension(300, 400));
        return panel;
    }

    private void agregarParticulasAleatorias(JPanel leftPanel, JPanel rightPanel, int cantidad, Color color) {
        if (cantidad <= 0) return;

        final int TAMANO_PARTICULA = 8;
        Random rand = new Random();

        for (int i = 0; i < cantidad; i++) {
            JPanel panelDestino = rand.nextBoolean() ? leftPanel : rightPanel;

            
            int ancho = panelDestino.getWidth();
            int alto = panelDestino.getHeight();

            if (ancho <= TAMANO_PARTICULA || alto <= TAMANO_PARTICULA) {
                ancho = panelDestino.getPreferredSize().width;
                alto = panelDestino.getPreferredSize().height;
            }

            int x = rand.nextInt(Math.max(1, ancho - TAMANO_PARTICULA));
            int y = rand.nextInt(Math.max(1, alto - TAMANO_PARTICULA));

            JLabel particula = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(getColorFromParticle(this));
                    g.fillRect(0, 0, TAMANO_PARTICULA, TAMANO_PARTICULA);
                }
            };
            setColorToParticle(particula, color); 
            particula.setBounds(x, y, TAMANO_PARTICULA, TAMANO_PARTICULA);
            panelDestino.add(particula);
        }

        leftPanel.revalidate();
        leftPanel.repaint();
        rightPanel.revalidate();
        rightPanel.repaint();
    }


    private void agregarAgujerosNegros(JPanel panel, int cantidad) {
        Random rand = new Random();
        for (int i = 0; i < cantidad; i++) {
            JLabel agujero = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, 10, 10);
                }
            };
            agujero.setBounds(
                rand.nextInt(panel.getWidth() - 10),
                rand.nextInt(panel.getHeight() - 10),
                10, 10
            );
            panel.add(agujero);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void prepareElementsControls() {
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(400, 100));  

        JButton botonPrueba = new JButton("Botón de prueba");
        controlPanel.add(botonPrueba);

        // CICLO 7
        JButton botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(e -> reiniciar());
        controlPanel.add(botonReiniciar);
    }


    //CICLO 4 
    private void mostrarDialogoColores() {
        JDialog dialog = new JDialog(this, "Preferencias de Color", true);
        dialog.setLayout(new GridLayout(4, 1, 10, 10));
    
        
        JButton btnRojas = new JButton("Cambiar color partículas rojas");
        JButton btnAzules = new JButton("Cambiar color partículas azules");
        
        JButton btnCerrar = new JButton("Cerrar");
    
       
        btnRojas.addActionListener(e -> {
            Color nuevo = JColorChooser.showDialog(dialog, "Color rojas", Color.RED);
            if (nuevo != null) actualizarColorParticulas(Color.RED, nuevo);
        });
    
        btnAzules.addActionListener(e -> {
            Color nuevo = JColorChooser.showDialog(dialog, "Color azules", Color.BLUE);
            if (nuevo != null) actualizarColorParticulas(Color.BLUE, nuevo);
        });
    
      
    
        btnCerrar.addActionListener(e -> dialog.dispose());
    
        
        dialog.add(btnRojas);
        dialog.add(btnAzules);
        
        dialog.add(btnCerrar);
    
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    private void actualizarColorParticulas(Color colorViejo, Color colorNuevo) {
        
        for (Component comp : leftPanel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel particula = (JLabel) comp;
                
                if (getColorFromParticle(particula).equals(colorViejo)) {
                    setColorToParticle(particula, colorNuevo);
                }
            }
        }
        
        for (Component comp : rightPanel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel particula = (JLabel) comp;
                if (getColorFromParticle(particula).equals(colorViejo)) {
                    setColorToParticle(particula, colorNuevo);
                }
            }
        }
        leftPanel.repaint();
        rightPanel.repaint();
    }
    
    
    private Color getColorFromParticle(JLabel particle) {
        
        Color color = (Color) particle.getClientProperty("particleColor");
        return color != null ? color : Color.BLACK; 
    }
    
    private void setColorToParticle(JLabel particle, Color color) {
        particle.putClientProperty("particleColor", color);
        particle.repaint();
    }

    // CICLO 7 - Método para reiniciar la simulación
    private void reiniciar() {
        
        leftPanel.removeAll();
        rightPanel.removeAll();

    
        SwingUtilities.invokeLater(() -> {
        agregarParticulasAleatorias(leftPanel, rightPanel, r, Color.RED);
        agregarParticulasAleatorias(leftPanel, rightPanel, b, Color.BLUE);
        agregarAgujerosNegros(leftPanel, h / 2);     
        agregarAgujerosNegros(rightPanel, h - (h / 2)); 
    });

   
    leftPanel.revalidate();
    leftPanel.repaint();
    rightPanel.revalidate();
    rightPanel.repaint();
    }

    // CICLO 8 - Método para mostrar el diálogo de cambio de tamaño
    private void mostrarDialogoCambiarTamaño() {
        JDialog dialog = new JDialog(this, "Cambiar Tamaño de la Ventana", true);
        dialog.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelTamaño = new JLabel("Tamaño de ventana (% de pantalla):");

        String[] opciones = {"25%", "50%", "75%", "100%"};
        JComboBox<String> comboTamaño = new JComboBox<>(opciones);

        
        int indiceSeleccionado = (int)(windowSizeFactor * 4) - 1;
        comboTamaño.setSelectedIndex(Math.max(0, Math.min(3, indiceSeleccionado)));

        form.add(labelTamaño);
        form.add(comboTamaño);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Cancelar");
        JButton applyButton = new JButton("Aplicar");

        cancelButton.addActionListener(e -> dialog.dispose());
        applyButton.addActionListener(e -> {
            int selectedIndex = comboTamaño.getSelectedIndex();
            double newFactor = (selectedIndex + 1) * 0.25; // 0.25, 0.5, 0.75 o 1.0
            cambiarTamañoVentana(newFactor);
            dialog.dispose();
        });

        buttons.add(cancelButton);
        buttons.add(applyButton);

        dialog.add(form, BorderLayout.CENTER);
        dialog.add(buttons, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // CICLO 8 - Método para cambiar el tamaño de la ventana
    private void cambiarTamañoVentana(double factor) {
        
        this.windowSizeFactor = factor;

        
        ajustarTamañoVentana();

        
        reiniciar();
    }
}