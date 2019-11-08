package model;
/**
 * Represents Students data in the System
 * @author Kim Mong Qi Yvette, Chan Wei Zhe Shaw, Tan Xian Ren Clement
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class Student extends Users{
	/**
	* name of the student
	*/
	private String studentName;
	/**
	* gender of the student
	*/
	private char studentGender;
	/**
	* year of the student
	*/
	private int studentYear;
	/**
	* programme enrolled of the student
	*/
	private String studentProgramme;
	/**
	* nationality of the student
	*/
	private String studentNationality;
	/**
	* notification code of the student
	*/
	private char studentNotificationMode;
	/**
	* email of the student
	*/
	private String studentEmail;
	/**
	* phone number of the student
	*/
	private String studentPhoneNumber;
	/**
	* list of student
	*/
	
	public Student(){
	}
	
	public Student(String studentId, String password, char userType){
		super(studentId,password,userType);
	}
	
	public Student(String studentId, String password, char userType,School school, String studentName, char studentGender, int studentYear, String studentNationality, String studentProgramme,String studentEmail,String studentPhoneNumber,char studentNotificationMode){
		super(studentId,password,userType,school);
		this.studentName = studentName;
		this.studentGender = studentGender;
		this.studentYear = studentYear;
		this.studentProgramme = studentProgramme;
		this.studentNationality = studentNationality;
		this.studentEmail = studentEmail;
		this.studentPhoneNumber = studentPhoneNumber;
		this.studentNotificationMode = studentNotificationMode;
	}
	
	/**
	* Gets the name of the student
	* @return studentName
	*/
	public String getStudentName(){
		return studentName;
	}
	
	/**
	* Sets the name of the student
	* @param studentName
	*/
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}
	/**
	* Gets the gender of the student
	* @return studentGender
	*/
	public char getStudentGender(){
		return studentGender;
	}
	/**
	* Sets the gender of the student
	* @param studentGender
	*/
	public void setStudentGender(char studentGender){
		this.studentGender = studentGender;
	}
	/**
	* Gets the year of the student
	* @return studentYear
	*/
	public int getStudentYear(){
		return studentYear;
	}
	/**
	* Sets the year of the student
	* @param studentYear
	*/
	public void setStudentYear(int studentYear){
		this.studentYear = studentYear;
	}
	/**
	* Gets the programme of the student
	* @return studentProgramme
	*/
	public String getStudentProgramme(){
		return studentProgramme;
	}
	/**
	* Sets the programme of the student
	* @param studentProgramme
	*/
	public void setStudentProgramme(String studentProgramme){
		this.studentProgramme = studentProgramme;
	}
	/**
	* Gets the nationality of the student
	* @return studentNationality
	*/
	public String getStudentNationality(){
		return studentNationality;
	}
	/**
	* Sets the nationality of the student
	* @param studentNationality
	*/
	public void setStudentNationality(String studentNationality){
		this.studentNationality = studentNationality;
	}
	/**
	* Gets the notification mode of the student
	* @return studentNotificationMode
	*/
	public char getStudentNotificationMode(){
		return studentNotificationMode;
	}
	/**
	* Sets the notification mode of the student
	* @param studentNotificationMode
	*/
	public void setStudentNotificationMode(char studentNotificationMode){
		this.studentNotificationMode = studentNotificationMode;
	}
	/**
	* Gets the email of the student
	* @return studentEmail
	*/
	public String getStudentEmail(){
		return studentEmail;
	}
	/**
	* Sets the email of the student
	* @param studentEmail
	*/
	public void setStudentEmail(String studentEmail){
		this.studentEmail = studentEmail;
	}
	/**
	* Gets the handphone number of the student
	* @return studentPhoneNumber
	*/
	public String getStudentPhoneNumber(){
		return studentPhoneNumber;
	}
	/**
	* Sets the handphone number of the student
	* @param studentPhoneNumber
	*/
	public void setStudentPhoneNumber(String studentPhoneNumber){
		this.studentPhoneNumber = studentPhoneNumber;
	}
	
}
