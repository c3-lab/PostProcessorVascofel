/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postprocessorvascofel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author asafe
 */
public class FileProcessor{
    
    private String fileName, filePath, fileExtension;
    
    private BufferedReader reader;
    private BufferedWriter writer;
    
    private final String COMMAND_TO_SEARCH = "M03";
    private final String COMMAND_TO_ADD = "\nG04 P200\nM05\nM03";
    
    public void loadFile(File file){
        filePath = file.getParent();
        fileName = file.getName();
        
        String[] fileNameSplited = fileName.split("\\.");
        if (fileNameSplited.length == 2) 
            fileExtension = fileNameSplited[1];
        else
            fileExtension = "";
    }
    
    public void processFile(String newFileName) throws FileNotFoundException, IOException{      
        reader = new BufferedReader(new FileReader(filePath + File.separator + fileName));
        writer = new BufferedWriter(new FileWriter(filePath + File.separator + newFileName + "." + fileExtension));

        String line;
        int counter = 0;
        while((line = reader.readLine()) != null){
            boolean containsCode = line.toUpperCase().contains(COMMAND_TO_SEARCH);
            if (containsCode) {
                counter++;

                if(counter > 1)
                    line = line.concat(COMMAND_TO_ADD);
            }
            writer.write(line + "\n");
        }

        reader.close();
        writer.close();        
    }
    
}
