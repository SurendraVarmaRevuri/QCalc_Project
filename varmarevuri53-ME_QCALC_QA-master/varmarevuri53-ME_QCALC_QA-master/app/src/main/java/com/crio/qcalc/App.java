/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.crio.qcalc;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {     
     LogicCalculator calc = new LogicCalculator();
     calc.AND(8, 12 );
     calc.printResult();

        // System.out.println(new App().getGreeting());
        // StandardCalculator calc = new StandardCalculator();
        // calc.add(Double.MAX_VALUE,1.0);
        // calc.printResult();
        // System.out.println(calc.getResult());

        // calc.subtract(1, 2);  
        // System.out.println(calc.getResult());

        // calc.multiply(1, 2);
        // System.out.println(calc.getResult());

        // calc.divide(1, 2);
        // System.out.println(calc.getResult());
       

    }

}

