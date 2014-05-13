/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsu.coursework.structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author th13f
 */
public class CharacteristicVector {

    /**
     *
     * @param flowIndex the value of flowIndex
     * @param vectors the value of vectors
     * @param cyclicEdges the value of cyclicEdges
     * @param spanningTree the value of spanningTree
     * @param systems the value of systems
     * @param t the value of t
     * @param p the value of p
     * @param d the value of d
     */
    static void calculateParticleSolve(
            int flowIndex, 
            LinkedList<CharacteristicVector> vectors, 
            LinkedList<Edge> cyclicEdges, 
            LinkedList<Edge> spanningTree, 
            ArrayList<ArrayList<Equation>> systems, 
            List<Integer> t, 
            Integer[] p, 
            Integer[] d, 
            LinkedList<CharacteristicVector> characteristicVectors) {
        Edge edge = new Edge(0, 0);
        CharacteristicVector vector = new CharacteristicVector(flowIndex, edge.toString());
        ArrayList<Equation> system = new ArrayList<>();
        for (Equation e : systems.get(flowIndex)) {
            system.add(e.copy());
        }
        for (Edge e : cyclicEdges) {
            Commutation tmp = new Commutation(new Variable("x", e.toString(), "" + flowIndex, 1));
            tmp.addTo(new Constant(0));
            vector.addCommutation(tmp);
        }
        for (Equation e : system) {
            for (Commutation com : vector.getContent()) {
                e.insert(com);
            }
        }
        for (int j = 0; j < t.size() - 1; j++) {
            Commutation com;
            if (d[t.get(j)] == 1) {
                com = system.get(t.get(j)).solve(new Variable("x", t.get(j) + "," + p[t.get(j)], "" + flowIndex, 1));
            } else {
                com = system.get(t.get(j)).solve(new Variable("x", p[t.get(j)] + "," + t.get(j), "" + flowIndex, 1));
            }
            vector.addCommutation(com);
        }
        characteristicVectors.add(vector);
    }

    /**
     *
     * @param flowIndex the value of flowIndex
     * @param vectors the value of vectors
     * @param cyclicEdges the value of cyclicEdges
     * @param spanningTree the value of spanningTree
     * @param systems the value of systems
     * @param t the value of t
     * @param p the value of p
     * @param d the value of d
     */
    static void calculateCharacteristicVectors(
            int flowIndex, 
            LinkedList<CharacteristicVector> vectors, 
            LinkedList<Edge> cyclicEdges, 
            LinkedList<Edge> spanningTree, 
            ArrayList<ArrayList<Equation>> systems, 
            List<Integer> t, 
            Integer[] p, 
            Integer[] d, 
            LinkedList<CharacteristicVector> characteristicVectors) {
        for (int i = 0; i < cyclicEdges.size(); i++) {
            Edge edge = cyclicEdges.get(i);
            CharacteristicVector vector = new CharacteristicVector(flowIndex, edge.toString());
            Commutation mainEdge = new Commutation(new Variable("x", edge.toString(), "" + flowIndex, 1));
            mainEdge.addTo(new Constant(1));
            vector.addCommutation(mainEdge);
            ArrayList<Equation> system = new ArrayList<>();
            for (Equation e : systems.get(flowIndex)) {
                Equation eq = e.copy();
                eq.zeroRight();
                system.add(eq);
            }
            for (Edge e : cyclicEdges) {
                if (!e.equals(edge)) {
                    Commutation tmp = new Commutation(new Variable("x", e.toString(), "" + flowIndex, 1));
                    tmp.addTo(new Constant(0));
                    vector.addCommutation(tmp);
                }
            }
            for (int systemIndex = 0; systemIndex < system.size(); systemIndex++) {
                for (Commutation com : vector.getContent()) {
                    system.get(systemIndex).insert(com);
                }
            }
            for (int j = 0; j < t.size() - 1; j++) {
                Commutation com;
                if (d[t.get(j)] == 1) {
                    com = system.get(t.get(j)).solve(new Variable("x", t.get(j) + "," + p[t.get(j)], "" + flowIndex, 1));
                } else {
                    com = system.get(t.get(j)).solve(new Variable("x", p[t.get(j)] + "," + t.get(j), "" + flowIndex, 1));
                }
                vector.addCommutation(com);
            }
            
            characteristicVectors.add(vector);
        }
    }
    private int flow;
    private String index;
    private LinkedList<Commutation> content;

    public CharacteristicVector(int flow, String index) {
        this.flow = flow;
        this.index = index;
        content = new LinkedList<>();
    }
    
    public void addCommutation(Commutation com){
        content.add(com);
    }

    public LinkedList<Commutation> getContent() {
        return content;
    }

    @Override
    public String toString() {
        String result = "Commutation"+flow+"_"+index+'\n';
        for(Commutation com:content)
                result+=com.toString()+'\n';
        return result;
    }
}
