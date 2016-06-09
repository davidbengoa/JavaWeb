/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaio;
import java.io.*;
import java.util.Calendar;
import java.sql.*;
/**
 *
 * @author David
 */
public class ReadWriteTextFile {
    String curFile;

    public ReadWriteTextFile(String curFile) {
        this.curFile = curFile;
    }
    
    public String ReadFile(){
        String wholeText = "";
        try{
            String line = "";
            FileReader fr = new FileReader(curFile);
            BufferedReader br = new BufferedReader(fr);
            
            while((line = br.readLine()) != null){
                wholeText += line;
            }
            br.close();
        }
        catch(FileNotFoundException ex){
            //System.out.println("Unable to open file " + curFile);
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return wholeText;
    }
    
    public void WriteFile(String input, boolean append, boolean addTimeStamp){
        try{
            FileWriter fw = new FileWriter(curFile, append);
            BufferedWriter bw = new BufferedWriter(fw);
            if(addTimeStamp){
                java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                bw.write(currentTimeStamp.toString() + " - ");
            }
            bw.write(input);
            bw.newLine();
            bw.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
