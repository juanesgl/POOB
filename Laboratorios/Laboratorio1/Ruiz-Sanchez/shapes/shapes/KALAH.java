
/**
 * Write a description of class KALAH here.
 *
 * @author (Daniel Ruiz/Juan Sanchez)
 * @version (Version 1)
 */

import javax.swing.JOptionPane;
public class KALAH
{
    // instance variables - replace the example below with your own
    private Pit mesa;
    private Pit almacenN;
    private Pit almacenS;
    private Casa[] casas; //arreglo de casas
    private int seeds;
    private int operar;
    private String jugador;
    private String turnoActual = "S";
    private boolean juegoActivo = true; // Bandera para controlar el juego


    /**
     * Constructor for objects of class KALAH
     */
    public KALAH()
    {
        // initialize Mesa
        mesa= new Pit(true);//PIT GRANDE
        //mesa.gethueco().changeSize(200,800);
        mesa.changeColor("green");
        mesa.makeVisible();
        // initialize Almacen norte
        almacenN = new Pit(false);//PIT PEQUEÑO
        //almacenN.changeSize(190,90);
        almacenN.changeColor("blue");
        almacenN.moveTo(5,5);
        almacenN.makeVisible();
        //initialize Almacen sur
        almacenS = new Pit(false);
        almacenS.changeColor("red");
        almacenS.moveTo(705,5);
        almacenS.makeVisible();
        // El juego comienza con el Jugador Sur
        jugador = "S"; // "N" para Norte, "S" para Sur
        //initialize casas
        casas=new Casa[12];
        for(int i=0;i<12;i++){
            casas[i]=new Casa();
            casas[i].makeVisible();
             // Distribuir en dos filas de 6
            if (i < 6) {
                // Fila inferior (Casas 1 a 6) → de izquierda a derecha
                casas[i].moveTo(105 + (i * 100), 105);
                casas[i].changeColor("red");
            } else {
                // Fila superior (Casas 12 a 7) → de derecha a izquierda
                casas[i].moveTo(105 + ((11 - i) * 100), 5);
            }
                    }
        
        
        JOptionPane.showMessageDialog(null, "Se creó todo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        
    }

    /**
     * Restart game
     *
     */
    public void restartGame()
    {
        for(int i=0;i<12;i++){
        seeds=casas[i].seeds();
        operar=seeds-3;
        if(operar<0){
            casas[i].putSeeds(Math.abs(operar));}
        else{
            casas[i].removeSeeds(operar);
        
        }
        
        }
        jugador = "S"; // Se reinicia el jugador S
        seeds=almacenS.seeds();
        almacenS.removeSeeds(seeds);
        seeds=almacenN.seeds();
        almacenN.removeSeeds(seeds);
      
        
    }
    
        /**
     * Number of seeds Casa x
     *param x casa a la que se le desea conocer la cantidad de semillas
     */
    public int seedsCasas(int x)
    {
        return casas[x].seeds();           
    }
     /**
     * Number of seeds Almacen S
     */
    public int seedsAlmacenS()
    {
        return almacenS.seeds();           
    }
     /**
     * Number of seeds Almacen N
     */
    public int seedsAlmacenN()
    {
        return almacenN.seeds();           
    }
    
    
    /**
     * Elegir casa 
     * param x es el jugador "N" para norte "S" para sur 
     */
    public int elegirCasa(String x)
    {
    int casaElegida = -1; // Valor inicial fuera del rango válido

        while (true) {
            String input = JOptionPane.showInputDialog("¿Con cuál casa deseas jugar?");
            
            // Si el usuario cancela o deja vacío, termina el bucle y devuelve -1
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número.");
                continue;
            }

            try {
                casaElegida = Integer.parseInt(input); // Convertir el valor a entero
                casaElegida = casaElegida-1;
                if (x.equals("N")) { // Jugador Norte
                    if (casaElegida >= 6 && casaElegida <= 11) {
                        if (casas[casaElegida].seeds() > 0) { // Verifica que la casa tenga semillas
                            return casaElegida;
                        } else {
                            JOptionPane.showMessageDialog(null, "Esa casa está vacía. Elige otra.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Número inválido. Eres jugador Norte, debe ser entre 7 y 12.");
                    }
                } else if (x.equals("S")) { // Jugador Sur
                    if (casaElegida >= 0 && casaElegida <= 5) {
                        if (casas[casaElegida].seeds() > 0) { // Verifica que la casa tenga semillas
                            return casaElegida;
                        } else {
                            JOptionPane.showMessageDialog(null, "Esa casa está vacía. Elige otra.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Número inválido. Eres jugador Sur, debe ser entre 1 y 6.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Jugador inválido.");
                    return -1;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Debes ingresar un número.");
            }
        }
    
    }
    /**
     * Ver el estado a tiempo real del juego
     */
    public void estadoActual() {
        StringBuilder estado = new StringBuilder("Estado actual del juego:\n\n");

    for (int i = 0; i < casas.length; i++) {
        estado.append("Casa ").append(i+1).append(": ").append(seedsCasas(i)).append(" semillas\n");
    }
    // Mostrar los almacenes
    estado.append("\n🏆 Almacén Norte: ").append(almacenN.seeds()).append(" semillas\n");
    estado.append("🏆 Almacén Sur: ").append(almacenS.seeds()).append(" semillas\n");

    JOptionPane.showMessageDialog(null, estado.toString(), "Estado del Juego", JOptionPane.INFORMATION_MESSAGE);
}
 
    /**
     * Hacer movimiento
     * param x es el jugador "N" para norte "S" para sur
     */
    public void movimiento(String x)
    {
        int seleccionada=elegirCasa(x);
        int iteraciones=casas[seleccionada].seeds();
        casas[seleccionada].removeSeeds(iteraciones);//vaciar la casa seleccionada
        int indice=-1;
     if(x.equals("N")){
         indice = seleccionada+1; // Empezamos en la casa seleccionada
        } else if (x.equals("S")){
         indice = seleccionada;
        }

    for (int k = 0; k < iteraciones; k++) {
        indice = (indice + 1) % 14; // Avanzamos antes de colocar la semilla


        // Mostrar en qué índice se coloca la semilla
        //JOptionPane.showMessageDialog(null, "Semilla colocada en índice: " + indice);

        if (indice == 13) { 
            almacenN.putSeeds(1); // Depositar en almacén Norte
        } else if (indice == 6) { 
            almacenS.putSeeds(1); // Depositar en almacén Sur
        } else {
            int casaReal = (indice > 6) ? indice - 1 : indice; // Ajustar el índice si pasó por el almacén
            casas[casaReal].putSeeds(1);
        }
    }
    }
    /**
     * Accion de jugar
     */
    public void jugar()
    {   
        juegoActivo = true;
        while (juegoActivo) { // Se mantiene en bucle hasta que se cumpla una condición de fin
        movimiento(turnoActual); // El jugador actual hace su movimiento

        // Alternar turno
        if (turnoActual.equals("N")) {
            turnoActual = "S";
        } else {
            turnoActual = "N";
        }

        // Mostrar quién juega ahora
        JOptionPane.showMessageDialog(null, "Turno del jugador: " + turnoActual);
        // Preguntar si desea continuar jugando
        int respuesta = JOptionPane.showConfirmDialog(
            null, 
            "¿Quieres pausar el juego?", 
            "Continuar Juego", 
            JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            juegoActivo = false; // Termina el juego si elige "No"
            JOptionPane.showMessageDialog(null, "El juego ha sido pausado .");
        }
        // Verificar si el juego debe terminar
        if (finDelJuego()) {
            juegoActivo = false; // Detener el bucle si el juego termina
            JOptionPane.showMessageDialog(null, "HAY UN GANADOR!!!FELICITACIONES.");
            restartGame();
            
        }
    }
    }
    /**
 * Método para verificar si hay ganador.
  */
private boolean finDelJuego() {
    if(almacenN.seeds()>1 ||almacenS.seeds()>1)
    { return true; // Temporalmente, siempre devuelve falso para que el juego siga.
}else {return false;}
}

}

