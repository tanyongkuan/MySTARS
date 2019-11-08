package utility;

/**
 * Prints an error for the system
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class ErrorException extends Exception{
	/**
	 * Print out the error based on the error type
	 * @param error given by the application
	 */
	public ErrorException(String error){
		if(error.equals("choice")){
			System.out.println("Please enter a valid choice.");
            System.out.println("");
		}
        if(error.equals("studentYear")){
            System.out.println("Please enter a valid student year.");
            System.out.println("");
        }
	}
}
