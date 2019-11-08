package controller;

import java.util.*;
import database.Database;
import model.*;
/**
Controller that contains methods that uses Course Index entity objects and connect to database
@author Tan Yong Kuan
@version 1.0
@since 2017-04-12
*/
public class CourseIndexController {
	/**
	 * a list of course index from the data file
	 */
	private ArrayList<String>courseIndexList;
	
	public CourseIndexController(){
		courseIndexList = Database.fileRead("courseIndex.csv");
	}

	/**
	 * Check existing index from the data file based on the course index
	 * @param courseIndexObj
	 * @return true if it exists else false
	 */
	public boolean checkDuplicateIndex(CourseIndex courseIndexObj){
		for(int i =0; i<courseIndexList.size()&& courseIndexList.get(i)!=null;i++){	
			if(Integer.parseInt(courseIndexList.get(i).split(",")[0]) == courseIndexObj.getCourseIndex())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check whether both index has the same course code
	 * @param index
	 * @param swopIndex
	 * @return true if matching else false
	 */
	public boolean checkMatchingCourseIndex(int index,int swopIndex){
		String courseCode = "";
		String swopCourseCode = "";
		
		for(int i =0; i<courseIndexList.size()&& courseIndexList.get(i)!=null;i++){	
			if(Integer.parseInt(courseIndexList.get(i).split(",")[0]) == index)
			{
				courseCode = courseIndexList.get(i).split(",")[1];
			}
			if(Integer.parseInt(courseIndexList.get(i).split(",")[0]) == swopIndex)
			{
				swopCourseCode = courseIndexList.get(i).split(",")[1];
			}
		}
		return courseCode.equals(swopCourseCode);
	}
	
	/**
	 * Create the index with the index details
	 * @param courseIndexObj
	 * @return true if created successfully else false
	 */
	public boolean createIndex(CourseIndex courseIndexObj){
		courseIndexList.add((courseIndexObj.getCourseIndex()) + "," + (courseIndexObj.getCourse().getCourseCode()) + "," + (courseIndexObj.getCourseIndexName())+ "," +
				(courseIndexObj.getCourseLectureFirstDay()) + "," + (courseIndexObj.getCourseLectureFirstTime())+ "," + (courseIndexObj.getCourseLectureSecondDay())+ "," +
				(courseIndexObj.getCourseLectureSecondTime()) + "," + (courseIndexObj.getCourseTutorialDay())+ "," + (courseIndexObj.getCourseTutorialTime())+ "," + 
				(courseIndexObj.getCourseLabDay())+ "," + (courseIndexObj.getCourseLabTime())+ "," + (courseIndexObj.getAvailableVacancy())+ "," + (courseIndexObj.getVacancy())+ "," + (courseIndexObj.getWaitList()));
		
		if(Database.fileWrite("courseIndex.csv",courseIndexList)){
			return true;
		}		
		else{
			return false;	
		}
	}
	
	/**
	 * Retrieve all course index
	 * @return courseIndexArrayList a list of course index object
	 */
	public ArrayList<CourseIndex> retrieveAllCourseIndex(){
	    ArrayList<CourseIndex>courseIndexArrayList = new ArrayList<CourseIndex>();
	    Course course;
	    
	    for(int i=0;i<courseIndexList.size();i++){
	        String[] courseIndex = courseIndexList.get(i).split(",");
	        course = new CourseController().retrieveCourseDetails(courseIndex[1]);
	        courseIndexArrayList.add(new CourseIndex(course,Integer.parseInt(courseIndex[0]),
	                courseIndex[2],courseIndex[3],courseIndex[4],courseIndex[5],
	                courseIndex[6],courseIndex[7],courseIndex[8],courseIndex[9],courseIndex[10],
	                Integer.parseInt(courseIndex[11]),Integer.parseInt(courseIndex[12]),Integer.parseInt(courseIndex[13])));
	    }
	    
	    return courseIndexArrayList;
	}
	/**
	 * Retrieve all course index by courseCode
	 * @param courseCode
	 * @return courseIndexArrayList a list of course index object
	 */
	public ArrayList<CourseIndex> retrieveAllCourseIndex(String courseCode){
        ArrayList<CourseIndex>courseIndexArrayList = new ArrayList<CourseIndex>();
        Course course;
        
        for(int i=0;i<courseIndexList.size();i++){
            String[] courseIndex = courseIndexList.get(i).split(",");
            
            if(courseCode.equals(courseIndex[1])){
            	course = new CourseController().retrieveCourseDetails(courseIndex[1]);
    	        courseIndexArrayList.add(new CourseIndex(course,Integer.parseInt(courseIndex[0]),
    	                courseIndex[2],courseIndex[3],courseIndex[4],courseIndex[5],
    	                courseIndex[6],courseIndex[7],courseIndex[8],courseIndex[9],courseIndex[10],
    	                Integer.parseInt(courseIndex[11]),Integer.parseInt(courseIndex[12]),Integer.parseInt(courseIndex[13])));
    	    }
        }
        
        return courseIndexArrayList;
    }
	
	/**
	 * Update the course index base on the index, column number and input
	 * @param index
	 * @param indexNo
	 * @param input
	 */
	public void updateIndex(int index, int indexNo, String input){
	    for(int i=0;i<courseIndexList.size();i++){
	        String[] courseIndexDetail = courseIndexList.get(i).split(",");
	        if(index == Integer.parseInt(courseIndexDetail[0])){
	            courseIndexDetail[indexNo] = input;
	            courseIndexList.remove(i);
	            
	            courseIndexList.add(i,courseIndexDetail[0]+","+courseIndexDetail[1]+","+courseIndexDetail[2]+","+courseIndexDetail[3]+","
	                    +courseIndexDetail[4]+","+courseIndexDetail[5]+","+courseIndexDetail[6]+","+courseIndexDetail[7]+","+courseIndexDetail[8]+","
	                    +courseIndexDetail[9]+","+courseIndexDetail[10]+","+courseIndexDetail[11]+","+courseIndexDetail[12]+","+courseIndexDetail[13]);
	        }
	    }
        Database.fileWrite("courseIndex.csv", courseIndexList);
	}
	
	/**
	 * Update the course index's course index code
	 * @param originalCode the code in the datafile
	 * @param courseCode the code to be replaced
	 */
	public void updateIndexCourseCode(String originalCode,String courseCode){
	    for(int i=0;i<courseIndexList.size();i++){
	        String[] courseIndexDetail = courseIndexList.get(i).split(",");
	        if(originalCode.equals(courseIndexDetail[1])){
	            courseIndexDetail[1] = courseCode;
	            courseIndexList.remove(i);
            
            courseIndexList.add(i,courseIndexDetail[0]+","+courseIndexDetail[1]+","+courseIndexDetail[2]+","+courseIndexDetail[3]+","
                    +courseIndexDetail[4]+","+courseIndexDetail[5]+","+courseIndexDetail[6]+","+courseIndexDetail[7]+","+courseIndexDetail[8]+","
                    +courseIndexDetail[9]+","+courseIndexDetail[10]+","+courseIndexDetail[11]+","+courseIndexDetail[12]+","+courseIndexDetail[13]);
        }
    }
    Database.fileWrite("courseIndex.csv", courseIndexList);
	}
	
     /**
      * Check the number of slot left based on the course index
      * @param courseIndexObj
      * @return slots
      */
	public int checkAvailableSlot(CourseIndex courseIndexObj){        
		for(int i =0; i<courseIndexList.size()&& courseIndexList.get(i)!=null;i++){
			if(courseIndexObj.getCourseIndex() == Integer.parseInt(courseIndexList.get(i).split(",")[0])){ 
				return Integer.parseInt(courseIndexList.get(i).split(",")[11]);
			}
		}
		return -1;
	}
   /**
    * Check the vacancies available based on the course code
    * @param courseCode
    */
	public void checkVacanciesAvailable(String courseCode)
	{	
		System.out.println("Course code: "+courseCode);
		System.out.println("Course Index \t Vacancy \t WaitList");
		for(int i=0;i<courseIndexList.size()&&courseIndexList.get(i)!=null;i++)
		{
			String [] courseIndexInfo=courseIndexList.get(i).split(",",14);
			if(courseIndexInfo[1].equals(courseCode))
			{
				System.out.println(courseIndexInfo[0]+"\t\t "+courseIndexInfo[11]+"\t\t "+courseIndexInfo[13]);
			}
		}

	}
}
