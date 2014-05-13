/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.coursework;

import by.bsu.coursework.structures.Structure;

/**
 *
 * @author th13f
 */
public class Logger {
    public static Structure system = null;
    public static void info(String info){
        system.result+=info+'\n';
    }
}
