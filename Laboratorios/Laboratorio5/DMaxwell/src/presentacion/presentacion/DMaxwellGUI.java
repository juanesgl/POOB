package presentacion;

import javax.swing.*;
import java.awt.*;

public class DMaxwellGUI extends JFrame {

    private DMaxwellGUI(){
        prepareElements();
    }


    private void prepareElements(){

        setTitle("Maxwell Discreto");
        //setPreferredSize(new java.awt.Dimension(800, 600));

        //pack();
        //setLocationRelativeTo(null);
        /*This also works*/

        setSize((Toolkit.getDefaultToolkit().getScreenSize().width)/2 , (Toolkit.getDefaultToolkit().getScreenSize().height)/2);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/4, (Toolkit.getDefaultToolkit().getScreenSize().height)/4 );
    }

    private void prepareActions(){

    }

    public static void main(String [] args){
        DMaxwellGUI gui  = new DMaxwellGUI();
        gui.setVisible(true);
    }

}
