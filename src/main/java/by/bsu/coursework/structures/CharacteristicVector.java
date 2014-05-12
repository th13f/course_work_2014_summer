/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsu.coursework.structures;

import java.util.LinkedList;

/**
 *
 * @author th13f
 */
public class CharacteristicVector {
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
