/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import by.bsu.coursework.Logger;
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
        String file = "/*|I|*/4\n" +
"/*|K|*/2\n" +
"/*widetilde{U}^1*/\n" +
"/*{2,1}*/\n" +
"/*{1,3}*/\n" +
"/*{1,4}*/\n" +
"/*{2,3}*/\n" +
"/*{2,4}*/\n" +
"/*{3,4}*/\n" +
"/*widetilde{U}^2*/\n" +
"/*{1,3}*/\n" +
"/*{1,4}*/\n" +
"/*{2,1}*/\n" +
"/*{2,3}*/\n" +
"/*{2,4}*/\n" +
"/*{3,4}*/\n" +
"/*widetilde{U}_0*/\n" +
"/*{2,1}*/1\n" +
"/*{2,4}*/1\n" +
"/*widetilde{U}_0^1*/\n" +
"/*{2,1}*/1\n" +
"/*{2,4}*/1\n" +
"/*widetilde{U}_0^2*/\n" +
"/*{2,1}*/1\n" +
"/*{2,4}*/1\n" +
"/*q*/2\n" +
"/*lambda_13^11*/2\n" +
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
"/*a_1^1*/2\n" +
"/*a_2^1*/9\n" +
"/*a_3^1*/4\n" +
"/*a_4^1*/-15\n" +
"/*a_1^2*/0\n" +
"/*a_2^2*/18\n" +
"/*a_3^2*/-7\n" +
"/*a_4^2*/-11\n" +
"/*alpha_1*/69\n" +
"/*alpha_2*/58\n" +
"/*z_1*/11\n" +
"/*z_2*/6"; 
        
        Structure system = Converter.convert(file,false);
        Logger.system = system;
        system.calculate();
        System.out.println(system.result);
    }
}