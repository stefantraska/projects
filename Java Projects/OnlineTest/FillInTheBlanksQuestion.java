package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class FillInTheBlanksQuestion extends Question implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String[] answer;
	
	// Sorts answers then stores it
	public FillInTheBlanksQuestion(int questionNumber, String text, 
								double points, String[] answer) {
		super(questionNumber, text, points);
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		};
		ArrayList<String> answers = new ArrayList<>();
		for(int i = 0; i < answer.length; i++) {
			answers.add(answer[i]);
		}
		
		answers.sort(comparator);
		
		String[] newAnswers = new String[answer.length];
		
		for(int i = 0; i < answer.length; i++) {
			newAnswers[i] = answers.get(i);
		}
		this.answer = newAnswers;
	}
	
	public String[] getAnswer() {
		return answer;
	}
}
