using UnityEngine;
using System.Collections;

public class Tennis : MonoBehaviour {

	private string spriteName = "tennis";
	private SpriteRenderer spriteR;
	private Sprite sprites;
	public GameObject obj;
	
	
	public void ButtonClick(){
		spriteR = obj.GetComponent<SpriteRenderer>();
		sprites = Resources.Load<Sprite>(spriteName);
		spriteR.sprite = sprites;
	}
	
}
