package com.addy.learn.stuck;

import com.addy.learn.utils.Scenario;

import java.util.HashMap;
import java.util.Map;

public class Hogger implements Scenario, Runnable {

    public static Map<String,String> fileList;
    public static String rootScan = "./";
    public Hogger(){
        fileList = new HashMap<String, String>();
    }

    private void hoggingAllICan(){
       for (int i = 0; i < 100; i++){
            if (i % 2 == 0){
                fileList.put("value" + Integer.toString(i), "some string which I want to put when there is a even number ");
            }else{
                fileList.put("value" + Integer.toString(i),"some string when the number is odd");
            }

            if ( i == 99){
                i = 0;
            }

       }
    }

    public void run(){
       hoggingAllICan();
    }

    public void runScenario() throws Exception {
      Thread t = new Thread(this);
      t.setDaemon(true);
      t.start();
    }
}
