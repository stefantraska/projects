using UnityEngine;
using System.Collections;

public class Ball : MonoBehaviour {

	private Paddle paddle;
	private Vector3 paddleToBallVector;
	public bool hasStarted = false;
	public Vector3 ballPos;
	float x, y, z;

	// Use this for initialization
	void Start () {
		paddle = GameObject.FindObjectOfType<Paddle>();
		paddleToBallVector = this.transform.position - paddle.transform.position;
	}
	
	// Update is called once per frame
	void Update () {
		
		if(!hasStarted){
			//Locks ball relative to paddle
			this.transform.position = paddle.transform.position + paddleToBallVector;
			//Once mouse is clicked, ball is released into the air and the game starts
			if(Input.GetMouseButtonDown(0)){
				hasStarted = true;
				this.GetComponent<Rigidbody2D>().velocity = new Vector2(0f, 11f);
			}
		}
		//Updates psoition of where ball would be if death occurs
		x = paddle.transform.position.x;
		y = 0.95f;
		z = paddle.transform.position.z;
	}
	
	//Sets ball position if death occurs
	public void ifDeathNoLoss(){
		ballPos.Set(x, y, z);
		this.transform.position = ballPos;
		hasStarted = false;
	}
	
	void OnCollisionEnter2D(Collision2D col){
		Vector2 tweak = new Vector2((Random.Range (0.0f, 0.2f)), Random.Range(0.0f, 0.2f));
		if(hasStarted){
			GetComponent<AudioSource>().Play();
			GetComponent<Rigidbody2D>().velocity += tweak;
		}
	}
}
