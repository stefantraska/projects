package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Exam implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private HashMap<Integer, Question> questions;
	private int examId;
	private String title;
	
	/*
	 * This class describes an Exam that stores 
	 * questions.
	 */
	
	public Exam(int examId, String title) {
		this.examId = examId;
		this.title = title;
		questions = new HashMap<>();
	}
	
	public void addQuestion(Question q) {
		questions.put(q.getQuestionNumber(), q);
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getExamId() {
		return examId;
	}
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj.getClass() == this.getClass()) {
			return this.examId == ((Exam)obj).examId;
		}
		return false;
	}
	
	public int hashCode() {
		return examId;
	}
	
	public double totalPoints() {
		double pointTotal = 0.0;
		for(Integer key : getQuestions().keySet()) {
			Question q = getQuestions().get(key);
			pointTotal += q.getPoints();
		}
		return pointTotal;
	}
	
	/* Grades a question and returns an array with the first 
	 * value being the student's exam points and the second value 
	 * being the total exam points.
	 */
	public double[] gradeQuestion(int questionNumber, 
									Question studentQuestion) {
		double studentPoints = 0.0;
		double totalPoints = 0.0;
		Question q = questions.get(questionNumber);
		
		// Question is type TrueFalseQuestion
		if(q instanceof TrueFalseQuestion) {
			
			double points = ((TrueFalseQuestion)q).getPoints();
			
			totalPoints += points;
			
			if(((TrueFalseQuestion)q).getAnswer()
					== ((TrueFalseQuestion)studentQuestion).getAnswer()){
				studentPoints += points;
			}
		// Question is type MultipleChoiceQuestion
		}else if(q instanceof MultipleChoiceQuestion){
			
			String[] studentMCAnswer = 
					((MultipleChoiceQuestion)studentQuestion).getAnswer();
			
			String[] examMCAnswer = 
					((MultipleChoiceQuestion)q).getAnswer();
			
			double questionTotalPoints = 
					((MultipleChoiceQuestion)q).getPoints();
			
			totalPoints += questionTotalPoints;

			boolean correctChoices = true;
			// checks to see all correct choices made
			for(String studentChoice : studentMCAnswer) {
				boolean correctChoiceMade = false;
				for(String examChoice : examMCAnswer ) {
					if(studentChoice.equals(examChoice)) {
						correctChoiceMade = true;
						break;
					}
				}
				if(!correctChoiceMade) {
					correctChoices = false;
					break;
				}
			}
			
			if(correctChoices && studentMCAnswer.length 
					== examMCAnswer.length) {
				studentPoints += questionTotalPoints;
			}
		// Question is type FillInTheBlanksQuestion
		}else {
			String[] studentFillAnswer = 
					((FillInTheBlanksQuestion)studentQuestion).getAnswer();
			
			String[] examFillAnswer = 
					((FillInTheBlanksQuestion)q).getAnswer();
			
			double questionTotalPoints = 
					((FillInTheBlanksQuestion)q).getPoints();
			
			totalPoints += questionTotalPoints;
			
			double correctChoices = 0.0;
			
			// Checks how many correct choices made
			for(String studentChoice : studentFillAnswer) {
				for(String examChoice : examFillAnswer ) {
					if(studentChoice.equals(examChoice)) {
						correctChoices++;
						break;
					}
				}
			}
			// Partial credit
			double studentPointsCorrect = 
					correctChoices / examFillAnswer.length
					* questionTotalPoints;
			
			studentPoints += studentPointsCorrect;
		}
		return new double[] {studentPoints, totalPoints};
	}
	
	public HashMap<Integer, Question> getQuestions() {
		return questions;
	}

}
