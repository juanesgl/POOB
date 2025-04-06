package src.presentation;

import src.domain.Plan15;
import src.domain.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Plan15GUI extends JFrame {

    private static final Dimension PREFERRED_DIMENSION = new Dimension(700,700);
    private Plan15 plan;

    /* List */
    private JButton buttonList;
    private JButton buttonRestartList;
    private JTextArea textDetails;

    /* Add */
    private JTextField code;
    private JTextField name;    
    private JTextField credits;
    private JTextField inPerson;
    private JTextArea basics;
    private JButton buttonAdd;
    private JButton buttonRestartAdd;

    /* Search */
    private JTextField textSearch;
    private JTextArea textResults;

    private Plan15GUI(){
        plan = new Plan15();
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        setTitle("Plan 15");
        code = new JTextField(50);
        name = new JTextField(50);
        credits = new JTextField(50);
        inPerson = new JTextField(50);
        basics = new JTextArea(10, 50);
        basics.setLineWrap(true);
        basics.setWrapStyleWord(true);
        
        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar", prepareAreaList());
        etiquetas.add("Adicionar", prepareAreaAdd());
        etiquetas.add("Buscar", prepareSearchArea());
        getContentPane().add(etiquetas);
        setSize(PREFERRED_DIMENSION);
    }

    private JPanel prepareAreaList(){
        textDetails = new JTextArea(10, 50);
        textDetails.setEditable(false);
        textDetails.setLineWrap(true);
        textDetails.setWrapStyleWord(true);

        JScrollPane scrollArea = new JScrollPane(textDetails,
                                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel botones = new JPanel();
        buttonList = new JButton("Listar");
        buttonRestartList = new JButton("Limpiar");
        botones.add(buttonList);
        botones.add(buttonRestartList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel prepareAreaAdd(){
        JPanel fields = new JPanel(new GridLayout(9,1));
        fields.add(new JLabel("Sigla"));
        fields.add(code);
        fields.add(new JLabel("Nombre"));
        fields.add(name);
        fields.add(new JLabel("Créditos o porcentaje"));
        fields.add(credits);        
        fields.add(new JLabel("Horas presenciales (solo para cursos)"));
        fields.add(inPerson);    
        fields.add(new JLabel("Cursos (solo para núcleos)"));

        JPanel textDetailsPanel = new JPanel(new BorderLayout());
        textDetailsPanel.add(fields, BorderLayout.NORTH);
        textDetailsPanel.add(basics, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        buttonAdd = new JButton("Adicionar");
        buttonRestartAdd = new JButton("Limpiar");
        botones.add(buttonAdd);
        botones.add(buttonRestartAdd);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textDetailsPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel prepareSearchArea(){
        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        textSearch = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(textSearch);
        
        textResults = new JTextArea(10,50);
        textResults.setEditable(false);
        textResults.setLineWrap(true);
        textResults.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(textResults,
                                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel buttonListea = new JPanel(new BorderLayout());
        buttonListea.add(busquedaArea, BorderLayout.NORTH);
        buttonListea.add(scrollArea, BorderLayout.CENTER);

        return buttonListea;
    }

    private void prepareActions(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                setVisible(false);
                System.exit(0);
            }
        });

        /* List */
        buttonList.addActionListener(ev -> actionList());

        buttonRestartList.addActionListener(ev -> textDetails.setText(""));

        /* Add */
        buttonAdd.addActionListener(ev -> actionAdd());

        buttonRestartAdd.addActionListener(ev -> {
            code.setText("");
            name.setText("");
            credits.setText("");
            inPerson.setText("");
            basics.setText("");
        });

        /* Search */
        textSearch.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){ actionSearch(); }
            public void insertUpdate(DocumentEvent ev){ actionSearch(); }
            public void removeUpdate(DocumentEvent ev){ actionSearch(); }
        });
    }

    private void actionList(){
        textDetails.setText(plan.toString());
    }

    private void actionAdd() {
    try {
        String sigla = code.getText().trim();
        String nombre = name.getText().trim();
        String creditos = credits.getText().trim();
        String presenciales = inPerson.getText().trim();
        String cursosNucleo = basics.getText().trim();

        if (sigla.isEmpty() || nombre.isEmpty() || creditos.isEmpty()) {
            throw new Plan15Exception("Todos los campos son obligatorios.");
        }

        
        int confirmation = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas agregar este curso/núcleo?\n" +
                "Sigla: " + sigla + "\n" +
                "Nombre: " + nombre + "\n" +
                "Créditos: " + creditos + "\n" +
                "Horas presenciales: " + presenciales + "\n" +
                "Cursos Núcleo: " + cursosNucleo,
                "Confirmar Adición",
                JOptionPane.YES_NO_OPTION);

        
        if (confirmation == JOptionPane.YES_OPTION) {
            if (cursosNucleo.isEmpty()) {
                plan.addCourse(sigla, nombre, creditos, presenciales);
            } else {
                plan.addCore(sigla, nombre, creditos, cursosNucleo);
            }

            JOptionPane.showMessageDialog(this, "Agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            
            code.setText("");
            name.setText("");
            credits.setText("");
            inPerson.setText("");
            basics.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "La acción ha sido cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Plan15Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}



    private void actionSearch(){
        String patronBusqueda = textSearch.getText();
        String answer = "";
        if (patronBusqueda.length() > 0) {
            answer = plan.search(patronBusqueda);
        }
        textResults.setText(answer);
    }

    public static void main(String args[]){
        Plan15GUI gui = new Plan15GUI();
        gui.setVisible(true);
    }
}