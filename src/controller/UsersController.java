package controller;

import java.util.ArrayList;

import database.Database;
import model.Users;
import utility.StarsSystem;

/**
Controller that contains methods that uses userlist entity objects and connect to database
@author Tan Yong Kuan
@version 1.0
@since 2017-04-12
*/
public class UsersController {
	/**
	 * A list of users from the datafile
	 */
	ArrayList<String> data;
	public UsersController(){
		data = Database.fileRead("users.csv");
	}
	/**
	 * Check whether username and password is correct
	 * @return user type if sign in success
	 */
	public char signIn(Users user){
		ArrayList<Users> userList = new ArrayList<Users>();
		
		for(int i=0; i<data.size() && data.get(i)!=null; i++){
			String[] singledata = data.get(i).split(",",8);
			userList.add(new Users(singledata[0],singledata[1],singledata[2].charAt(0)));
		}
		
        String passwordToHash = StarsSystem.convertToHash(user.getPassword());
        
        for(int i=0;i<userList.size();i++){
        	if(passwordToHash.equals(userList.get(i).getPassword()) && user.getUserId().equals(userList.get(i).getUserId())){
        		return userList.get(i).getUserType();
        	}
        }
        
        return ' ';
	}
}
