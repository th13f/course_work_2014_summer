/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework.structures;

/**
 *
 * @author th13f
 */
public class Lambda {
    public int from;
    public int to;
    public int flow;
    public int value;

    public Lambda(String from, String to, String flow, String value) {
        this.from = Integer.parseInt(from);
        this.to = Integer.parseInt(to);
        this.flow = Integer.parseInt(flow);
        this.value = Integer.parseInt(value);
    }

    public Lambda(int from, int to, int flow, int value) {
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.value = value;
    }
}
