/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework.structures;

/**
 *
 * @author th13f
 */
public class Variable implements EquationPart{
    private String name;
    private String subIndex;
    private String topIndex;
    private double coefficient;

    public Variable(String name, String subIndex, String topIndex, double coefficient) {
        this.name = name;
        this.subIndex = subIndex;
        this.topIndex = topIndex;
        this.coefficient = coefficient;
    }

    @Override
    public String toString(){
        return toString(true);
    }
    
    public String toString(boolean hasSign) {
        String result="";
        if (coefficient>=0){
            if (hasSign)
                result = "+";
        }
        else{
            result = "-";
        }
        
        if (Math.abs((int)coefficient)==1)
            return result+name+topIndex+"_"+subIndex;
        
        return result+Math.abs((int)coefficient)+name+topIndex+"_"+subIndex;
    }

    @Override
    public EquationPart inversed() {
        return new Variable(name, subIndex, topIndex, -coefficient);
    }

    @Override
    public String getType() {
        return "Variable";
    }

    public String getName() {
        return name;
    }
    
    public String getSubIndex() {
        return subIndex;
    }

    public String getTopIndex() {
        return topIndex;
    }

    @Override
    public double getCoefficient() {
        return coefficient;
    }
    
    @Override
    public void setCoefficient(double coeff){
        this.coefficient = coeff;
    }

    @Override
    public void divide(double divider) {
        this.coefficient/=divider;
    }
}
