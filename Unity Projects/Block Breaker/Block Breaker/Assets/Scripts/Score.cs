using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class Score : MonoBehaviour {

	public static int current_score;
	private static int high_score = 0;
	public Text score_text;
	private int zero = 0;

	// Use this for initialization
	void Start () {
		score_text.text = zero.ToString();
	}
	
	// Update is called once per frame
	void Update () {
		score_text.text = high_score.ToString();
	}
	
	public static void AddToCurrentScore(){
		current_score++;
	}
	
	public static void CurrentScoreZero(){
		current_score = 0;
	}
	
	public static void CompareToHighScore(){
		if(high_score < current_score){
			high_score = current_score;
		}
	}
	
	
}
