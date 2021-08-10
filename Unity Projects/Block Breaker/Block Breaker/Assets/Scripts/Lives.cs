using UnityEngine;
using System.Collections;

public class Lives : MonoBehaviour {


	public static int lifeNumber = 3;
	public Sprite[] hearts;
	
	static Lives instance = null;
	
	// Don't reset lives if move onto next level
	void Awake(){
		if(instance != null){
			Destroy (gameObject);
		}else{
			instance = this;
			GameObject.DontDestroyOnLoad(gameObject);
		}
	}
	
	//Shows sprites for # of lives
	void Update(){
		if(lifeNumber == 3){
			this.GetComponent<SpriteRenderer>().sprite = hearts[0];
		}
		if(lifeNumber == 2){
			this.GetComponent<SpriteRenderer>().sprite = hearts[1];
		}
		if(lifeNumber == 1){
			this.GetComponent<SpriteRenderer>().sprite = hearts[2];
		}
		if(lifeNumber == 0){
			this.GetComponent<SpriteRenderer>().sprite = hearts[3];
		}
	}

	public static void onDeathCollision(){
		lifeNumber--;
	}
	
	public static void resetLives(){
		lifeNumber = 3;
	}
}
