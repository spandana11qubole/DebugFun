package com.addy.learn.javafix.filemon;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by adityak on 15/7/17.
 */
public class FileMon  implements  Runnable{

    public static Logger LOG = Logger.getLogger(FileMon.class.getCanonicalName());

    private Map<String,String> fileMap;
    private File rootDirectory;
    private Thread fileMonThread;

    public FileMon(String rootDirectory) throws FileNotFoundException {

       this.rootDirectory = new File(rootDirectory);
       fileMap = new HashMap<String, String>();

    }

    /**
     * @param - void
     *
      */
    public void run() {
                LOG.info("FileGen Thread Enter - Files are being monitored");
                while (true) {
                    List<String> fileList = new ArrayList<String>();
                    for (String file : rootDirectory.list()) {
                       try {
                           Path filePath = Paths.get(rootDirectory.getAbsolutePath(), file);
                           BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
                           String line = reader.readLine();
                           String lastLine = line;
                           while (line != null) {
                               line = reader.readLine();
                               lastLine = line;
                           }
                           if (fileMap.containsKey(filePath.toString())) {
                               if (!fileMap.get(filePath.toString()).equals(lastLine)) {
                                   LOG.info(filePath.toString() + "has changed");
                               }

                               fileMap.put(filePath.toString(), lastLine);

                           }
                           reader.close();
                       }catch (FileNotFoundException e){
                          e.printStackTrace();
                       }catch (IOException e){
                          e.printStackTrace();
                       }

                    }
                }
     }

}
