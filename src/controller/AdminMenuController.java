package controller;
import java.util.*;
import java.text.*;
import model.*;

/**
 * Controller that contains methods that admin can do
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-06
 *
 */
public class AdminMenuController {
	/**
	 * Course object
	 */
	private Course course;
	/**
	 * CourseIndex object
	 */
	private CourseIndex courseIndexObj;
	/**
	 * Student object
	 */
	private Student stud;
	/**
	 * AppSettings object
	 */
	private AppSettings app;
	/**
	 * School object
	 */
	private School school;
	/**
	 * SchoolController object
	 */
	private SchoolController schoolController = new SchoolController();
	/**
	 * StudentController object
	 */
	private StudentController studentController = new StudentController();
	/**
	 * CourseController object
	 */
	private CourseController courseController = new CourseController();
	/**
	 * CourseIndexController object
	 */
	private CourseIndexController courseIndexController = new CourseIndexController();
	/**
	 * CourseRegistrationController object
	 */
	private CourseRegistrationController courseRegistrationController = new CourseRegistrationController();
	/**
	 * AppSettingsController object
	 */
	private AppSettingsController appSetController = new AppSettingsController();
	
	/**
	 * Change the access time of the system with the given input
	 * @param input the access time of the system
	 */
	public void changeAccessTime(String input){
		Date startAccess = null,endAccess=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		try {
			startAccess = sdf.parse(input.split("-",2)[0]);
			endAccess = sdf.parse(input.split("-",2)[1]);
			if(startAccess.before(endAccess)){
				app = new AppSettings();
				app.setStartAccess(startAccess);
				app.setEndAccess(endAccess);
				
				if(appSetController.changeAccessTime(app)){
					System.out.println("Student access period is updated successfully.");
				}
				else{
					System.out.println("Student access period is not updated successfully.");
				}
			}
			else
			{
				System.out.println("Start access time is after end access time!");
			}
		} 
		catch (ParseException e) {
			System.out.println("Incorrect date format, please re-enter");
		}
	}
	/**
	 * Check whether a school exists based on the schoolCode
	 * @param schoolCode code of the school
	 * @return true if the school exists, else false
	 */
	public boolean checkExistingSchool(String schoolCode){
	    school = new School();
	    school.setSchoolCode(schoolCode);
	    
	    boolean status = schoolController.checkExistingSchool(school);
	    
	    if(!status){
	        System.out.println("Please enter valid school code.");
	    }
	    return status;
	}
	
	/**
	 * Check whether a course index exists based on the index
	 * @param index course index
	 * @return true if the course index exists, else false
	 */
	public boolean checkValidCourseIndex(int index){
	    courseIndexObj = new CourseIndex();
	    courseIndexObj.setCourseIndex(index);
	    return courseIndexController.checkDuplicateIndex(courseIndexObj);
	}
	
	/**
	 * Check whether a course exists based on the course code
	 * @param courseCode course code
	 * @return true if the course exists, else false
	 */
	public boolean checkValidCourse(String courseCode){
	    course = new Course();
	    course.setCourseCode(courseCode);
	    
	    return courseController.checkExistingCourse(course);
	}
	/**
	 * Check whether a student exists based on the userId
	 * @param userId
	 * @return true if the student exists else false
	 */
	public boolean checkExistingStudent(String userId){
		stud = new Student();
		stud.setUserId(userId);
		return studentController.checkExistingStudent(stud);
	}
	
