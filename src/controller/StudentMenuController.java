package controller;
import java.util.*;

import model.*;

/**
 * Controller that contains methods that student can do 
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-06
 */
public class StudentMenuController {
	/**
	 * CourseRegistration object
	 */
	private CourseRegistration reg;
	/**
	 * Course object
	 */
	private Course course;
	/**
	 * CourseIndex object
	 */
	private CourseIndex courseIndexObj;
	/**
	 * CourseController object
	 */
	private CourseController courseController = new CourseController();
	/**
	 * StudentController object
	 */
	private StudentController studentController = new StudentController();

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
	 * Check whether the user can access the system
	 * @param currentAccess current date and time upon login
	 * @return true if the user can access the system else false
	 */
	public boolean checkAccessTime(Date currentAccess){
		if(!appSetController.checkAccessTime(currentAccess).equals("")){
			System.out.println("You cant access at this timing. System time : " +appSetController.checkAccessTime(currentAccess).replace(',', '-'));
			return false;
		}
		return true;
	}
	
	/**
	 * Change the notification choice of the student
	 * @param stud Student object
	 * @param notificationChoice notification choice to be changed
	 */
	public void changeNotification(Student stud, char notificationChoice){
		studentController.updateNotification(stud,notificationChoice);
		System.out.println("You have successfully change your notification settings");
	}
	
	/**
	 * Change the phone number of the student
	 * @param stud Student object
	 * @param phoneNo phone number to be changed
	 */
	public void changeStudentHandphoneNumber(Student stud, String phoneNo){
		studentController.updateStudentHandphoneNumber(stud,phoneNo);
		System.out.println("Phone Number has been updated.");
	}
	/**
	 * Add the course requested by the student
	 * @param stud Student object
	 * @param courseIndex course index to be added
	 */
	public void addCourse(Student stud,int courseIndex){
		courseIndexObj = new CourseIndex();
		courseIndexObj.setCourseIndex(courseIndex);
		
		if(courseIndexController.checkDuplicateIndex(courseIndexObj)){
			reg = new CourseRegistration(stud,courseIndexObj,' ');
			if(courseRegistrationController.checkExistingCourseRegistration(reg)){
                System.out.println("You have already registered for the course.");
			}
			else{
				if(!courseRegistrationController.checkCourseClash(stud,courseIndex,0)){
					System.out.println(courseRegistrationController.addCourse(reg));
				}
				else{
					System.out.println("Course index cannot add due to timetable clash");
				}
			}
		}
		else{
			System.out.println("Course index is not in the record");
		}
	}
	
	/**
	 * Drop the course requested by the student
	 * @param stud Student object
	 * @param courseIndex course index to be drop
	 */
	public void dropCourse(Student stud,int courseIndex){
		courseIndexObj = new CourseIndex();
		courseIndexObj.setCourseIndex(courseIndex);
		
		if(courseIndexController.checkDuplicateIndex(courseIndexObj)){
			reg = new CourseRegistration(stud,courseIndexObj,' ');
			if(courseRegistrationController.checkExistingCourseRegistration(reg)){
				System.out.println(courseRegistrationController.dropCourse(reg));
			}
			else{
                System.out.println("You have not registered for the course.");
			}
		}
		else{
			System.out.println("Course index is not in the record");
		}
	}
	
	/**
	 * Print the course registered by the student
	 * @param stud Student object
	 */
	public void printCourseRegistered(Student stud){
		reg = new CourseRegistration();
		reg.setStud(stud);
		if(courseRegistrationController.checkExistingStudentCourseRegistration(reg)){
			courseRegistrationController.printCourseRegistered(stud);
		}
		else{
			System.out.println("You have not registered for any course");
		}
	}
	
