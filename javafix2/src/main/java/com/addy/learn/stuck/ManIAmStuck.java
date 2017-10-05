package com.addy.learn.stuck;

import com.addy.learn.utils.Scenario;

public class ManIAmStuck implements  Scenario {

    public static class FactCalc implements  Runnable{

        public static Object factLock = new Object();

        public static int factLimit = 20;

        private static int calcFibo(int factLimit){
            synchronized (FactCalc.factLock){
                if(FactCalc.factLimit == 0){
                    return 1;
                }else{
                    return factLimit * calcFibo(factLimit -1);
                }
            }
        }

     public void run() {
            int res = calcFibo(FactCalc.factLimit);
            System.out.println(res);
        }
    }

    public void runScenario() throws Exception {
        Thread t1 = new Thread(new FactCalc());
        t1.setDaemon(true);
        t1.start();

    }

}