	/**
	 * Create the student based on the inputs given
	 * @param studentId id of the student
	 * @param studentName name of the student
	 * @param studentGender gender of the student
	 * @param studentYear year of the student
	 * @param schoolCode code of the school
	 * @param studentProgramme programme student enrolled in
	 * @param studentNationality nationality of the student
	 * @param studentEmail email address of the student
	 * @param studentPhoneNumber phone number of the student
	 * @param studentNotificationMode notification mode of the student
	 */
	public void createStudent(String studentId,String studentName,char studentGender,int studentYear,String schoolCode,String studentProgramme,String studentNationality,String studentEmail,String studentPhoneNumber,char studentNotificationMode){
		school = new School();
		school.setSchoolCode(schoolCode);
		stud = new Student(studentId, "password", 'S', school, studentName, studentGender, studentYear, studentProgramme, studentNationality,studentEmail,studentPhoneNumber,studentNotificationMode);
		
		if(studentController.checkExistingStudent(stud)){
			System.out.println("Student ID is already in the record");
		}
		
		else{
			if(studentController.createStudent(stud)){
				System.out.println("Student record created successfully");
				getAllStudentDetails();
		        System.out.println("");
			}
			else{
				System.out.println("Student record created unsuccessfully");
			}
		}
	}
	/**
	 * Create the course based on the input given
	 * @param schoolCode code of the school
	 * @param courseCode code of the course
	 * @param courseName name of the course
	 * @param courseType course type of the course
	 */
	public void createCourse(String schoolCode,String courseCode,String courseName,char courseType){
		school = new School();
		school.setSchoolCode(schoolCode);
		course = new Course(school,courseCode,courseName,courseType);
		
		if(courseController.checkExistingCourse(course)){
			System.out.println("Course code or course name is already in the record");
		}
		else{
			if(courseController.createCourse(course)){
				System.out.println("Course record created successfully");
				getAllCourseDetails();
		        System.out.println("");
			}
			else{
				System.out.println("Course record created unsuccessfully");
			}
		}
	}
	
	/**
	 * Create the course index based on the input
	 * @param courseCodes code of the course
	 * @param courseIndex index number of the course index
	 * @param courseType course type of the course
	 * @param courseIndexName name of the course index
	 * @param courseLectureFirstDay day of the first lecture
	 * @param courseLectureFirstTime time of the first lecture
	 * @param courseLectureSecondDay day of the second lecture
	 * @param courseLectureSecondTime time of the second lecture
	 * @param courseTutorialDay day of the tutorial
	 * @param courseTutorialTime time of the tutorial
	 * @param courseLabDay day of the lab
	 * @param courseLabTime time of the lab
	 * @param availableVacancy vacancy left of the course index
	 * @param courseVacancy total vacancy given by the course index
	 * @param waitList number of waiting list in the course index
	 */
	public void createCourseIndex(String courseCodes,int courseIndex,char courseType, String courseIndexName,
			String courseLectureFirstDay,String courseLectureFirstTime,String courseLectureSecondDay,String courseLectureSecondTime,
			String courseTutorialDay,String courseTutorialTime,String courseLabDay,String courseLabTime,int availableVacancy, int courseVacancy, int waitList){
		course = new Course();
		course.setCourseCode(courseCodes);
		course.setCourseType(courseType);
		courseIndexObj = new CourseIndex(course,courseIndex,courseIndexName,
				courseLectureFirstDay,courseLectureFirstTime,courseLectureSecondDay,courseLectureSecondTime,
				courseTutorialDay,courseTutorialTime,courseLabDay,courseLabTime,availableVacancy, courseVacancy,waitList);
		
		if(courseIndexController.checkDuplicateIndex(courseIndexObj)){
			System.out.println("Course Index is already in the record");
		}
		else{
			if(courseIndexController.createIndex(courseIndexObj)){
				System.out.println("Course index created successfully");
				getAllCourseIndexDetails(courseCodes);
		        System.out.println("");
			}
			else{
				System.out.println("Course index created unsuccessfully");
			}
		}
	}
	
	/**
	 * Update the course details based on the input given
	 * @param courseCode name of the course
	 * @param option option based on the menu
	 * @param input input to be changed
	 */
	public void changeCourse(String courseCode,int option,String input){
        courseController.updateCourse(courseCode, option, input);
        if(option == 0){
            courseIndexController.updateIndexCourseCode(courseCode,input);
            courseRegistrationController.updateCourseRegistration(courseCode,input);
        }
        System.out.println("Course has been updated.");
        System.out.println("");
    }
	