	/**
	 * Check the vacancies available based on the course code
	 * @param courseCode code of the course
	 */
	public void checkVacanciesAvailable(String courseCode){
		course = new Course();
		course.setCourseCode(courseCode);
		
		if(courseController.checkExistingCourse(course)){
	        ArrayList<CourseIndex> courseIndexList = courseIndexController.retrieveAllCourseIndex(courseCode);
	        System.out.println("Course code : " +courseCode);
	        String leftAlignFormat = "| %-7s | %-7s | %-7s | %-9s |\n";
	        System.out.format("+---------+---------+---------+-----------+\n");
	        System.out.format("|  Index  |TotalSize| Vacancy | Wait List |\n");
	        System.out.format("+---------+---------+---------+-----------+\n");
	        
	        for(int i = 0; i<courseIndexList.size(); i++){
	            System.out.format(leftAlignFormat,courseIndexList.get(i).getCourseIndex(),courseIndexList.get(i).getVacancy(),
	            		courseIndexList.get(i).getAvailableVacancy(),courseIndexList.get(i).getWaitList());
	        }
	        System.out.format("+---------+---------+---------+-----------+\n");
		}
		else{
			System.out.println("Invalid course code entered.");
		}
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
	 * Display all the index based on the course code
	 * @param courseCode code of the course
	 */
	public void getAllCourseIndexDetails(String courseCode){
        ArrayList<CourseIndex> courseIndexList = courseIndexController.retrieveAllCourseIndex(courseCode);
        System.out.println("Course code : " +courseCode);
        String leftAlignFormat = "| %-7s | %-5s | %-8s | %-4s | %-17s | %-4s | %-17s | %-4s | %-17s | %-4s | %-17s | %-7s | %-9s |\n";
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+---------+-----------+\n");
        System.out.format("|Index    | Course |Index Name|Day(L)|Time(L)            |Day(L)|Time(L)            |Day(P)|Time(P)            |Day(T)|Time(T)            | Vacancy | Wait List |\n");
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+---------+-----------+\n");
        
        for(int i = 0; i<courseIndexList.size(); i++){
            System.out.format(leftAlignFormat,courseIndexList.get(i).getCourseIndex(),courseIndexList.get(i).getCourse().getCourseCode(),
                    courseIndexList.get(i).getCourseIndexName(), courseIndexList.get(i).getCourseLectureFirstDay(), courseIndexList.get(i).getCourseLectureFirstTime()
                    , courseIndexList.get(i).getCourseLectureSecondDay(), courseIndexList.get(i).getCourseLectureSecondTime(), courseIndexList.get(i).getCourseLabDay(),
                    courseIndexList.get(i).getCourseLabTime(),courseIndexList.get(i).getCourseTutorialDay(),courseIndexList.get(i).getCourseTutorialTime(),
                    courseIndexList.get(i).getAvailableVacancy(),courseIndexList.get(i).getWaitList());
        }
        System.out.format("+---------+--------+----------+------+-------------------+------+-------------------+------+-------------------+------+-------------------+---------+-----------+\n");
    }
	
	/**
	 * Switch to another index with the registered index
	 * @param stud Student object
	 * @param courseIndex index to be switched
	 */
	public void changeIndexNoOfCourse(Student stud,int courseIndex){
		courseIndexObj = new CourseIndex();
		courseIndexObj.setCourseIndex(courseIndex);
		CourseIndex courseIndexObj2;
		int changeIndex;
		
		if(courseIndexController.checkDuplicateIndex(courseIndexObj)){
			reg = new CourseRegistration(stud,courseIndexObj,' ');
			if(courseRegistrationController.checkExistingCourseRegistration(reg)){
				//Need check whether same course and valid
				do{
					System.out.println("Enter index no to change course: " );
					Scanner sc = new Scanner(System.in);
					changeIndex = sc.nextInt();
					courseIndexObj2 = new CourseIndex();
					courseIndexObj2.setCourseIndex(changeIndex);
					
					if(!courseIndexController.checkDuplicateIndex(courseIndexObj2)){
						System.out.println("Course index is not in the record");
					}
				}while(!courseIndexController.checkDuplicateIndex(courseIndexObj2));
				
				if(courseIndexController.checkMatchingCourseIndex(courseIndex, changeIndex)){
					if(!courseRegistrationController.checkCourseClash(stud,changeIndex,courseIndex)){
						courseRegistrationController.dropCourse(reg);
						reg = new CourseRegistration(stud,courseIndexObj2,' ');
						courseRegistrationController.addCourse(reg);
						System.out.println("You have successfully change to index " +changeIndex);
					}
					else{
						System.out.println("Course index cannot change due to timetable clash");
					}
				}
				else{
					System.out.println("Course code of both index does not match.");
				}
			}
			else{
                System.out.println("You have not registered for the course");
			}
		}
		else{
			System.out.println("Course index is not in the record");
		}
	}
	
	/**
	 * Swop index with another student
	 * @param courseIndex index registered
	 * @param userId student ID
	 * @param swopCourseIndex student index to be swopped
	 * @param swopUserId student ID to be swopped
	 */
	public void swopIndexNoWithStudent(int courseIndex, String userId, int swopCourseIndex, String swopUserId){
		if(!userId.equals(swopUserId)){
			if(courseIndexController.checkMatchingCourseIndex(courseIndex, swopCourseIndex)){
				if(!courseRegistrationController.checkCourseClash(new Student(userId,"",' '), swopCourseIndex,courseIndex) && !courseRegistrationController.checkCourseClash(new Student(swopUserId,"",' '), courseIndex,swopCourseIndex)){
					courseRegistrationController.swopIndexNoWithStudent(courseIndex, userId, swopCourseIndex, swopUserId);
				}
				else{
					System.out.println("Course index cannot change due to timetable clash");
				}
			}
			else{
				System.out.println("Course code of both index does not match.");
			}
		}
		else{
		    System.out.println("Can not swop index with yourself.");
		}
	}
}
