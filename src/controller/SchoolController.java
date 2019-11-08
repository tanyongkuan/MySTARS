package controller;
import java.util.*;

import database.Database;
import model.School;

/**
Controller that contains methods that uses school entity objects and connect to database
@author Tan Yong Kuan
@version 1.0
@since 2017-04-12
*/
public class SchoolController {
	/**
	 * a list of school from the data file
	 */
	ArrayList<String>schoolList;
	
	public SchoolController(){
		schoolList = Database.fileRead("school.csv");
	}
	
	/**
	 * Check whether the school exist using the school code
	 * @return true if there's a school with the school code
	 */
	public boolean checkExistingSchool(School school){
        for(int i =0; i<schoolList.size()&& schoolList.get(i)!=null;i++){
            if((schoolList.get(i).split(",")[0].equals(school.getSchoolCode())))
            {
                return true;
            }
        }
        return false;
    }
}