	/**
	 * Update the course index details based on the input given
	 * @param index index of the course index
	 * @param option option based on the menu
	 * @param input input to be changed
	 */
	public void changeCourseIndex(int index,int option,String input){
	    courseIndexController.updateIndex(index, option, input);
	    System.out.println("Course Index has been updated.");
	    System.out.println("");
	}
	
	/**
	 * Gets the course type based on the course code
	 * @param courseCode code of the course
	 * @return courseType type of the course
	 */
	public char getCourseType(String courseCode){
		course = new Course();
		course.setCourseCode(courseCode);
		//May need to check whether existing
		return courseController.checkCourseType(course);
	}
	
	/**
	 * Get all the student details from the datafile
	 */
	public void getAllStudentDetails(){
		ArrayList<Student>studentList = studentController.retrieveAllStudentDetails();
		
		if(studentList.size()!=0){
		    String leftAlignFormat = "| %-7s | %-15s | %-10s | %-25s|\n";
	        System.out.format("+-----------+-----------------+------------+--------------------------+\n");
	        System.out.format("|User Id    |       Name      |   Gender   |          Course          |\n");
	        System.out.format("+-----------+-----------------+------------+--------------------------+\n");
	        
			for(int i = 0; i<studentList.size(); i++){
			    String gender;
			    if(studentList.get(i).getStudentGender() == 'F'){
			        gender = "Female";
			    }
			    else{
			        gender = "Male";
			    }
				System.out.format(leftAlignFormat,studentList.get(i).getUserId(),studentList.get(i).getStudentName(),
				        gender,studentList.get(i).getStudentProgramme());
			}
			System.out.format("+-----------+-----------------+------------+--------------------------+\n");
		}
	}
	
	/**
	 * Displays all the courses with its index
	 */
	public void getAllCourseIndexDetails(){
	    ArrayList<CourseIndex> courseIndexList = courseIndexController.retrieveAllCourseIndex();
	    
	    String leftAlignFormat = "| %-7s | %-5s | %-8s | %-4s | %-17s | %-4s | %-17s | %-4s | %-17s | %-4s | %-17s |\n";
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+\n");
        System.out.format("|Index    | Course |Index Name|Day(L)|Time(L)            |Day(L)|Time(L)            |Day(P)|Time(P)            |Day(T)|Time(T)            |\n");
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+\n");
        
        for(int i = 0; i<courseIndexList.size(); i++){
            System.out.format(leftAlignFormat,courseIndexList.get(i).getCourseIndex(),courseIndexList.get(i).getCourse().getCourseCode(),
                    courseIndexList.get(i).getCourseIndexName(), courseIndexList.get(i).getCourseLectureFirstDay(), courseIndexList.get(i).getCourseLectureFirstTime()
                    , courseIndexList.get(i).getCourseLectureSecondDay(), courseIndexList.get(i).getCourseLectureSecondTime(), courseIndexList.get(i).getCourseLabDay(),
                    courseIndexList.get(i).getCourseLabTime(),courseIndexList.get(i).getCourseTutorialDay(),courseIndexList.get(i).getCourseTutorialTime());
        }
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+\n");
	}
	
	/**
	 * Display all the index based on the course code
	 * @param courseCode code of the course
	 */
	public void getAllCourseIndexDetails(String courseCode){
        ArrayList<CourseIndex> courseIndexList = courseIndexController.retrieveAllCourseIndex(courseCode);
        System.out.println("Course code : " +courseCode);
        String leftAlignFormat = "| %-7s | %-5s | %-8s | %-4s | %-17s | %-4s | %-17s | %-4s | %-17s | %-4s | %-17s |\n";
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+\n");
        System.out.format("|Index    | Course |Index Name|Day(L)|Time(L)            |Day(L)|Time(L)            |Day(P)|Time(P)            |Day(T)|Time(T)            |\n");
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+\n");
        
        for(int i = 0; i<courseIndexList.size(); i++){
            System.out.format(leftAlignFormat,courseIndexList.get(i).getCourseIndex(),courseIndexList.get(i).getCourse().getCourseCode(),
                    courseIndexList.get(i).getCourseIndexName(), courseIndexList.get(i).getCourseLectureFirstDay(), courseIndexList.get(i).getCourseLectureFirstTime()
                    , courseIndexList.get(i).getCourseLectureSecondDay(), courseIndexList.get(i).getCourseLectureSecondTime(), courseIndexList.get(i).getCourseLabDay(),
                    courseIndexList.get(i).getCourseLabTime(),courseIndexList.get(i).getCourseTutorialDay(),courseIndexList.get(i).getCourseTutorialTime());
        }
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+------------------+\n");
    }
	
