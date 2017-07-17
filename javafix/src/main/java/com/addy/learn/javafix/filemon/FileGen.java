package com.addy.learn.javafix.filemon;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class FileGen implements Runnable{

    public static final long DEFAULT_NUMBER_OF_FILES = 1000;
    public static final String[] FILE_CONTENT_LIST = new String[] {
            "Quick brown fox jumps over the lazy dog",
            "Quick brown fox got lazy",
            "Quick brown pig jumped over lazy fox",
            "Quick brown pig got lazy",
            "Quick brown hen jumped over lazy pig",
            "The hen is sleeping now"
    };
    public static Logger log = Logger.getLogger(FileGen.class.getCanonicalName());

    private File rootDirectory;
    private Thread fileGenThread;
    private long currentFileNumber = 0;
    private ArrayList<String> fileList = new ArrayList<String>();

    private String getRandomString(){
        log.info("getRandomString Enter");
        Random r = new Random();
        int next = r.nextInt(FILE_CONTENT_LIST.length);
        String s = null;
        if (next < FILE_CONTENT_LIST.length){
            s =  FILE_CONTENT_LIST[next];
        }else{
            s = "";
        }

        log.info("getRandomString exit");
        return s;
    }

    public FileGen(String directoryPath) throws FileNotFoundException,IOException {

        log.info("FileGen constructor - Enter");
        rootDirectory = new File(directoryPath);
        for(int i = 0; i < FileGen.DEFAULT_NUMBER_OF_FILES; i++){
            String fileName = FileGen.class.getName() + Integer.toString(i);
            Path filePath = Paths.get(rootDirectory.getAbsolutePath(),fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()));
            for(int k = 0; k < 10; k++){
                writer.write(getRandomString());
                writer.newLine();
            }
            writer.close();
            fileList.add(filePath.toString());
        }
        log.info("FileGen constructor - Exit");

    }

    public void run() {
            log.info("Enter the filegen thread - files are being updated");
           while((true)){
                for(String filePath : fileList){

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));
                        writer.write(getRandomString());
                        writer.newLine();
                        Thread.sleep(20000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
           }
    }


}