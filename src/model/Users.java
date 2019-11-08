package model;

/**
 *  
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class Users {
    /**
    * ID of the user(admin and student)
    */
	private String userId;
	/**
     * Password of the user
     */
	private String password;
	/**
     * User type of the user
     */
	private char userType;
	/**
     * School object
     */
	private School school;
	
	 /**
     * Gets the ID of the user
     * @return user ID
     */
	public String getUserId(){
		return userId;
	}
	
	/**
     * Set the ID of the user
     * @param userId
     */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
     * Gets the password of the user
     * @return password
     */
	public String getPassword(){
		return password;
	}
	
	/**
     * Set the password of the user
     * @param password
     */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
     * Gets the user type of the user
     * @return userType
     */
	public char getUserType(){
		return userType;
	}
	
	/**
     * Set the user type of the user
     * @param userType
     */
	public void setUserType(char userType){
		this.userType = userType;
	}
	
	/**
     * Gets the school object of the user
     * @return school object
     */
	public School getSchool(){
		return school;
	}
	
	/**
     * Set the school object
     * @param school
     */
	public void setSchool(School school){
		this.school = school;
	}
	
	public Users(){}
	
	public Users(String userId, String password, char userType){
		this.userId = userId;
		this.password = password;
		this.userType = userType;
	}
	
	public Users(String userId, String password,char userType,School school){
		this.userId = userId;
		this.password = password;
		this.userType = userType;
		this.school = school;
	}
	
	
}
