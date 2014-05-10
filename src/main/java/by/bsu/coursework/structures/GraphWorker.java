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
        LinkedList<Edge> tmpEdges;
        LinkedList<Edge> edges;
        LinkedList<Edge> tmpEdges1 = new LinkedList<>();
        LinkedList<Edge> tmpEdges2 = new LinkedList<>();
        Stack<Integer> vertices = new Stack<>();
        
        int edgesIdentifier=1;
        edges=tmpEdges1;
        tmpEdges=tmpEdges2;
        
        for(Edge e:tree){
            tmpEdges.add(e);
        }
        edges=(LinkedList)tmpEdges.clone();
        
        for (int i=0; i<verticesCount; i++){
            p[i] = -2;
        }
        p[root]=-1;
        
        int current = root;
        while (!tmpEdges.isEmpty()){
            for (Edge e:edges){
                if (e.getTo() == current){
                    p[e.getFrom()] = current;
                    vertices.push(e.getFrom());
                    newTree.add(e.clone());
                    tmpEdges.remove(e);
                }
                else if (e.getFrom() == current){
                    p[e.getTo()] = current;
                    vertices.push(e.getTo());
                    newTree.add(new Edge(e.getTo(),e.getFrom()));
                    tmpEdges.remove(e);
                }
            }
            current = vertices.pop();   
            edges=(LinkedList)tmpEdges.clone();
        }
        
        return p;
    }
    
    public static void work(Integer[] pArray, LinkedList<Integer> tArray, int current, int size){
        if (tArray.size()==size)
            return;
        
        for (int i=0; i<pArray.length; i++){
            if (pArray[i]==current){
                tArray.add(i);
                work(pArray, tArray,i,size);
            }
        }
    }
    
    public static LinkedList<Integer> getTArray(LinkedList<Edge> tree, Integer[] pArray){
        int verticesCount = pArray.length;
        int size=0;
        LinkedList<Integer> tmp = new LinkedList<>();
        int root = -1;
        int current;
        for(int i=0; i<verticesCount; i++){
            if (pArray[i]==-1){
                root = i;
            }
            if (pArray[i]!=-2)
                size++;
        }
        current = root;
        tmp.push(root);
        
        work(pArray,tmp,current,size);
        
        return tmp;
    }
}
