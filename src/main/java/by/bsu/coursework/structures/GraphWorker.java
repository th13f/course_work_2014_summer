/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework.structures;

import java.util.LinkedList;

/**
 *
 * @author th13f
 */
public class GraphWorker {
    public static LinkedList<Edge> getSpanningTree(LinkedList<Edge> graph){
        LinkedList<Edge> spanningTree = new LinkedList<>();
        LinkedList<Integer> vertices = new LinkedList<>();
        
        for (Edge e:graph){
            if (!vertices.contains(e.getFrom()))
                vertices.add(e.getFrom());
            if (!vertices.contains(e.getTo()))
                vertices.add(e.getTo());
        }
        
        for (Edge e:graph){
            if (vertices.contains(e.getFrom()) || vertices.contains(e.getTo())){
                spanningTree.add(e.clone());
                if (vertices.contains(e.getTo()))
                    vertices.remove((Integer)e.getTo());
                if (vertices.contains(e.getFrom()))
                    vertices.remove((Integer)e.getFrom());
            }
        }
        
        return spanningTree;
    }
    
    public static int[] getPArray(LinkedList<Edge> tree, int vertices){
        int[] p = new int[vertices];
        
        return p;
    }
}
