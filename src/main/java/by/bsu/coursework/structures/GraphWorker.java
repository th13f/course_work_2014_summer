/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework.structures;

import java.util.Collections;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

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
    
    public static Integer[] getPArray(LinkedList<Edge> tree, LinkedList<Edge> newTree, int root, int verticesCount){
        Integer[] p = new Integer[verticesCount];
        LinkedList<Edge> tmpEdges = new LinkedList<>();
        Stack<Integer> vertices = new Stack<>();
        
        for(Edge e:tree){
            tmpEdges.add(e);
        }
        
        for (int i=0; i<verticesCount; i++){
            p[i] = -2;
        }
        p[root]=-1;
        
        int current = root;
        while (!tmpEdges.isEmpty()){
            for (Edge e:tmpEdges){
                if (e.getTo() == current){
                    p[e.getFrom()] = current;
                    vertices.push(e.getFrom());
                    newTree.add(e.clone());
                    tmpEdges.remove(e);
                    break;
                }
                else if (e.getFrom() == current){
                    p[e.getTo()] = current;
                    vertices.push(e.getTo());
                    newTree.add(new Edge(e.getTo(),e.getFrom()));
                    tmpEdges.remove(e);
                    break;
                }
            }
            current = vertices.pop();
        }
        
        return p;
    }
}
