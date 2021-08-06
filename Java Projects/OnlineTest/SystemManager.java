package onlineTest;

import java.io.*;
import java.util.*;


public class SystemManager implements Manager, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private HashMap<Integer, Exam> examAnswers;
	private HashMap<String, Student> studentList;
	private String[] letterGrades;
	private double[] gradeCutoffs;
	
	public SystemManager() {
		examAnswers = new HashMap<>();
		studentList = new HashMap<>();
	}
	
	/**
	 * Adds the specified exam to the database.
	 * @param examId
	 * @param title
	 * @return false is exam already exists.
	 */
	public boolean addExam(int examId, String title) {
		Exam newExam = new Exam(examId, title);
		if(examAnswers.containsKey(examId)) {
			return false;
		}
		examAnswers.put(examId, newExam);
		return true;
	}
	
	
	/**
	 * Adds a true and false question to the specified exam.  If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addTrueFalseQuestion(int examId, int questionNumber, 
								String text, double points, boolean answer) {
		if(examAnswers.get(examId) != null) {
			examAnswers.get(examId).addQuestion(
					new TrueFalseQuestion(questionNumber, text, 
							points, answer));
		}
	}
	
	
	/**
	 * Adds a multiple choice question to the specified exam.   If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addMultipleChoiceQuestion(int examId, int questionNumber,
		    String text, double points, String[] answer) {
		if(examAnswers.get(examId) != null) {
			examAnswers.get(examId).addQuestion(
					new MultipleChoiceQuestion(questionNumber, text, 
							points, answer));
		}
	}
	
	
	/**
	 * Adds a fill-in-the-blanks question to the specified exam.  If the question
	 * already exits it is overwritten.  Each correct response is worth
	 * points/entries in the answer.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
	public void addFillInTheBlanksQuestion(int examId, int questionNumber,
		    String text, double points, String[] answer) {
		if(examAnswers.get(examId) != null) {
			examAnswers.get(examId).addQuestion(
					new FillInTheBlanksQuestion(questionNumber, text, 
							points, answer));
		}
	}
	
	
	/**
	 * Returns a string with the following information per question:<br />
	 * "Question Text: " followed by the question's text<br />
	 * "Points: " followed by the points for the question<br />
	 * "Correct Answer: " followed by the correct answer. <br />
	 * The format for the correct answer will be: <br /> 
	 *    a. True or false question: "True" or "False"<br />
	 *    b. Multiple choice question: [ ] enclosing the answer (each entry separated by commas) and in
	 *       sorted order. <br />
	 *    c. Fill in the blanks question: [ ] enclosing the answer (each entry separated by commas) and
	 *       in sorted order. <br />
	 * @param examId
	 * @return "Exam not found" if exam not found, otherwise the key
	 */
	public String getKey(int examId) {
		String result = "";
		
		// Loops through all questions, prints the correct answer
		
		for(Integer key : examAnswers.get(examId).getQuestions().keySet()) {
			Question q = examAnswers.get(examId).getQuestions().get(key);
			result += "Question Text : " + q.getText() 
					+ "\nPoints: " + q.getPoints() + "\nCorrect Answer: ";
					if(q instanceof TrueFalseQuestion) {
						result += ((TrueFalseQuestion)q).getAnswer();
					}else if(q instanceof MultipleChoiceQuestion) {
						result += Arrays.toString(
								((MultipleChoiceQuestion)q).getAnswer());
					}else {
						result += Arrays.toString(
								((FillInTheBlanksQuestion)q).getAnswer());
					}
			result+= "\n";
		}
		
		return result;
	}
	
	
	/**
	 * Adds the specified student to the database.  Names are specified in the format
	 * LastName,FirstName
	 * @param name
	 * @return false if student already exists.
	 */
	public boolean addStudent(String name) {
		Student newStudent = new Student(name);
		if(studentList.containsKey(name)) {
			return false;
		}
		studentList.put(name, newStudent);
		return true;
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerTrueFalseQuestion(String studentName, int examId, 
									int questionNumber, boolean answer) {
		if(studentList.get(studentName) != null) {
			studentList.get(studentName).answerTrueFalseQuestion(
									examId, questionNumber, answer);
		}
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerMultipleChoiceQuestion(String studentName, int examId, 
									int questionNumber, String[] answer) {
		if(studentList.get(studentName) != null) {
			studentList.get(studentName).answerMultipleChoiceQuestion(
									examId, questionNumber, answer);
		}
	}
	
	
	/**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
	public void answerFillInTheBlanksQuestion(String studentName, int examId, 
									int questionNumber, String[] answer) {
		if(studentList.get(studentName) != null) {
			studentList.get(studentName).answerFillInTheBlanksQuestion(
									examId, questionNumber, answer);
		}
	}
	

	/**
	 * Returns the score the student got for the specified exam.
	 * @param studentName
	 * @param examId
	 * @return score
	 */
	public double getExamScore(String studentName, int examId) {
		double studentPoints = 0.0;
		if(examAnswers.containsKey(examId) 
				&& studentList.get(studentName) != null
				&& studentList.get(studentName).getExam(examId) != null) {

			// Loops through all questions, adds up 
			// student's points on the exam
			
			for(Integer key : examAnswers.get(examId).getQuestions().keySet()){
				double [] points = examAnswers.get(examId).gradeQuestion(key, 
						studentList.get(studentName).getExam(examId)
						.getQuestions().get(key));
				
					studentPoints += points[0];
			}
		}
		return studentPoints;
	}
	
	
	/**
	 * Generates a grading report for the specified exam.  The report will include
	 * the following information for each exam question:<br />
	 * "Question #" {questionNumber} {questionScore} " points out of " {totalQuestionPoints}<br />
	 * The report will end with the following information:<br />
	 * "Final Score: " {score} " out of " {totalExamPoints};  
	 * @param studentName
	 * @param examId
	 * @return report
	 */
	public String getGradingReport(String studentName, int examId) {
		String result = "";
		double studentPoints = 0.0;
		double totalPoints = 0.0;
		if(examAnswers.containsKey(examId) 
				&& studentList.get(studentName) != null
				&& studentList.get(studentName).getExam(examId) != null) {
		
			// Loops through all questions on exam, prints appropriate 
			// result with student points and total points
			for(Integer key : examAnswers.get(examId).getQuestions().keySet()){
				result += "Question #" + key + " ";
				double [] points = examAnswers.get(examId).gradeQuestion(key, 
						studentList.get(studentName).getExam(examId)
						.getQuestions().get(key));
					
					studentPoints += points[0];
					totalPoints += points[1];
					result += points[0] + " points out of "
								+ points[1] + "\n";
			}
		}
		return result + "Final Score: " + studentPoints 
				+ " out of " + totalPoints;
	}
	
	
	/**
	 * Sets the cutoffs for letter grades.  For example, a typical curve we will have
	 * new String[]{"A","B","C","D","F"}, new double[] {90,80,70,60,0}.  Anyone with a 90 or
	 * above gets an A, anyone with an 80 or above gets a B, etc.  Notice we can have different
	 * letter grades and cutoffs (not just the typical curve).
	 * @param letterGrades
	 * @param cutoffs
	 */
	public void setLetterGradesCutoffs(String[] letterGrades, 
										double[] cutoffs) {
		this.letterGrades = letterGrades;
		gradeCutoffs = cutoffs;
	}
	
	
	/**
	 * Computes a numeric grade (value between 0 and a 100) for the student taking
	 * into consideration all the exams.  All exams have the same weight. 
	 * @param studentName
	 * @return grade
	 */
	public double getCourseNumericGrade(String studentName) {
		double totalPoints = 0.0;
		double studentPoints = 0.0;
		double total = 0.0;
		double totalNumberOfExams = 0;
		
		// Loops through all exams for given student, 
		// uses weighted average to compute grade
		Student s = studentList.get(studentName);
		for(Integer key : examAnswers.keySet()) {
			Exam e = examAnswers.get(key);
			totalNumberOfExams++;
			totalPoints = examAnswers.get(e.getExamId()).totalPoints();
			studentPoints = getExamScore(s.getName(), e.getExamId());
			
			total += studentPoints / totalPoints;
		}
		if(totalPoints == 0.0) {
			return 0.0;
		}
		return total/totalNumberOfExams * 100;
	}
	
	
	/** 
	 * Computes a letter grade based on cutoffs provided.  It is assumed the cutoffs have
	 * been set before the method is called.
	 * @param studentName
	 * @return letter grade
	 */
	public String getCourseLetterGrade(String studentName) {
		double studentScore = getCourseNumericGrade(studentName);
		
		for(int i = 0; i < gradeCutoffs.length - 1; i++) {
			if(gradeCutoffs[i] <= studentScore) {
				return letterGrades[i];
			}
		}
		return letterGrades[gradeCutoffs.length - 1];
	}
	
	
	/**
	 * Returns a listing with the grades for each student.  For each student the report will
	 * include the following information: <br />
	 * {studentName} {courseNumericGrade} {courseLetterGrade}<br />
	 * The names will appear in sorted order.
	 * @return grades
	 */
	public String getCourseGrades() {
		String result = "";
		ArrayList<Student> studentArrayList = new ArrayList<>();
		Comparator<Student> comparator = new Comparator<Student>() {
			public int compare(Student a, Student b) {
				return a.getName().compareTo(b.getName());
			}
		};
		for(String key : studentList.keySet()) {
			Student s = studentList.get(key);
			studentArrayList.add(s);
		}
		studentArrayList.sort(comparator);
		for(Student s : studentArrayList) {
			result += s.getName() + " " + getCourseNumericGrade(s.getName()) 
			+ " " + getCourseLetterGrade(s.getName()) + "\n";
		}
		return result;
	}
	
	
	/**
	 * Returns the maximum score (among all the students) for the specified exam.
	 * @param examId
	 * @return maximum
	 */
	public double getMaxScore(int examId) {
		double maxScore = Double.MIN_VALUE;
		for(String key : studentList.keySet()) {
			Student s = studentList.get(key);
			double studentScore = getExamScore(s.getName(), examId);
			if(studentScore > maxScore) {
				maxScore = studentScore;
			}
		}
		return maxScore;
	}
	
	
	/**
	 * Returns the minimum score (among all the students) for the specified exam.
	 * @param examId
	 * @return minimum
	 */
	public double getMinScore(int examId) {
		double minScore = Double.MAX_VALUE;
		for(String key : studentList.keySet()) {
			Student s = studentList.get(key);
			double studentScore = getExamScore(s.getName(), examId);
			if(studentScore < minScore) {
				minScore = studentScore;
			}
		}
		return minScore;
	}
	
	
	/**
	 * Returns the average score for the specified exam.
	 * @param examId
	 * @return average
	 */
	public double getAverageScore(int examId) {
		double totalPoints = 0.0;
		double studentTotal = 0.0;
		
		for(String key : studentList.keySet()) {
			Student s = studentList.get(key);
			studentTotal++;
			totalPoints += getExamScore(s.getName(), examId);
		}
		if(totalPoints == 0.0) {
			return 0.0;
		}
		return totalPoints / studentTotal;
	}
	
	
	/**
	 * It will serialize the Manager object and store it in the
	 * specified file.
	 */
	public void saveManager(Manager manager, String fileName) {
		try {
			File file = new File(fileName);
			ObjectOutputStream output 
				= new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(manager);
			output.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * It will return a Manager object based on the serialized data
	 * found in the specified file.
	 */
	public Manager restoreManager(String fileName) {
		Manager m = new SystemManager();
		try {
			File file = new File(fileName);
			ObjectInputStream input 
					= new ObjectInputStream(new FileInputStream(file));
			m = (Manager) input.readObject();
			input.close();
			return m;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new SystemManager();
	}
	
}
