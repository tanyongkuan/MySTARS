package model;
/**
 * Represents Students data in the System
 * @author Tan Yong Kuan, Chan Wei Zhe Shaw, Tan Xian Ren Clement
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class CourseRegistration{
	/**
	* registration status of the course registration
	*/
	private char registrationStatus;
	/**
	* student object
	*/
	private Student stud;
	/**
	* courseIndex object
	*/
	private CourseIndex courseIndex;
    
	/**
	 * get registration status of the course registration
	 * @return registrationStatus
	 */
	public char getRegistrationStatus(){
		return registrationStatus;
	}
	/**
	 * set registration status of the course registration
	 * @param registrationStatus
	 */
	public void setRegistrationStatus(char registrationStatus){
		this.registrationStatus = registrationStatus;
	}
	
	/**
	 * get student object
	 * @return stud
	 */
	public Student getStud(){
		return stud;
	}
	
	/**
	 * set student object
	 * @param stud
	 */
	public void setStud(Student stud){
		this.stud = stud;
	}
	
	/**
	 * get courseIndex object
	 * @return courseIndex
	 */
	public CourseIndex getCourseIndexObj(){
		return courseIndex;
	}
	
	/**
	 * set courseIndex object
	 * @param courseIndex
	 */
	public void setCourseIndexObj(CourseIndex courseIndex){
		this.courseIndex = courseIndex;
	}
	
	public CourseRegistration(Student stud,CourseIndex courseIndex,char registrationStatus){
		this.stud = stud;
		this.courseIndex = courseIndex;
		this.registrationStatus = registrationStatus;
	}
	
	public CourseRegistration(){
	}
	
}
