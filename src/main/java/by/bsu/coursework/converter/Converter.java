/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsu.coursework.converter;

import by.bsu.coursework.Logger;
import by.bsu.coursework.structures.Edge;
import by.bsu.coursework.structures.Structure;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author th13f
 */
public class Converter {
    public static Structure convert(String file,boolean isWeb){
        String splitter;
        if(isWeb)
            splitter="\r\n";
        else
            splitter="\n";
        String[] content = file.split(splitter);
        int I,U,K;
        I = Integer.parseInt(content[0].replace("/*|I|*/", ""));
        K = Integer.parseInt(content[1].replace("/*|K|*/", ""));
        Structure system = new Structure(K,I);
        Pattern indicatorEdgesPattern = Pattern.compile("/\\*widetilde\\{U}\\^(\\d+)\\*/");
        Pattern edgesPattern = Pattern.compile("/\\*\\{(\\d+),(\\d+)}\\*/");
        Pattern edgesInSmallEquationsPattern = Pattern.compile("/\\*widetilde\\{U}_0\\*/");
        Pattern alphaPattern = Pattern.compile("/\\*a_(\\d)\\^(\\d)\\*/(-?\\d+)");
        Pattern lambdaPattern = Pattern.compile("/\\*lambda_(\\d)(\\d)\\^(\\d)(\\d)\\*/(-?\\d+)");
        int index = 2;
        int currentFlow=-1;
        boolean edgesReaded = false;
        while(index<content.length){
            if (!edgesReaded){
                Matcher tmp = indicatorEdgesPattern.matcher(content[index]);
                if(tmp.find()){
                    currentFlow = Integer.parseInt(tmp.group(1))-1;
                }

                Matcher edge = edgesPattern.matcher(content[index]);
                if (edge.find()){
                    system.addEdge(new Edge(Integer.parseInt(edge.group(1))-1,Integer.parseInt(edge.group(2))-1), currentFlow);
                }

                Matcher smallEquationIndicator = edgesInSmallEquationsPattern.matcher(content[index]);
                if (smallEquationIndicator.find())
                    edgesReaded = true;
            }
            else{
                Matcher alphaMatcher = alphaPattern.matcher(content[index]);
                if (alphaMatcher.find()){
                    system.addAlpha(Integer.parseInt(alphaMatcher.group(3)), Integer.parseInt(alphaMatcher.group(2))-1, Integer.parseInt(alphaMatcher.group(1))-1);
                }
                Matcher lambdaMatcher = lambdaPattern.matcher(content[index]);
                if (lambdaMatcher.find()){
                    system.addLambda(Integer.parseInt(lambdaMatcher.group(1))-1, Integer.parseInt(lambdaMatcher.group(2))-1,Integer.parseInt(lambdaMatcher.group(3))-1,Integer.parseInt(lambdaMatcher.group(4))-1,Integer.parseInt(lambdaMatcher.group(5)));
                }
            }
            
            index++;
        }
        return system;
    }
}