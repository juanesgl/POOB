import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class GraphTest{
    private GraphCalculator calculator;
    private Graph graph;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        calculator = new GraphCalculator();
        String[] vertices = {"A", "B", "C", "D"};
        String[][] edges = {{"A", "B"}, {"B", "C"}, {"C", "D"}, {"A", "D"}};
        graph = new Graph(vertices, edges);
    }

    
     @Test
    public void shouldCreateEmptyGraph(){
        String [] vertices ={};
        String [][] edges = {};
        assertEquals(0, new Graph(vertices,edges).vertices());
        assertEquals(0, new Graph(vertices,edges).edges());
    }    
   
    @Test
    public void shouldCreateGraphs(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }    
    
    @Test
    public void shouldNotHaveDuplicateVerticesEdges(){
        String [] vertices ={"DDYA","MYSD","DOPO","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"},{"DDYA","DOPO"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }    

    @Test
    public void shouldNotBeCaseSensitive(){     
        String [] vertices ={"Ddya","MYSD","DOPO","dopo"};
        String [][] edges = {{"DDYA","Mysd"},{"ddya","dopo"},{"DDya","doPo"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }
    
    @Test
    public void shouldConvertToString(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};  
        String data= "(DDYA, DOPO) (DDYA, MYSD)";
        assertEquals(data, new Graph(vertices,edges).toString());
    }

    @Test
    public void shouldValityEquality(){
        String [] vertices ={};
        String [][] edges = {};
        assertEquals(new Graph(vertices,edges),new Graph(vertices,edges));
        String [] verticesA ={"DDYA","MYSD","DOPO"};
        String [][] edgesA = {{"DDYA","MYSD"},{"DDYA","DOPO"}};      
        String [] verticesB ={"Ddya","MYSD","DOPO","dopo"};
        String [][] edgesB = {{"DDYA","Mysd"},{"ddya","dopo"},{"DDya","doPo"}}; 
        assertEquals(new Graph(verticesA,edgesA),new Graph(verticesB,edgesB));
    }    


    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void shouldPass() {
        
        String[] vertices = {"DDYA", "MYSD", "DOPO"};
        String[][] edges = {{"DDYA", "MYSD"}, {"DDYA", "DOPO"}};
    
        Graph graph = new Graph(vertices, edges);
        
        assertEquals(3, graph.vertices());
        
        assertEquals(2, graph.edges());
    }
    
    @Test
    public void shouldFail() {
        
        String[] vertices = {"DDYA", "MYSD", "DOPO"};
        String[][] edges = {{"DDYA", "MYSD"}, {"DDYA", "DOPO"}};
    
        Graph graph = new Graph(vertices, edges);
    
        assertEquals(4, graph.vertices());
    }
    
    @Test
    public void shouldErr() {
        String[] vertices = {};  
        String[][] edges = {{"DDYA", "MYSD"}}; 
    
        try {
            Graph graph = new Graph(vertices, edges);
            
            fail("Se esperaba un error por crear un grafo vacío con bordes");
        } catch (IllegalArgumentException e) {
            
        }
    }
    
    
    //ciclo 1 
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void shouldCreateGraphCalculator() {
        assertNotNull(calculator);
    }

    @Test
    public void shouldCreateVariable() {
        calculator.create("G1");
        assertEquals("", calculator.toString("G1"));
    }

    @Test
    public void shouldAssignGraphToVariable() {
        calculator.create("G1");
        String[] vertices = {"A", "B", "C"};
        String[][] edges = {{"A", "B"}, {"B", "C"}};
        calculator.assign("G1", vertices, edges);
        assertEquals("(A, B) (B, C)", calculator.toString("G1"));
    }

    @Test
    public void shouldNotAssignGraphToNonExistentVariable() {
        String[] vertices = {"A", "B"};
        String[][] edges = {{"A", "B"}};
        calculator.assign("G1", vertices, edges); // G1 no ha sido creada
    }

    @Test
    public void shouldNotRetrieveNonExistentGraph() {
        calculator.toString("G1"); // G1 no existe
    }

    @Test
    public void shouldNotCreateVariableWithEmptyName() {
        calculator.create("");
    }

   @Test
    public void shouldNotCreateVariableWithNullName() {
    try {
        calculator.create(null);
        fail("Se esperaba una NullPointerException");
    } catch (NullPointerException e) {
        // La prueba pasa si se lanza NullPointerException
    }
    }
    
    //ciclo 2
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     @Test
    public void testRemoveEdge() {
        graph.removeEdge("A", "B");
        assertFalse(graph.findPathThrough(new String[]{"A", "B"}) != null);
    }

    @Test
    public void testContainsAllVertices() {
        assertTrue(graph.containsAllVertices(new String[]{"A", "B", "C"}));
        assertFalse(graph.containsAllVertices(new String[]{"A", "E"}));
    }

    @Test
    public void testFindPathThrough() {
        List<String> path = graph.findPathThrough(new String[]{"A", "B", "C"});
        assertNotNull(path);
        assertEquals("A", path.get(0));
        assertEquals("B", path.get(1));
        assertEquals("C", path.get(2));

        List<String> noPath = graph.findPathThrough(new String[]{"A", "C", "D"});
        assertNull(noPath);  // No hay un camino directo A -> C -> D en el grafo inicial.
    }
    
    
    //ciclo 3
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    @Test
    public void shouldUnionTwoGraphsCorrectly() {
        String[] v1 = {"A", "B"};
        String[][] e1 = {{"A", "B"}};
        Graph g1 = new Graph(v1, e1);

        String[] v2 = {"B", "C"};
        String[][] e2 = {{"B", "C"}};
        Graph g2 = new Graph(v2, e2);

        Graph unionGraph = g1.union(g2);

        assertTrue(unionGraph.contains("A"));
        assertTrue(unionGraph.contains("B"));
        assertTrue(unionGraph.contains("C"));
        assertEquals(2, unionGraph.edges()); // Debe contener ambas aristas
    }

    @Test
    public void shouldIntersectTwoGraphsCorrectly() {
        String[] v1 = {"A", "B", "C"};
        String[][] e1 = {{"A", "B"}, {"B", "C"}};
        Graph g1 = new Graph(v1, e1);

        String[] v2 = {"B", "C", "D"};
        String[][] e2 = {{"B", "C"}, {"C", "D"}};
        Graph g2 = new Graph(v2, e2);

        Graph intersectionGraph = g1.intersection(g2);

        assertTrue(intersectionGraph.contains("B"));
        assertTrue(intersectionGraph.contains("C"));
        assertEquals(1, intersectionGraph.edges()); // Solo (B, C) es común
    }

    @Test
    public void shouldComputeDifferenceCorrectly() {
    // Grafo 1
    String[] v1 = {"A", "B", "C", "D"};
    String[][] e1 = {{"A", "B"}, {"B", "C"}, {"C", "D"}};
    Graph g1 = new Graph(v1, e1);

    // Grafo 2 (conjunto a eliminar)
    String[] v2 = {"B", "C"};
    String[][] e2 = {{"B", "C"}};
    Graph g2 = new Graph(v2, e2);

    // Diferencia g1 - g2
    Graph differenceGraph = g1.difference(g2);

    // Verificar que los vértices correctos están en el grafo resultante
    assertTrue(differenceGraph.contains("A"));
    assertTrue(differenceGraph.contains("D"));
    assertFalse(differenceGraph.contains("B"));
    assertFalse(differenceGraph.contains("C"));

    // Debería haber solo una arista, ya que (A, B) y (C, D) deben eliminarse
    assertEquals(0, differenceGraph.edges());
    }

    

    @Test
    public void shouldReturnEmptyGraphWhenSubtractingItself() {
        String[] v = {"A", "B"};
        String[][] e = {{"A", "B"}};
        Graph g = new Graph(v, e);

        Graph diff = g.difference(g);

        assertEquals(0, diff.vertices());
        assertEquals(0, diff.edges());
    }

    @Test
    public void shouldJoinTwoGraphsCorrectly() {
        String[] v1 = {"A", "B"};
        String[][] e1 = {{"A", "B"}};
        Graph g1 = new Graph(v1, e1);

        String[] v2 = {"C", "D"};
        String[][] e2 = {{"C", "D"}};
        Graph g2 = new Graph(v2, e2);

        Graph joinedGraph = g1.join(g2);

        assertEquals(4, joinedGraph.vertices()); // Todos los vértices están
        assertTrue(joinedGraph.edges() > 2); // Debe haber más conexiones nuevas
    }

    
}
