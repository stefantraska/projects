using UnityEngine;
using System.Collections;

public class LevelManager : MonoBehaviour {

	public void LoadLevel(string name){
		Debug.Log("Level load requested for " + name);
		Brick.brickCount = 0;
		Application.LoadLevel(name);
	}
	
	public void LoadNextLevel(){
		Brick.brickCount = 0;
		Application.LoadLevel(Application.loadedLevel + 1);
		if(Application.loadedLevel == 0){
			Score.CurrentScoreZero();
			Debug.Log("Score is " + Score.current_score);
		}
		else{
			Score.AddToCurrentScore();
			Score.CompareToHighScore();
			Debug.Log("Score is " + Score.current_score);
		}
	}
	
	public void BrickDestroyed(){
		if(Brick.brickCount <= 0){
			LoadNextLevel();
		}
	}
}
