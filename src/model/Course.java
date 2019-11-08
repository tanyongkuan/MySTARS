package model;
/**
 * Represents all Courses 
 * @author Kim Mong Qi Yvette
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class Course {
	/**
	* code of the course
	*/
	private String courseCode;
	/**
	* name of the course
	*/
	private String courseName;
	/**
	* type of the course
	*/
	private char courseType;
	/**
	* school the course is under
	*/
	private School school;

	/**
    * Gets the code of the course
    * @return courseCode
    */
	public String getCourseCode(){
		return courseCode;
	}
	
	/**
     * Set the code of the course
     * @param courseCode
     */
	public void setCourseCode(String courseCode){
		this.courseCode = courseCode;
	}
	/**
    * Gets the name of the course
	* @return courseName
	*/
	public String getCourseName(){
		return courseName;
	}
	/**
     * Set the name of the course
     * @param courseName
     */
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	/**
	* Gets the type of the course
	* @return courseType
	*/
	public char getCourseType(){
		return courseType;
	}
	/**
     * Set the type of the course
     * @param courseType
     */
	public void setCourseType(char courseType){
		this.courseType = courseType;
	}
	/**
	* Gets the school the course is under
	* @return school
	*/
	public School getSchool(){
		return school;
	}
	/**
     * Set the school of the course
     * @param school
     */
	public void setSchool(School school){
		this.school = school;
	}
	
	public Course(){
	}
	
	public Course(String courseCode,String courseName,char courseType){
		this.courseCode=courseCode;
		this.courseName = courseName;
		this.courseType = courseType;
	}
	
	public Course(School school,String courseCode,String courseName,char courseType){
		this.school = school;
		this.courseCode=courseCode;
		this.courseName = courseName;
		this.courseType = courseType;
	}
	
}
