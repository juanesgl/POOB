package domain;

import java.awt.Color;

/**
 * Representa un agente en el sistema, que puede tener tres posibles estados: feliz, indiferente o insatisfecho.
 * Los agentes realizan pasos y pueden cambiar su estado según las decisiones que tomen en el sistema.
 * Esta clase sirve como base para la implementación de agentes más específicos.
 */
public abstract class Agent {

    public final static char HAPPY = 'h', INDIFFERENT = 'i', DISSATISFIED = 'd';

    protected char state;

    private int steps;

    /**
     * Crea un nuevo agente con el estado inicial de insatisfecho y con 0 pasos dados.
     */
    public Agent() {
        state = DISSATISFIED;
        steps = 0;
    }

    /**
     * El agente da un paso, incrementando el contador de pasos.
     */
    protected void step() {
        steps++;
    }

    /**
     * Devuelve la cantidad de pasos que ha dado el agente.
     *
     * @return El número de pasos dados por el agente.
     */
    public final int getSteps() {
        return steps;
    }

    /**
     * Indica que este objeto es un agente.
     *
     * @return true, ya que siempre es un agente.
     */
    public final boolean isAgent() {
        return true;
    }

    /**
     * Devuelve si el agente está feliz.
     *
     * @return true si el estado del agente es HAPPY; false, en caso contrario.
     */
    public final boolean isHappy() {
        return (state == Agent.HAPPY);
    }

    /**
     * Devuelve si el agente está indiferente.
     *
     * @return true si el estado del agente es INDIFFERENT; false, en caso contrario.
     */
    public final boolean isIndifferent() {
        return (state == Agent.INDIFFERENT);
    }

    /**
     * Devuelve si el agente está insatisfecho.
     *
     * @return true si el estado del agente es DISSATISFIED; false, en caso contrario.
     */
    public final boolean isDissatisfied() {
        return (state == Agent.DISSATISFIED);
    }

    /**
     * Método abstracto para que las subclases implementen su lógica de decisión.
     * El comportamiento de este método dependerá de la implementación específica de cada tipo de agente.
     */
    public abstract void decide();
}
