/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework.structures;

import by.bsu.coursework.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author th13f
 */
public class Worker {
    public static LinkedList<Edge> getModifiedSpanningTree(LinkedList<Edge> graph){
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
    
    public static Integer[] getDArray(LinkedList<Edge> flow, LinkedList<Edge> tree, int root, int size){
        Integer[] d = new Integer[size];
        for (int i=0; i<size; i++){
            d[i]=-2;
        }
        d[root]=0;
        for (Edge t:tree){
            for (Edge f:flow){
                if (t.getFrom()==f.getFrom() && t.getTo()==f.getTo()){
                    d[t.getFrom()]=1;
                    break;
                }
                else if (t.getFrom()==f.getTo() && t.getTo()==f.getFrom()){
                    d[t.getFrom()]=-1;
                    break;
                }
            }
        }
        
        return d;
    }

    static LinkedList<Edge> getSpanningTree(LinkedList<Edge> tree, LinkedList<Edge> newTree) {
        LinkedList<Edge> realTree = new LinkedList<Edge>();
        
        for (Edge t:newTree){
            for (Edge f:tree){
                if ((t.getFrom()==f.getFrom() && t.getTo()==f.getTo()) || (t.getFrom()==f.getTo() && t.getTo()==f.getFrom())){
                    realTree.add(f);
                }
            }
        }
        
        return realTree;
    }
    
    static LinkedList<Edge> getCyclicEdges(LinkedList<Edge> flow, LinkedList<Edge> tree){
        LinkedList<Edge> edges = (LinkedList) flow.clone();
        
        for (Edge t:tree){
            for (Edge e:edges){
                if ((t.getFrom()==e.getFrom() && t.getTo()==e.getTo())){
                    edges.remove(e);
                    break;
                }
            }
        }
        
        return edges;
    }
    
    static ArrayList<Equation> getSystem(LinkedList<Edge> flow, int vertices,int flowIndex,ArrayList<Integer> alphas){
        ArrayList<Equation> system = new ArrayList<>(vertices);
        for (int i=0; i<vertices; i++){
            Equation eq = new Equation();
            for (Edge e:flow){
                if(e.getTo()==i){
                    eq.addToLeft(new Variable("x", e.getFrom()+","+e.getTo(), ""+flowIndex, -1));
                }
                if (e.getFrom()==i){
                    eq.addToLeft(new Variable("x", e.getFrom()+","+e.getTo(), ""+flowIndex, 1));
                }
            }
            eq.addToRight(new Constant(alphas.get(i)));
            Logger.info(eq.toString());
            system.add(eq);
        }
        Logger.info("");

        return system;
    }
    
    
}
