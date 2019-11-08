package model;

/**
 * Represents all Courses data 
 * @author Kim Mong Qi Yvette
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class CourseIndex{
	/**
	* index of the course index
	*/
	private int courseIndex;
	/**
	* name of the course index
	*/
	private String courseIndexName;
	/**
	* first lecture day of the course index
	*/
	private String courseLectureFirstDay;
	/**
	* second lecture day of the course index
	*/
	private String courseLectureSecondDay;
	/**
	* first lecture time of the course index
	*/
	private String courseLectureFirstTime;
	/**
	* second lecture time of the course index
	*/
	private String courseLectureSecondTime;
	/**
	* day of the tutorial
	*/
	private String courseTutorialDay;
	/**
	* time of the tutorial
	*/
	private String courseTutorialTime;
	/**
	* day of the lab
	*/
	private String courseLabDay;
	/**
	* time of the lab
	*/
	private String courseLabTime;
	/**
	* vacancy available during registration
	*/
	private int availableVacancy;
	/**
	* vacancy created
	*/
	private int vacancy;
	/**
	* number of waitlist in the course index
	*/
	private int waitList;
	/**
	* course object of the course index
	*/
	private Course course;
	
	public CourseIndex(){
	}
	
	public CourseIndex(Course course,int courseIndex,String courseIndexName,String courseLectureFirstDay,String courseLectureFirstTime,String courseLectureSecondDay,String courseLectureSecondTime,String courseTutorialDay,String courseTutorialTime,String courseLabDay,String courseLabTime,int availableVacancy,int vacancy,int waitList){
		this.course = course;
		this.courseIndex = courseIndex;
		this.courseIndexName = courseIndexName;
		this.courseLectureFirstDay = courseLectureFirstDay;
		this.courseLectureFirstTime = courseLectureFirstTime;
		this.courseLectureSecondDay = courseLectureSecondDay;
		this.courseLectureSecondTime = courseLectureSecondTime;
		this.courseTutorialDay = courseTutorialDay;
		this.courseTutorialTime = courseTutorialTime;
		this.courseLabDay = courseLabDay;
		this.courseLabTime = courseLabTime;
		this.availableVacancy = availableVacancy;
		this.vacancy = vacancy;
		this.waitList = waitList;
	}
	/**
	 * get the index of the course index
	 * @return courseIndex
	 */
	public int getCourseIndex(){
		return courseIndex;
	}
	
	/**
	 * set the index of the course index
	 * @param courseIndex
	 */
	public void setCourseIndex(int courseIndex){
		this.courseIndex = courseIndex;
	}

	/**
	 * get the name of the course index
	 * @return courseIndexName
	 */
	public String getCourseIndexName(){
		return courseIndexName;
	}
	
	/**
	 * set the name of the course index
	 * @param courseIndexName
	 */
	public void setCourseIndexName(String courseIndexName){
		this.courseIndexName = courseIndexName;
	}
	/**
	 * get the day of the first lecture
	 * @return courseLectureFirstDay
	 */
	public String getCourseLectureFirstDay(){
		return courseLectureFirstDay;
	}
	/**
	 * set the day of the first lecture
	 * @param courseLectureFirstDay
	 */
	public void setCourseLectureFirstDay(String courseLectureFirstDay){
		this.courseLectureFirstDay = courseLectureFirstDay;
	}
	/**
	 * get the time of the first lecture
	 * @return courseLectureFirstTime
	 */
	public String getCourseLectureFirstTime(){
		return courseLectureFirstTime;
	}
	/**
	 * set the time of the first lecture
	 * @param courseLectureFirstTime
	 */
	public void setCourseLectureFirstTime(String courseLectureFirstTime){
		this.courseLectureFirstTime = courseLectureFirstTime;
	}
	/**
	 * get the day of the second lecture
	 * @return courseLectureSecondDay
	 */
	public String getCourseLectureSecondDay(){
		return courseLectureSecondDay;
	}
	/**
	 * set the day of the second lecture
	 * @param courseLectureSecondDay
	 */
	public void setCourseLectureSecondDay(String courseLectureSecondDay){
		this.courseLectureSecondDay = courseLectureSecondDay;
	}
	/**
	 * get the time of the second lecture
	 * @return courseLectureSecondTime
	 */
	public String getCourseLectureSecondTime(){
		return courseLectureSecondTime;
	}

	/**
	 * set the time of the second lecture
	 * @param courseLectureSecondTime
	 */
	public void setCourseLectureSecondTime(String courseLectureSecondTime){
		this.courseLectureSecondTime = courseLectureSecondTime;
	}
	/**
	 * get the day of the tutorial
	 * @return courseTutorialDay
	 */
	public String getCourseTutorialDay(){
		return courseTutorialDay;
	}
	/**
	 * set the day of the tutorial
	 * @param courseTutorialDay
	 */
	public void setCourseTutorialDay(String courseTutorialDay){
		this.courseTutorialDay = courseTutorialDay;
	}
	/**
	 * get the time of the tutorial
	 * @return courseTutorialTime
	 */
	public String getCourseTutorialTime(){
		return courseTutorialTime;
	}
	/**
	 * set the time of the tutorial
	 * @param courseTutorialTime
	 */
	public void setCourseTutorialTime(String courseTutorialTime){
		this.courseTutorialTime = courseTutorialTime;
	}
	/**
	 * get the day of the lab
	 * @return courseLabDay
	 */
	public String getCourseLabDay(){
		return courseLabDay;
	}
	/**
	 * set the day of the lab
	 * @param courseLabDay
	 */
	public void setCourseLabDay(String courseLabDay){
		this.courseLabDay = courseLabDay;
	}
	/**
	 * get the time of the lab
	 * @return courseLabTime
	 */
	public String getCourseLabTime(){
		return courseLabTime;
	}
	/**
	 * set the time of the lab
	 * @param courseLabTime
	 */
	public void setCourseLabTime(String courseLabTime){
		this.courseLabTime = courseLabTime;
	}
	/**
	 * get the available vacancy of the course index
	 * @return availableVacancy
	 */
	public int getAvailableVacancy(){
		return availableVacancy;
	}
	/**
	 * set the available vacancy of the course index
	 * @param availableVacancy
	 */
	public void setAvailableVacancy(int availableVacancy){
		this.availableVacancy = availableVacancy;
	}
	/**
	 * get the total vacancy of the course index
	 * @return vacancy
	 */
	public int getVacancy(){
		return vacancy;
	}
	/**
	 * set the total vacancy of the course index
	 * @param vacancy
	 */
	public void setVacancy(int vacancy){
		this.vacancy = vacancy;
	}
	/**
	 * get the waitlist of the course index
	 * @return vacancy
	 */
	public int getWaitList(){
		return waitList;
	}
	/**
	 * set the waitlist of the course index
	 * @param waitList
	 */
	public void setWaitList(int waitList){
		this.waitList = waitList;
	}
	/**
	 * get the course object of the course index
	 * @return vacacoursency
	 */
	public Course getCourse(){
		return course;
	}
	/**
	 * set the course object of the course index
	 * @param course
	 */
	public void setCourse(Course course){
		this.course = course;
	}
	
}
