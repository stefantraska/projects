package cmdLineInterpreter;

/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */

import java.util.*;

public class Interpreter {

	public static void main(String[] args) {
		boolean keepRunning = true;
		Scanner sc = new Scanner(System.in);
		onlineTest.Manager manager = new onlineTest.SystemManager();
		
		// Prints commands
		System.out.println("Welcome to the Online Test program!\n"
				+ "Commands:\n"
				+ "To add a student: addStudent {studentName}\n"
				+ "To add an exam: addExam {examId} {title}\n"
				+ "To add a question: addQuestion {examId} {type} "
				+ "{questionNumber} {text} ^& {points} {answer}\n"
				+ "^& indictaes the question text is completed.\n"
				+ "\tQuestion {type}:\n"
				+ "\t\tTrue or False, denoted TF\n"
				+ "\t\tMultiple Choice, denoted MC\n"
				+ "\t\tFill In The Blanks, denoted FITB\n"
				+ "\tPoints {points}: Use a decimal\n"
				+ "\tAnswer {answer}:\n"
				+ "\t\tTrue or False, type true or false\n"
				+ "\t\tMultiple Choice, use the format [ {answer1} "
				+ "{answer2} ... ]\n"
				+ "\t\tFill In The Blanks, use the format [ {answer1} "
				+ "{answer2} ... ]\n"
				+ "To answer a question: answerQuestion {studentName} "
				+ "{examId} {type} {questionNumber} {answer}\n"
				+ "\tQuestion {type}:\n"
				+ "\t\tTrue or False, denoted TF\n"
				+ "\t\tMultiple Choice, denoted MC\n"
				+ "\t\tFill In The Blanks, denoted FITB\n"
				+ "\tPoints {points}: Use a decimal\n"
				+ "\tAnswer {answer}:\n"
				+ "\t\tTrue or False, type true or false\n"
				+ "\t\tMultiple Choice, use the format [ {answer1} "
				+ "{answer2} ... ]\n"
				+ "\t\tFill In The Blanks, use the format [ {answer1} "
				+ "{answer2} ... ]\n"
				+ "To get an exam score: getExamScore {student} {examId}\n"
				+ "To finish using this program, type: finish\n"
				+ "To see this again, type: help\n"
				);
		
		while(keepRunning) {
			try {
				String input = sc.nextLine();
				Scanner inputScanner = new Scanner(input);
				String command = inputScanner.next();
				
				// Adds a student with a given name
				if(command.equals("addStudent")) {
					String name = inputScanner.next();
					manager.addStudent(name);
					System.out.println(name + " added.");
					
				// Adds an exam with a given examId, title
				}else if(command.equals("addExam")) {
					int examId = inputScanner.nextInt();
					String title = inputScanner.next();
					manager.addExam(examId, title);
					System.out.println(title + " added.");
					
				// Adds a question, given examId, question type, 
				// questionNumber, text, and answer
				}else if(command.equals("addQuestion")) {
					int examId = inputScanner.nextInt();
					String type = inputScanner.next();
					int questionNumber = inputScanner.nextInt();
					String text = "";
					while(inputScanner.hasNext()) {
						String s = inputScanner.next();
						if(s.equals("^&")) {
							break;
						}else {
							text += s + " ";
						}
					}
					double points = inputScanner.nextDouble();
					if(type.equals("TF")) {
						boolean answer = inputScanner.nextBoolean();
						manager.addTrueFalseQuestion(examId, questionNumber,
								text, points, answer);
					}else if(type.equals("MC")) {
						ArrayList<String> answer = new ArrayList<>();
						inputScanner.next();
						while(inputScanner.hasNext()) {
							String s = inputScanner.next();
							if(!s.equals("]")) {
								answer.add(s);
							}
						}
						String[] answerArray = new String[answer.size()];
						for(int i = 0; i < answer.size(); i++) {
							answerArray[i] = answer.get(i);
						}
						manager.addMultipleChoiceQuestion(examId, questionNumber,
								text, points, answerArray);
					}else if(type.equals("FITB")) {
						ArrayList<String> answer = new ArrayList<>();
						inputScanner.next();
						while(inputScanner.hasNext()) {
							String s = inputScanner.next();
							if(!s.equals("]")) {
								answer.add(s);
							}
						}
						String[] answerArray = new String[answer.size()];
						for(int i = 0; i < answer.size(); i++) {
							answerArray[i] = answer.get(i);
						}
						manager.addFillInTheBlanksQuestion(examId, questionNumber,
								text, points, answerArray);
					}
					System.out.println("Question " + questionNumber + " added to examID: " + examId);
				}
				
				// Answers a question for a given student
				else if(command.equals("answerQuestion")) {
					String studentName = inputScanner.next();
					int examId = inputScanner.nextInt();
					String type = inputScanner.next();
					int questionNumber = inputScanner.nextInt();
					if(type.equals("TF")) {
						boolean answer = inputScanner.nextBoolean();
						manager.answerTrueFalseQuestion(studentName, examId, 
								questionNumber, answer);
					}else if(type.equals("MC")) {
						ArrayList<String> answer = new ArrayList<>();
						inputScanner.next();
						while(inputScanner.hasNext()) {
							String s = inputScanner.next();
							if(!s.equals("]")) {
								answer.add(s);
							}
						}
						String[] answerArray = new String[answer.size()];
						for(int i = 0; i < answer.size(); i++) {
							answerArray[i] = answer.get(i);
						}
						manager.answerMultipleChoiceQuestion(studentName, 
								examId, questionNumber, answerArray);
					}else if(type.equals("FITB")) {
						ArrayList<String> answer = new ArrayList<>();
						inputScanner.next();
						while(inputScanner.hasNext()) {
							String s = inputScanner.next();
							if(!s.equals("]")) {
								answer.add(s);
							}
						}
						String[] answerArray = new String[answer.size()];
						for(int i = 0; i < answer.size(); i++) {
							answerArray[i] = answer.get(i);
						}
						manager.answerFillInTheBlanksQuestion(studentName, 
								examId, questionNumber, answerArray);
					}
					System.out.println("Question " + questionNumber + " answered for " + studentName);
					
				// Prints an exam score for a given student
				}else if(command.equals("getExamScore")) {
					String studentName = inputScanner.next();
					int examId = inputScanner.nextInt();
					System.out.println(studentName + " Score for examId " + examId + ": " + manager.getExamScore(studentName, examId));
				
				// Ends the program
				}else if(command.equals("finish")) {
					keepRunning = false;
				
				// Prints commands
				}else if(command.equals("help")) {
					System.out.println("Welcome to the Online Test program!\n"
							+ "Commands:\n"
							+ "To add a student: addStudent {studentName}\n"
							+ "To add an exam: addExam {examId} {title}\n"
							+ "To add a question: addQuestion {examId} {type} "
							+ "{questionNumber} {text} ^& {points} {answer}\n"
							+ "^& indictaes the question text is completed.\n"
							+ "\tQuestion {type}:\n"
							+ "\t\tTrue or False, denoted TF\n"
							+ "\t\tMultiple Choice, denoted MC\n"
							+ "\t\tFill In The Blanks, denoted FITB\n"
							+ "\tPoints {points}: Use a decimal\n"
							+ "\tAnswer {answer}:\n"
							+ "\t\tTrue or False, type true or false\n"
							+ "\t\tMultiple Choice, use the format [ {answer1} "
							+ "{answer2} ... ]\n"
							+ "\t\tFill In The Blanks, use the format [ {answer1} "
							+ "{answer2} ... ]\n"
							+ "To answer a question: answerQuestion {studentName} "
							+ "{examId} {type} {questionNumber} {answer}\n"
							+ "\tQuestion {type}:\n"
							+ "\t\tTrue or False, denoted TF\n"
							+ "\t\tMultiple Choice, denoted MC\n"
							+ "\t\tFill In The Blanks, denoted FITB\n"
							+ "\tPoints {points}: Use a decimal\n"
							+ "\tAnswer {answer}:\n"
							+ "\t\tTrue or False, type true or false\n"
							+ "\t\tMultiple Choice, use the format [ {answer1} "
							+ "{answer2} ... ]\n"
							+ "\t\tFill In The Blanks, use the format [ {answer1} "
							+ "{answer2} ... ]\n"
							+ "To get an exam score: getExamScore {student} {examId}\n"
							+ "To finish using this program, type: finish\n"
							+ "To see this again, type: help\n"
							);
				}
				inputScanner.close();
			}catch(Exception e) {
				System.out.println("Invalid command.");
			}
		}
		
		sc.close();
	}
}
