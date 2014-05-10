/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import by.bsu.coursework.converter.Converter;
import by.bsu.coursework.structures.Structure;

/**
 *
 * @author th13f
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String file = 
                "/*|I|*/5\n" + //количество вершин
                "/*|K|*/3\n" + //количество потоков
                
                "/*widetilde{U}^1*/\n" + //указан номер потока с перечислением его ребер
                "/*{1,2}*/\n" +
                "/*{1,3}*/\n" +
                "/*{2,3}*/\n" +
                
                "/*widetilde{U}^2*/\n" +
                "/*{2,3}*/\n" +
                "/*{2,4}*/\n" +
                "/*{3,4}*/\n" +
                "/*{4,5}*/\n" +
                "/*{5,3}*/\n" +
                "/*{3,1}*/\n" +
                
                "/*widetilde{U}^3*/\n" +
                "/*{2,3}*/\n" +
                "/*{2,4}*/\n" +
                "/*{3,4}*/\n" +
                "/*{4,5}*/\n" +
                "/*{5,3}*/\n" +
                "/*{2,5}*/\n" +
                
                "/*widetilde{U}_0*/\n" + //указаны индексы, которые будут учавствовать в маленьких уравнениях. уравнения вида x2_32+x3_32=2, x1_12+x2_12+x3_12=1;
                "/*{2,4}*/1\n" +
                
                "/*widetilde{U}_0^1*/\n" + //коэффициент перед переменной с данным индексом.
                "/*{2,4}*/0\n" +
                
                "/*widetilde{U}_0^2*/\n" +
                "/*{2,4}*/1\n" +
                
                "/*widetilde{U}_0^3*/\n" +
                "/*{2,4}*/1\n" +
                
                "/*q*/2\n" + //количество больших уравнений
                
                "/*lambda_12^11*/2\n" + //коэффициенты перед иксами с заданными индексами в больших уравнениях
                "/*lambda_13^11*/3\n" +
                "/*lambda_23^11*/1\n" +
                "/*lambda_23^21*/4\n" +
                "/*lambda_23^31*/2\n" +
                "/*lambda_24^21*/3\n" +
                "/*lambda_24^31*/-4\n" +
                "/*lambda_34^21*/2\n" +
                "/*lambda_34^31*/1\n" +
                "/*lambda_45^21*/-1\n" +
                "/*lambda_45^31*/7\n" +
                "/*lambda_53^21*/1\n" +
                "/*lambda_53^31*/2\n" +
                
                "/*lambda_12^12*/1\n" +
                "/*lambda_13^12*/2\n" +
                "/*lambda_23^12*/2\n" +
                "/*lambda_23^22*/5\n" +
                "/*lambda_23^32*/3\n" +
                "/*lambda_24^22*/-1\n" +
                "/*lambda_24^32*/-1\n" +
                "/*lambda_34^22*/1\n" +
                "/*lambda_34^32*/1\n" +
                "/*lambda_45^22*/-2\n" +
                "/*lambda_45^32*/3\n" +
                "/*lambda_53^22*/2\n" +
                "/*lambda_53^32*/-1\n" +
                
                "/*a_1^1*/4\n" + //правая часть систем
                "/*a_2^1*/6\n" +
                "/*a_3^1*/-10\n" +
                "/*a_4^1*/0\n" +
                "/*a_5^1*/0\n" +
                
                "/*a_1^2*/5\n" +
                "/*a_2^2*/-5\n" +
                "/*a_3^2*/1\n" +
                "/*a_4^2*/-1\n" +
                "/*a_5^2*/0\n" +
                
                "/*a_1^3*/5\n" +
                "/*a_2^3*/-7\n" +
                "/*a_3^3*/1\n" +
                "/*a_4^3*/1\n" +
                "/*a_5^3*/0\n" +
                
                "/*alpha_1*/69\n" + //правая часть больших уравнений
                "/*alpha_2*/58\n" +
                
                "/*z_1*/1"; //правая часть маленьких уравнений
        
        Structure system = Converter.convert(file);
        system.calculate();
        int flow=0;
        for (int i=0; i<system.getVertices(); i++){
            System.out.print(system.getdArray(flow)[i]+",");
        }
        System.out.println("\nroot="+system.getRoot(flow));
        System.out.println("OK");
    }
}