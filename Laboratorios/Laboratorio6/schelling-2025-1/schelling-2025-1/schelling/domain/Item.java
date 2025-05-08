package domain;

import java.awt.Color;

/**
 * Representa un objeto en el sistema que tiene una forma, un color y un estado activo.
 * Los objetos que implementen esta interfaz pueden tomar decisiones, cambiar de estado y
 * tener una representación visual (forma y color).
 */
public interface Item {
    public static final int ROUND = 1;
    public static final int SQUARE = 2;

    /**
     * Método que permite al objeto tomar una decisión.
     */
    public abstract void decide();
   
    /**
     * Método por defecto que permite cambiar el estado del objeto.
     * Se puede sobrescribir si se necesita un comportamiento específico.
     */
    public default void change() {
    };

    /**
     * Método por defecto que devuelve la forma del objeto.
     * El valor por defecto es {@link Item#ROUND}.
     *
     * @return El tipo de forma del objeto. 
     */
    public default int shape() {
        return ROUND;
    }

    /**
     * Método por defecto que devuelve el color del objeto.
     * El valor por defecto es {@link Color#BLACK}.
     *
     * @return El color del objeto.
     */
    public default Color getColor() {
        return Color.black;
    }

    /**
     * Método por defecto que indica si el objeto está activo.
     * El valor por defecto es `true`, indicando que el objeto está activo.
     *
     * @return true si el objeto está activo, false en caso contrario.
     */
    public default boolean isActive() {
        return true;
    }

    /**
     * Método por defecto que indica si el objeto es un agente.
     * El valor por defecto es `false`, ya que los objetos no son agentes por defecto.
     *
     * @return true si el objeto es un agente, false en caso contrario.
     */
    public default boolean isAgent() {
        return false;
    }
}
