package com.addy.learn.javafix.filemon;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{

    public static Thread FILE_MON_THREAD;
    public static Thread FILE_GEN_THREAD;
    public static Logger LOG = Logger.getLogger(App.class.getCanonicalName());

    public static void help(){
       LOG.log(Level.INFO,"Enter help");

    }

    public static void main( String[] args ) throws IllegalArgumentException, IOException{
        LOG.log(Level.INFO,"Enter App");
        String rootDirectory = null;
        if(args.length < 2){
           LOG.log(Level.SEVERE, "App Needs a root directory please specify -root-dir <path>");
           throw new IllegalArgumentException("No root directory specified");
        }

        if(args[0].equals("-root-dir") || args[0].equals("-r")){
            rootDirectory = args[1];
        }else if (args[0].equals("-h") || args[0].equals("--help")){
            help();
        }else{
            help();
            LOG.log(Level.SEVERE,"Illeagal argument passed " + args[0]);
            throw new IllegalArgumentException("Invalid argument exception " + args[0] );
        }

        FILE_GEN_THREAD = new Thread(new FileGen(rootDirectory));
        FILE_GEN_THREAD.start();
        FILE_MON_THREAD = new Thread(new FileMon(rootDirectory));
        FILE_MON_THREAD.start();
        Thread monitorThread = new Thread() {
            @Override
            public void run() {
               while(true){
                   try {
                       Thread.sleep(5000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   if(FILE_GEN_THREAD.isAlive() || FILE_MON_THREAD.isAlive()){
                       continue;
                   }else{
                       break;
                   }

               }
            }
        };

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                LOG.info("Shutdown hook - enter");
                FILE_MON_THREAD.interrupt();
                FILE_GEN_THREAD.interrupt();
                LOG.info("Shutdown hook - exit");
            }
        });

        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.log(Level.INFO,"Exit the app");
    }
}
