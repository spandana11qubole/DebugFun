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

    public Chaos(){
        c = 0;
        incrementThread = new Thread() {
            @Override
            public void run(){
                while(true){
                    try {
                        increment();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("increment thread interrupted");
                    }
                }
            }
        };

        decrementThread = new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        decrement();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("increment thread interrupted");
                    }
                }
            }
        };

        showThread = new Thread() {
            @Override
            public void run () {
                while(true){
                    try {
                        showC();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("increment thread interrupted");
                    }
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

    public void decrement(){
        c--;
    }

    public void showC(){
        System.out.println("Value of the field c is " + Integer.toString(c));
    }

    public void startThreads(){
        incrementThread.start();
        decrementThread.start();
        showThread.start();

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
    }

    public static void main( String[] args ) {

       Chaos chaos = new Chaos();
       chaos.startThreads();
       chaos.monitorThreads();
    }
}
