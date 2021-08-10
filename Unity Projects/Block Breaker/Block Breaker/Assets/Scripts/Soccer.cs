using UnityEngine;
using System.Collections;

public class Soccer : MonoBehaviour {

	private string spriteName = "football";
	private SpriteRenderer spriteR;
	private Sprite sprites;
	public GameObject obj;
	
	
	public void ButtonClick(){
		spriteR = obj.GetComponent<SpriteRenderer>();
		sprites = Resources.Load<Sprite>(spriteName);
		spriteR.sprite = sprites;
	}
}
