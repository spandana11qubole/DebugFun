package com.addy.learn.javafix3.chaos;

/**
 * Hello world!
 *
 */
public class Chaos {
    private int c;
    private Thread incrementThread;
    private Thread decrementThread;
    private Thread showThread;
    private Thread showThreadStates;

    public Chaos(){
        c = 0;
        incrementThread = new Thread() {
            @Override
            public void run(){
                while(true){
                    increment();
                }
            }
        };

        decrementThread = new Thread(){
            @Override
            public void run(){
                while(true){
                    decrement();
                }
            }
        };

        showThread = new Thread() {
            @Override
            public void run () {
                while(true){
                    showC();
                }
            }
        };

        showThreadStates = new Thread() {
            @Override
            public void run(){
                while(true){
                    showThreadStatesThread();
                }
            }
        };

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                shutdownThreads();
            }
        });
    }

    public void increment(){
        c++;
    }

    public  void decrement(){
        c--;
    }

    public  void showC(){
        System.out.println("Value of C = " + Integer.toString(c));
    }

    public  void startThreads(){
        incrementThread.start();
        decrementThread.start();
        showThread.start();
        showThreadStates.start();

    }

    public void monitorThreads(){
        while(incrementThread.isAlive() && decrementThread.isAlive() && showThread.isAlive()){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdownThreads(){
        incrementThread.interrupt();
        decrementThread.interrupt();
        showThread.interrupt();
        showThreadStates.interrupt();
    }

    public void showThreadStatesThread(){
        System.out.println("Increment Thread is " + incrementThread.getState().toString());
        System.out.println("Decrement Thread is " + decrementThread.getState().toString());
        System.out.println("Show Thread is " + showThread.getState().toString());
    }


    public static void main( String[] args ) {

       Chaos chaos = new Chaos();
       chaos.startThreads();
       chaos.monitorThreads();
    }
}
