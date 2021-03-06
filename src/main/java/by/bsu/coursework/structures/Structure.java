/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsu.coursework.structures;

import by.bsu.coursework.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author th13f
 */
public class Structure {
    private ArrayList<LinkedList<Edge>> cyclicEdges;
    private ArrayList<LinkedList<Edge>> flows;
    private ArrayList<LinkedList<Lambda>> bigEquations;
    private ArrayList<ArrayList<Integer>> alphas;
    private ArrayList<LinkedList<Edge>> spanningTrees;
    private ArrayList<Integer[]> pArrays;
    private ArrayList<Integer[]> dArrays;
    private ArrayList<LinkedList<Integer>> tArrays;
    private ArrayList<Integer> roots;
    private ArrayList<ArrayList<Equation>> systems;
    private LinkedList<CharacteristicVector> characteristicVectors;
    
    private int types;
    private int vertices;
    public String result = "";
    
    public Structure(int types, int vertices) {
        //initializing
        this.types = types;
        this.vertices = vertices;
        flows = new ArrayList<>(types);
        bigEquations = new ArrayList<>(types);
        alphas = new ArrayList<>(types);
        cyclicEdges = new ArrayList<>(types);
        spanningTrees = new ArrayList<>(types);
        pArrays = new ArrayList<>(types);
        dArrays = new ArrayList<>(types);
        tArrays = new ArrayList<>(types);
        roots = new ArrayList<>(types);
        systems = new ArrayList<>(types);
        characteristicVectors = new LinkedList<>();
        for (int i=0; i<types; i++){
            flows.add(new LinkedList<Edge>());
            bigEquations.add(new LinkedList<Lambda>());
            alphas.add(new ArrayList<Integer>(vertices));
            for (int j=0; j<vertices; j++){
                alphas.get(i).add((Integer)0);
            }
        }
    }
    
    public void calculate() {
        int flowIndex=0;
        for (LinkedList<Edge> flow:flows){
            LinkedList<Edge> tree = Worker.getModifiedSpanningTree(flow);
            LinkedList<Edge> newTree = new LinkedList<>();
            Integer[] pArray = Worker.getPArray(tree, newTree, tree.get(0).getTo(), vertices);
            LinkedList<Integer> tArray = Worker.getTArray(newTree,pArray);
            int root = tree.get(0).getTo();
            Integer[] dArray = Worker.getDArray(flow,newTree,root,vertices);
            LinkedList<Edge> spanningTree = Worker.getSpanningTree(tree,newTree);
            LinkedList<Edge> cyclicEdge = Worker.getCyclicEdges(flow, spanningTree);
            spanningTrees.add(spanningTree);
            pArrays.add(pArray);
            tArrays.add(tArray);
            dArrays.add(dArray);
            roots.add(root);
            systems.add(Worker.getSystem(flow, vertices, flowIndex, alphas.get(flowIndex)));
            cyclicEdges.add(cyclicEdge);
            
            CharacteristicVector.calculateCharacteristicVectors(
                    flowIndex, 
                    characteristicVectors, 
                    cyclicEdge, 
                    spanningTree, 
                    systems, 
                    gettArrayInversed(flowIndex),
                    getpArray(flowIndex),
                    getdArray(flowIndex),
                    characteristicVectors);
            
            CharacteristicVector.calculateParticleSolve(
                    flowIndex, 
                    characteristicVectors, 
                    cyclicEdge, 
                    spanningTree, 
                    systems, 
                    gettArrayInversed(flowIndex),
                    getpArray(flowIndex),
                    getdArray(flowIndex),
                    characteristicVectors);
            
            flowIndex++;
        }
        
        for (CharacteristicVector vector:characteristicVectors)
            Logger.info(vector.toString());
    }
    
    
    public void addEdge(Edge edge, int flow){
        flows.get(flow).add(edge);
        //Logger.info("Edge: "+edge.getFrom()+" -> "+edge.getTo()+" flow="+flow);
    }
    
    public void addAlpha(int alpha, int flow, int equation){
        alphas.get(flow).set(equation, alpha);
        //Logger.info("Alpha: value="+alpha+" equation="+equation+" flow="+flow);
    }
    
    public void addLambda(int from, int to, int flow, int equation, int value){
        bigEquations.get(equation).add(new Lambda(from,to,flow,value));
        //Logger.info("Lambda: "+from+" -> "+to+" equation="+equation+" flow="+flow+" value="+value);
    }
    
    public LinkedList<Edge> getFlow(int i){
        return flows.get(i);
    }
    
    public LinkedList<Edge> getCyclicEdges(int i) {
        return cyclicEdges.get(i);
    }
    
    public int getTypes() {
        return types;
    }
    
    public int getVertices() {
        return vertices;
    }

    public Integer[] getpArray(int i) {
        return pArrays.get(i);
    }
    
    public List<Integer> gettArray(int i) {
        return tArrays.get(i);
    }
    
    public List<Integer> gettArrayInversed(int i) {
        LinkedList<Integer> tmp = (LinkedList)tArrays.get(i).clone();
        Collections.reverse(tmp);
        return tmp;
    }
    
    public Integer[] getdArray(int i) {
        return dArrays.get(i);
    }
    
    public int getRoot(int i) {
        return roots.get(i);
    }
}
