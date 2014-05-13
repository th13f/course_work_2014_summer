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
public class Commutation {
    private Variable from;
    private LinkedList<EquationPart> to;

    public Commutation(Variable from) {
        this.from = from;
        this.to = new LinkedList<>();
    }

    public void setTo(LinkedList<EquationPart> to) {
        this.to = to;
    }
    
    public void addTo(EquationPart v){
        to.add(v);
    }

    @Override
    public String toString() {
        String result = from.toString(false)+"->";
        if (to.isEmpty())
            result+= "0";
        else{
            result+=to.get(0).toString(false);
            for(int i=1; i<to.size(); i++)
                result+=to.get(i).toString();
        }
        
        return result;
    }

    public Variable getFrom() {
        return from;
    }

    public LinkedList<EquationPart> getTo() {
        return to;
    }
}