	/**
	 * Displays all the courses
	 */
	public void getAllCourseDetails(){
	    ArrayList<Course>courseList = courseController.retrieveAllCourseDetails();
	    
	    String leftAlignFormat = "| %-9s | %-10s | %-21s | %-10s|\n";
        System.out.format("+-----------+------------+-----------------------+-----------|\n");
        System.out.format("|Course Code|   School   |      Course Name      |Course Type|\n");
        System.out.format("+-----------+------------+-----------------------+-----------|\n");
        
        for(int i =0; i<courseList.size();i++){
            System.out.format(leftAlignFormat, courseList.get(i).getCourseCode(),courseList.get(i).getSchool().getSchoolCode(),
                    courseList.get(i).getCourseName(),courseList.get(i).getCourseType());
        }
        System.out.format("+-----------+------------+-----------------------+-----------|\n");
        
	}
	
	/**
	 * Get the available vacancy of the index
	 * @param courseIndex index of the course
	 */
	public void checkAvailableSlot(int courseIndex){
		courseIndexObj = new CourseIndex();
		courseIndexObj.setCourseIndex(courseIndex);
		
		if(courseIndexController.checkDuplicateIndex(courseIndexObj)){
			if(courseIndexController.checkAvailableSlot(courseIndexObj) !=-1){
				System.out.println("Available Slots: " + courseIndexController.checkAvailableSlot(courseIndexObj));
			}
			else{
				System.out.println("No slots is entered for the course index.");
			}
		}
		else{
			System.out.println("Course Index is not in the record");
		}
		System.out.println("");
	}
	
	/**
	 * Print a list of student by course code or the course index
	 * @param byCourseIndex true if by course index, false if by course code
	 * @param courseInput the course index/course code
	 */
	public void printStudentList(boolean byCourseIndex,String courseInput){
		ArrayList<Student>studentList;
		
		if(byCourseIndex){
			courseIndexObj = new CourseIndex();
			courseIndexObj.setCourseIndex(Integer.parseInt(courseInput));
			course = new Course();
		}
		else{
			course = new Course(courseInput,"",' ');
			courseIndexObj = new CourseIndex();
		}
			
		if(courseIndexController.checkDuplicateIndex(courseIndexObj) || courseController.checkExistingCourse(course)){
			studentList = courseRegistrationController.retrieveStudentList(byCourseIndex, courseInput);
			
			System.out.println("List of students in course index " + courseInput);
			if(studentList.size()!=0){
			    String leftAlignFormat = "| %-7s | %-15s | %-10s | %-25s|\n";
		        System.out.format("+-----------+-----------------+------------+--------------------------+\n");
		        System.out.format("|User Id    |       Name      |   Gender   |          Course          |\n");
		        System.out.format("+-----------+-----------------+------------+--------------------------+\n");
		        
				for(int i = 0; i<studentList.size(); i++){
				    String gender;
				    if(studentList.get(i).getStudentGender() == 'F'){
				        gender = "Female";
				    }
				    else{
				        gender = "Male";
				    }
					System.out.format(leftAlignFormat,studentList.get(i).getUserId(),studentList.get(i).getStudentName(),
					        gender,studentList.get(i).getStudentProgramme());
				}
				System.out.format("+-----------+-----------------+------------+--------------------------+\n");
			}
			else{
				System.out.println("No student in the course");
			}
			System.out.println("");
		}
		else{
			System.out.println("Course Code is not in the record.");
		}
	}
}
