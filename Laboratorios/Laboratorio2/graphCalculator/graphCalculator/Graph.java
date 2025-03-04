import java.util.*;

public class Graph {
    private Set<String> vertices;
    private Set<Edge> edges;
    private Map<String, String> normalization;
    private Map<String, Set<String>> adjacencyList;
    
    
    private static class Edge {
        String from, to;

        Edge(String from, String to) {
            this.from = from;
            this.to = to;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Edge)) return false;
            Edge other = (Edge) obj;
            return (from.equals(other.from) && to.equals(other.to)) || (from.equals(other.to) && to.equals(other.from));
        }
        
        @Override
        public int hashCode() {
            return from.hashCode() + to.hashCode();
        }
        
        @Override
        public String toString() {
            return "(" + from + ", " + to + ")";
        }
    }
    
    public Graph(String[] vertices, String[][] edges) {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
        this.normalization = new HashMap<>();
        this.adjacencyList = new HashMap<>();
        
        for (String vertex : vertices) {
            String normalized = vertex.toUpperCase();
            if (!this.normalization.containsKey(normalized)) {
                this.vertices.add(normalized);
                this.normalization.put(normalized, normalized);
            }
        }
        
        for (String[] edge : edges) {
            if (edge.length != 2) continue;
            String from = normalize(edge[0]);
            String to = normalize(edge[1]);
            if (this.vertices.contains(from) && this.vertices.contains(to)) {
                this.edges.add(new Edge(from, to));
            } else {
                throw new IllegalArgumentException("Edge contains vertices not in graph");
            }
        }
    }
    
    private String normalize(String vertex) {
        return this.normalization.getOrDefault(vertex.toUpperCase(), vertex.toUpperCase());
    }
    
    public boolean contains(String vertex) {
        return vertices.contains(vertex.toUpperCase());
    }
    
    public Graph path(String start, String end) {
        return null; // Implementar búsqueda de camino
    }
    
    public Graph union(Graph g) {
        Set<String> combinedVertices = new HashSet<>(this.vertices);
        combinedVertices.addAll(g.vertices);
        
        Set<Edge> combinedEdges = new HashSet<>(this.edges);
        combinedEdges.addAll(g.edges);
        
        return new Graph(combinedVertices.toArray(new String[0]), combinedEdges.stream().map(e -> new String[]{e.from, e.to}).toArray(String[][]::new));
    }
    
    public int vertices() {
        return vertices.size();
    }
    
    public int edges() {
        return edges.size();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Graph)) return false;
        Graph other = (Graph) obj;
        return this.vertices.equals(other.vertices) && this.edges.equals(other.edges);
    }
    
    @Override
    public int hashCode() {
        return vertices.hashCode() + edges.hashCode();
    }
    
    @Override
    public String toString() {
        List<String> edgeList = new ArrayList<>();
        for (Edge edge : edges) {
            edgeList.add(edge.toString());
        }
        Collections.sort(edgeList);
        return String.join(" ", edgeList);
    }
     /**
     * Agrega un arco entre dos vértices.
     */
    public void addEdge(String from, String to) {
        if (vertices.contains(from) && vertices.contains(to)) {
            adjacencyList.get(from).add(to);
        }
    }

    /**
     * Elimina un arco entre dos vértices.
     */
    public void removeEdge(String from, String to) {
        if (adjacencyList.containsKey(from)) {
            adjacencyList.get(from).remove(to);
        }
    }

    /**
     * Verifica si un conjunto de vértices pertenece al grafo.
     */
    public boolean containsAllVertices(String[] verticesToCheck) {
        return vertices.containsAll(Arrays.asList(verticesToCheck));
    }

    /**
     * Encuentra un camino que pase por un conjunto de vértices.
     * Retorna una lista con el camino encontrado o null si no existe.
     */
    public List<String> findPathThrough(String[] verticesToPass) {
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        
        for (String vertex : verticesToPass) {
            if (!vertices.contains(vertex)) return null;
        }

        if (dfs(verticesToPass[0], verticesToPass, 0, path, visited)) {
            return path;
        }
        return null;
    }

    private boolean dfs(String current, String[] verticesToPass, int index, List<String> path, Set<String> visited) {
        if (index == verticesToPass.length) return true;
        
        path.add(current);
        visited.add(current);
        
        if (current.equals(verticesToPass[index])) {
            index++;
        }
        
        for (String neighbor : adjacencyList.getOrDefault(current, new HashSet<>())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, verticesToPass, index, path, visited)) {
                    return true;
                }
            }
        }
        
        path.remove(path.size() - 1);
        return false;
    }
    
    //ciclo 3
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   


    public Graph intersection(Graph g) {
    Set<String> commonVertices = new HashSet<>(this.vertices);
    commonVertices.retainAll(g.vertices);

    Set<Edge> commonEdges = new HashSet<>(this.edges);
    commonEdges.retainAll(g.edges);

    return new Graph(commonVertices.toArray(new String[0]), commonEdges.stream()
            .map(e -> new String[]{e.from, e.to})
            .toArray(String[][]::new));
    }

    public Graph difference(Graph g) {
    Set<String> diffVertices = new HashSet<>(this.vertices);
    diffVertices.removeAll(g.vertices); // Eliminamos los vértices que están en g2

    Set<Edge> diffEdges = new HashSet<>();
    for (Edge edge : this.edges) {
        // Solo agregamos aristas si AMBOS vértices siguen en diffVertices
        if (diffVertices.contains(edge.from) && diffVertices.contains(edge.to)) {
            diffEdges.add(edge);
        }
    }

    return new Graph(diffVertices.toArray(new String[0]), 
                     diffEdges.stream().map(e -> new String[]{e.from, e.to}).toArray(String[][]::new));
    }   
    
    

    public Graph join(Graph g) {
    Set<String> combinedVertices = new HashSet<>(this.vertices);
    combinedVertices.addAll(g.vertices);

    Set<Edge> combinedEdges = new HashSet<>(this.edges);
    combinedEdges.addAll(g.edges);

    // Agregar una arista entre todos los nodos de ambos grafos
    for (String v1 : this.vertices) {
        for (String v2 : g.vertices) {
            combinedEdges.add(new Edge(v1, v2));
        }
    }

    return new Graph(combinedVertices.toArray(new String[0]), combinedEdges.stream()
            .map(e -> new String[]{e.from, e.to})
            .toArray(String[][]::new));
    }

    
    
    
    
    
    
    

    }
