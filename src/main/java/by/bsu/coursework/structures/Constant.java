/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework.structures;

/**
 *
 * @author th13f
 */
public class Constant implements EquationPart{
    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String result="";
        if (value>=0)
            result="+";
        return result+(int)value;
    }

    @Override
    public EquationPart inversed() {
        return new Constant(-this.value);
    }
    
    public double getValue(){
        return value;
    }
    
    public void setValue(double value){
        this.value = value;
    }
    
    @Override
    public String getType() {
        return "Constant";
    }
    
    @Override
    public double getCoefficient(){
        return this.value;
    }
    
    @Override
    public void setCoefficient(double coeff){
        this.value = coeff;
    }
}
