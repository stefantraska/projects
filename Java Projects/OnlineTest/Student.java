package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private HashMap<Integer, Exam> examAttempts;
	
	/*
	 * This class describes a Student that can take 
	 * an exam and answer questions.
	 * */
	public Student(String name) {
		this.name = name;
		examAttempts = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj.getClass() == this.getClass()) {
			return this.name.equals(((Student)obj).name);
		}
		return false;
	}
	
	// Attempts a new Exam based
	public void attemptNewExam(int examId, String title) {
		Exam newExam = new Exam(examId, title);
		if(!examAttempts.containsKey(examId)) {
			examAttempts.put(examId, newExam);
		}
	}
	
	
	// Answers a true or false question
	public void answerTrueFalseQuestion(int examId,
									int questionNumber, boolean answer) {
		if(!examAttempts.containsKey(examId)) {
			attemptNewExam(examId, "");
		}
		examAttempts.get(examId).addQuestion(
				new TrueFalseQuestion(questionNumber, "", 
										0.0, answer));
	}
	
	// Answers a multiple choice question
	public void answerMultipleChoiceQuestion(int examId, 
									int questionNumber, String[] answer) {
		if(!examAttempts.containsKey(examId)) {
			attemptNewExam(examId, "");
		}
		examAttempts.get(examId).addQuestion(
			new MultipleChoiceQuestion(questionNumber, "", 0.0, answer));
	}
	
	// Answers a fill in the blanks question
	public void answerFillInTheBlanksQuestion(int examId, 
									int questionNumber, String[] answer) {
		if(!examAttempts.containsKey(examId)) {
			attemptNewExam(examId, "");
		}
		examAttempts.get(examId).addQuestion(
			new FillInTheBlanksQuestion(questionNumber, "", 0.0, answer));
	}
	
	public Exam getExam(int examId) {
		if(examAttempts.containsKey(examId)) {
			return examAttempts.get(examId);
		}
		return null;
	}
	
	public HashMap<Integer, Exam> getAllExams(){
		return examAttempts;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
}
