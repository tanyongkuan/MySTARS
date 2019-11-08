package view;
import java.io.*;
import java.util.*;
import controller.*;
import model.*;
import utility.*;
/**
 * Main application of the system
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-12
 */
public class StarsApp {
	public static void main(String args[]){
		String userId,password;
		char userType;
		int menuOption = 0;
		UserMenuController userController = new UserMenuController();
		
		Scanner sc = new Scanner(System.in);
		
		do{
		    System.out.println("=========Welcome to STARS Planner============");
			System.out.println("Welcome to STARS Planner");
			System.out.println("(1) Login");
			System.out.println("(2) Exit");
			System.out.println("=============================================");
			System.out.print("Enter your choice: ");
			
			try{
				menuOption = sc.nextInt();
				sc.nextLine();
				if(menuOption<0 || menuOption>2)
					throw new ErrorException("choice");
			}
			catch(InputMismatchException e){
				System.out.println("Please enter a valid choice.");
				menuOption = 0;
				sc.nextLine();
			}
			catch(ErrorException e){
			}
			
			System.out.println("");
			
			if(menuOption == 1){
			    Console console = System.console();
				userId = console.readLine("Enter username: ");
				password = String.copyValueOf((console.readPassword("Enter password: ")));

				userType = userController.signIn(userId, password);
		
				if(userType == 'A'){
					AdminView.printAdminMenu();
				}
		
				else if(userType == 'S'){
					Student stud = new Student(userId,"",'S');
					StudentView.printStudentMenu(stud);
				}
			}
			System.out.println("");
		}while(menuOption!=2);
		userController = null;
		sc.close();
	}
}
