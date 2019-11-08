package controller;

import java.text.*;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import database.*;
import model.*;
import utility.*;

/**
Controller that contains methods that uses waitlist entity objects and connect to database
@author Tan Yong Kuan
@version 1.0
@since 2017-04-12
*/
public class WaitListController {
	/**
	 * A CourseRegistrationController
	 */
	private CourseRegistrationController courseRegController;

	/**
	 * a list of waitlist from the data file
	 */
	private ArrayList<String>waitList;

	/**
	 * a list of user from the data file
	 */
	private ArrayList<String>checkUsers;
	
	public WaitListController(){
		waitList = Database.fileRead("waitList.csv");
		checkUsers = Database.fileRead("users.csv");
	}

	/**
     * Insert given student into the waitList
     * @param waitListObj
     */
	public void insertWaitList(WaitList waitListObj){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		waitList.add(waitListObj.getCourseReg().getStud().getUserId()+","+waitListObj.getCourseReg().getCourseIndexObj().getCourseIndex()+","+sdf.format(waitListObj.getRequestDate()));
		Database.fileWrite("waitList.csv", waitList);
		
	}
	
	/**
     * Check any student with the course index are in the waiting list
     * If there is, the first student in the list will be added into the course
     * An email will be sent to the student
     */
	public void checkWaitList(WaitList waitListObj){
		//Check anyone in the waitList based on the index
		//Find the first person in the list
		//Call the add course method
		//Delete the person from the waitList
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		String emailAdd = "",handphoneNo="";
		char notificationMode=' ';
		
		int rowNo = 0;
		Date rowNoDate = null;
		
		for(int i = 0; i<waitList.size();i++){
			if(Integer.parseInt(waitList.get(i).split(",")[1]) == waitListObj.getCourseReg().getCourseIndexObj().getCourseIndex()){
				if(rowNoDate == null){
					rowNo = i;
					try {
						rowNoDate = sdf.parse(waitList.get(i).split(",")[2]);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else{
					try {
						if(rowNoDate.after(sdf.parse(waitList.get(i).split(",")[2]))){
							rowNo = i;
							rowNoDate = sdf.parse(waitList.get(i).split(",")[2]);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(rowNoDate != null){
			CourseIndex courseIndex = new CourseIndex();
			courseIndex.setCourseIndex(waitListObj.getCourseReg().getCourseIndexObj().getCourseIndex());
			CourseRegistration courseRegistration = new CourseRegistration(new Student(waitList.get(rowNo).split(",")[0],"",' '),
					courseIndex,'S');
			courseRegController.dropCourse(courseRegistration);
			courseRegController.addCourse(courseRegistration);
			waitListObj.setCourseReg(courseRegistration);
			deleteWaitList(waitListObj);
			
			for(int i=0; i<checkUsers.size(); i++){
				if(waitListObj.getCourseReg().getStud().getUserId().equals(checkUsers.get(i).split(",")[0])){
					emailAdd = checkUsers.get(i).split(",")[10];
					handphoneNo = checkUsers.get(i).split(",")[11];
					notificationMode = checkUsers.get(i).split(",")[9].charAt(0);
				}
			}
			
			if(notificationMode=='B'){
				//sendEmail
				try {
					String content = "Hi " + waitListObj.getCourseReg().getStud().getUserId()+",\n\n";
					content += "You have been registered to " +waitListObj.getCourseReg().getCourseIndexObj().getCourseIndex();
					StarsSystem.sendNotification(emailAdd,content);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				//sendSMS
				System.out.println("A SMS is sent to " +handphoneNo);
			}
			else if(notificationMode=='E'){
				//sendEmail
				try {
					String content = "Hi " + waitListObj.getCourseReg().getStud().getUserId()+",\n\n";
					content += "You have been registered to " +waitListObj.getCourseReg().getCourseIndexObj().getCourseIndex();
					StarsSystem.sendNotification(emailAdd,content);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			else if(notificationMode=='S'){
				//sendSMS
				System.out.println("A SMS is sent to " +handphoneNo);
			}
		}
	}
	
	/**
     * Delete student from the waitlist if they have decided to drop the course
     * @param waitListObj
     */
	public void deleteWaitList(WaitList waitListObj){
		for(int i =0;i<waitList.size();i++){
			if(waitListObj.getCourseReg().getStud().getUserId().equals(waitList.get(i).split(",")[0]) && waitListObj.getCourseReg().getCourseIndexObj().getCourseIndex() == Integer.parseInt(waitList.get(i).split(",")[1])){
				waitList.remove(i);
				i = waitList.size();
			}
		}
		Database.fileWrite("waitList.csv", waitList);
	}
	
}
