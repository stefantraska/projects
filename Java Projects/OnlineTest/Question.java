package onlineTest;

import java.io.Serializable;

public class Question implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	 * This class defines a question, extended by the 
	 * types of questions used on the exams
	 */
	
	protected int questionNumber;
	protected String text;
	protected double points;
	
	public Question(int questionNumber, String text, double points) {
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
	}
	
	public double getPoints() {
		return points;
	}
	
	public String getText() {
		return text;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}
	
}
