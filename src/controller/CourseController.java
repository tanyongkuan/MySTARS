package controller;

import java.util.ArrayList;

import database.Database;
import model.Course;
import model.School;
/**
Controller that contains methods that uses Course entity objects and connect to database
@author Tan Yong Kuan
@version 1.0
@since 2017-04-12
*/
public class CourseController {
	/**
	 * a list of course from the datafile
	 */
	ArrayList<String>courseList;
	
	public CourseController(){
		courseList = Database.fileRead("courses.csv");
	}
	
	/**
	 * Get the course type when course code is given
	 * @return empty if no such course, else return course type
	 */
	public char checkCourseType(Course course){
		for(int i =0; i<courseList.size()&& courseList.get(i)!=null;i++){
			if((courseList.get(i).split(",")[0].equals(course.getCourseCode())))
			{
				return courseList.get(i).split(",")[3].charAt(0);
			}
		}
		return ' ';
	}
	
	/**
	 * Check whether course existed based on the course code and course name
	 * @return true if there's such a course else false
	 */
	public boolean checkExistingCourse(Course course){
		for(int i =0; i<courseList.size()&& courseList.get(i)!=null;i++){
			if((courseList.get(i).split(",")[0].equals(course.getCourseCode())) || (courseList.get(i).split(",")[2].equals(course.getCourseName())))
			{
				return true;
			}
		}
		return false;
	}
	
	//ADMIN METHODS
    /**
     * Create new course 
     * @return true if created successfully else false
     */
	public boolean createCourse(Course course){
		courseList.add((course.getCourseCode()) + "," + (course.getSchool().getSchoolCode())+ "," + (course.getCourseName())+ "," + (course.getCourseType()));		
		if(Database.fileWrite("courses.csv",courseList)){
			return true;
		}
		return false;
	}
	
	/**
	 * Get course details based on the courseCode
	 * @param courseCode
	 * @return Course object with the course details
	 */
	public Course retrieveCourseDetails(String courseCode){
	    for(int i=0;i<courseList.size();i++){
	        if(courseList.get(i).split(",")[0].equals(courseCode)){
	            return new Course(courseList.get(i).split(",")[0],courseList.get(i).split(",")[2],courseList.get(i).split(",")[3].charAt(0));
	        }
	    }
	    return new Course();
	}
	
	
	/**
	 * Get all the course details
	 * @return courseArrayList an array with courses and its details
	 */
	public ArrayList<Course> retrieveAllCourseDetails(){
	    ArrayList<Course>courseArrayList = new ArrayList<Course>();
	    
        for(int i=0;i<courseList.size();i++){
    	   School school = new School();
    	   school.setSchoolCode(courseList.get(i).split(",")[1]);
           courseArrayList.add(new Course(school,courseList.get(i).split(",")[0],courseList.get(i).split(",")[2],courseList.get(i).split(",")[3].charAt(0)));     
        }
        return courseArrayList;
    }
	
	/**
	 * Update the course based on the courseCode and the attribute to change		
	 * @param courseCode
	 * @param indexNo attribute to change
	 * @param input data to change
	 */
	public void updateCourse(String courseCode, int indexNo, String input){
        for(int i=0;i<courseList.size();i++){
            String[] courseDetail = courseList.get(i).split(",");
            if(courseCode.equals(courseDetail[0])){
                courseDetail[indexNo] = input;
                courseList.remove(i);
                
                courseList.add(i,courseDetail[0]+","+courseDetail[1]+","+courseDetail[2]+","+courseDetail[3]);
            }
        }
        Database.fileWrite("courses.csv", courseList);
    }
	
}
