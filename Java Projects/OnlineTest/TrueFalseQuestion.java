package onlineTest;

import java.io.Serializable;

public class TrueFalseQuestion extends Question implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean answer;
	
	public TrueFalseQuestion(int questionNumber, String text, 
								double points, boolean answer) {
		super(questionNumber, text, points);
		this.answer = answer;
	}
	
	public String getAnswer() {
		if(answer) {
			return "True";
		}
		return "False";
	}
}
