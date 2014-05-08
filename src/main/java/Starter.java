/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import by.bsu.coursework.converter.Converter;
import by.bsu.coursework.structures.Structure;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.algorithms.shortestpath.MinimumSpanningForest;

/**
 *
 * @author th13f
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String file = 
                "/*|I|*/5\n" +
                "/*|K|*/3\n" +
                "/*widetilde{U}^1*/\n" +
                "/*{1,2}*/\n" +
                "/*{1,3}*/\n" +
                "/*{2,3}*/\n" +
                "/*widetilde{U}^2*/\n" +
                "/*{2,3}*/\n" +
                "/*{2,4}*/\n" +
                "/*{3,4}*/\n" +
                "/*{4,5}*/\n" +
                "/*{5,3}*/\n" +
                "/*widetilde{U}^3*/\n" +
                "/*{2,3}*/\n" +
                "/*{2,4}*/\n" +
                "/*{3,4}*/\n" +
                "/*{4,5}*/\n" +
                "/*{5,3}*/\n" +
                "/*widetilde{U}_0*/\n" +
                "/*{2,4}*/1\n" +
                "/*widetilde{U}_0^1*/\n" +
                "/*{2,4}*/0\n" +
                "/*widetilde{U}_0^2*/\n" +
                "/*{2,4}*/1\n" +
                "/*widetilde{U}_0^3*/\n" +
                "/*{2,4}*/1\n" +
                "/*q*/2\n" +
                "/*lambda_12^11*/2\n" +
                "/*lambda_13^11*/3\n" +
                "/*lambda_23^11*/1\n" +
                "/*lambda_23^21*/4\n" +
                "/*lambda_23^31*/2\n" +
                "/*lambda_24^21*/3\n" +
                "/*lambda_24^31*/-4\n" +
                "/*lambda_34^21*/2\n" +
                "/*lambda_34^31*/1\n" +
                "/*lambda_45^21*/-1\n" +
                "/*lambda_45^31*/7\n" +
                "/*lambda_53^21*/1\n" +
                "/*lambda_53^31*/2\n" +
                "/*lambda_12^12*/1\n" +
                "/*lambda_13^12*/2\n" +
                "/*lambda_23^12*/2\n" +
                "/*lambda_23^22*/5\n" +
                "/*lambda_23^32*/3\n" +
                "/*lambda_24^22*/-1\n" +
                "/*lambda_24^32*/-1\n" +
                "/*lambda_34^22*/1\n" +
                "/*lambda_34^32*/1\n" +
                "/*lambda_45^22*/-2\n" +
                "/*lambda_45^32*/3\n" +
                "/*lambda_53^22*/2\n" +
                "/*lambda_53^32*/-1\n" +
                "/*a_1^1*/4\n" +
                "/*a_2^1*/6\n" +
                "/*a_3^1*/-10\n" +
                "/*a_1^2*/5\n" +
                "/*a_2^2*/-5\n" +
                "/*a_3^2*/1\n" +
                "/*a_4^2*/-1\n" +
                "/*a_1^3*/5\n" +
                "/*a_2^3*/-7\n" +
                "/*a_3^3*/1\n" +
                "/*a_4^3*/1\n" +
                "/*alpha_1*/69\n" +
                "/*alpha_2*/58\n" +
                "/*z_1*/1";
        
        Structure system = Converter.convert(file);
        System.out.println("OK");
        
        
        // Graph<V, E> where V is the type of the vertices 
        // and E is the type of the edges
        Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
        // Add some vertices. From above we defined these to be type Integer.
        g.addVertex((Integer)1);
        g.addVertex((Integer)2);
        g.addVertex((Integer)3); 
        // Add some edges. From above we defined these to be of type String
        // Note that the default is for undirected edges.
        g.addEdge("a", 1, 2); // Note that Java 1.5 auto-boxes primitives
        g.addEdge("b", 2, 3); 
        g.addEdge("c", 1, 3); 
        // Let's see what we have. Note the nice output from the
        // SparseMultigraph<V,E> toString() method
        System.out.println("The graph g = " + g.toString());
        new MinimumSpanningForest(g, null, g);
    }
}