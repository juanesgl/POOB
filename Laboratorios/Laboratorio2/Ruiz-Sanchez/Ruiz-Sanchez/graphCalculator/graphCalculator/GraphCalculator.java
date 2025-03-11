import java.util.TreeMap;
import java.util.List;

/**
 * GraphCalculator.java
 * 
 * Calculadora de grafos para realizar operaciones básicas y más complejas.
 * Ciclo 1: Crear una calculadora, asignar un grafo a una variable y consultarlo.
 * 
 * @author ESCUELA 2025-01
 */
public class GraphCalculator {
    
    // Mapa de variables que asocia nombres a objetos Graph.
    private TreeMap<String, Graph> variables;
    // Bandera para indicar si la última operación fue exitosa.
    private boolean lastOk;
    
    // Constructor: Inicializa la calculadora.
    public GraphCalculator() {
        this.variables = new TreeMap<>();
        this.lastOk = true;
    }
    //ciclo 1 
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Crea una nueva variable en la calculadora.
     * Si la variable ya existe, se marca la operación como fallida.
     * 
     * @param nombre el nombre de la variable a crear.
     */
    public void create(String nombre) {
        if (variables.containsKey(nombre)) {
            lastOk = false; // No se debe crear una variable que ya existe.
        } else {
            variables.put(nombre, null);
            lastOk = true;
        }
    }
    
    /**
     * Asigna un nuevo grafo a la variable indicada.
     * Se utiliza el constructor de Graph que recibe vértices y aristas.
     * 
     * @param graph   el nombre de la variable donde asignar el grafo.
     * @param vertices un arreglo de vértices.
     * @param edges   una matriz con las aristas (pares de vértices).
     */
    public void assign(String graph, String[] vertices, String[][] edges) {
        try {
            Graph g = new Graph(vertices, edges);
            variables.put(graph, g);
            lastOk = true;
        } catch (Exception e) {
            lastOk = false;
        }
    }
    
    /**
     * Retorna la representación en cadena del grafo asociado a la variable.
     * Se espera que Graph.toString() retorne las aristas en mayúsculas y en orden alfabético.
     * 
     * @param graph el nombre de la variable.
     * @return la representación en cadena del grafo, o una cadena vacía si no existe.
     */
    public String toString(String graph) {
        Graph g = variables.get(graph);
        if (g != null) {
            return g.toString();
        }
        return "";
    }
    
    /**
     * Retorna true si la última operación se completó exitosamente.
     * 
     * @return true si la última operación fue exitosa, false en caso contrario.
     */
    public boolean ok() {
        return lastOk;
    }
    
    // Los métodos assignUnary y assignBinary se implementarán en ciclos posteriores.
    
    //ciclo 2
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     /**
     * Inserta un arco en un grafo existente.
     */
    public void addEdge(String graphName, String from, String to) {
        Graph graph = variables.get(graphName);
        if (graph == null) {
            lastOk = false;
        } else {
            graph.addEdge(from, to);
            lastOk = true;
        }
    }

    /**
     * Elimina un arco de un grafo existente.
     */
    public void removeEdge(String graphName, String from, String to) {
        Graph graph = variables.get(graphName);
        if (graph == null) {
            lastOk = false;
        } else {
            graph.removeEdge(from, to);
            lastOk = true;
        }
    }

    /**
     * Verifica si un conjunto de vértices pertenece a un grafo.
     */
    public boolean containsAllVertices(String graphName, String[] verticesToCheck) {
        Graph graph = variables.get(graphName);
        if (graph == null) {
            lastOk = false;
            return false;
        }
        lastOk = true;
        return graph.containsAllVertices(verticesToCheck);
    }

    /**
     * Retorna un camino que pase por un conjunto de vértices en un grafo.
     */
    public List<String> findPathThrough(String graphName, String[] verticesToPass) {
        Graph graph = variables.get(graphName);
        if (graph == null) {
            lastOk = false;
            return null;
        }
        lastOk = true;
        return graph.findPathThrough(verticesToPass);
    }    
    
    //ciclo 3
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void intersection(String var1, String var2, String result) {
    if (variables.containsKey(var1) && variables.containsKey(var2)) {
        Graph g1 = variables.get(var1);
        Graph g2 = variables.get(var2);
        variables.put(result, g1.intersection(g2));
        lastOk = true;
    } else {
        lastOk = false;
    }
    }

    public void difference(String var1, String var2, String result) {
    if (variables.containsKey(var1) && variables.containsKey(var2)) {
        Graph g1 = variables.get(var1);
        Graph g2 = variables.get(var2);
        variables.put(result, g1.difference(g2));
        lastOk = true;
    } else {
        lastOk = false;
    }
    }

    public void join(String var1, String var2, String result) {
    if (variables.containsKey(var1) && variables.containsKey(var2)) {
        Graph g1 = variables.get(var1);
        Graph g2 = variables.get(var2);
        variables.put(result, g1.join(g2));
        lastOk = true;
    } else {
        lastOk = false;
    }
    }


}






