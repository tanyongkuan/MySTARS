package controller;

import java.text.*;
import java.util.*;
import database.Database;
import model.AppSettings;
/**
 * Controller that contains methods that uses AppSettings entity objects and connect to database
 * @author Yong Kuan
 * @version 1.0
 * @since 2017-04-06
 */
public class AppSettingsController {
	/**
	 * A list that stores data from the data file
	 */
	ArrayList<String>appSetList;
	
	public AppSettingsController(){
		appSetList = Database.fileRead("appSettings.csv");
	}
	/**
	 * Check whether the application is open to use when the student logins
	 * @param currentAccess time of the login
	 * @return true if currentAccess time is within the application access time
	 */
	public String checkAccessTime(Date currentAccess){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date startAccess = null,endAccess=null;
		
		try {
			startAccess = sdf.parse(appSetList.get(0).split(",",2)[0]);
			endAccess = sdf.parse(appSetList.get(0).split(",",2)[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(startAccess.before(currentAccess) && endAccess.after(currentAccess))
			return "";
		else
			return appSetList.get(0);
	}
	
	/**
	 * Update access time of the application
	 * @return true if the system has updated the application settings
	 */
	public boolean changeAccessTime(AppSettings app){
		ArrayList<String>updateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		updateList.add(sdf.format(app.getStartAccess()) + "," + sdf.format(app.getEndAccess()));
		
		if(Database.fileWrite("appSettings.csv",updateList)){
			return true;
		}
		return false;
	}
}
