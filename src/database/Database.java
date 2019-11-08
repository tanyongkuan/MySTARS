package database;
import java.io.*;
import java.util.*;

/**
    Contains general file reading and writing for the whole application to use
    @author Tan Yong Kuan
    @version 1.0
    @since 2017-04-12
 */

public class Database {
    /**
    * Return data in list of strings based on the given file name.
    * @param filename File Name of the data file
    */
	public static ArrayList<String> fileRead(String filename) 
	{
		ArrayList<String> output = new ArrayList<String>();
		
		try
		{
			FileReader reader=new FileReader("data/"+filename);
			BufferedReader bReader=new BufferedReader(reader);
			String readIn = bReader.readLine();
			while(readIn!=null){
				output.add(readIn);
				readIn = bReader.readLine();
			}
			bReader.close();
		}
		catch (IOException e)
		{
			System.out.println("Cant read file due to file been opened!");
		}
		
		return output;
	}
	
	//Takes in filename : e.g. Users.csv
	//Takes in ArrayList<String> : each row consists of one line e.g. admin,password,A to insert to csv
	//Return true if update success, return false if update fail
	 /**
     * Updates data based on the given file name and the list of data in string
     * @param filename File Name of the data file
     * @param data Data to be inserted into the data file
     */
	public static boolean fileWrite(String filename,ArrayList<String> data){
		File file = new File("data/"+filename);
		try{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(writer);
			
			for(int i=0; i<data.size()&& data.get(i)!=null;i++){
				bWriter.write(data.get(i));
				bWriter.newLine();
			}
			bWriter.close();
		}
		catch(IOException e){
			System.out.println("Cant write file due to file been opened!!");
			return false;
		}
		
		return true;
	}
	
}
