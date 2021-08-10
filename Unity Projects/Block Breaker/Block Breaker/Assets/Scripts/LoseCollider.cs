using UnityEngine;
using System.Collections;

public class LoseCollider : MonoBehaviour {

	private LevelManager levelManager;
	private Ball ball;
	private Vector3 paddleToBallVector;
	
	void Start(){
		levelManager = GameObject.FindObjectOfType<LevelManager>();
		ball = GameObject.FindObjectOfType<Ball>();
	}
	
	//If ball goes below paddle
	public void OnTriggerEnter2D(Collider2D trigger){
		Lives.onDeathCollision();
		if(Lives.lifeNumber <= 0){
		Lives.resetLives();
		levelManager.LoadLevel("Lose");
		}else{
			ball.ifDeathNoLoss();
		}
	}
}
