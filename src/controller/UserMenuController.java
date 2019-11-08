package controller;
import model.*;

/**
 * Controller that contains methods that user can do
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-06
 *
 */
public class UserMenuController {
	/**
	 * User object
	 */
	private Users user;
	/**
	 * UsersController object
	 */
	private UsersController usersController = new UsersController();

	
	/**
	 * Allows the user to sign in into the system
	 * @param userId userId of the user
	 * @param password password of the user
	 * @return empty if wrong username or password else return userType
	 */
	public char signIn(String userId,String password){
		user = new Users(userId,password,' ');
		char userType = usersController.signIn(user);
		
		if(userType == ' '){
			System.out.println("Incorrect username or password.");
			return ' ';
		}
		
		return userType;
	}
}
