using UnityEngine;
using System.Collections;

public class Volleyball : MonoBehaviour {

	private string spriteName = "volleyball";
	private SpriteRenderer spriteR;
	private Sprite sprites;
	public GameObject obj;
	
	
	public void ButtonClick(){
		spriteR = obj.GetComponent<SpriteRenderer>();
		sprites = Resources.Load<Sprite>(spriteName);
		spriteR.sprite = sprites;
	}
}
