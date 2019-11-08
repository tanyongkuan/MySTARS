package controller;

import java.util.ArrayList;

import database.Database;
import model.School;
import model.Student;
import utility.StarsSystem;

/**
Controller that contains methods that uses student entity objects and connect to database
@author Tan Yong Kuan
@version 1.0
@since 2017-04-12
*/
public class StudentController {
	/**
	 * a list of students from the data file
	 */
	private ArrayList<String>studentList;
	
	public StudentController(){
		studentList =  Database.fileRead("users.csv");
	}
	//ADMIN METHODS
	/**
    * Create new student 
    * @return true if student created successfully else false
    */
	public boolean createStudent(Student stud){
		studentList.add(stud.getUserId() + "," + StarsSystem.convertToHash(stud.getPassword()) 
		+ "," + stud.getUserType() + "," +(stud.getStudentName()) + "," + (stud.getStudentGender())
		+ "," + (stud.getStudentYear()) + "," + (stud.getSchool().getSchoolCode()) +"," + (stud.getStudentProgramme())
		+ "," + (stud.getStudentNationality()) + "," + (stud.getStudentNotificationMode()) + "," 
		+ (stud.getStudentEmail()) + "," + (stud.getStudentPhoneNumber()));
		
		if(Database.fileWrite("users.csv", studentList)){
			return true;
		}
		return false;
	}
	
	/**
	 * Get the student detail based on the userId
	 * @param userId userId of the student
	 * @return student object of the student detail
	 */
	public Student retrieveStudentDetails(String userId){
		School school = new School();
	    for(int i=0; i<studentList.size() && studentList.get(i)!=null; i++){
            if(studentList.get(i).split(",")[0].equals(userId)){
                String[] studentdata = studentList.get(i).split(",");
                school.setSchoolCode(studentdata[6]);
                school.setSchoolName(studentdata[7]);
                return new Student(studentdata[0], studentdata[1],
                        studentdata[2].charAt(0), school, studentdata[3],
                        studentdata[4].charAt(0), Integer.parseInt(studentdata[5]),
                        studentdata[8],studentdata[7],studentdata[10],studentdata[11],studentdata[9].charAt(0));
            }
        }
	    return new Student();
	}
	
	/**
	 * Get all the students' details
	 * @return list of student with the details
	 */
	public ArrayList<Student> retrieveAllStudentDetails(){
		School school = new School();
		ArrayList<Student>studentArrayList = new ArrayList<Student>();
		for(int i=0; i<studentList.size() && studentList.get(i)!=null; i++){
			String[] studentdata = studentList.get(i).split(",");
			if(studentList.get(i).split(",")[2].equals("S")){
				school.setSchoolCode(studentdata[6]);
                school.setSchoolName(studentdata[7]);
                studentArrayList.add(new Student(studentdata[0], studentdata[1],
            		studentdata[2].charAt(0),school, studentdata[3],
                    studentdata[4].charAt(0),Integer.parseInt(studentdata[5]),
                    studentdata[8],studentdata[7],studentdata[10],studentdata[11],studentdata[9].charAt(0)));
			}
		}
		return studentArrayList;
	}
	/**
	 * Check whether the student exists in the data file based on the userId
	 * @return true if student exists else false
	 */
	public boolean checkExistingStudent(Student stud){
		for(int i=0; i<studentList.size() && studentList.get(i)!=null; i++){
			if(studentList.get(i).split(",")[0].equals(stud.getUserId())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Update notification of the student
	 * @param notificationChoice
	 */
	public void updateNotification(Student stud,char notificationChoice)
	{
		ArrayList<Student>userDb=new ArrayList<Student>();
		ArrayList<String>objUser=Database.fileRead("users.csv");
		Student objStudent=null;
		School objSchool=null;
		int i=0,store=0;
		String combineStr="";
		for(i=0;i<objUser.size();i++)
		{
			String [] tempUser=objUser.get(i).split(",",12);
			if(!(tempUser[0].equals("admin")))
			{
				userDb.add(new Student(tempUser[0],tempUser[1],tempUser[2].charAt(0),objSchool,tempUser[3],tempUser[4].charAt(0),
					Integer.parseInt(tempUser[5]),tempUser[8],tempUser[7],tempUser[10],tempUser[11],tempUser[9].charAt(0)));
				if(tempUser[0].equals(stud.getUserId()))
				{
					objStudent=userDb.get(i);
					objSchool = new School();
					objSchool.setSchoolCode(tempUser[6]);

					store=i;
				}
			}		
		}
		combineStr=objStudent.getUserId()+","+objStudent.getPassword()+","+objStudent.getUserType()+","+objStudent.getStudentName()+","+
				objStudent.getStudentGender()+","+objStudent.getStudentYear()+","+objSchool.getSchoolCode()+","+objStudent.getStudentProgramme()+","+objStudent.getStudentNationality()
				+","+notificationChoice+","+objStudent.getStudentEmail()+","+objStudent.getStudentPhoneNumber();

		objUser.remove(store);
		objUser.add(store, combineStr);
		Database.fileWrite("users.csv",objUser);
	}
	/**
	 * Update student's handphone number given the phoneNo and the student object
	 * @param phoneNo handphone number of the student
	 */
	public void updateStudentHandphoneNumber(Student stud,String phoneNo){
		ArrayList<String> studRead = Database.fileRead("users.csv");
		ArrayList<Student> studList = new ArrayList<Student>();
		int temp = -1;
		Student studObj = null;
		School objSchool = null;
		for (int i=0; i<studRead.size() && studRead.get(i)!=null; i++){
			String[] studReadList = studRead.get(i).split(",", 12);
			objSchool = new School();
			objSchool.setSchoolCode(studReadList[6]);
			studList.add(new Student(studReadList[0], studReadList[1], studReadList[2].charAt(0), objSchool, studReadList[3], studReadList[4].charAt(0), Integer.parseInt(studReadList[5]), studReadList[8], studReadList[7], studReadList[10], studReadList[11], studReadList[9].charAt(0) ));
			if (stud.getUserId().equals(studReadList[0])){
				temp = i;
				studReadList[11] = phoneNo;
				System.out.println(temp);
				System.out.println(studReadList[11]);
				break;
			}
		}
		
		if (temp != -1){
			
			for (int i=0; i<studList.size() && studList.get(i)!=null; i++){
				studObj = studList.get(i);
				if (temp == i){
					String test = studObj.getUserId() + "," +  studObj.getPassword() + "," + studObj.getUserType() + "," + studObj.getStudentName() + "," +  studObj.getStudentGender() + "," + Integer.toString(studObj.getStudentYear()) + "," + objSchool.getSchoolCode() + "," + studObj.getStudentProgramme() + "," + studObj.getStudentNationality() + "," + studObj.getStudentNotificationMode() + "," + studObj.getStudentEmail() + "," + phoneNo;
					studRead.remove(i);
					studRead.add(i, test);
				}
			}
		}	
		Database.fileWrite("users.csv", studRead);
	}
}
