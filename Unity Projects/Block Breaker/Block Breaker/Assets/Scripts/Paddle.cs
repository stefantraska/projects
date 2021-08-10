using UnityEngine;
using System.Collections;

public class Paddle : MonoBehaviour {

	public bool autoPlay = false;
	public float ballMinXPos;
	public float ballMaxXPos;
	
	private Ball ball;

	// Use this for initialization
	void Start () {
		ball = GameObject.FindObjectOfType<Ball>();
	}
	
	// Update is called once per frame
	void Update () {
		if(autoPlay == false){
		MoveWithMouse();
		}else{
		AutoPlay();
		}
	}
	
	void MoveWithMouse (){
		Vector3 paddlePos = new Vector3(0f, this.transform.position.y, 0f);	
		float mousePosInBlocks = Input.mousePosition.x / Screen.width * 16;
		paddlePos.x = Mathf.Clamp(mousePosInBlocks, ballMinXPos, ballMaxXPos);
		this.transform.position = paddlePos;
	}
	void AutoPlay(){
		Vector3 paddlePos = new Vector3(0f, this.transform.position.y, 0f);	
		float mousePosInBlocks = Input.mousePosition.x / Screen.width * 16;
		paddlePos.x = Mathf.Clamp(mousePosInBlocks, ballMinXPos, ballMaxXPos);
		this.transform.position = paddlePos;
	}
}
