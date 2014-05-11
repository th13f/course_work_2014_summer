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
public class Equation {
    private LinkedList<EquationPart> leftPart;
    private LinkedList<EquationPart> rightPart;

    public Equation() {
        leftPart=new LinkedList<>();
        rightPart=new LinkedList<>();
    }
    
    public void addToLeft(EquationPart var){
        leftPart.add(var);
    }
    
    public void addToRight(EquationPart var){
        leftPart.add(var);
    }

    @Override
    public String toString() {
        String result = "";
        for(EquationPart v:leftPart)
            result+=v.toString();
        result+=" = ";
        for(EquationPart v:rightPart)
            result+=v.toString();
        return result;
    }
    
    public void simplify(LinkedList<EquationPart> to){
        Constant tempConstant = new Constant(0);
        for (int i=0; i<to.size();){
            if (to.get(i).getType().equals("Constant")){
                tempConstant.setValue(tempConstant.getValue()+((Constant)to.get(i)).getValue());
                to.remove(i);
            }
            else
                i++;
        }
        to.add(tempConstant);
    }
    
    public Commutation solve(Variable what){
        Commutation result = new Commutation(what);
        
        LinkedList<EquationPart> to = (LinkedList)rightPart.clone();
        for (EquationPart var:leftPart){
            to.add(var.inversed());
        }
        simplify(to);
        result.setTo(to);
        
        return result;
    }
    
    public void insert(Commutation what){
        for (int i=0; i<leftPart.size();){
            if (what.getFrom().getName().equals(what.getFrom().getName()) &&
                what.getFrom().getSubIndex().equals(what.getFrom().getSubIndex()) &&
                what.getFrom().getTopIndex().equals(what.getFrom().getTopIndex())){
                double alpha = leftPart.get(i).getCoefficient();
                leftPart.remove(leftPart.get(i));
                for (EquationPart var:what.getTo()){
                    switch (var.getType()) {
                        case "Variable":
                            leftPart.add(new Variable(
                                    ((Variable)var).getName(),
                                    ((Variable)var).getSubIndex(),
                                    ((Variable)var).getTopIndex(),
                                    ((Variable)var).getCoefficient()*alpha));
                            break;
                        case "Constant":
                            leftPart.add(new Constant(
                                    ((Variable)var).getCoefficient()*alpha));
                            break;
                    }
                }
            }
            else{
                i++;
            }
        }
        for (int i=0; i<rightPart.size();){
            if (what.getFrom().getName().equals(what.getFrom().getName()) &&
                what.getFrom().getSubIndex().equals(what.getFrom().getSubIndex()) &&
                what.getFrom().getTopIndex().equals(what.getFrom().getTopIndex())){
                double alpha = rightPart.get(i).getCoefficient();
                rightPart.remove(rightPart.get(i));
                for (EquationPart var:what.getTo()){
                    switch (var.getType()) {
                        case "Variable":
                            rightPart.add(new Variable(
                                    ((Variable)var).getName(),
                                    ((Variable)var).getSubIndex(),
                                    ((Variable)var).getTopIndex(),
                                    ((Variable)var).getCoefficient()*alpha));
                            break;
                        case "Constant":
                            rightPart.add(new Constant(
                                    ((Variable)var).getCoefficient()*alpha));
                            break;
                    }
                }
            }
            else{
                i++;
            }
        }
    }
}
