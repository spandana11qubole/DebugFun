package com.addy.learn;


import com.addy.learn.stuck.Hogger;
import com.addy.learn.stuck.ManIAmStuck;
import com.addy.learn.utils.Scenario;

import java.util.Scanner;

/**
 * CLI environment for scenario execution and trying out new stuff
 *
 */
public class App {
    public static void main(String[] args) {
        String inputString = null;
        while (true) {
            System.out.println("Scenario Prompt>");
            Scanner scanner = new Scanner(System.in);
            inputString = scanner.nextLine();

            if(inputString.equalsIgnoreCase("ls")){
                System.out.println("Stuck, Hogger");
            }else if(inputString.matches("Stuck")) {
                try{
                    ManIAmStuck m = new ManIAmStuck();
                    ManIAmStuck.FactCalc.factLimit = 100;
                    m.runScenario();
                }catch (Exception e){
                    System.out.println("Failed with exception "+ e.getMessage() );
                    e.printStackTrace();
                }
            }else if(inputString.equalsIgnoreCase("Hogger")){
                try{
                    Hogger h = new Hogger();
                    h.runScenario();
                }catch(Exception e){
                     System.out.println("Failed with exception "+ e.getMessage() );
                     e.printStackTrace();
                }
            }else if(inputString.equalsIgnoreCase("exit")){
                System.out.println("Bye");
                System.exit(0);
            }else if(inputString.equalsIgnoreCase("help")){
                System.out.println("ls");
                System.out.println("hogger");
                System.out.println("stuck");
                System.out.println("help shows help");
                System.out.println("exit - exits app");
            }
            else{
                System.out.println("Don't know what are you talking about");
            }
        }
    }
}
