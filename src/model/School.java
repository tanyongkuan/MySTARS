package model;

/**
 *  
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class School {
	/**
	* school code such as SCSE
	*/
	private String schoolCode;
	/**
	* name of the school
	*/
	private String schoolName;
	
    /**
     * Gets the code of the school
     * @return schoolCode
     */
	public String getSchoolCode(){
		return schoolCode;
	}
	
	/**
     * Sets the code of the school
     * @param schoolCode
     */
	public void setSchoolCode(String schoolCode){
		this.schoolCode = schoolCode;
	}
	
	/**
     * Gets the name of the school
     * @return schoolName
     */
	public String getSchoolName(){
		return schoolName;
	}
	
	/**
     * Sets the name of the school
     * @param schoolName
     */
	public void setSchoolName(String schoolName){
		this.schoolName = schoolName;
	}
}
