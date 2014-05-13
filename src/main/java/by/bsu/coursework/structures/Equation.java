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
    private final LinkedList<EquationPart> leftPart;
    private final LinkedList<EquationPart> rightPart;

    public Equation() {
        leftPart=new LinkedList<>();
        rightPart=new LinkedList<>();
    }
    
    public Equation(LinkedList<EquationPart> leftPart, LinkedList<EquationPart> rightPart) {
        this.leftPart=new LinkedList<>(leftPart);
        this.rightPart=new LinkedList<>(rightPart);
    }
    
    public void addToLeft(EquationPart var){
        leftPart.add(var);
    }
    
    public void addToRight(EquationPart var){
        rightPart.add(var);
    }
    
    public void zeroRight(){
        rightPart.clear();
        rightPart.add(new Constant(0));
    }

    @Override
    public String toString() {
        String result = "";
        for(EquationPart v:leftPart)
            result+=v.toString();
        if (leftPart.isEmpty())
            result+= "0";
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
        for(int i=0; i<to.size(); i++){
            if (to.get(i).getType().equals("Variable")){
                Variable current = (Variable)to.get(i);
                if (current.getName().equals(what.getName()) &&
                    current.getSubIndex().equals(what.getSubIndex()) &&
                    current.getTopIndex().equals(what.getTopIndex())){
                    
                    to.remove(current);
                    break;
                }
            }
        }
        
        result.setTo(to);
        
        return result;
    }
    
    public void insert(Commutation what){
        for (int i=0; i<leftPart.size();){
            if (leftPart.get(i).getType().equals("Variable")){
                Variable current = (Variable) leftPart.get(i);
                if (current.getName().equals(what.getFrom().getName()) &&
                    current.getSubIndex().equals(what.getFrom().getSubIndex()) &&
                    current.getTopIndex().equals(what.getFrom().getTopIndex())){
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
                                        ((Constant)var).getCoefficient()*alpha));
                                break;
                        }
                    }
                }
                else{
                    i++;
                }
            }
            else
                i++;
        }   
        for (int i=0; i<rightPart.size();){
            if (rightPart.get(i).getType().equals("Variable")){
            Variable current = (Variable) rightPart.get(i);
            if (current.getName().equals(what.getFrom().getName()) &&
                    current.getSubIndex().equals(what.getFrom().getSubIndex()) &&
                    current.getTopIndex().equals(what.getFrom().getTopIndex())){
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
            else
                i++;
        }
    }

    Equation copy() {
        return new Equation(this.leftPart,this.rightPart);
    }
}
