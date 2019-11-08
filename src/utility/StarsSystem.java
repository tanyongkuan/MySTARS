package utility;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.*;
import java.util.*;
import java.text.*;
import java.text.ParseException;

/**
	*Contains general methods for the whole application to use
	*@author Tan Yong Kuan
	*@version 1.0
	*@since 2017-04-12
*/
public class StarsSystem {

	/**
     * Convert into hash format based on the string given. (For password)
     * @param input Password to be converted
     */
	//Takes in a string and convert into hash (For password only)
	public static String convertToHash(String input){
		String output = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(input.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            output = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        
        return output;
	}
	
	/**
     * Check whether date format is inserted correctly by the user
     * e.g. dd/MM/yy HH:mm:ss-dd/MM/yy HH:mm:ss
     * @param date Date given by the user
     * @return true if valid, else false
     */
	public static boolean checkValidDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        try{
            sdf.parse(date.split("-",2)[0]);
            sdf.parse(date.split("-",2)[1]);
            return true;
        }
        catch(ParseException e){
            System.out.println("Incorrect date format is input");
            return false;
        }
	}
	/**
     * Check whether time format is inserted correctly by the user
     * e.g. HH:mm:ss-HH:mm:ss
     * @param time Time given by the user
     * @return true if valid, else false
     */
	public static boolean checkValidTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try{
            sdf.parse(time.split("-",2)[0]);
            sdf.parse(time.split("-",2)[1]);
            return true;
        }
        catch(ParseException e){
            System.out.println("Incorrect time format is input");
            return false;
        }
	}
	
	/**
     * Compare 2 dates provided by the system and compare whether it clashes
     * @param time1 the time in the course index
     * @param time2 the time in another course index
     * @return true if clashes, else false
     */
	public static boolean compareTimes(String time1,String time2){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try{
            Date startDateTime = sdf.parse(time1.split("-",2)[0]);
            Date endDateTime = sdf.parse(time1.split("-",2)[1]);
            Date startDateTime2 = sdf.parse(time2.split("-",2)[0]);
            Date endDateTime2 = sdf.parse(time2.split("-",2)[1]);
            
            if(startDateTime.before(endDateTime2) && startDateTime2.before(endDateTime)){
            	return true;
            }
            else{
            	return false;
            }
        }
        catch(ParseException e){
            return true;
        }
	}
	
	/**
     * Send email notification for student who has converted wait list to success in course registration
     * @param recipient Student's email address
     * @param content Content to be send to the email
     */
	public static void sendNotification(String recipient,String content) throws AddressException, MessagingException{
        String sender = "yongkuan94@gmail.com";
        String password = "Exact123!";
        
        Properties props = System.getProperties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        //create a session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        //create a message
        MimeMessage msg = new MimeMessage(session);

        //set from(sender address) and to(receiver address)
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

        //set email subject
        msg.setSubject("Waitlist notification");
        msg.setText(content);
        
        Transport.send(msg);
    }
}
