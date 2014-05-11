/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework.structures;

/**
 *
 * @author th13f
 */
public interface EquationPart {
    @Override
    public String toString();
    
    public EquationPart inversed();
    
    public String getType();
    
    public double getCoefficient();
    public void setCoefficient(double coeff);
}
