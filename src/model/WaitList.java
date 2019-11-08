package model;

import java.util.*;

/**
 * Represents waitlist of students waiting to be registered
 * A waitlist can contains a lot of students waiting to be registered
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class WaitList{
    /**
     * The date the student request to be registered
     */
	private Date requestDate;
	
	/**
	 * A CourseRegistration object that contains the student's course registration details
	 */
	private CourseRegistration courseReg;
	
	/**
	 * Gets the request date during course registration
	 * @return requestDate
	 */
	public Date getRequestDate(){
		return requestDate;
	}
	
	/**
	 * Gets the request date during course registration
	 * @param requestDate
	 */
	public void setRequestDate(Date requestDate){
		this.requestDate = requestDate;
	}
	
	/**
	 * Gets the course registration details from the student
	 * @return CourseRegistration object
	 */
	public CourseRegistration getCourseReg(){
		return courseReg;
	}
	
	/**
     * Sets the course registration details from the student
     */
	public void setCourseReg(CourseRegistration courseReg){
		this.courseReg = courseReg;
	}
	
	/**
     * Creates a wait list for student who is transferred to the waiting list
     * @param courseReg course registration details from the student
     * @param requestDate date they have request to be registered
     */
	public WaitList(CourseRegistration courseReg,Date requestDate){
		this.courseReg = courseReg;
		this.requestDate = requestDate;
	}
	
}
