package view;

import java.io.*;
import java.util.*;

import controller.*;
import model.*;
import utility.*;

/**
 * Represents the student menu after student signs in
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class StudentView {
	public static void printStudentMenu(Student stud){
		Date currentDayTime = new Date();
		Scanner sc = new Scanner(System.in);
		StudentMenuController studentController = new StudentMenuController();
		int subMenuOption = 0;

		UserMenuController userController = new UserMenuController();
	
		if(studentController.checkAccessTime(currentDayTime)){
			do{
			    System.out.println("=========Welcome to Student Menu===============");
				System.out.println("1 Add Course");
				System.out.println("2 Drop Course");
				System.out.println("3 Check/Print Course Registered");
				System.out.println("4 Show Course Available");
				System.out.println("5 Check Vacancies Available");
				System.out.println("6 Change Index Number of Course");
				System.out.println("7 Swop Index Number with Another Student");
				System.out.println("8 Change Notification");
				System.out.println("9 Change Phone Number");
				System.out.println("10 Exit");
	            System.out.println("===============================================");
				System.out.print("Enter your choice: ");
				try{
					subMenuOption = sc.nextInt();
					
					if(subMenuOption<0 || subMenuOption>10)
						throw new ErrorException("choice");
				}
				catch(InputMismatchException e){
					System.out.println("Please enter a valid choice");
					sc.nextLine();
                    System.out.println("");
    				subMenuOption=0;
				}

				catch(ErrorException e){
				}
				
				System.out.println("");

				int courseIndex = 0,swopCourseIndex = 0;
				String swopUserId, swopUserPassword, courseCode;
				
				switch(subMenuOption){
					case 1:
					    do{
                            try{
                                System.out.print("Enter index number of course to add: ");
                                courseIndex = sc.nextInt();
                                studentController.addCourse(stud, courseIndex);
	                        }
	                        catch(InputMismatchException e){
	                            System.out.println("Please enter a valid index number");
	                            sc.nextLine();
                                System.out.println("");
	                        }
					    }while(courseIndex==0);

                        break;
					case 2:
					    do{
                            try{
                                System.out.print("Enter index no of course to drop: ");
                                courseIndex = sc.nextInt();
                                studentController.dropCourse(stud, courseIndex);
                            }
                            catch(InputMismatchException e){
                                System.out.println("Please enter a valid index number");
                                sc.nextLine();
                                System.out.println("");
                            }
                        }while(courseIndex==0);
						break;
					case 3:
						studentController.printCourseRegistered(stud);
						break;
					case 4:
						do{
						System.out.print("Enter course code: ");
						courseCode = sc.next();
						if(studentController.checkValidCourse(courseCode)){
							studentController.getAllCourseIndexDetails(courseCode);
						}
						else{
							System.out.println("Invalid course code entered.");
						}
						}while(courseCode.equals(""));
						break;
					case 5:
						sc.nextLine();
						System.out.print("Enter course code to check vacancy: ");
						courseCode = sc.next();
						studentController.checkVacanciesAvailable(courseCode);
						break;
					case 6:
					    do{
                            try{
                                System.out.print("Enter current index number: ");
                                courseIndex = sc.nextInt();
                                studentController.changeIndexNoOfCourse(stud, courseIndex);
                            }
                            catch(InputMismatchException e){
                                System.out.println("Please enter a valid index number");
                                sc.nextLine();
                                System.out.println("");
                            }
                        }while(courseIndex==0);
                        break;
					case 7:
					    do{
                            try{
                            	Console console = System.console();
                                System.out.print("Enter your index no: ");
                                courseIndex = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter peer student Id: ");
                                swopUserId = sc.nextLine();
                                swopUserPassword = String.copyValueOf((console.readPassword("Enter peer student password: ")));
                                System.out.print("Enter peer index no to swop: ");
                                swopCourseIndex = sc.nextInt();
                                
                                if(userController.signIn(swopUserId, swopUserPassword) == 'S'){
                                    studentController.swopIndexNoWithStudent(courseIndex, stud.getUserId(), swopCourseIndex, swopUserId);
                                }
                            }
                            catch(InputMismatchException e){
                                System.out.println("Please enter a valid index number");
                                sc.nextLine();
                                System.out.println("");
                            }
                        }while(courseIndex==0 || swopCourseIndex==0);
						break;
					case 8:
						sc.nextLine();
						char notificationChoice = ' ';
						do{
						    try{
	                            System.out.println("E: Email Only, S: SMS Only, B: Email and SMS");
	                            System.out.print("Enter notification of choice: ");
	                            notificationChoice = sc.next().toUpperCase().charAt(0);
	                            if(notificationChoice != 'E' && notificationChoice != 'S' && notificationChoice != 'B')
	                                throw new ErrorException("choice");
	                            studentController.changeNotification(stud, notificationChoice);
	                        }
	                        catch(InputMismatchException e){
	                            System.out.println("Please enter a valid choice.");
                                System.out.println("");
	                        }
	                        catch(ErrorException e){
	                        }
						}while(notificationChoice != 'E' && notificationChoice != 'S' && notificationChoice != 'B');
				
						break;
					case 9:
						sc.nextLine();
						String phoneNo = "";
						do{
						    try{
						        System.out.print("Enter phone number: ");
						        phoneNo = sc.nextLine();
						        Integer.parseInt(phoneNo);
						        studentController.changeStudentHandphoneNumber(stud, phoneNo);
						    }
						    catch(NumberFormatException e){
						        System.out.println("Please enter a valid handphone number.");
						        phoneNo = "";
						    }
						}while(phoneNo.equals(""));
						break;
					case 10:
						System.out.println("Logging Out from Stars Application.");
						break;
					default : 
						break;
				}
                System.out.println("");
			}while(subMenuOption!=10);
			stud = null;
			studentController = null;
		}
	}
}
