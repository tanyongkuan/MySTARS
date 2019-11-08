package view;

import java.util.*;
import controller.*;
import utility.*;

/**
 * Represents the admin menu after admin signs in
 * @author Tan Yong Kuan
 * @version 1.0
 * @since 2017-04-12
 *
 */
public class AdminView {
	public static void printAdminMenu(){
		Scanner sc = new Scanner(System.in);
		int subMenuOption = 0, courseIndex = 0;
		AdminMenuController adminController = new AdminMenuController();
		
		do{
		    System.out.println("=========Welcome to Admin Menu===============");
			System.out.println("1 Edit student access period");
			System.out.println("2 Add student");
			System.out.println("3 Add course");
			System.out.println("4 Update course");
			System.out.println("5 Add course index");
			System.out.println("6 Update course index");
			System.out.println("7 Check vacancy based on index number");
			System.out.println("8 Print student list by index number");
			System.out.println("9 Print student list by course");
			System.out.println("10 Exit");
			System.out.println("=============================================");
			System.out.print("Enter your choice: ");
			
			try{
				subMenuOption = sc.nextInt();
				sc.nextLine();
				
				if(subMenuOption<0 || subMenuOption>10)
					throw new ErrorException("choice");
			}
			catch(InputMismatchException e){
				System.out.println("Please enter a valid choice");
				sc.nextLine();
				subMenuOption=0;
			}
			catch(ErrorException e){
			}
			
			System.out.println("");

			char courseType = ' ';
			
			switch(subMenuOption){
				case 1 : 
				    String accessPeriod;
					do{
	                    System.out.println("Enter student access period(DD/MM/YYYY HH:MM:SS-DD/MM/YYYY HH:MM:SS): ");
	                    accessPeriod = sc.nextLine();
                    }while(!StarsSystem.checkValidDate(accessPeriod));
                    adminController.changeAccessTime(accessPeriod);
					break;
					
				case 2 :
					String studentName, studentId, studentNationality, studentProgramme, schoolCode,studentPhoneNumber,studentEmail;
					char studentGender=' ',studentNotificationMode= ' ';
					int studentYear;
					
					adminController.getAllStudentDetails();

					do{
						System.out.print("Enter student name: ");
						studentName = sc.nextLine();
						if(studentName.matches(".*\\d+.*")){
							System.out.println("Student name shouldnt have numbers.");
							studentName = "";
						}
					}while(studentName.equals(""));
					
					do{
					System.out.print("Enter student marticulation number: ");
					studentId = sc.nextLine();

					if(adminController.checkExistingStudent(studentId)){
						System.out.println("Student ID is already in the record");
						studentId = "";
					}
					}while(studentId.equals(""));

                    do{
                        try{
                            System.out.print("Enter student gender (M/F): ");
                            studentGender = sc.nextLine().toUpperCase().charAt(0);
                            if(studentGender != 'M' && studentGender != 'F')
                                throw new ErrorException("choice");
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please enter a valid choice.");
                            System.out.println("");
                        }
                        catch(ErrorException e){
                        }
                    }while(studentGender != 'M' && studentGender != 'F');
                    
					
					do{
						System.out.print("Enter student nationality: ");
						studentNationality = sc.nextLine();
						if(studentNationality.matches(".*\\d+.*")){
							System.out.println("Student Nationality shouldnt have numbers.");
							studentNationality = "";
						}
					}while(studentNationality.equals(""));
					
					do{
					    System.out.print("Enter student school code: ");
					    schoolCode = sc.nextLine();
					}while(!adminController.checkExistingSchool(schoolCode));
					
					System.out.print("Enter student course name: ");
					studentProgramme = sc.nextLine();
					
					do{
					    try{
					        System.out.print("Enter student year: ");
					        studentYear = sc.nextInt();
					        
					        if(studentYear<0 || studentYear>4)
					            throw new ErrorException("studentYear");

		                    sc.nextLine();
					    }
					    catch(InputMismatchException e){
					        System.out.println("Please enter a valid student year");
					        sc.nextLine();
					        System.out.println("");
                            studentYear = 0;
					    }
					    catch(ErrorException e){
					        studentYear = 0;
					    }
					}while(studentYear==0);
					
					do{
                        try{
                            System.out.print("Enter student phone number: ");
                            studentPhoneNumber = sc.nextLine();
                            Integer.parseInt(studentPhoneNumber);
                        }
                        catch(NumberFormatException e){
                            System.out.println("Please enter a valid handphone number.");
                            studentPhoneNumber = "";
                        }
                    }while(studentPhoneNumber.equals(""));
					
					do{
						System.out.print("Enter student email address: ");
						studentEmail = sc.nextLine();
						if(!studentEmail.contains("@e.ntu.edu.sg")){
							System.out.println("Incorrect email format");
							studentEmail = "";
						}
					}while(studentEmail.equals(""));
					
					do{
                        try{
                            System.out.print("Enter student notification mode (E)Email (S)SMS (B)Both: ");
                            studentNotificationMode = sc.next().toUpperCase().charAt(0);
                            if(studentNotificationMode != 'E' && studentNotificationMode != 'S' && studentNotificationMode != 'B')
                                throw new ErrorException("choice");
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please enter a valid choice.");
                            System.out.println("");
                        }
                        catch(ErrorException e){
                        }
                    }while(studentNotificationMode != 'E' && studentNotificationMode != 'S' && studentNotificationMode != 'B');
            
					
					adminController.createStudent(studentId, studentName, studentGender, studentYear, schoolCode,studentNationality, studentProgramme,studentEmail,studentPhoneNumber,studentNotificationMode);
					break;
				case 3 : 
					String schoolCodes,courseCode,courseName;
					adminController.getAllCourseDetails();
					do{
					    System.out.print("Enter school code: ");
					    schoolCodes = sc.nextLine();
                    }while(!adminController.checkExistingSchool(schoolCodes));
					
					do{
						System.out.print("Enter course code: ");
						courseCode = sc.nextLine();
						if(adminController.checkValidCourse(courseCode)){
							System.out.println("Course code is already in the record");
							courseCode = "";
						}
					}while(courseCode.equals(""));
					
					
					do{
						System.out.print("Enter course name: ");
						courseName = sc.nextLine();
						if(courseName.matches(".*\\d+.*")){
							System.out.println("Course name shouldnt have numbers.");
							courseName = "";
						}
					}while(courseName.equals(""));
					
					do{
                        try{
                            System.out.print("Enter course type(L = Lecture Only, T = Lecture and Tutorial, P = Lecture, Tutorial and Practical)");
                            courseType = sc.next().toUpperCase().charAt(0);
                            if(courseType != 'L' && courseType != 'T' && courseType != 'P')
                                throw new ErrorException("choice");
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please enter a valid choice.");
                            System.out.println("");
                        }
                        catch(ErrorException e){
                        }
                    }while(courseType != 'L' && courseType != 'T' && courseType != 'P');
            
					adminController.createCourse(schoolCodes,courseCode,courseName,courseType);
					break;
				case 4 : 
				    do{
	                    adminController.getAllCourseDetails();
	                    System.out.print("Enter course code to change: ");
	                    courseCode = sc.nextLine();
	                    
	                    int option = 0;
	                    if(adminController.checkValidCourse(courseCode)){
	                        do{
	                            System.out.println("(0) Course Code");
	                            System.out.println("(1) School");
	                            System.out.println("(2) Course Name");
	                            System.out.println("(3) Course Type(L = Lecture Only, T = Lecture and Tutorial, P = Lecture, Tutorial and Practical)");
	                            System.out.println("(4) Exit");
	                            try{
		                            System.out.print("Enter option and data to change e.g. (0,CZ0001): ");
		                            
		                            String optionData = sc.nextLine();
	                                option = Integer.parseInt(optionData.split(",")[0]);
	                                
	                                switch(option){
	                                case 0 :
	                                	
	                                }
	                                if(option!=4){
	                                    String data = optionData.split(",")[1];
	                                    
	                                    if(option == 0){
	                                    	adminController.changeCourse(courseCode,option,data);
	                                    	adminController.getAllCourseDetails();
	                                    	courseCode = data;
	                                    }
	                                    
	                                    else if(option == 1){
	                                    	if(adminController.checkExistingSchool(data)){
		                                    	adminController.changeCourse(courseCode,option,data);
		                                    	adminController.getAllCourseDetails();
	                                    	}
	                                    }
	                                    
	                                    else if(option == 2){
	                                    	if(!data.matches(".*\\d+.*")){
	                                    		adminController.changeCourse(courseCode,option,data);
		                                    	adminController.getAllCourseDetails();
	                                    	}
	                                    	else{
	                							System.out.println("Course name shouldnt have numbers.");
	                                    	}
	                                    }
	                                    
	                                    else if(option == 3){
	                                    	try{
	                                            courseType = data.toUpperCase().charAt(0);
	                                            if(courseType != 'L' && courseType != 'T' && courseType != 'P')
	                                                throw new ErrorException("choice");
	                                            else{
	                                            	adminController.changeCourse(courseCode,option,Character.toString(courseType));
			                                    	adminController.getAllCourseDetails();
	                                            }
	                                        }
	                                    	catch(ErrorException e){
	                                        }
	                                    	
	                                    }
	                                }
	                            }
	                            catch(NumberFormatException e){
                                    System.out.println("Please input the correct format");
                                    System.out.println("");
                                }
	                            catch(ArrayIndexOutOfBoundsException e){
	                            	System.out.println("Please input the correct format");
                                    System.out.println("");
	                            }
	                        }while(option!=4);
	                    }
	                    else{
	                        courseCode = "";
	                        System.out.println("Invalid course code entered.");
	                    }
				    }while(courseCode.equals(""));
				    
				    break;
				case 5 :
					adminController.getAllCourseDetails();
					do{
					System.out.print("Enter course code: ");
					courseCode = sc.next();
					courseType = adminController.getCourseType(courseCode);//change to courseCode
					//check courseType
					int courseVacancy = 0;
					String courseIndexName,courseLectureFirstDay,courseLectureSecondDay,courseLectureFirstTime,
					courseLectureSecondTime;
					String courseTutorialDay="",courseTutorialTime="",courseLabDay="",courseLabTime = "";
					
					if(courseType!= ' '){
                        adminController.getAllCourseIndexDetails(courseCode);
						do{
                            try{
                                System.out.print("Enter course index: ");
                                courseIndex = sc.nextInt();
                                sc.nextLine();
                                if(adminController.checkValidCourseIndex(courseIndex)){
                                	courseIndex = 0;
                                	System.out.println("Course index is already in the record");
                                }
                            }
                            catch(InputMismatchException e){
                                System.out.println("Please enter a valid course index");
                                System.out.println("");
                                sc.nextLine();
                            }
                        }while(courseIndex == 0);
						
						System.out.print("Enter course index name: ");
						courseIndexName = sc.nextLine();
						    System.out.print("Enter course first lecture day: ");
						    courseLectureFirstDay = sc.nextLine();

	                    do{
	                        System.out.print("Enter course first lecture time(HH:MM:SS-HH:MM:SS): ");
	                        courseLectureFirstTime = sc.nextLine();
                        }while(!StarsSystem.checkValidTime(courseLectureFirstTime));
	                    
	                    System.out.print("Enter course second lecture day: ");
						courseLectureSecondDay = sc.nextLine();
						
						do{
	                        System.out.print("Enter course second lecture time(HH:MM:SS-HH:MM:SS): ");
	                        courseLectureSecondTime = sc.nextLine();
                        }while(!StarsSystem.checkValidTime(courseLectureSecondTime));
						
						if(courseType == 'P'){
							System.out.print("Enter course lab day: ");
							courseLabDay = sc.nextLine();
							do{
	                            System.out.print("Enter course lab time(HH:MM:SS-HH:MM:SS): ");
	                            courseLabTime = sc.nextLine();
	                        }while(!StarsSystem.checkValidTime(courseLabTime));
						}
						
						if(courseType == 'T' || courseType == 'P'){
							System.out.print("Enter course tutorial day: ");
							courseTutorialDay = sc.nextLine();
                            do{
                                System.out.print("Enter course tutorial time(HH:MM:SS-HH:MM:SS): ");
                                courseTutorialTime = sc.nextLine();
                            }while(!StarsSystem.checkValidTime(courseTutorialTime));
						}
						
						do{
                            try{
                                System.out.print("Enter course vacancy: ");
                                courseVacancy = sc.nextInt();
                            }
                            catch(InputMismatchException e){
                                System.out.println("Please enter a valid course vacancy");
                                sc.nextLine();
                                System.out.println("");
                            }
                        }while(courseVacancy == 0);
						
						adminController.createCourseIndex(courseCode,courseIndex,courseType, courseIndexName,
								courseLectureFirstDay,courseLectureFirstTime,courseLectureSecondDay,courseLectureSecondTime,
								courseTutorialDay,courseTutorialTime,courseLabDay,courseLabTime,0, courseVacancy,0);
					}
					else{
						System.out.println("Invalid course code entered.");
						courseCode = "";
					}
					}while(courseCode.equals(""));
					break;
				case 6 :
				    do{
				        try{
				            adminController.getAllCourseIndexDetails();
				            System.out.print("Enter course index to change: ");
				            courseIndex = sc.nextInt();
				        
				            if(adminController.checkValidCourseIndex(courseIndex)){
				                int option = 0;
				                sc.nextLine();
				                do{
				                    System.out.println("(0) Index");
				                    System.out.println("(1) Index Name");
				                    System.out.println("(2) Day(L)-First");
				                    System.out.println("(3) Time(L)-First");
				                    System.out.println("(4) Day(L)-Second");
				                    System.out.println("(5) Time(L)-Second");
				                    System.out.println("(6) Day(P)");
				                    System.out.println("(7) Time(P)");
				                    System.out.println("(8) Day(T)");
				                    System.out.println("(9) Time(T)");
				                    System.out.println("(10) Exit");
				                    System.out.print("Enter option and data to change e.g. (1,10000): ");
				                    
				                    String optionData = sc.nextLine();
				                    try{
				                        option = Integer.parseInt(optionData.split(",")[0]);
				                    
				                        if(option!=10){
				                            String data = optionData.split(",")[1];
				                
				                            if(option == 0){
				                            	if(data.matches(".*\\d+.*")){
				                            		adminController.changeCourseIndex(courseIndex,option,data);
						                            adminController.getAllCourseIndexDetails();
						                            courseIndex = Integer.parseInt(data);
				                            	}
				                            }
				                            
				                            else if(option == 1 || option == 2 || option == 4 || option == 6 || option == 8){
				                            	adminController.changeCourseIndex(courseIndex,option+1,data);
					                            adminController.getAllCourseIndexDetails();
				                            }
				                            
				                            else if(option == 3 || option == 5 || option == 7 || option == 9){
				                            	if(StarsSystem.checkValidTime(data)){
				                            		adminController.changeCourseIndex(courseIndex,option+1,data);
				                            		adminController.getAllCourseIndexDetails();
				                            	}
				                            }
				                        }
				                    }

	                                catch(NumberFormatException e){
	                                    System.out.println("Please input the correct format");
	                                    System.out.println("");
	                                }
		                            catch(ArrayIndexOutOfBoundsException e){
		                            	System.out.println("Please input the correct format");
	                                    System.out.println("");
		                            }
				                }while(option!=10);
				            }
				            else{
				            	System.out.println("Index number is not in the record");
				            	courseIndex = 0;
				            }
				        }
				        catch(InputMismatchException e){
				            System.out.println("Please enter a valid index number");
				            sc.nextLine();
				            System.out.println("");
				        }
				    }while(courseIndex == 0);
				    break;
				case 7 :
					do{
                        try{
                            System.out.print("Enter index number to be checked: ");
                            courseIndex = sc.nextInt();
                            adminController.checkAvailableSlot(courseIndex);
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please enter a valid index number");
                            sc.nextLine();
                            System.out.println("");
                        }
                    }while(courseIndex==0);
					break;
				case 8 :
				    do{
                        try{
                            System.out.print("Please enter the course index to print: ");
                            courseIndex = sc.nextInt();
                            adminController.printStudentList(true,Integer.toString(courseIndex));
                        }
                        catch(InputMismatchException e){
                            System.out.println("Please enter a valid index number");
                            sc.nextLine();
                            System.out.println("");
                        }
                    }while(courseIndex==0);
					break;
				case 9 :
					System.out.print("Please enter the course code to print: ");
					String courseChoice = sc.next();
					adminController.printStudentList(false,courseChoice);
					break;
				case 10 : 
					System.out.println("Logging Out from Stars Application.");
					break;
				default : 
					break;
			}
		}while(subMenuOption!=10);
		adminController = null;
	}
}
