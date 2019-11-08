package model;
import java.util.*;

/**
    Represents the application settings
    @author Tan Yong Kuan
    @version 1.0
    @since 2017-04-12
*/

public class AppSettings {
    /**
     * Start Access Time of the application
     */
	private Date startAccess;
	
	/**
     * End Access Time of the application
     */
	private Date endAccess;
	
	/**
	 * Gets the start access time of the application
	 * @return the start access time
	 */
	public Date getStartAccess(){
		return startAccess;
	}
	
	/**
	 * Set the start access time of the application
	 * @param startAccess
	 */
	public void setStartAccess(Date startAccess){
		this.startAccess = startAccess;
	}
	
	/**
     * Gets the end access time of the application
     * @return the end access time
     */
	public Date getEndAccess(){
		return endAccess;
	}
	
	/**
     * Set the end access time of the application
     * @param endAccess
     */
	public void setEndAccess(Date endAccess){
		this.endAccess = endAccess;
	}
}
	
