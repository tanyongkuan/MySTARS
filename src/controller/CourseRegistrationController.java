package controller;

import java.util.ArrayList;
import java.util.Date;

import database.Database;
import model.Course;
import model.CourseIndex;
import model.CourseRegistration;
import model.Student;
import model.WaitList;
import utility.StarsSystem;
/**
Controller that contains methods that uses CourseRegistration entity objects and connect to database
@author Tan Yong Kuan
@version 1.0
@since 2017-04-12
*/
public class CourseRegistrationController {
	/**
	* list of students'course registration
	*/
	private ArrayList<String>courseRegisteredStudent;
	/**
	* list of course index
	*/
	private ArrayList<String>courseRegisteredIndex;
	/**
	 * WaitListController object
	 */
	private WaitListController waitListController;
	
	
	public CourseRegistrationController(){
		courseRegisteredStudent=Database.fileRead("courseRegistration.csv");
		courseRegisteredIndex=Database.fileRead("courseIndex.csv");
	}
	/**
	 * Check whether student has registered for a course based on the userId and courseIndex
	 * @return true if student has registered else false
	 */
	public boolean checkExistingCourseRegistration(CourseRegistration courseRegObj){
		for(int i=0; i<courseRegisteredStudent.size() && courseRegisteredStudent.get(i)!=null; i++){
            String[] courseRegisteredInfo = courseRegisteredStudent.get(i).split(",");
            if(courseRegisteredInfo[0].equals(courseRegObj.getStud().getUserId()) && Integer.parseInt(courseRegisteredInfo[2]) == courseRegObj.getCourseIndexObj().getCourseIndex()){
                return true;
            }
        }
		return false;
	}
	/**
	 * Check whether student has registered for a course based on the userId
	 * @return true if student has registered else false
	 */
	public boolean checkExistingStudentCourseRegistration(CourseRegistration courseRegObj){
		for(int i=0; i<courseRegisteredStudent.size() && courseRegisteredStudent.get(i)!=null; i++){
            String[] courseRegisteredInfo = courseRegisteredStudent.get(i).split(",");
            if(courseRegisteredInfo[0].equals(courseRegObj.getStud().getUserId())){
                return true;
            }
        }
		return false;
	}
	/**
	 * Check whether student has registered for a course based on the userId
	 * @param stud student object
	 * @param courseIndex courseIndex the student wants to register
	 * @param exceptionCourseIndex courseIndex student wants to swop/delete 
	 * @return true if student has timetable clashes else false
	 */
	public boolean checkCourseClash(Student stud,int courseIndex,int exceptionCourseIndex){
		for(int i=0;i<courseRegisteredStudent.size();i++){
			for(int j=0;j<courseRegisteredIndex.size();j++){
				if(courseRegisteredStudent.get(i).split(",")[0].equals(stud.getUserId()) && courseRegisteredStudent.get(i).split(",")[2].equals(courseRegisteredIndex.get(j).split(",")[0])){
					for(int k=0; k<courseRegisteredIndex.size();k++){
						if(courseRegisteredIndex.get(k).split(",")[0].equals(Integer.toString(courseIndex))){
							for(int l = 3; l<=9; l+=2){
								for(int m = 3; m<=9; m+=2){
									if(courseRegisteredIndex.get(k).split(",")[l].equals(courseRegisteredIndex.get(j).split(",")[m]) && !(courseRegisteredIndex.get(j).split(",")[m].equals(""))){
										if(StarsSystem.compareTimes(courseRegisteredIndex.get(k).split(",")[l+1], courseRegisteredIndex.get(j).split(",")[m+1])){
											if(!courseRegisteredIndex.get(j).split(",")[0].equals(Integer.toString(exceptionCourseIndex)))
												return true;
										}
									}
											
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * add course based on the student object and the course index student wants to add
	 * @return message Either on waitlist or successfully registered or invalid
	 */
	public String addCourse(CourseRegistration courseRegObj){
		String message = "";
		ArrayList<CourseIndex> courseIndexList = new ArrayList<CourseIndex>();
        int courseIndexRow = 0;
        String codeDuplicate="";
        boolean duplicateStatus = false;
        CourseIndex specificCourseIndex= null;
        for(int i=0; i<courseRegisteredIndex.size() && courseRegisteredIndex.get(i)!=null; i++){
            String[] courseIndexInfo = courseRegisteredIndex.get(i).split(",", 14);
            try {
            	Course course = new Course();
            	course.setCourseCode(courseIndexInfo[1]);
                courseIndexList.add(new CourseIndex(course,Integer.parseInt(courseIndexInfo[0]),courseIndexInfo[2],
                        courseIndexInfo[3],courseIndexInfo[4],courseIndexInfo[5],courseIndexInfo[6],courseIndexInfo[7],
                        courseIndexInfo[8],courseIndexInfo[9],courseIndexInfo[10],Integer.parseInt(courseIndexInfo[11]),
                        Integer.parseInt(courseIndexInfo[12]),Integer.parseInt(courseIndexInfo[13])));
                 
                if(courseRegObj.getCourseIndexObj().getCourseIndex() == Integer.parseInt(courseIndexInfo[0])){
                    specificCourseIndex = courseIndexList.get(i);
                    courseIndexRow = i;
                    codeDuplicate=courseIndexInfo[1];
                }
            } 
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
      }
        
      for(int j=0;j<courseRegisteredStudent.size()&& courseRegisteredStudent.get(j)!=null;j++){
      	  String[] checkDuplicate = courseRegisteredStudent.get(j).split(",");
      	  if((courseRegObj.getStud().getUserId().equals(checkDuplicate[0])) && checkDuplicate[1].equals(codeDuplicate) )
      	  {
      		  duplicateStatus=true;
      	  }
      }
      if(duplicateStatus==false)
      { 
    	  String[] courseIndexInfo = courseRegisteredIndex.get(courseIndexRow).split(",");
    	  String newCourseIndexInfo = "";
        
          if(specificCourseIndex.getAvailableVacancy() == 0){
        	  courseRegisteredStudent.add(courseRegObj.getStud().getUserId() + "," + specificCourseIndex.getCourse().getCourseCode() + "," + 
                        specificCourseIndex.getCourseIndex() + "," + "W");
              specificCourseIndex.setWaitList(specificCourseIndex.getWaitList()+1);
 
              courseIndexInfo[13] = Integer.toString(specificCourseIndex.getWaitList());
                 
              //Insert into waitList
              CourseIndex courseIndexObj = new CourseIndex();
              courseIndexObj.setCourseIndex(courseRegObj.getCourseIndexObj().getCourseIndex());
              WaitList waitList = new WaitList(new CourseRegistration(new Student(courseRegObj.getStud().getUserId(),"",' '),
              courseIndexObj,'W'),new Date());
              waitListController.insertWaitList(waitList);
              message = "You have successfully been added in the waitlist under index, " +specificCourseIndex.getCourseIndex();
          }
          else{
        	  courseRegisteredStudent.add(courseRegObj.getStud().getUserId() + "," + specificCourseIndex.getCourse().getCourseCode() + "," + 
                        specificCourseIndex.getCourseIndex() + "," + "S");
              specificCourseIndex.setAvailableVacancy(specificCourseIndex.getAvailableVacancy()-1);
              courseIndexInfo[11] = Integer.toString(specificCourseIndex.getAvailableVacancy());
                 
              message = "You have successfully added the index, " +specificCourseIndex.getCourseIndex();
          }
 
          for(int i=0;i<courseIndexInfo.length;i++){
        	  if(newCourseIndexInfo.equals("")){
        		  newCourseIndexInfo = courseIndexInfo[i];
              }
              else{
                  newCourseIndexInfo += "," +courseIndexInfo[i];
              }
          }
             
            courseRegisteredIndex.remove(courseIndexRow);
            courseRegisteredIndex.add(courseIndexRow, newCourseIndexInfo);
             
            Database.fileWrite("courseIndex.csv", courseRegisteredIndex);
            Database.fileWrite("courseRegistration.csv", courseRegisteredStudent);
      }
      else
      {
    	  message = "You have added courseRegObj course before!";
      }
      return message;
    }
	
	/**
	 * Update course registration status of the student based on the userid and course index registered
	 */
	public void updateCourseRegistrationStatus(CourseRegistration courseRegObj){
	    for(int i=0; i<courseRegisteredStudent.size() && courseRegisteredStudent.get(i)!=null; i++){
            String[] courseRegInfo = courseRegisteredStudent.get(i).split(",");
            if(courseRegObj.getStud().getUserId().equals(courseRegInfo[0]) && courseRegObj.getCourseIndexObj().getCourseIndex() == Integer.parseInt(courseRegInfo[2])){
                courseRegInfo[3] = "S";
                courseRegisteredStudent.remove(i);
                courseRegisteredStudent.add(i, courseRegInfo[0]+","+courseRegInfo[1]+","+courseRegInfo[2]+","+courseRegInfo[3]);
            }
        }
	    Database.fileWrite("courseRegistration.csv", courseRegisteredStudent);
	}
	/**
	 * drop course based on the student object and the course index student wants to drop
	 * will check whether there's waitlist
	 * @return message Either successfully dropped or invalid
	 */
	public String dropCourse(CourseRegistration courseRegObj)
	{     
		String message = "";
        ArrayList<CourseIndex> courseIndexList = new ArrayList<CourseIndex>();
        int courseIndexRow = 0;
        CourseIndex specificCourseIndex= null;

        boolean checkVacancy=false;
         
        for(int i=0; i<courseRegisteredIndex.size() && courseRegisteredIndex.get(i)!=null; i++){
            String[] courseIndexInfo = courseRegisteredIndex.get(i).split(",", 14);
            try {
            	Course course = new Course();
            	course.setCourseCode(courseIndexInfo[1]);
                courseIndexList.add(new CourseIndex(course,Integer.parseInt(courseIndexInfo[0]),courseIndexInfo[2],
                        courseIndexInfo[3],courseIndexInfo[4],courseIndexInfo[5],courseIndexInfo[6],courseIndexInfo[7],
                        courseIndexInfo[8],courseIndexInfo[9],courseIndexInfo[10],Integer.parseInt(courseIndexInfo[11]),
                        Integer.parseInt(courseIndexInfo[12]),Integer.parseInt(courseIndexInfo[13])));
                 
                if(courseRegObj.getCourseIndexObj().getCourseIndex() == Integer.parseInt(courseIndexInfo[0])){
                    specificCourseIndex = courseIndexList.get(i);
                    courseIndexRow = i;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

		for(int i=0; i<courseRegisteredStudent.size() && courseRegisteredStudent.get(i)!=null;i++)
		{
			 String[] courseRegisteredInfo = courseRegisteredStudent.get(i).split(",");
			 if(courseRegisteredInfo[0].equals(courseRegObj.getStud().getUserId()) && Integer.parseInt(courseRegisteredInfo[2]) == courseRegObj.getCourseIndexObj().getCourseIndex())
			 {
				 if(courseRegisteredInfo[3].equals("S"))
				 {
					 checkVacancy=true;
				 }
				 courseRegisteredStudent.remove(i);
			 }
		}
		if(checkVacancy==true)
		{
			String temp="";
			String[] courseRegisteredInfo=courseRegisteredIndex.get(courseIndexRow).split(",");
			courseRegisteredIndex.remove(courseIndexRow);
			courseRegisteredInfo[11]=Integer.toString(specificCourseIndex.getAvailableVacancy()+1);
			specificCourseIndex.setAvailableVacancy(specificCourseIndex.getAvailableVacancy()+1);
			
			for(int i=0;i<courseRegisteredInfo.length;i++)
			{
				if(temp=="")
				{
					temp=courseRegisteredInfo[i];
				}
				else
				{
					temp += "," + courseRegisteredInfo[i];
				}
			}
			courseRegisteredIndex.add(courseIndexRow,temp);	
			message = "You have successfully drop the index "+courseRegObj.getCourseIndexObj().getCourseIndex();		

	        Database.fileWrite("courseIndex.csv", courseRegisteredIndex);
	        Database.fileWrite("courseRegistration.csv", courseRegisteredStudent);
	        WaitList wl = new WaitList(courseRegObj, new Date());
	        waitListController.checkWaitList(wl);
		}
		else
		{
			String temp="";
			String[] courseRegisteredInfo=courseRegisteredIndex.get(courseIndexRow).split(",");
			courseRegisteredIndex.remove(courseIndexRow);
			courseRegisteredInfo[13]=Integer.toString(specificCourseIndex.getWaitList()-1);
			specificCourseIndex.setWaitList(specificCourseIndex.getWaitList()-1);
			for(int i=0;i<courseRegisteredInfo.length;i++)
			{
				if(temp=="")
				{
					temp=courseRegisteredInfo[i];
				}
				else
				{
					temp += "," + courseRegisteredInfo[i];
				}
			}
			courseRegisteredIndex.add(courseIndexRow,temp);
			message = "You have successfully removed from the waitlist for the index "+courseRegObj.getCourseIndexObj().getCourseIndex();
	        Database.fileWrite("courseIndex.csv", courseRegisteredIndex);
	        Database.fileWrite("courseRegistration.csv", courseRegisteredStudent);
	        WaitList wl = new WaitList(courseRegObj, new Date());
	        waitListController.deleteWaitList(wl);
		}
		return message;
	}
	
	/**
	 * Print course registered by the student
	 * @param stud
	 */
	public void printCourseRegistered(Student stud)
    {
		String userId = stud.getUserId();
       
        String leftAlignFormat = "| %-7s | %-5s | %-6s | %-3s | %-26s | %-26s | %-26s | %-26s |\n";
		System.out.format("+---------+-------+------------+------+----------------------------+----------------------------+----------------------------+----------------------------+\n");
		System.out.format("|Course   | Index |Status      |Group |Day and Time                |Day and Time                |Day and Time                |Day and Time                |\n");
		System.out.format("+---------+-------+------------+------+----------------------------+----------------------------+----------------------------+----------------------------+\n");
		
		for (int i =0; i< courseRegisteredStudent.size() && courseRegisteredStudent.get(i)!=null; i++){
			String[] courseRegisteredInfo = courseRegisteredStudent.get(i).split(",", 4);
			if (userId.equals(courseRegisteredInfo[0])){
				for (int j=0; j<courseRegisteredIndex.size() && courseRegisteredIndex.get(j)!=null; j++){
					String[] courseIndexRegisteredInfo = courseRegisteredIndex.get(j).split(",");
					if (courseIndexRegisteredInfo[0].equals(courseRegisteredInfo[2])){
						if (courseRegisteredInfo[3].charAt(0) == 'S'){
							System.out.format(leftAlignFormat, courseRegisteredInfo[1] , courseRegisteredInfo[2], "REGISTERED", courseIndexRegisteredInfo[2], courseIndexRegisteredInfo[3] + " "+ courseIndexRegisteredInfo[4], 
								courseIndexRegisteredInfo[5] + " " + courseIndexRegisteredInfo[6], courseIndexRegisteredInfo[7] + " " + courseIndexRegisteredInfo[8], courseIndexRegisteredInfo[9] + " "+ courseIndexRegisteredInfo[10]);
						}
						else {
							System.out.format(leftAlignFormat, courseRegisteredInfo[1] , courseRegisteredInfo[2], "WAITLIST  ", courseIndexRegisteredInfo[2], courseIndexRegisteredInfo[3] + " "+ courseIndexRegisteredInfo[4], 
									courseIndexRegisteredInfo[5] + " " + courseIndexRegisteredInfo[6], courseIndexRegisteredInfo[7] + " " + courseIndexRegisteredInfo[8], courseIndexRegisteredInfo[9] + " "+ courseIndexRegisteredInfo[10]);
						}
						j = courseRegisteredIndex.size();
					}
				}
			}
		}
		System.out.format("+---------+-------+------------+------+----------------------------+----------------------------+----------------------------+----------------------------+\n");
    }
	
	/**
	 * Swop Index number with another student
	 * @param courseIndex student's course index
	 * @param userId student's userId
	 * @param swopCourseIndex swop student's course index
	 * @param swopUserId swop student's userId
	 */
	public void swopIndexNoWithStudent(int courseIndex,String userId,int swopCourseIndex,String swopUserId)
    {
        int temp1=-1, temp2=-1;
        String courseCode="";
        ArrayList<String>courseRegisteredStudent=Database.fileRead("courseRegistration.csv");
        ArrayList<CourseRegistration> courseRegistrationList  = new ArrayList<CourseRegistration>();
        CourseRegistration objCourseRegistration = null;
        
        for (int i=0; i<courseRegisteredStudent.size() && courseRegisteredStudent.get(i)!=null; i++){
            String [] parts = courseRegisteredStudent.get(i).split(",");
            Student objStudent = new Student(parts[0], " ", 'S');
            CourseIndex objCourseIndex = new CourseIndex();
            objCourseIndex.setCourseIndex(Integer.parseInt(parts[2]));
            courseRegistrationList.add(new CourseRegistration(objStudent, objCourseIndex, parts[3].charAt(0)));
            if (Integer.parseInt(parts[2]) == courseIndex && userId.equals(parts[0])){
            	temp1 = i;
            	courseCode=parts[1];
            }
            if (Integer.parseInt(parts[2]) == swopCourseIndex && swopUserId.equals(parts[0])){
            	temp2 = i;
            }
        }
        
        if (temp1 != -1 && temp2 != -1){
        	for (int i=0; i<courseRegistrationList.size() && courseRegistrationList.get(i)!=null; i++){
        		objCourseRegistration = courseRegistrationList.get(i);
        		if (i==temp1){
        			
        			String temp = objCourseRegistration.getStud().getUserId() + "," + courseCode + "," + swopCourseIndex + "," + 'S';
        			courseRegisteredStudent.remove(i);
        			courseRegisteredStudent.add(i, temp);
        		}
        		if (i == temp2){
        			String temp = objCourseRegistration.getStud().getUserId() +"," + courseCode +"," + courseIndex + "," + 'S';
        			courseRegisteredStudent.remove(i);
        			courseRegisteredStudent.add(i, temp);	
        		}
        	}  
    		System.out.println("Swopping of Index successful with " + swopUserId);
        	Database.fileWrite("courseRegistration.csv", courseRegisteredStudent);
        }
        else{
        	if (temp1 == -1){
        		System.out.println("Invalid index by current user");
        	}
        	else 
        		System.out.println("Invalid index by swopped user");
        }
    }
	
	/**
	 * Get studentlist by course/course index
	 * @param byCourseIndex courseIndex = true, course = false
	 * @param courseInput name of the course or index number of the course index
	 * @return studentlist list of student
	 */
	public ArrayList<Student> retrieveStudentList(boolean byCourseIndex, String courseInput){
		ArrayList<Student>studentList = new ArrayList<Student>();
		int columnNo = 0;
		
		if(byCourseIndex){
			columnNo = 2;
		}
		else{
			columnNo = 1;
		}
		
		for(int i =0; i<courseRegisteredStudent.size()&& courseRegisteredStudent.get(i)!=null;i++){
			if(courseInput.equals(courseRegisteredStudent.get(i).split(",")[columnNo])){
			    StudentController studController = new StudentController();
			    studentList.add(studController.retrieveStudentDetails(courseRegisteredStudent.get(i).split(",")[0]));
			}
		}
		return studentList;
	}
	
	/**
	 * update course registration by courseCode
	 * @param originalCode original course code in the data file
	 * @param courseCode course code that want to be replaced
	 */
	public void updateCourseRegistration(String originalCode,String courseCode){
        for(int i=0;i<courseRegisteredStudent.size();i++){
            String[] courseIndexDetail = courseRegisteredStudent.get(i).split(",");
            if(originalCode.equals(courseIndexDetail[1])){
                courseIndexDetail[1] = courseCode;
                courseRegisteredStudent.remove(i);
            
                courseRegisteredStudent.add(i,courseIndexDetail[0]+","+courseIndexDetail[1]+","+courseIndexDetail[2]+","+courseIndexDetail[3]);
            }
        }
        Database.fileWrite("courseRegistration.csv", courseRegisteredStudent);
    }
}
